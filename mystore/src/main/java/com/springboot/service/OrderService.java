package com.springboot.service;

import java.util.List;

import com.springboot.entity.Order;

public interface OrderService {

	Order findById(Integer id);

	List<Order> findAll();

	Order create(Order entity);
	
	void update(Order entity);

	Order delete(Integer id);
}
