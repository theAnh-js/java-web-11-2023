package com.springboot.service;

import java.util.List;

import com.springboot.entity.Product;

public interface ProductService {

	Product findById(Integer id);

	List<Product> findAll();

	Product create(Product entity);
	
	void update(Product entity);

	Product delete(Integer id);
	
	List<Product> findByName(String keyword);

	List<Product> findByFavorate(String ids);
	
	List<Product> findByFavo(String ids);// hàm tự query
	
	List<Product> findWatchedProdList(Integer ids);
	
	List<Product> findNewProduct(int numbers);
	
	List<Product> findBestViewProduct(int numbers);
	
	List<Product> findBestDiscountProduct(int numbers);
	
	List<Product> findBestSellerProduct(int number);
	
}
