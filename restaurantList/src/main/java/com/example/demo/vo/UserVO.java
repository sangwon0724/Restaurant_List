package com.example.demo.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

//import com.example.demo.annotaion.Birthday;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(builderClassName = "UserVOBuilder", toBuilder = true)
@JsonDeserialize(builder = UserVO.UserVOBuilder.class)
public class UserVO {
	private String name;
	
	@Min(value = 0, message = "나이는 0살부터 입력가능합니다.")
	private int age;
	
	@Email(message = "이메일 형식에 맞지 않은 잘못된 값입니다.")
	private String email;
	
	private String phoneNumber;
	
	//@Birthday(regexp = "yyyyMMdd")
	private String birthday;
	
	@JsonPOJOBuilder(withPrefix = "") public static class UserVOBuilder { 
		
	}
}
