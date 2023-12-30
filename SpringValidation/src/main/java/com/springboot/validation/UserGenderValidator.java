package com.springboot.validation;

import java.util.Arrays;
import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserGenderValidator implements ConstraintValidator<ValidateUserGender, String> {

	@Override
	public boolean isValid(String userGender, ConstraintValidatorContext context) {
	
		List<String> genderList = Arrays.asList("Male", "Female", "Another");
		
		return genderList.contains(userGender);
	}

	/*
	 * ConstraintValidator là một interface trong Java, thường được sử dụng trong
	 * ngữ cảnh của Bean Validation (JSR 380) để tạo ra các validator tùy chỉnh cho
	 * các ràng buộc (constraints) tự định nghĩa.
	 * 
	 * Cụ thể, ConstraintValidator cung cấp một cách để triển khai logic kiểm tra
	 * hợp lệ (validation logic) cho một ràng buộc cụ thể. Mỗi ConstraintValidator
	 * được liên kết với một kiểu ràng buộc (annotation) và kiểu dữ liệu mà nó kiểm
	 * tra.
	 */
}
