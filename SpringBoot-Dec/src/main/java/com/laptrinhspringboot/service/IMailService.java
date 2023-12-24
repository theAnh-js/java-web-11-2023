package com.laptrinhspringboot.service;

import com.laptrinhspringboot.dto.MailInfo;

import jakarta.mail.MessagingException;

public interface IMailService {

	void send(MailInfo mail) throws MessagingException;
	void send(String to, String subject, String body) throws MessagingException;
	
	void queue(MailInfo mail);
	void queue(String to, String subject, String body);
	
	
}
