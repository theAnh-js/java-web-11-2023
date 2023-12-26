package com.laptrinhspringboot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.laptrinhspringboot.model.Blog;

@Service
public interface IBlogService {

	List<Blog> findAll();
	
	Blog findById(int id);
	
	Blog findByAuthorAndTopic(String author, String topic);
	
	Page<Blog> findAllBlogPaging(Pageable pageable);
	
	Page<Blog> findBlogPagingByAuthorName(String author, Pageable pageable);
	
	Page<Blog> findAllBlogPagingByName(String name, Pageable pageable);
	
	Page<Blog> findAllBlogPagingByECommerce(int id, Pageable pageable);
	
	Page<Blog> findAllBlogPagingByEcomAndAuthorName(int ecomId, String authorName, Pageable pageable);
	
}
