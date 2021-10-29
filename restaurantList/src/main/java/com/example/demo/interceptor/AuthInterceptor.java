package com.example.demo.interceptor;

import com.example.demo.annotaion.AuthUser;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Start Interceptor");
		//url 가져오는 방법
		String url = request.getRequestURI();
		
		//uri 가져오는 방법
		URI uri = UriComponentsBuilder.fromUriString(url).build().toUri();

		//어노테이션 존재 여부 확인
        boolean validAuthUser = checkValidAccessAnnotation(handler, AuthUser.class);
        
        log.info("annotation check : " + validAuthUser);
        
        //요청들어온 handler에 어노테이션 존재 => 요청을 통해 가려는 url을 담당하는 컨트롤러에 해당 어노테이션이 존재
        //쿼리 가져오는 방법 : uri.getQuery()
        if(validAuthUser){
        	/* --- 쿼리 관련 로직 또는 권한 관련 로직 --- */
            log.info("End interceptor");
    		return true;
        }

        log.info("End Interceptor");
        
        return true;
    }

	//class는 예약어이기때문에 clazz라고 쓰는 것이 관행이다.
    private boolean checkValidAccessAnnotation(Object handler, Class clazz) {

    	//instanceof : 객체의 타입을 확인하는 연산자
    	
    	//resource check - ex) html, css, javascript
        if(handler instanceof ResourceHttpRequestHandler){
        	log.info("리소스 요청 class "+clazz.getName());
            return true;
        }

        //annotation check
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if(null != handlerMethod.getMethodAnnotation(clazz) || null != handlerMethod.getBeanType().getAnnotation(clazz)){
        	log.info("어노테이션 체크 class "+clazz.getName());
            return true;
        }

        return false;
    }
}
