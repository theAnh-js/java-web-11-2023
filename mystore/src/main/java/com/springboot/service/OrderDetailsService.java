package com.springboot.service;

import java.util.List;

import com.springboot.entity.OrderDetails;

public interface OrderDetailsService {
	
	OrderDetails findById(Integer id);

	List<OrderDetails> findAll();

	OrderDetails create(OrderDetails entity);
	
	void update(OrderDetails entity);

	OrderDetails delete(Integer id);
}
