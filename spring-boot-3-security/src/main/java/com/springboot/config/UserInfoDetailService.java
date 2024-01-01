package com.springboot.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.springboot.entity.UserInfo;
import com.springboot.repository.UserInfoRepository;

@Component
public class UserInfoDetailService implements UserDetailsService {

	@Autowired
	private UserInfoRepository userInfoRepository;
	
	// Convert từ user lấy từ database -> UserDetails
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<UserInfo> userInfo = userInfoRepository.findByName(username);
		
		return userInfo.map(UserInfoUserDetails::new) 
			.orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy " + username));
		
		// tương đương khi dùng lambda thay cho ::new
		//return user.map(userInfo -> new UserInfoUserDetails(user))
		// -> truyền user từ database vào constructor của UserInfoUserDetails 
		// để chuyển nó thành UserInfoUserDetails(chính là đối tượng UserDetails)
		// vì nó đang implements thằng này.
	}
	
	/*
	 * phương thức này được sử dụng trong quá trình xác thực (authentication) của
	 * Spring Security. Khi một người dùng cố gắng đăng nhập với một username,
	 * phương thức này sẽ được gọi để tìm kiếm thông tin người dùng từ cơ sở dữ liệu
	 * và chuyển đổi nó thành một đối tượng UserDetails để Spring Security sử dụng
	 * trong quá trình xác thực.
	 */

}
