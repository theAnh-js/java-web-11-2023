package com.fuku.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fuku.connection.DBConnect;
import com.fuku.model.CategoryModel;

public class CategoryDAO {

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public List<CategoryModel> findAllCategories() {

		List<CategoryModel> list = new ArrayList<>();
		conn = DBConnect.getConnection();
		String sql = "select * from category;";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				CategoryModel categoryModel = new CategoryModel();
				categoryModel.setCategoryID(rs.getInt(1));
				categoryModel.setCategoryName(rs.getString(2));
				categoryModel.setIcon(rs.getString(3));

				list.add(categoryModel);
			}
			return list;
		} catch (SQLException e) {
			return null;
		}
	}
}
