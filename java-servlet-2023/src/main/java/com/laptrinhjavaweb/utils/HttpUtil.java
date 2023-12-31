package com.laptrinhjavaweb.utils;

import java.io.BufferedReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtil {

	private String value;

	public HttpUtil(String value) {
		this.value = value;
	}

	// Đây là phương thức generic,
	// trước kiểu dữ liệu trả về, cần để kiểu tham số nhận vào vào trong dấu <>
	public <T> T toModel(Class<T> tClass) {
		try {
			return new ObjectMapper().readValue(value, tClass);
			// ObjectMapper để đọc dữ liệu từ thằng value
			// và chuyển đổi(map nó) nó thành đối tượng của kiểu tClass

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// convert json sang string
	public static HttpUtil of(BufferedReader bufferedReader) {

		StringBuilder sb = new StringBuilder();
		String line;
		
		// từ BufferedReader đọc ra dữ liệu json theo từng dòng
		try {   
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new HttpUtil(sb.toString()); // vừa trả về 1 HttpUtil, vừa khởi tạo giá trị cho biến value
	}
}
