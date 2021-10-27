package com.example.demo.vo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;

import com.example.demo.annotaion.Birthday;

public class UserVO {
	private String name;
	@Min(value = 0, message = "나이는 0살부터 입력가능합니다.")
	private int age;
	@Email(message = "이메일 형식에 맞지 않은 잘못된 값입니다.")
	private String email;
	private String phoneNumber;
	@Birthday(regexp = "yyyyMMdd")
	private String birthday;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
}
