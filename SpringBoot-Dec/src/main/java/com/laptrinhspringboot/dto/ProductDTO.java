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
public class ProductDTO {

	private Integer id;
	private String name;
	private String email;
	private double price;
	private Date  createdDate;
	private Integer available;
	private Integer categoryId;
	
}
