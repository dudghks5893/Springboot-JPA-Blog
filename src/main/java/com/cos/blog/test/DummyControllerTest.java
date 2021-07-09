package com.cos.blog.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {
	
	@Autowired // 의존성 주입(DI)
	private UserRepository userRepository;
	
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
