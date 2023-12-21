package com.laptrinhspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	private Integer id;
	
	private String username;
	
	private String password;
	
	private String email;	

	private String photo;
	
	private Integer activated;
	
	private Integer admin;
	
}
