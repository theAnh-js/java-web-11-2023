package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.dto.UserRequest;
import com.springboot.entity.UserEntity;
import com.springboot.exception.UserNotFoundException;
import com.springboot.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/signup")
	public ResponseEntity<UserEntity> saveUser(@RequestBody @Valid UserRequest userRequest){
		return new ResponseEntity<UserEntity>(userService.saveUser(userRequest), HttpStatus.CREATED);
	}
	
	@GetMapping("/fetchAll")
	public ResponseEntity<List<UserEntity>> getAllUsers(){
		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserEntity> getUser(@PathVariable int id) throws UserNotFoundException{
		return ResponseEntity.ok(userService.getUser(id));
	}
	
	/*
	 * @RequestBody được sử dụng để ánh xạ nội dung của request body vào đối tượng
	 * User. Khi một POST request được gửi đến "/api/users/signup" với dữ liệu JSON
	 * trong request body, Spring Boot sẽ tự động chuyển đổi dữ liệu JSON đó thành
	 * một đối tượng User và truyền nó vào phương thức saveUser
	 */
	
	/*
	 * Trong Spring Framework, ResponseEntity là một lớp được sử dụng để đại diện
	 * cho toàn bộ HTTP response, bao gồm cả status code, headers, và body. Nó cung
	 * cấp một cách linh hoạt để xây dựng và trả về các phản hồi HTTP từ các phương
	 * thức trong các controller.
	 */
	
	/*
	 * Đúng, nếu tham số của phương thức không đáp ứng các ràng buộc được đặt bởi
	 * các annotation kiểm tra (ví dụ: @NotNull, @Size, @Pattern, ...) và nếu bạn sử
	 * dụng　
	 * @Valid để kích hoạt quá trình kiểm tra, thì ngoại lệ
	 * MethodArgumentNotValidException sẽ được ném ra trước khi phương thức thực sự
	 * được gọi. Điều này có nghĩa là nếu dữ liệu đầu vào không hợp lệ, phương thức
	 * sẽ không được thực thi và quá trình xử lý sẽ chuyển sang xử lý ngoại lệ. Quá
	 * trình xử lý sẽ chuyển sang phương thức xử lý ngoại lệ, nếu có, được đánh dấu
	 * bởi @ExceptionHandler hoặc được xử lý mặc định của Spring.
	 */
}
