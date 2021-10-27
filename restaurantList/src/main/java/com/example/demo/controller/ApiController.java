package com.example.demo.controller;

import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.vo.UserVO;

@RestController
public class ApiController {
	@PostMapping("/apitest")
	public ResponseEntity apiTest(@Valid @RequestBody UserVO user, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			StringBuilder sb = new StringBuilder();
			
			bindingResult.getAllErrors().forEach(error -> {
				//FieldError field = (FieldError) error;
				String message = error.getDefaultMessage();
				
				//System.out.println("field : " + field);
				System.out.println("message : " + message);
				
				//sb.append("field : " + field);
				sb.append("message : " + message);
			});
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
		}
		
		return ResponseEntity.ok(user);
	}
}
