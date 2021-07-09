package com.cos.blog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



//@Getter //게터
//@Setter //세터
@Data //게터&세터

//@RequiredArgsConstructor //final 생성자
//@AllArgsConstructor //모든 생성자
@NoArgsConstructor //빈생성자
public class Member {
	private  int id;
	private  String username;
	private  String password;
	private  String email;
	
	@Builder
	public Member(int id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	

	
}
