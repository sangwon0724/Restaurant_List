package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@RestController를 붙이게 되면 해당 컨트롤러는 view 페이지를 반환하는게 아닌 응답본문 객체를 반환하는 것이기 때문에 Ajax같은 기능에 대한 메소드들이 모여있는 클래스에서 사용하는 것이 좋다.

@Controller
public class HomeController {
	@GetMapping(path="/")
	public String home(Model model) {
		System.out.println("메인 입장");
		model.addAttribute("test","test");
		
		return "index";
	}
}
