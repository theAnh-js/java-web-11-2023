package com.laptrinhspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {
	
	private Integer id;

	private Integer orderId;

	private Integer productId;
	
	private Integer quantity;
	
	private double price;

}
