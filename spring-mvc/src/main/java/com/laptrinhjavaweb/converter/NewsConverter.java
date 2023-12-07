package com.laptrinhjavaweb.converter;

import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.entity.NewEntity;

@Component
public class NewsConverter {
	
	public NewDTO toDTO(NewEntity newEntity) {
		
		NewDTO result = new NewDTO();
		result.setId(newEntity.getId());
		result.setTitle(newEntity.getTitle());
		result.setShortDescription(newEntity.getShortDescription());
		result.setContent(newEntity.getContent());
		result.setThumbnail(newEntity.getThumbnail());
		result.setCategoryCode(newEntity.getCategoryEntity().getCode());
		return result;
	}
	
	public NewEntity toEntity(NewDTO newDTO) {	
		NewEntity result = new NewEntity();
		result.setTitle(newDTO.getTitle());
		result.setShortDescription(newDTO.getShortDescription());
		result.setContent(newDTO.getContent());
		result.setThumbnail(newDTO.getThumbnail());
		
		return result;
	}
	
	public NewEntity toEntity(NewEntity oldNew, NewDTO newDTO) {	
		
		oldNew.setTitle(newDTO.getTitle());
		oldNew.setShortDescription(newDTO.getShortDescription());
		oldNew.setContent(newDTO.getContent());	
		oldNew.setThumbnail(newDTO.getThumbnail());
		return oldNew;
	}
	
}
