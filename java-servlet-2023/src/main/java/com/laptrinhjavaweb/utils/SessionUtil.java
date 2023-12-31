package com.laptrinhjavaweb.utils;

import javax.servlet.http.HttpServletRequest;

public class SessionUtil {
	
	// để tránh new nhiều đối tượng giống nhau 
	// mà 1 đối tượng có thể sử dụng lại nhiều lần: 
	// (đây là một dạng design pattern)
	private static SessionUtil sessionUtil = null;
	
	public static SessionUtil getInstance(){
		if(sessionUtil == null) {
			sessionUtil = new SessionUtil();
		}
		return sessionUtil;
	}
	
	// Cần có tham số HttpServletRequest để tạo và get, delete session.
	public void putValue(HttpServletRequest req, String key, Object value) {
		req.getSession().setAttribute(key, value); 
		// tạo session - getSession() và set key,value vào session.
		// nếu có session rồi và chưa hết hiệu lực thì không tạo mới nữa mà dùng cái đã có.
	}
	
	public Object getValue(HttpServletRequest req, String key) {
		return req.getSession().getAttribute(key);
	}
	
	public void removeValue(HttpServletRequest req, String key) {
		req.getSession().removeAttribute(key);
	}
}
