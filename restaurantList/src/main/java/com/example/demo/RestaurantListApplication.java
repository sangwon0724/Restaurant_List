package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class RestaurantListApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantListApplication.class, args);
		//pom.xml이 에러나서 [프로젝트 우클릭 → properties → Maven 클릭 → "pom.xml" 지우기]를 진행함
	}

}