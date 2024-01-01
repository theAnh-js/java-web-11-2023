package com.springboot.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.springboot.entity.UserInfo;

public class UserInfoUserDetails implements UserDetails{

	private String name;
	private String password;
	private List<GrantedAuthority> authorities;
	
	// chuyển user lấy từ database ở class UserInfoDetailService
	// sang UserInfoUserDetails bằng constructor.
	
	public UserInfoUserDetails(UserInfo userInfo) {
		this.name = userInfo.getName();
		this.password = userInfo.getPassword();
		this.authorities = Arrays.stream(userInfo.getRole().split(","))
				.map(SimpleGrantedAuthority::new) // bằng với .map(role -> new SimpleGrantedAuthority(role))
				.collect(Collectors.toList());
	}
	/*
	 * toán tử :: để tham chiếu đến constructor của lớp SimpleGrantedAuthority. Nó
	 * tương đương với việc sử dụng một biểu thức lambda để tạo một đối tượng mới từ
	 * constructor của lớp
	 */
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {	
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.name;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
