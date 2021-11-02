package com.example.demo.validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.demo.annotation.Birthday;

public class BirthdayValidator implements ConstraintValidator<Birthday, String>{
	private String pattern;
	
	@Override
	public void initialize(Birthday constraintAnnotation) {
		this.pattern=constraintAnnotation.regexp();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		System.out.println(value);
		try {
			LocalDate localDate = LocalDate.parse(value, DateTimeFormatter.ofPattern("yyyyMMdd"));
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}

}
