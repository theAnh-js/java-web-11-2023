package com.laptrinhjavaweb.converter;

import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.CategoryDTO;
import com.laptrinhjavaweb.entity.CategoryEntity;

@Component
public class CategoryConverter {
	
	public CategoryDTO toDTO(CategoryEntity entity) {
		
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(entity.getId());
		categoryDTO.setName(entity.getCode());
		categoryDTO.setCode(entity.getCode());
		
		return categoryDTO;
	}
	
	public CategoryEntity toEntity(CategoryDTO dto) {
		
		CategoryEntity categoryEntity = new CategoryEntity();
		categoryEntity.setName(dto.getName());
		categoryEntity.setCode(dto.getCode());
		
		return categoryEntity;
	}

}
