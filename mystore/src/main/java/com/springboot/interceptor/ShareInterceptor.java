package com.springboot.interceptor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;

import com.springboot.entity.Category;
import com.springboot.service.CategoryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ShareInterceptor extends WebRequestHandlerInterceptorAdapter {

	@Autowired
	private CategoryService categoryService;
	
	public ShareInterceptor(WebRequestInterceptor requestInterceptor) {
		super(requestInterceptor);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		List<Category> categoryList = categoryService.findAll();
		if(modelAndView != null) {
			modelAndView.addObject("categoryList", categoryList);
		}	
	}
}
