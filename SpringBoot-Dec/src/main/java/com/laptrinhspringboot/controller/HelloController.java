package com.laptrinhspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.laptrinhspringboot.entity.User;

@Controller
public class HelloController {

	@RequestMapping("/hello")
	public String hello(Model model) {
		
		User user = new User();
		user.setName("theanh");
		model.addAttribute("myUser", user);
		return "hello";
	}
}
