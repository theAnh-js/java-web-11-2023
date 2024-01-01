package com.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	
	@Bean
	public UserDetailsService userDetailsService() {
		// Hard Code
//		UserDetails admin = User.withUsername("tony")
//				.password(encoder.encode("123"))
//				.roles("ADMIN")
//				.build();
		
//		UserDetails user = User.withUsername("john")
//				.password(encoder.encode("789"))
//				.roles("USER")
//				.build();	
	
//		return new InMemoryUserDetailsManager(admin, user);
		
		// from Database
		
		return new UserInfoDetailService();
		
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf().disable()
				.authorizeHttpRequests()
				.requestMatchers("/products/welcome", "/products/new").permitAll()
			.and()
				.authorizeHttpRequests().requestMatchers("/products/**").authenticated()
			.and()
				.formLogin()
			.and().build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); 
	}
	
	//giải quyết lỗi ở browser: No AuthenticationProvider found for org.springframework.security.authentication.UsernamePasswordAuthenticationToken
	@Bean
	public AuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userDetailsService());
		auth.setPasswordEncoder(passwordEncoder());
		
		return auth;
	}
	
	/*
	 * UsernamePasswordAuthenticationToken là loại token mặc định được Spring
	 * Security sử dụng để đại diện cho xác thực dựa trên tên người dùng và mật
	 * khẩu. Khi chúng ta cố gắng xác thực bằng tên người dùng và mật khẩu, Spring
	 * Security tìm kiếm AuthenticationProvider thích hợp để xử lý quá trình xác
	 * thực.
	 */
}
