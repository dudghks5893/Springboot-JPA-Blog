package com.cos.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice // 오류가 나면 해당 클레스로 온다
@RestController
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value=Exception.class) // 오류가나면 해당 함수를 보여준다.
	public String handleArgumentException(Exception e) {
		return "<h1>"+e.getMessage()+"</h1>";
	}
}
