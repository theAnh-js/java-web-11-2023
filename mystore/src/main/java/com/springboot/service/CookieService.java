package com.springboot.service;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CookieService {

	@Autowired
	HttpServletRequest request;

	@Autowired
	HttpServletResponse response;

	public Cookie create(String name, String value, int days) {
		
		String encodeValue = Base64.getEncoder().encodeToString(value.getBytes());
		Cookie cookie = new Cookie(name, encodeValue);
		cookie.setMaxAge(days * 24 * 60 * 60);
		cookie.setPath("/");
		response.addCookie(cookie);
		return cookie;
	}

	/*
	 * setPath("/") được sử dụng để đặt giá trị của thuộc tính "Path" cho cookie.
	 * Thuộc tính "Path" xác định đường dẫn trên server mà cookie sẽ được gửi đến.
	 * Trong trường hợp cookie.setPath("/"), nó có nghĩa là cookie sẽ được gửi đến
	 * mọi đường dẫn trên domain hiện tại. Nói cách khác, cookie sẽ có hiệu lực cho
	 * toàn bộ trang web, không chỉ riêng cho một phần cụ thể nào đó.
	 */

	public Cookie read(String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals(name)) {
					String decodedValue = 
							new String(Base64.getDecoder().decode(c.getValue()));
					c.setValue(decodedValue);
					return c;
				}
			}
		}
		return null;
	}

	public void delete(String name) {

	}
	
	// phương thức decode ở dòng 45 trả về 1 mảng byte nên để chuyển sang string
	// mà các giá trị vẫn nguyên dạng -> new String();
	// Nếu dùng .toString -> trả về chuỗi đại diện cho mảng đối tượng byte,
	// chứ không trả về giá trị thực.
}
