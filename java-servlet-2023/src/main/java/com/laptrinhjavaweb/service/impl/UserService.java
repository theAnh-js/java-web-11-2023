package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.dao.IUserDAO;
import com.laptrinhjavaweb.dao.impl.UserDAO;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.service.IUserService;

public class UserService implements IUserService {

	IUserDAO userDao = new UserDAO();

	@Override
	public UserModel findByUserNameAndPasswordAndStatus(String username, String password, Integer status) {
		return userDao.findByUserNameAndPasswordAndStatus(username, password, status);
	}

}
