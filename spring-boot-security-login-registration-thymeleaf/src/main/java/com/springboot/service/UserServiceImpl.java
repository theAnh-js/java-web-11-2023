package com.springboot.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.model.Role;
import com.springboot.model.User;
import com.springboot.repository.UserRepository;
import com.springboot.web.dto.UserRegistrationDto;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	/*
	 * public UserServiceImpl(UserRepository userRepository) { this.userRepository =
	 * userRepository; }
	 */
	
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	
	
	@Override
	public User save(UserRegistrationDto registrationDto) {
		
		User user = new User();
		user.setFirstName(registrationDto.getFirstName());
		user.setLastName(registrationDto.getLastName());
		user.setPassword(pwdEncoder.encode(registrationDto.getPassword())); // mã hóa password trước khi lưu vào db
		user.setEmail(registrationDto.getEmail());
		user.setRoles(Arrays.asList(new Role("ROLE_USER")));
		
		return  userRepository.save(user);
	}

	//  trả về đối tượng UserDetails
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByEmail(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("Tài khoản không tồn tại.");
		}
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
		// ở đây ta truyền vào tham số username của User của spring với giá trị là của email
		// Nên nếu thông qua đối tượng Principal để lấy thông tin của người dùng thì ta cần dùng username,
		// ví dụ như ở bên index.html -> Xin chào <span sec:authentication="principal.username"></span>
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		/*　Cách 1: dùng stream
		 * List<SimpleGrantedAuthority> authorityList = roles.stream().map(role -> new
		 * SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		 */
		
		// cách 2: dùng foreach
		List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
		for(Role role : roles) {
			authorityList.add(new SimpleGrantedAuthority(role.getName()));
		}
		
		return authorityList;		
	}
	

	/*
	 * Phương thức loadUserByUsername là một phương thức quan trọng trong Spring
	 * Security và thường được triển khai trong interface UserDetailsService.
	 * Interface này cung cấp một cách để tìm kiếm một người dùng trong hệ thống dựa
	 * trên tên người dùng (username) và trả về một đối tượng UserDetails (một
	 * interface mô tả thông tin về người dùng).
	 * 
	 * Đối tượng UserDetails chứa thông tin cần thiết để thực hiện quá trình xác
	 * thực (authentication) và quyết định xem một người dùng có quyền truy cập vào
	 * ứng dụng hay không. Sau khi tìm kiếm thành công, phương thức trả về một đối
	 * tượng UserDetails. Đối tượng này chứa thông tin như tên người dùng, mật khẩu
	 * (đã được mã hóa), và các quyền (roles) mà người dùng có.
	 */
	
	/*
	 * return new
	 * org.springframework.security.core.userdetails.User(user.getEmail(),
	 * user.getPassword(), mapRolesToAuthorities(user.getRoles()));: Nếu người dùng
	 * được tìm thấy, bạn trả về một đối tượng UserDetails. Trong đối tượng này,
	 * thông tin về mật khẩu của người dùng có thể được lấy từ user.getPassword().
	 * 
	 * Trong nhiều trường hợp, kiểm tra mật khẩu thường xuyên được thực hiện bởi
	 * Spring Security mà không cần phải làm thêm trong mã nguồn. Spring Security sẽ
	 * tự động so sánh mật khẩu được cung cấp bởi người dùng với mật khẩu đã lưu trữ
	 * trong cơ sở dữ liệu. Nếu chúng không khớp, quá trình xác thực sẽ thất bại và
	 * người dùng sẽ không được phép đăng nhập.
	 */
}
