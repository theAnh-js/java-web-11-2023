package com.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.springboot.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
 
	@Autowired
	private UserService userService;
	
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() { // thêm static để tránh lỗi Spring Security circular bean dependency
														    // giữa class này với class ServiceImpl
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder(passwordEncoder()); // mã hóa mật khẩu người dùng và so sánh với mật khẩu đã lưu trữ trong cơ sở dữ liệu
		
		return auth;  
	}
	//DaoAuthenticationProvider là một trong những AuthenticationProvider chính trong Spring Security 
	//và được sử dụng để xác thực người dùng dựa trên thông tin từ một UserDetailsService. 
	//Khi quá trình xác thực thành công, DaoAuthenticationProvider sẽ 
	//tạo ra một đối tượng Authentication.
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
		
		// Khi có một yêu cầu xác thực, AuthenticationManager sẽ sử dụng DaoAuthenticationProvider 
		// để kiểm tra xác thực người dùng.
	}
	
	//1 - Cung Cấp Thông Tin Người Dùng:
	//	Khi một yêu cầu xác thực được gửi (ví dụ: khi người dùng cố gắng đăng nhập), DaoAuthenticationProvider sẽ gọi dịch vụ 
	//	người dùng (được đặt bởi setUserDetailsService) để lấy thông tin về người dùng từ cơ sở dữ liệu hoặc nguồn dữ liệu khác.
	//	UserDetailsService phải cung cấp một đối tượng UserDetails chứa thông tin về người dùng như tên đăng nhập, mật khẩu đã được mã hóa, 
	//	và danh sách các quyền hạn (roles) của người dùng.
	//2 -	Sử Dụng trong Quá Trình Xác Thực:
	//	Khi thông tin người dùng đã được trả về từ UserDetailsService, DaoAuthenticationProvider sử dụng nó để kiểm tra 
	//	mật khẩu và tạo đối tượng Authentication nếu thông tin đăng nhập hợp lệ.
	//	Authentication object sẽ được sử dụng để xác định quyền truy cập của người dùng trong ứng dụng.
	//3 -	Customization của Quá Trình Xác Thực:
	//	setUserDetailsService cho phép bạn tùy chỉnh cách mà thông tin người dùng được lấy và sử dụng trong quá trình xác thực.
	//	Bạn có thể cung cấp một implemention của UserDetailsService để đáp ứng theo cách riêng của bạn, chẳng hạn như lấy thông tin 
	//	người dùng từ cơ sở dữ liệu, API, hoặc nguồn dữ liệu khác.
	
	// Nếu đăng nhập thành công -> đối tượng Authentication được tạo ra -> chạy vào configure http
	// nếu quá trình xác thực thất bại và không tạo ra một đối tượng Authentication, 
	// thì quá trình điều hướng sẽ không chạy vào phương thức configure(HttpSecurity http) được cấu hình cho formLogin().
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()  
				.antMatchers("/registration").permitAll()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated()
		    .and()
				.formLogin()
				.loginPage("/login")
				.permitAll()
			.and()
				.logout()
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login?logout")
				.permitAll()
			.and()
				.exceptionHandling()
					.accessDeniedPage("/access-denied");// Đặt trang cho trường hợp truy cập bị từ chối
	
		
		/*
		 * .authorizeRequests(): Bắt đầu cấu hình quy tắc cho việc xác thực và phân quyền truy cập.
		 * .antMatchers(): Xác định các mẫu URL và ánh xạ chúng với quyền truy cập.
			.permitAll(): Cho phép mọi người truy cập mà không cần xác thực.
			.hasRole("ADMIN"): Yêu cầu người dùng có vai trò "ADMIN".
			.anyRequest().authenticated(): Yêu cầu xác thực cho bất kỳ yêu cầu nào khác.
			.formLogin(): Cho phép xác thực qua form đăng nhập.
			.loginPage("/login"): Đặt trang đăng nhập tùy chỉnh (nếu có).
			.logout(): Cấu hình xử lý đăng xuất.
			.logoutUrl("/logout"): Đường dẫn xử lý đăng xuất.
			.logoutSuccessUrl("/login?logout"): Đường dẫn sau khi đăng xuất thành công.
			.permitAll(): Cho phép mọi người truy cập đường dẫn đăng xuất.
		 * .logout(): Bắt đầu cấu hình xử lý đăng xuất.
		 * 
		 * .invalidateHttpSession(true): Xác định xem có nên hủy bỏ (invalidate) phiên
		 * làm việc HTTP của người dùng sau khi đăng xuất hay không. Nếu được đặt thành
		 * true, thì thông tin về phiên làm việc của người dùng sẽ bị hủy khi họ đăng
		 * xuất.
		 * 
		 * .clearAuthentication(true): Đặt xem có nên xóa thông tin về xác thực của
		 * người dùng sau khi đăng xuất hay không. Nếu được đặt thành true, thông tin về
		 * xác thực sẽ bị xóa sau khi người dùng đăng xuất.
		 * 
		 * .logoutRequestMatcher(new AntPathRequestMatcher("/logout")): Xác định đường
		 * dẫn mà Spring Security sẽ sử dụng để xác định yêu cầu đăng xuất. Trong trường
		 * hợp này, đường dẫn "/logout" sẽ được sử dụng để kích hoạt xử lý đăng xuất.
		 * 
		 * .logoutSuccessUrl("/login?logout"): Đặt đường dẫn mà người dùng sẽ được
		 * chuyển đến sau khi đăng xuất thành công. Trong trường hợp này, người dùng sẽ
		 * được chuyển đến "/login?logout" sau khi đăng xuất.
		 * 
		 * .permitAll(): Cho phép mọi người dùng, kể cả những người dùng chưa đăng nhập,
		 * truy cập đường dẫn xử lý đăng xuất. Điều này đảm bảo rằng mọi người dùng có
		 * thể truy cập trang đăng xuất mà không cần xác thực.
		 */
	}
	
	/*
	 * Thằng bean BCryptPasswordEncoder được định nghĩa trong class
	 * SecurityConfiguration, mà class SecurityConfiguration lại có phụ thuộc là
	 * thằng UserService(chính là UserServiceImple), Trong khi thằng UserServiceImpl
	 * lại chứa phụ thuộc là thằng BCryptPasswordEncoder -> Spring container không
	 * biết nên khởi tạo bên nào trước -> vòng lặp phụ thuộc
	 */
	
	/*
	 * Khi bạn đặt một phương thức @Bean là static trong một cấu hình Spring, đó là
	 * một cách để giải quyết vấn đề của phụ thuộc vòng lặp (circular dependency)
	 * giữa các bean trong ứng dụng Spring của bạn. Điều này thường xuyên xảy ra khi
	 * có sự phụ thuộc giữa các bean mà khi Spring cố gắng tạo chúng, chúng tạo ra
	 * một chuỗi vòng lặp không thể giải quyết được.
	 * 
	 * Bằng cách đặt phương thức @Bean là static, bạn làm cho phương thức đó trở
	 * thành một phần của lớp chứ không phải của đối tượng cụ thể được tạo ra bởi
	 * Spring Container. Điều này có nghĩa là bạn có thể gọi phương thức @Bean trực
	 * tiếp từ tên lớp mà không cần phải tạo một thực thể của lớp đó.
	 * 
	 * Khi bạn sử dụng @Bean static như trên, bạn có thể gọi
	 * AppConfig.passwordEncoder() trực tiếp mà không cần tạo một đối tượng
	 * AppConfig. Điều này giúp tránh phụ thuộc vòng lặp trong trường hợp nhất định.
	 * 
	 * Tuy nhiên, như đã đề cập trước đó, việc sử dụng static cũng mang theo một số
	 * vấn đề về thiết kế và kiểm soát mã, do đó, nên được sử dụng cẩn thận và chỉ
	 * khi không có giải pháp thiết kế khác có sẵn.
	 */
	
	/*
	 * A simple solution is to take out the PasswordEncoder bean definition out of
	 * SecurityConfiguration. 
	 */
	
	// mặc dù ở project này không dùng đến các đường dẫn như của image, js,css,...
	// nhưng code vào đây cho nhớ.
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**"); 
	}
	// ta cũng có thể config nó ngay trong file config http, dùng permitAll():  
		// .authorizeRequests()
	    // .antMatchers("/images/**", "/js/**", "/webjars/**").permitAll()
}
