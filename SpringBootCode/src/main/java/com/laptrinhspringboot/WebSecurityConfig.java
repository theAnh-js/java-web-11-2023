/*
 * package com.laptrinhspringboot;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.config.annotation.authentication.builders.
 * AuthenticationManagerBuilder; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfigurerAdapter; import
 * org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 * 
 * import com.laptrinhspringboot.security.MyUserDetailsService;
 * 
 * @Configuration
 * 
 * @EnableWebSecurity public class WebSecurityConfig extends
 * WebSecurityConfigurerAdapter {
 * 
 * 
 * @Autowired private MyUserDetailsService myUserDetailsService;
 * 
 * @Bean public BCryptPasswordEncoder pwdEncoder() { return new
 * BCryptPasswordEncoder(); }
 * 
 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
 * Exception {
 * auth.userDetailsService(myUserDetailsService).passwordEncoder(pwdEncoder());
 * }
 * 
 * @Override protected void configure(HttpSecurity http) throws Exception {
 * http.csrf().disable();
 * http.authorizeHttpRequests().and().exceptionHandling().accessDeniedPage(
 * "/403"); http .formLogin() .and() .authorizeHttpRequests()
 * .antMatchers("/").permitAll() .antMatchers("/list/**").hasAnyRole("EMPLOYEE",
 * "ADMIN") .anyRequest().authenticated(); }
 * 
 * }
 */