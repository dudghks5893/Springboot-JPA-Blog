package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

// html 파일이 아니라 data를 리턴해주는 controller = RestController
@RestController
public class DummyControllerTest {
	
	@Autowired // 의존성 주입(DI)
	private UserRepository userRepository;
	
	// save함수는 id를 전달하지 않으면 insert를 해주고
	// save함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
	// save함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 함.
	// email, password
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) { // DB에 없는 Exception (기억안나면 그냥 Exception으로 해도 됨 모든 Exception의 부모관계 이기 때문에)
			return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
		}
		
		return "삭제되었습니다. id"+id;
	}
	
	@Transactional // 함수 종료시에 자동 commit 이 됨.
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) { // json 데이터를 요청 => Java Object(MessageConverter의 Jackson라이브러리가 변환해서 받아줌.)
		System.out.println("id :"+id);
		System.out.println("password :"+requestUser.getPassword());
		System.out.println("email :"+requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		//userRepository.save(user);
		
		// 더티 체킹
		return user;
	}
	
	// http://localhost:8000/blog/dummy/user
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	// 한페이지당 2건의 데이터를 리턴받아 볼 예정
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
		Page<User> pagingUser = userRepository.findAll(pageable);
		List<User> users = pagingUser.getContent();
		return users;
	}
	
	// {id}주소로 파라메터를 전달 받을 수 있음.
	// http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// user/4를 찾으을때 데이터베이스에서 못찾아오게 되면 user가 null이 될 것 이다
		// 그럼 return null 이 리턴이 되니 문제가 있다.
		// Optional로 User객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return 한다
		
//    람다식
//		User user = userRepository.findById(id).orElseThrow(()-> {
//			return new IllegalArgumentException("해당 유저는 없습니다. id :"+id);
//		});
		
		// Supplier는 추상클레스라 new를 못한다. 그래서 익명 객체 사용.
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id :"+id);
			}
		});
		// 요청 : 웹브라우저
		// user 객체 = 자바 오브젝트
		// 변환 (웹브라우저가 이해할 수 있는 데이터) -> json (Gson 라이브러리)
		// 스프링부트 = MessageConverter라는 애가 응답시에 자동 작동
		// 만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서
		// user 오브젝트를 json으로 변환해서 브라우저에게 던져줍니다.
		return user;
	}
	
	
	
	// http://localhost:8000/blog/dummy/join (요청)
	// http:의 body에 username, password, email 데이터를 가지고 (요청)
	@PostMapping("/dummy/join")
	public String join(User user) {// key=value (약속된 규칙)
		System.out.println("getId :"+user.getId());
		System.out.println("getUsername :"+user.getUsername());
		System.out.println("getPassword :"+user.getPassword());
		System.out.println("getEmail :"+user.getEmail());
		System.out.println("getRole :"+user.getRole());
		System.out.println("getCreateDate :"+user.getCreateDate());
		
		user.setRole(RoleType.USER); // 설정한 enum을 보낸다.
		userRepository.save(user); // save로 insert와update 다 처리 한다
		return "회원가입이 완료되었습니다.";
	}
}
