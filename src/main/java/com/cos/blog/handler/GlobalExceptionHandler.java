package com.cos.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;

@ControllerAdvice // 오류가 나면 해당 클레스로 온다
@RestController
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value=Exception.class) // 오류가나면 해당 함수를 보여준다.
	public ResponseDto<String> handleArgumentException(Exception e) {
		return  new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
	}
}
