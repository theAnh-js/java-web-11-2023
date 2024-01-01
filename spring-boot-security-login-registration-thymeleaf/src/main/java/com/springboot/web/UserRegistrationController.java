package com.springboot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.service.UserService;
import com.springboot.web.dto.UserRegistrationDto;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
	
	// nếu hiển thị form login mặc định -> username : user, password: dưới console

	@Autowired
	private UserService userService;
	
	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}
	
	@GetMapping("") 
	public String showRegistrationForm() {
		return "registration";
	}
	
	@PostMapping("")
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto userRegistrationDto) {
		userService.save(userRegistrationDto);
		return "redirect:/registration?success";
	}
	
	/*
	 * Bằng cách này, khi bạn chuyển hướng từ controller với
	 * redirect:/registration?success, Thymeleaf sẽ kiểm tra tham số success trong
	 * URL và hiển thị thông báo thành công tương ứng trên trang "/registration".
	 */
	
	/*
	 * @ModelAttribute("user") đảm bảo rằng đối tượng UserRegistrationDto sẽ được
	 * gắn vào model với tên "user" và có thể được sử dụng trong template Thymeleaf.
	 */
}
