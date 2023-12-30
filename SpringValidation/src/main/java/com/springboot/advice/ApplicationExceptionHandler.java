package com.springboot.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.springboot.exception.UserNotFoundException;

@RestControllerAdvice
public class ApplicationExceptionHandler {

	// hàm xử lý khi có ngoại lệ MethodArgumentNotValidException
	// MethodArgumentNotValidException là 1 loại ngoại lệ của springframwork
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex){
		Map<String, String> errorMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		
		return errorMap;
	}
	
	
	// hàm xử lý khi có ngoại lệ UserNotFoundException
    // UserNotFoundException là 1 loại ngoại lệ do ta tự xây dựng - tự custom 
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(UserNotFoundException.class)
	public Map<String, String> handlerBusinessException(UserNotFoundException ex){
		
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("errorMessage", ex.getMessage());
		
		return errorMap;
	}
	/*
	 * Khi bạn đánh dấu một lớp với @RestControllerAdvice, nó trở thành một loại
	 * interceptor global cho các controller. Các phương thức trong lớp này có thể
	 * chứa các xử lý ngoại lệ và logic chung mà bạn muốn áp dụng cho toàn bộ ứng
	 * dụng.
	 */
	
}
