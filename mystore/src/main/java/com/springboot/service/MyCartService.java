package com.springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entity.CartItem;
import com.springboot.entity.Product;
import com.springboot.repository.ProductRepository;

@Service
public class MyCartService {
	
	@Autowired
	ProductRepository productRepository;
	
	List<CartItem> cartList = new ArrayList<>();
	
	public void add(int id) {	
		System.out.println("id ====> " + id);
		CartItem item = getItem(id);
		
		
		
		if(item != null) {
			System.out.println("product name: " + item.getProduct().getName());
			item.setQuantity(item.getQuantity() + 1);
		}else {
			
			CartItem newItem = new CartItem();
			Product prod = productRepository.getById(id);
			newItem.setProduct(prod);
			newItem.setQuantity(1);
			newItem.setPrice(prod.getUnitPrice() * (1 - prod.getDiscount()));
			
			System.out.println("new product to cart: " + newItem.getProduct().getName());
			cartList.add(newItem);
		
		}	
		
		
	}
	
	public CartItem getItem(int id) {
		/*
		 * List<Product> productList = this.getProductList(); boolean isExist = false;
		 * for(Product prod : productList) { int productId = prod.getId(); if(productId
		 * == id) { isExist = true; break; } } return isExist;
		 */	
		if(getTotalItem() > 0) {
			for(CartItem item : cartList) {
				if(item.getProduct().getId() == id) {
					return item;
				}
			}
		}
		return null;
	}
	
	
	public List<Product> getProductList(){
		List<Product> list = new ArrayList<>();
		for(CartItem item : cartList) {
			list.add(item.getProduct());
		}		
		return list;
	}
	
	public double getTotalPrice() {
		double totalPrice = 0;
		for(CartItem item : cartList) {
			totalPrice += item.getPrice() * item.getQuantity();
		}
		return totalPrice;
	}
	
	public int getTotalProduct() {
		int totalAmount = 0;
		for(CartItem item : cartList) {
			totalAmount += item.getQuantity();
		}		
		return totalAmount;
	}
	
	public int getTotalItem() {
		return cartList.size();
	}
	
}
