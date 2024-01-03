package com.springboot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springboot.entity.Order;
import com.springboot.repository.OrderRepository;
import com.springboot.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	private OrderRepository orderRepository;

	public OrderServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Override
	public Order findById(Integer id) {

		Optional<Order> Order = orderRepository.findById(id);
		if (Order.isPresent()) {
			return Order.get();
		}
		return null;
	}

	@Override
	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	@Override
	public Order create(Order entity) {
		return orderRepository.save(entity);
	}

	@Override
	public void update(Order entity) {

		Order existOrder = findById(entity.getId());
		if (existOrder != null) {
			
			existOrder.setId(entity.getId());
			existOrder.setCustomer(entity.getCustomer());
			existOrder.setAddress(entity.getAddress());
			existOrder.setAmount(entity.getAmount());
			existOrder.setDescription(entity.getDescription());
			existOrder.setOrderDate(entity.getOrderDate());

			orderRepository.save(existOrder);
		}

	}

	@Override
	public Order delete(Integer id) {

		Order deleteOrder = findById(id);
		if (deleteOrder != null) {
			orderRepository.delete(deleteOrder);
			return deleteOrder;
		} else {
			return null;
		}

	}
}
