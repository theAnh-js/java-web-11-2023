package com.springboot.service;

import java.util.List;

import com.springboot.entity.Product;

public interface ProductService {

	Product findById(Integer id);

	List<Product> findAll();

	Product create(Product entity);
	
	void update(Product entity);

	Product delete(Integer id);
}
