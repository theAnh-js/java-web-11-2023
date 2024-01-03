package com.springboot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springboot.entity.Category;
import com.springboot.repository.CategoryRepository;
import com.springboot.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	private CategoryRepository categoryRepository;
	
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	

	@Override
	public Category findById(Integer id) {
		
		Optional<Category> category = categoryRepository.findById(id);
		if(category.isPresent()) {
			return category.get();
		}
		return null;
	}

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Category create(Category entity) {
		return categoryRepository.save(entity);
	}

	@Override
	public void update(Category entity) {
		
		Category existCategory = findById(entity.getId());
		if(existCategory != null) {
			existCategory.setId(entity.getId());
			existCategory.setName(entity.getName());
			existCategory.setNameVN(entity.getNameVN());
			existCategory.setProductList(entity.getProductList());
			
			categoryRepository.save(existCategory);
		}
		
	}

	@Override
	public Category delete(Integer id) {
		
		Category deleteCategory = findById(id);	
		if(deleteCategory != null){
			categoryRepository.delete(deleteCategory);
			return deleteCategory;
		}else {
			return null;
		}
		
	}
}
