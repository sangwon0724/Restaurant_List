package com.example.demo.controller;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.annotaion.AuthUser;
import com.example.demo.service.AsyncService;
import com.example.demo.vo.UserVO;

import lombok.extern.slf4j.Slf4j;

@AuthUser
@RestController
@Slf4j
@RequestMapping("/api/test")
public class ApiController {
	@Autowired
	AsyncService asyncService;
	
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
    
	//============================================= 비동기 처리 ===================================================

    @GetMapping("/async/listenableFuture")
    public ListenableFuture<Integer> listenableFuture() throws InterruptedException, ExecutionException {
    	log.info("/async/listenableFuture");
        return asyncService.listenableFuture(10000);
    }

    @GetMapping("/async/completableFuture")
    public CompletableFuture<String> completableFuture() throws Exception {
    	log.info("/async/completableFuture");
        return asyncService.completableFuture(1000);
    }
}
