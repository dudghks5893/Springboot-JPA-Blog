package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	// 매개변수에 @AuthenticationPrincipal PrincipalDetail principal 컨트롤러에서 시큐리티 세션 받는 방법
	@GetMapping({"","/"})
	public String index(Model model) {
		model.addAttribute("boards", boardService.글목록());
		return "index"; // viewResolver 작동!
	}
	
	// USER 권한이 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
	
}
