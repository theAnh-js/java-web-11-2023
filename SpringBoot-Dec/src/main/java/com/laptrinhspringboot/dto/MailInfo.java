package com.laptrinhspringboot.dto;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MailInfo {

	String from = "theanh";
	String to;
	String[] cc;
	String[] bcc;
	String subject;
	String body;
	List<File> files = new ArrayList<>();
	public MailInfo(String to, String subject, String body) {
		this.to = to;
		this.subject = subject;
		this.body = body;
	}
	
}
