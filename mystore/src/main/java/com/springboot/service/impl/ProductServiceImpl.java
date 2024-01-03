package com.springboot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springboot.entity.Product;
import com.springboot.repository.ProductRepository;
import com.springboot.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;
	
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	

	@Override
	public Product findById(Integer id) {
		
		Optional<Product> Product = productRepository.findById(id);
		if(Product.isPresent()) {
			return Product.get();
		}
		return null;
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Product create(Product entity) {
		return productRepository.save(entity);
	}

	@Override
	public void update(Product entity) {
		
		Product existProduct = findById(entity.getId());
		if(existProduct != null) {
			existProduct.setId(entity.getId());
			existProduct.setName(entity.getName());
			existProduct.setImage(entity.getImage());
			existProduct.setDate(entity.getDate());
			existProduct.setAvailable(entity.getAvailable());
			existProduct.setCategory(entity.getCategory());
			existProduct.setDescription(entity.getDescription());
			existProduct.setDiscount(entity.getDiscount());
			existProduct.setViewCount(entity.getViewCount());
			existProduct.setQuantity(entity.getQuantity());
			existProduct.setSpecial(entity.getSpecial());
			existProduct.setUnitPrice(entity.getUnitPrice());
			
			productRepository.save(existProduct);
		}
		
	}

	@Override
	public Product delete(Integer id) {
		
		Product deleteProduct = findById(id);	
		if(deleteProduct != null){
			productRepository.delete(deleteProduct);
			return deleteProduct;
		}else {
			return null;
		}
		
	}

}
