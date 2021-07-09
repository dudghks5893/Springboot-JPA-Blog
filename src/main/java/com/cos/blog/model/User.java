package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴
//ORM -> Java(다른언어) Object -> 테이블로 매핑해주는 기술
@Entity // User 클래스가 MySQL에 테이블이 생성이 된다. 이게 클레스에 가까이 있는 것이 좋다
//@DynamicInsert = insert시에 null인 필드를 제외 시켜 준다.
public class User {
	
	@Id // Primary key (기본키)
	@GeneratedValue(strategy = GenerationType.IDENTITY)// 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; // 시퀀스, auto_increment
	
	@Column(nullable = false, length = 30)// null을 허용하지 않고, 문자 길이를 정한다.
	private String username; // 아이디
	
	@Column(nullable = false, length = 100) // 123456 => 해쉬(비밀번호 암호화)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	//@ColumnDefault("'user'")
	// DB는 RoleType이라는게 없다.
	@Enumerated(EnumType.STRING)
	private RoleType role; // Enum을 쓰는게 좋다. 왜냐하면 ADMIN, USER, 등 오타없이 도메인을 줄 수 있다. // ex)성별 = 남, 녀  초등학교 =1~6 등 
	
	@CreationTimestamp // 시간이 자동 입력
	private Timestamp createDate;
}
