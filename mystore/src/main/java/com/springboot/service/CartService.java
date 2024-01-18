package com.springboot.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entity.Product;
import com.springboot.repository.ProductRepository;

@Service
public class CartService {
	
	@Autowired
	ProductRepository productRepository;
	
	Map<Integer, Product> cartList = new HashMap<>();
	
	public void add(int id) {
		
		if(!cartList.containsKey(id)) {
			Product prod = productRepository.getById(id);
		}
		
		
		
	}
	
	public double getTotalPrice() {
		
		double totalPrice = 0;
		 
		List<Product> list = (List<Product>) getTotalItems();
		
		for(Product product : list) {
			totalPrice = product.getQuantity() * 
							product.getUnitPrice() * (1 - product.getDiscount());
		}
		
		return totalPrice;
	}
	
	public int getTotalProduct() {
		return getTotalItems().size();
	}
	
	public Collection<Product> getTotalItems() {
		return cartList.values();
	}
}
