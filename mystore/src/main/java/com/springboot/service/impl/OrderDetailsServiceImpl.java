package com.springboot.service.impl;

import java.util.List;
import java.util.Optional;

import com.springboot.entity.OrderDetails;
import com.springboot.repository.OrderDetailsRepository;
import com.springboot.service.OrderDetailsService;

public class OrderDetailsServiceImpl implements OrderDetailsService{
	
	
	private OrderDetailsRepository orderDetailsRepository;
	
	public OrderDetailsServiceImpl(OrderDetailsRepository orderDetailsRepository) {
		this.orderDetailsRepository = orderDetailsRepository;
	}
	

	@Override
	public OrderDetails findById(Integer id) {
		
		Optional<OrderDetails> OrderDetails = orderDetailsRepository.findById(id);
		if(OrderDetails.isPresent()) {
			return OrderDetails.get();
		}
		return null;
	}

	@Override
	public List<OrderDetails> findAll() {
		return orderDetailsRepository.findAll();
	}

	@Override
	public OrderDetails create(OrderDetails entity) {
		return orderDetailsRepository.save(entity);
	}

	@Override
	public void update(OrderDetails entity) {
		
		OrderDetails existOrderDetails = findById(entity.getId());
		if(existOrderDetails != null) {
			existOrderDetails.setId(entity.getId());
			existOrderDetails.setQuantity(entity.getQuantity());
			existOrderDetails.setDiscount(entity.getDiscount());
			existOrderDetails.setUnitPrice(entity.getUnitPrice());
			existOrderDetails.setProduct(entity.getProduct());
			existOrderDetails.setOrder(entity.getOrder());
			
			orderDetailsRepository.save(existOrderDetails);
		}
		
	}

	@Override
	public OrderDetails delete(Integer id) {
		
		OrderDetails deleteOrderDetails = findById(id);	
		if(deleteOrderDetails != null){
			orderDetailsRepository.delete(deleteOrderDetails);
			return deleteOrderDetails;
		}else {
			return null;
		}
		
	}

	
}
