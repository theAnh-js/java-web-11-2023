package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import com.laptrinhjavaweb.dao.ICategoryDAO;
import com.laptrinhjavaweb.mapper.CategoryMapper;
import com.laptrinhjavaweb.model.CategoryModel;

public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO {

	@Override
	public List<CategoryModel> findAll() {

		String sql = "SELECT * FROM category";

		return query(sql, new CategoryMapper()); 
		
		// vì CategoryDAO kế thừa từ AbstractDAO nên có thể gọi phương thức query(access modifier là public) 
		// trực tiếp như này mà ko cần new đối tượng.
		
		// Vì đây là CategoryDAO nên nó sẽ làm việc với CategoryModel
		// --> truyền CategoryModel vào <> khi extends AbstractDAO
		// -> bên AbstractDAO sẽ hiểu ngầm rằng: 
					// public class AbstractDAO<T> implements GenericDAO<T> {...}
				// ==> public class AbstractDAO<CategoryModel> implements GenericDAO<CategoryModel> {...}
		
		// Do đó, kiểu dữ liệu trả về của List cũng là CategoryModel
		// Và đương nhiên là khi chạy vào hàm query bên AbstractDAO thì kiểu dữ liệu trả về của nó cũng là CategoryModel

		// Phân tích tiếp chỗ tham số new CategoryMapper():
		// Khi sang đến hàm query bên AbstractDAO thì sẽ được ngầm hiểu là:
		// RowMapper<CategoryModel> rowMapper
		// Nến interface RowMapper sẽ có T = CategoryModel
		// đồng thời, public class CategoryMapper implements RowMapper<CategoryModel>
		// nên hàm mapRow trong class CategoryMapper sẽ được gọi.
	}
}
