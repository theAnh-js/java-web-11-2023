package com.springboot.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UserGenderValidator.class) // ta tự tạo ra ràng buộc UserGenderValidator
public @interface ValidateUserGender {
	
	public String message() default "Giới tính không hợp lệ. Bạn chỉ nên điền Male, Female hoặc Another";
	// Ta tự làm ra cái message này giống như message của các annotation
	// có sẵn vậy, kiểu:
	// 	@NotBlank(message = "username không được để trống")
	//	private String name;
	// thì annotation của ta sẽ là @ValidateUserGender, hehe
	
	Class<?>[] groups() default{};
	
	Class<? extends Payload>[] payload() default {}; 
	
	/*
	 * message: Chứa thông báo lỗi mặc định khi giá trị không hợp lệ. groups: Cho
	 * phép bạn gán ràng buộc vào một hoặc nhiều nhóm kiểm tra. Một kiểm tra chỉ
	 * được thực hiện nếu nó thuộc ít nhất một nhóm trong danh sách. payload: Cho
	 * phép bạn gắn thông tin phụ trợ với ràng buộc.
	 * 
	 * các thuộc tính như message, groups, và payload để định nghĩa cách thông báo
	 * lỗi và cấu hình khác liên quan đến ràng buộc. UserGenderValidator là một lớp
	 * cụ thể thực hiện logic kiểm tra hợp lệ cho ràng buộc này.
	 */	

	/*
	 * Đúng, nếu bạn không khai báo groups và payload trong định nghĩa của một ràng
	 * buộc (annotation), chương trình vẫn hoạt động bình thường. Cả hai thuộc tính
	 * này đều là tùy chọn và có giá trị mặc định là một mảng trống ({}).
	 */
	
}


/*
 * @Target(ElementType.FIELD, ElementType.PARAMETER) để chỉ ra rằng annotation
 * này có thể được áp dụng cho trường (field) và tham số (parameter) trong
 * phương thức.
 */

/*  VD: 
 * public class User {
 * 
 * @ValidateUserGender private String gender;  --> cho Field
 * 
 * // Constructors, getters, setters, etc. }
 * 
 * Đây là cho Parameter
 
 * public class UserService { public void updateUser(@ValidateUserGender String
 * newGender) { // Xử lý logic với tham số newGender } }
 */

/*
 * Khi một annotation được đánh dấu với @Retention(RetentionPolicy.RUNTIME), nó
 * có thể được đọc và xử lý tại thời điểm chạy của chương trình. Điều này có ý
 * nghĩa quan trọng khi bạn muốn sử dụng Java Reflection để kiểm tra hoặc thực
 * hiện các hành động dựa trên thông tin trong annotation.
 */

/*
 * Annotation @Documented không có ảnh hưởng đến việc thực thi chương trình, mà
 * chỉ là một annotation marker (đánh dấu) để chỉ định rằng annotation được đánh
 * dấu bởi nó sẽ được tài liệu hóa trong tài liệu Java API.
 */

/*
 * Annotation @Constraint được sử dụng trong hệ thống validation của Spring để
 * xác định một ràng buộc (constraint) và lớp cụ thể (được chỉ định bởi
 * validatedBy) để thực hiện kiểm tra hợp lệ. Trong ví dụ
 * này, @ValidateUserGender là một annotation định nghĩa một ràng buộc và
 * UserGenderValidator là lớp thực hiện kiểm tra hợp lệ cho ràng buộc đó.
 */