package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhjavaweb.converter.NewsConverter;
import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.entity.CategoryEntity;
import com.laptrinhjavaweb.entity.NewEntity;
import com.laptrinhjavaweb.repository.CategoryRepository;
import com.laptrinhjavaweb.repository.NewRepository;
import com.laptrinhjavaweb.service.INewService;

@Service
public class NewService implements INewService {
	
	
	@Autowired
	private NewRepository newRepository;
	
	@Autowired
	private NewsConverter newsConverter;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<NewDTO> findAll(Pageable pageable) {
		List<NewDTO> models = new ArrayList<>();	
		List<NewEntity> entities = newRepository.findAll(pageable).getContent();
		for(NewEntity item : entities) {
			NewDTO newDTO = newsConverter.toDTO(item);  // chuyển từ entity -> dto		
			models.add(newDTO);
		}
		return models;
	}

	@Override
	public int getTotalItem() {
		return (int)newRepository.count();
	}

	@Override
	public NewDTO findById(long id) {
		NewEntity newEntity = newRepository.findOne(id);
		return newsConverter.toDTO(newEntity);
	}

	/*
	 * @Override
	 * 
	 * @Transactional public NewDTO insert(NewDTO newDTO) {
	 * 
	 * NewEntity newEntity = newsConverter.toEntity(newDTO); CategoryEntity
	 * categoryEntity = categoryRepository.findOneByCode(newDTO.getCategoryCode());
	 * newEntity.setCategoryEntity(categoryEntity);
	 * 
	 * newEntity = newRepository.save(newEntity); return
	 * newsConverter.toDTO(newEntity); }
	 * 
	 * @Override
	 * 
	 * @Transactional public NewDTO update(NewDTO dto) { NewEntity oldNew =
	 * newRepository.findOne(dto.getId()); CategoryEntity categoryEntity
	 * =categoryRepository.findOneByCode(dto.getCategoryCode());
	 * oldNew.setCategoryEntity(categoryEntity);
	 * 
	 * NewEntity updateNew = newsConverter.toEntity(oldNew, dto); updateNew =
	 * newRepository.save(updateNew); return newsConverter.toDTO(updateNew); }
	 */
	
	@Override
	@Transactional
	public NewDTO save(NewDTO dto) {
		CategoryEntity categoryEntity =categoryRepository.findOneByCode(dto.getCategoryCode());
		NewEntity newEntity = new NewEntity();
		if(dto.getId() != null) {
			NewEntity oldNew = newRepository.findOne(dto.getId());
			oldNew.setCategoryEntity(categoryEntity);
			newEntity = newsConverter.toEntity(oldNew, dto);
		}else {
			newEntity = newsConverter.toEntity(dto);
			newEntity.setCategoryEntity(categoryEntity);
		}
		return newsConverter.toDTO(newRepository.save(newEntity));
	}

	@Override
	@Transactional
	public void delete(long[] ids) {
		for (long id : ids) {
			newRepository.delete(id);
		}
	}

}
