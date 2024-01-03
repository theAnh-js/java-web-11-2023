/*
 * package com.laptrinhspringboot.security;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.security.core.userdetails.UsernameNotFoundException;
 * import org.springframework.stereotype.Service;
 * 
 * import com.laptrinhspringboot.model.User; import
 * com.laptrinhspringboot.repository.UserRepository;
 * 
 * import jakarta.transaction.Transactional;
 * 
 * @Service
 * 
 * @Transactional public class MyUserDetailsService implements
 * UserDetailsService{
 * 
 * @Autowired private UserRepository userRepository;
 * 
 * @Override public UserDetails loadUserByUsername(String username) throws
 * UsernameNotFoundException {
 * 
 * User user = userRepository.findByUsername(username); if(user == null) { throw
 * new UsernameNotFoundException("Tên tài khoản không tồn tại"); } return new
 * MyUserDetails(user); }
 * 
 * 
 * 
 * 
 * String username: Là tên đăng nhập mà người dùng nhập vào form đăng nhập.
 * 
 * UserDetails: Là một giao diện định nghĩa thông tin về người dùng mà Spring
 * Security cần để thực hiện xác thực và kiểm tra quyền.
 * 
 * UsernameNotFoundException: Là một ngoại lệ sẽ được ném ra nếu không tìm thấy
 * người dùng với tên đăng nhập tương ứng.
 * 
 * Trong quá trình xử lý đăng nhập, sau khi người dùng nhập tên đăng nhập và mật
 * khẩu, Spring Security sẽ gọi phương thức loadUserByUsername của
 * UserDetailsService để lấy thông tin người dùng từ nguồn dữ liệu. Bạn cần
 * triển khai phương thức này để cung cấp thông tin về người dùng, bao gồm mật
 * khẩu được mã hóa và danh sách các quyền (hoặc vai trò).
 * 
 * 
 * }
 */