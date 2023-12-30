package com.springboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
@Entity
@Table(name="user")
public class UserEntity {

	@Id
	@GeneratedValue    //không có strategy = GenetionType.IDENTITY là id được tạo ngẫu nhiên không theo thứ tự
	private int userId;
	
	private String name;
	private String mail;
	private String mobile;
	private String gender;
	private int age;
	private String nationality;
	
	
	
}
