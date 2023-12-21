package com.laptrinhspringboot.service;

import java.util.List;

import com.laptrinhspringboot.dto.UserDTO;

public interface IUserService {
	
	List<UserDTO> findAll();
	
	UserDTO findOne(Integer id);
	
	void saveUser(UserDTO user);
	
	void deleteUser(UserDTO user);
	
	void insertUser(UserDTO user);
}
