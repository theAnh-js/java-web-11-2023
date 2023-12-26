package com.laptrinhspringboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.laptrinhspringboot.model.Blog;
import com.laptrinhspringboot.repository.BlogRepository;

@Service
public class BlogServiceImpl implements IBlogService{

	@Autowired
	BlogRepository blogRepository;
	
	@Override
	public List<Blog> findAll() {
		return blogRepository.findAll();
	}

	@Override
	public Blog findById(int id) {
		return blogRepository.findById(id).orElse(null);
	}

	@Override
	public Blog findByAuthorAndTopic(String author, String topic) {
		return blogRepository.findByAuthorAndTopicBlog(author, topic);
	}

	@Override
	public Page<Blog> findAllBlogPaging(Pageable pageable) {
		return blogRepository.findAll(pageable);
	}

	@Override
	public Page<Blog> findAllBlogPagingByName(String name, Pageable pageable) {
		return null;
	}

	@Override
	public Page<Blog> findAllBlogPagingByECommerce(int id, Pageable pageable) {
		return blogRepository.findAllBlogPagingByECommerce(id, pageable);
	}

	@Override
	public Page<Blog> findBlogPagingByAuthorName(String author, Pageable pageable) {
		return blogRepository.findBlogPagingByAuthorName(author, pageable);
	}

	@Override
	public Page<Blog> findAllBlogPagingByEcomAndAuthorName(int ecomId, String authorName, Pageable pageable) {
		return blogRepository.findAllBlogPagingByEcomAndAuthorName(ecomId, authorName, pageable);
				
	}

}
