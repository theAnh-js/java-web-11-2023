package com.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller
//@ResponseBody
@RestController
public class HelloWorldController {
	
	
	@GetMapping("")
	public String helloWorld() {
		return "Hello World";
	}
	
}
