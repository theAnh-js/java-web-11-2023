package com.laptrinhspringboot.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.laptrinhspringboot.entity.UserEntity;

@Controller
public class HelloController {

	@Autowired
	private ServletContext servletContext;

	@RequestMapping("/hello")
	public String hello(Model model) {
		return "hello";
	}

}
