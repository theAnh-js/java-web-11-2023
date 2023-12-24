package com.laptrinhspringboot.controller.admin;

import org.eclipse.jdt.internal.compiler.batch.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.laptrinhspringboot.dto.MailInfo;
import com.laptrinhspringboot.service.IMailService;

import jakarta.mail.MessagingException;

@Controller
@RequestMapping("/mailer")
public class MailController {
	
	@Autowired
	IMailService mailer;
	
	@GetMapping("")
	public String mailViewer() {
		return "admin/user/mail";
	}
	
	@ResponseBody
	@PostMapping("/send")
	public String send(Model model, String txtTo, 
			@RequestParam String txtSubject, 
			@RequestParam String txtContent
			) {
		
		// chưa dùng scheduling
		/*
		 * try { MailInfo mail = new MailInfo(); mail.setTo(txtTo);
		 * mail.setSubject(txtSubject); mail.setBody(txtContent); mailer.send(mail);
		 * 
		 * return "<h1>Gửi mail thành công</h1>";
		 * 
		 * }catch(MessagingException e){ return "<h1>Gửi mail thất bại</h1>" +
		 * e.getMessage(); }
		 */	
		
		// dùng scheduling (sẽ nhanh hơn, không bị nghẽn khi nhiều người gửi)
		MailInfo mail = new MailInfo();
		mail.setTo(txtTo);
		mail.setSubject(txtSubject);
		mail.setBody(txtContent);
		
		mailer.queue(mail);
		
		return "Mail của bạn đã được gửi đi thành công";
		
	}
}
