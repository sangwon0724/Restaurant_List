package com.example.demo.annotaion;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface AuthUser {
	//어노테이션 체크 매소드용 임시 어노테이션
	//유저의 권한을 나타내기 위한 어노테이션
}