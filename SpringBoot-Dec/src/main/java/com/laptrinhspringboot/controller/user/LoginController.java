package com.laptrinhspringboot.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.laptrinhspringboot.dto.UserDTO;
import com.laptrinhspringboot.service.IUserService;
import com.laptrinhspringboot.utils.HashUtil;

@Controller
public class LoginController {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	IUserService userService;
	
	@GetMapping(value="/login")
	public String showFormLogin() {
		return "admin/user/login";
	}
	
	@PostMapping(value="/login")
	public String checkAccount(@RequestParam("email") String email, @RequestParam("password") String password) {
		
		UserDTO user = userService.findByEmail(email);
		
		HttpSession session = request.getSession(true);
		
		if(user == null) {
			session.setAttribute("errorLogin", "Mật khẩu hoặc email chưa đúng");
			return "redirect:/login";
		}
		
		boolean checkPw = HashUtil.verify(password, user.getPassword());
		if(checkPw == false) {
			session.setAttribute("errorLogin", "Mật khẩu hoặc email chưa đúng");
			return "redirect:/login";
		}
		
		session.setAttribute("user", user);
		return "redirect:/admin/users";
	}

}
