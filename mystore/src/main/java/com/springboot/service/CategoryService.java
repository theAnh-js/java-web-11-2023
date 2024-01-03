package com.springboot.service;

import java.util.List;

import com.springboot.entity.Category;

public interface CategoryService {

	Category findById(Integer id);

	List<Category> findAll();

	Category create(Category entity);

	void update(Category entity);

	Category delete(Integer id);

}
