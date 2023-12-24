package com.laptrinhspringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.laptrinhspringboot.interceptor.AuthInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{
	
	@Autowired
	AuthInterceptor authInterceptor;
	
	public void addInterceptor(InterceptorRegistry registry) {
		registry.addInterceptor(authInterceptor)
			.addPathPatterns("/admin/**", "/users/**")
			.excludePathPatterns("/login", "/register");
	}

}
