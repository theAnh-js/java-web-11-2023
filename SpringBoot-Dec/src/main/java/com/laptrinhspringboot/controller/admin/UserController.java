package com.laptrinhspringboot.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/users")
public class UserController {

	@RequestMapping
	public String index() {
		return "admin/user/index";
	}
	
	@GetMapping(params="id")
	public String show(int id) {
		return "admin/user/show";
	}
	
	@GetMapping(value="/create")
	public String create() {
		return "admin/user/create";
	}
	
	@PostMapping(value="/store")
	public String store() {
		return "";
	}

	@GetMapping(value="/edit", params="id")
	public String edit(int id) {
		return "admin/user/edit";
	}

	@PostMapping(value="/update", params="id")
	public String update(int id) {
		return "";
	}

	@PostMapping(value="/delete", params="id")
	public String delete(int id) {
		return "";
	}
	
	// 45:37 / 1:46:21 
	// Springboot - Buổi 3 - Java5: Request Mapping; Model; Mapping trong Springboot

}
