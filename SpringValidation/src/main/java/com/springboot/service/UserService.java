package com.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.dto.UserRequest;
import com.springboot.entity.UserEntity;
import com.springboot.exception.UserNotFoundException;
import com.springboot.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public UserEntity saveUser(UserRequest userRequest) {
		UserEntity user = UserEntity.build(0, userRequest.getName(), 
				userRequest.getMail(), userRequest.getMobile(),
				userRequest.getGender(), userRequest.getAge(), 
				userRequest.getNationality());
		
		return userRepository.save(user);
	}
	
	public List<UserEntity> getAllUsers(){
		return userRepository.findAll();
	}
	
	public UserEntity getUser(int id) throws UserNotFoundException {
		UserEntity user = userRepository.findByUserId(id); // -> Giải thích 1
		if(user != null) {
			return user;
		}else {
			throw new UserNotFoundException("Không tìm thấy user có id là " + id); // ta tự tạo là UserNotFoundException()
		}
	}
	/* Giải thích 1:
	 
	 * Trong Spring Data JPA, có một quy ước đặt tên (naming convention) cho các
	 * phương thức truy vấn tìm kiếm dữ liệu trong repository. Nếu bạn đặt tên
	 * phương thức theo quy ước này, Spring Data JPA sẽ tự động thực hiện truy vấn
	 * dựa trên tên của phương thức.
	 * 
	 * Trong trường hợp của bạn, phương thức findByUserId không cần được bạn định
	 * nghĩa một cách rõ ràng trong repository. Theo quy ước đặt tên, Spring Data
	 * JPA sẽ tự động hiểu rằng bạn muốn tìm kiếm một UserEntity dựa trên trường
	 * userId.
	 */
}
