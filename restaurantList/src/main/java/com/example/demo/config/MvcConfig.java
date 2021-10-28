package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.interceptor.AuthInterceptor;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    private static final String[] DEFAULT_EXCLUDE_PATH = {
            "/api/ping",
    };

    //@AutoWired로 받을 수도 있지만 그렇게 되면 순환 참조가 일어날 수도 있다.
    private final AuthInterceptor authInterceptor;

    public MvcConfig(AuthInterceptor authInterceptor) {
        this.authInterceptor = authInterceptor;
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor);
    }
}