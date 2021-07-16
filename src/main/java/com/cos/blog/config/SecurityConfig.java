package com.cos.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// 빈 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것

@Configuration // 빈등록 (IoC관리)
@EnableWebSecurity // 시큐리티 필터가 등록이 된다 = 스프링 시큐리티가 활성화가 되어 있는데 어떤 설정을 해당 파일에서 하겠다.
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean // IoC가 된다 (스프링이 관리 = 필요할때마다 가지고 와서 쓰면 된다)
	public BCryptPasswordEncoder encodePWD() { // 시큐리티가 가지고있는 함수
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() // csrf 토큰 비활성화 (테스트시 걸어두는 게 좋음)
			.authorizeRequests() // 요청이 들어오면
				.antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**") // auth,js,css,image로 들어오면
				.permitAll() // 누구나 접근 가능
				.anyRequest() // 이게 아닌 다른 모든 요청은
				.authenticated() // 인증이 돼야 한다.
			.and()
				.formLogin()
				.loginPage("/auth/loginForm"); // 내가 만든 로그인 페이지를 보여 준다. 
	}
}