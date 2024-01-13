package com.springboot.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entity.Product;
import com.springboot.repository.ProductRepository;
import com.springboot.service.CookieService;
import com.springboot.service.ProductService;

import jakarta.servlet.http.Cookie;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Autowired
	CookieService cookieService;

	@Override
	public Product findById(Integer id) {

		Optional<Product> Product = productRepository.findById(id);
		if (Product.isPresent()) {
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
		if (existProduct != null) {
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
		if (deleteProduct != null) {
			productRepository.delete(deleteProduct);
			return deleteProduct;
		} else {
			return null;
		}

	}

	@Override
	public List<Product> findByName(String keyword) {
		return productRepository.findByKeyword(keyword);
	}

	@Override
	public List<Product> findByFavorate(String ids) {
		List<Product> listFavo = new ArrayList<>();
		String[] idList = ids.split(",");
		for (String id : idList) {
			Product pro = productRepository.getById(Integer.valueOf(id));
			listFavo.add(pro);
		}
		return listFavo;
	}

	@Override
	public List<Product> findByFavo(String ids) {
		return productRepository.findByFavo(Arrays.asList(ids.split(",")));
	}

	// lấy id các sản phẩm khách hàng đã xem chi tiết
	public String getWatchedIdList(Integer id) {

		Cookie watched = cookieService.read("watched");
		String value = id.toString();
		if (watched != null) {
			
			value = watched.getValue();
			if (!value.contains(id.toString())) {
				value += "," + id.toString();
			}

			/*
			 * String val = watched.getValue(); // 2,3,3 if (!val.contains(value)) { value
			 * += "," + val; }else { value = val; }
			 */
		} 
		
		cookieService.create("watched", value, 30);
		return value;
	}

	// Lấy ra các sản phẩm k/h đã xem chi tiết qua value cookie
	public List<Product> findWatchedProdList(Integer id) {
		String ids = getWatchedIdList(id);
		return productRepository.findByFavo(Arrays.asList(ids.split(",")));
	}

	@Override
	public List<Product> findNewProduct(int numbers) {
		return productRepository.findNewProduct(numbers);
	}

	@Override
	public List<Product> findBestViewProduct(int numbers) {
		return productRepository.findBestViewProduct(numbers);
	}

	@Override
	public List<Product> findBestDiscountProduct(int numbers) {
		return productRepository.findBestDiscountProduct(numbers);
	}

	@Override
	public List<Product> findBestSellerProduct(int number) {
		return productRepository.findBestSellerProduct(number);
	}

}
