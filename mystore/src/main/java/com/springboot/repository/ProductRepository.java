package com.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	
	List<Product> findByNameIsContaining(String name);
	// Ngoài cách để jparepository tự findbyname cho, ta có thể tự viết câu @Query như:
	
	@Query(value = "SELECT p FROM Product p WHERE p.name LIKE '%' || :key || '%'")
	List<Product> findByKeyword(@Param("key") String keyword);
	
	
	@Query(value = "SELECT p FROM Product p WHERE p.id IN :ids")
	List<Product> findByFavo(@Param("ids") 	List<String> idList);
	
	@Query(value = "SELECT p FROM Product p ORDER BY p.date DESC LIMIT :numbers")
	List<Product> findNewProduct(@Param("numbers") int num);
	
	@Query(value = "SELECT p FROM Product p ORDER BY p.viewCount DESC LIMIT :numbers")
	List<Product> findBestViewProduct(@Param("numbers") int num);
	
	@Query(value = "SELECT p FROM Product p ORDER BY p.discount DESC LIMIT :numbers")
	List<Product> findBestDiscountProduct(@Param("numbers") int num);
	
	@Query(value = "SELECT p FROM Product p ORDER BY size(p.orderDetailsList) DESC LIMIT :numbers")
	List<Product> findBestSellerProduct(@Param("numbers") int num);
}
