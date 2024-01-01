package com.springboot.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	private String email;
	
	private String password;
	
	/*
	 * Nếu foreign key không được tạo tự động trong database thì ta cần vào bảng
	 * user, role, users_role sửa engine thành InnoDB
	 */
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
			)
	private List<Role> roles = new ArrayList<>();
	
	/*
	 * fetch = FetchType.EAGER: Chỉ định chiến lược fetching cho mối quan hệ. EAGER
	 * có nghĩa là dữ liệu liên quan sẽ được tải ngay khi đối tượng chứa được tải.
	 * cascade = CascadeType.ALL: Chỉ định các thao tác cascade áp dụng cho các đối
	 * tượng liên quan. Trong trường hợp này, CascadeType.ALL có nghĩa là tất cả các
	 * thao tác cascade (ví dụ: lưu, cập nhật, xóa) sẽ được truyền từ đối tượng chứa
	 * đến các đối tượng liên quan.
	 */
	
	/*
	 * uniqueConstraints = @UniqueConstraint(columnNames = "email"): Xác định các
	 * ràng buộc duy nhất trên cơ sở dữ liệu. Trong trường hợp này, đang được thiết
	 * lập một ràng buộc duy nhất cho cột "email". Điều này có nghĩa là giá trị
	 * trong cột "email" phải là duy nhất trong toàn bộ bảng. Nếu bạn có nhiều bản
	 * ghi trong bảng, không có hai bản ghi nào có thể có giá trị "email" giống
	 * nhau.
	 */
			
	public User() {
	}

	public User(Long id, String firstName, String lastName, String email, String password, List<Role> roles) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
