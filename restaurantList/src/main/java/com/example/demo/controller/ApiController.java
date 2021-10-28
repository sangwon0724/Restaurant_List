package com.example.demo.controller;

import java.util.HashMap;


import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.annotaion.AuthUser;
import com.example.demo.vo.UserVO;

@AuthUser
@RestController
@RequestMapping("/api/test")
public class ApiController {
	
	//============================================= 유효성 검증 ===================================================
	@PostMapping("/validation")
	public ResponseEntity apiTestValidation(@Valid @RequestBody UserVO user, BindingResult bindingResult) {
		
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
	//============================================= 필터 ===================================================
	
	@PostMapping("/filter/post")
    public UserVO postApiTestFilter(@RequestBody UserVO user){
        return user;
    }

    @DeleteMapping("/filter/delete")
    public ResponseEntity deleteApiTestFilter(){
        return ResponseEntity.ok().build();
    }
}
