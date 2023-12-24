package com.laptrinhspringboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.laptrinhspringboot.dto.UserDTO;
import com.laptrinhspringboot.entity.UserEntity;
import com.laptrinhspringboot.mapper.UserMapper;
import com.laptrinhspringboot.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserMapper userMapper;
	
	@Override
	public List<UserDTO> findAll() {
		List<UserDTO> userDTOList = new ArrayList<>();
				
		List<UserEntity> UserEntityList = userRepository.findAll();
		
		for (UserEntity userEntity : UserEntityList) {
			userDTOList.add(userMapper.toDTO(userEntity));
		}
		
		return userDTOList;
		
	}

	@Override
	public UserDTO findOne(Integer id) {
		
		// UserEntity userEntity = userRepository.getOne(id); dùng ok, nhưng bị gạch ngang nên thử dùng cách khác.
		
		UserEntity userEntity = null;
		UserDTO userDTO = null;
		
		Optional<UserEntity> optionalUserEntity = userRepository.findById(id);
		if (optionalUserEntity.isPresent()) {
			
		    userEntity = optionalUserEntity.get();
		    
		    userDTO = userMapper.toDTO(userEntity);
		}
		
		return userDTO;
	}

	@Override
	public void saveUser(UserDTO user) {
		UserEntity userEntity = userMapper.toEntity(user);
		userRepository.save(userEntity);
	}

	@Override
	public void deleteUser(UserDTO user) {
	
		UserEntity userEntity = userMapper.toEntity(user);
		userRepository.delete(userEntity);
	}

	@Override
	public void insertUser(UserDTO user) {
		UserEntity userEntity = userMapper.toEntity(user);
		userRepository.save(userEntity);
	}

	@Override
	public List<UserDTO> findALLBySort(int pageNumber, int limit, String sortBy, String orderBy) {
		
		List<UserDTO> userDTOList = new ArrayList<>();
		
		/*
		 * Sort sort = Sort.by(Direction.ASC, sortBy); if(orderBy.equals("desc")) { sort
		 * = Sort.by(Direction.DESC, sortBy); }
		 */
				
		Pageable pageable = PageRequest.of(pageNumber, limit, Direction.ASC, sortBy);
		if(orderBy.equals("desc")) {
			pageable = PageRequest.of(pageNumber, limit, Direction.DESC, sortBy);
		}
		
		Page<UserEntity> UserEntityList = userRepository.findAll(pageable);  // lấy list theo phân trang + sort
		//List<UserEntity> UserEntityList = userRepository.findAll(sort); // lấy list theo sort
		
		for (UserEntity userEntity : UserEntityList) {
			userDTOList.add(userMapper.toDTO(userEntity));
		}

		return userDTOList;
	}

	@Override
	public UserDTO findByEmail(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		if(userEntity == null) return null;
		
		UserDTO userDTO = userMapper.toDTO(userEntity);
		return userDTO;
	}
	
	

}
