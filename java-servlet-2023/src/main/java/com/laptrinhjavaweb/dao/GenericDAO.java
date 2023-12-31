package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.mapper.RowMapper;

public interface GenericDAO<T> {

	// hàm select data từ database lên
	List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters);
	
	// hàm để cập nhật hoặc xóa
	void update (String sql, Object... parameters);
	
	// hàm để thêm mới
	Long insert(String sql, Object... parameters );
	
	int count(String sql, Object... parameters);
}
