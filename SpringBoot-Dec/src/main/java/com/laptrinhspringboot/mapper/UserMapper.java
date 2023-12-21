package com.laptrinhspringboot.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhspringboot.dto.UserDTO;
import com.laptrinhspringboot.entity.UserEntity;

@Service
public class UserMapper {

	//tiêm thằng bean ModelMapper mình tự tạo ở ModelMapperConfig.java vào đây
	
	@Autowired
	private ModelMapper modelMapper;
	
	public UserEntity toEntity(UserDTO userDTO) {
		
		UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
		
		return userEntity;
	}
	
	public UserDTO toDTO(UserEntity userEntity) {
		
		UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);
		
		return userDTO;
	}
	
}
