/*
 * package com.laptrinhspringboot.security;
 * 
 * import java.util.ArrayList; import java.util.Collection; import
 * java.util.List;
 * 
 * import org.springframework.security.core.GrantedAuthority; import
 * org.springframework.security.core.authority.SimpleGrantedAuthority; import
 * org.springframework.security.core.userdetails.UserDetails;
 * 
 * import com.laptrinhspringboot.model.Role; import
 * com.laptrinhspringboot.model.User;
 * 
 * public class MyUserDetails implements UserDetails {
 * 
 * private static final long serialVersionUID = 6596537351693031319L;
 * 
 * 
 * private User user;
 * 
 * public MyUserDetails(User user) { this.user = user; }
 * 
 * // lấy ra quyền của user
 * 
 * @Override public Collection<? extends GrantedAuthority> getAuthorities() {
 * List<GrantedAuthority> grantedAuthoriry = new ArrayList<>(); for (Role role :
 * user.getRoleList()) { grantedAuthoriry.add(new
 * SimpleGrantedAuthority(role.getName())); } return grantedAuthoriry; }
 * 
 * @Override public String getPassword() { return user.getPassword(); }
 * 
 * @Override public String getUsername() { return user.getUsername(); }
 * 
 * 
 * 
 * SimpleGrantedAuthority chủ yếu được sử dụng để định danh quyền. Khi người
 * dùng được xác thực và được cấp quyền, danh sách các GrantedAuthority được trả
 * về từ UserDetails hoặc Authentication. Mỗi SimpleGrantedAuthority trong danh
 * sách biểu diễn một quyền cụ thể.
 * 
 * 
 * 
 * Chú ý rằng trong Spring Security, quyền thường được đặt tên theo quy ước
 * "ROLE_..." (ví dụ: "ROLE_USER", "ROLE_ADMIN"). Điều này giúp Spring Security
 * hiểu rằng đó là quyền khi xử lý xác thực và kiểm tra quyền. Tuy nhiên, bạn có
 * thể sử dụng bất kỳ chuỗi nào để đại diện cho quyền mà bạn muốn.
 * 
 * 
 * 
 * @Override public boolean isAccountNonExpired() { // TODO Auto-generated
 * method stub return true; }
 * 
 * @Override public boolean isAccountNonLocked() { // TODO Auto-generated method
 * stub return true; }
 * 
 * @Override public boolean isCredentialsNonExpired() { // TODO Auto-generated
 * method stub return true; }
 * 
 * @Override public boolean isEnabled() { // TODO Auto-generated method stub
 * return true; }
 * 
 * }
 */