package com.laptrinhjavaweb.utils;import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class FormUtil {

	@SuppressWarnings({ "unchecked", "deprecation" })
	public static <T> T toModel(Class<T> tClass, HttpServletRequest req) throws InvocationTargetException {
		T obj = null;
		try {
			obj = tClass.newInstance();
			BeanUtils.populate(obj, req.getParameterMap());		
			//req.getParameterMap(): Phương thức này trả về một Map<String, String[]>
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			System.out.println(e.getMessage());
		}
		
		return obj;
	}
	
//	Phương thức populate từ Apache Commons BeanUtils được sử dụng để 
//	sao chép các giá trị từ một bản đồ tham số (thường là các tham số từ request) 
//	vào các thuộc tính của đối tượng obj. 
//	Điều này giúp tự động ánh xạ dữ liệu từ request vào đối tượng 
//	mà không cần phải thực hiện từng bước một.
	
//	Lưu ý: Việc sử dụng BeanUtils.populate có thể tạo ra các vấn đề an ninh 
//	nếu không được sử dụng cẩn thận, vì nó thực hiện ánh xạ các giá trị 
//	trực tiếp từ request vào các thuộc tính của đối tượng mà không 
//	kiểm tra tính hợp lệ của dữ liệu. 
//	Điều này có thể làm tăng rủi ro về các cuộc tấn công như "injection".
}
