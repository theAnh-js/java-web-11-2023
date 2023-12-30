package com.springboot.dto;

import com.springboot.validation.ValidateUserGender;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
public class UserRequest {
	
	
	private int userId;
	
	@NotBlank(message = "username không được để trống")
	private String name;
	
	@Email(message = "email chưa đúng định dạng")
	private String mail;
	
	@NotNull
	@Pattern(regexp = "^\\d{10}$", message = "Số điện thoại chưa đúng định dạng")
	private String mobile;
	
	// @ValidateUserGender(message = "Giới tính chưa hợp lệ !") message ở đây sẽ ghi để message bên file tạo annotation nếu ta thêm message=... ở đây.
	@ValidateUserGender()    // đây là annotation tự xây dựng, ta cũng có thê viết -> @ValidateUserGender(message = "Giới tính chưa hợp lệ !")
	private String gender;
	
	@Min(value = 18, message = "Bạn chưa đủ tuổi để đăng ký")
	@Max(60)
	private int age;
	
	@NotBlank(message = "Vui lòng nhập đầy đủ quốc tịch của bạn")
	private String nationality;

}
