package com.springboot.exception;

public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = -4632443851454903594L;

	public UserNotFoundException(String message) {
		super(message);
	}
	
}
