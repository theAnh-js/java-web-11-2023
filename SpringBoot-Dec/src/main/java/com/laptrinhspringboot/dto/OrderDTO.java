package com.laptrinhspringboot.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
	
	private Integer id;
	
	private Integer userId;
	
	private Date createDate;
	
	private String address;
	
}
