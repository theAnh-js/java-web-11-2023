package com.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {
	
	@GetMapping
	public String index() {
		return "redirect:/home";
	}
	@GetMapping("/home")
	public String getHome() {
		return "index";
	}
	
	@GetMapping("/product")
	public String getPro() {
		return "product-detail";
	}
	
	@GetMapping("/category")
	public String getCate() {
		return "category-detail";
	}
	
	@GetMapping("/cart")
	public String getCart() {
		return "cart";
	}

}
