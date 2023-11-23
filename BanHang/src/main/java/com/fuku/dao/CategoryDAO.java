package com.fuku.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fuku.connection.DBConnect;
import com.fuku.model.CategoryModel;
import com.fuku.model.ProductModel;

public class CategoryDAO {

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public List<CategoryModel> findAllCategories() {

		ProductDAO productDAO = new ProductDAO();

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
				categoryModel.setAmountOfProduct(productDAO.countAllProductByCategoryId(rs.getInt(1)));

				list.add(categoryModel);
			}
			return list;
		} catch (SQLException e) {
			return null;
		}
	}

	
	// Lấy ra danh sách các categoryID của các sản phẩm trong danh sách sản phẩm được lấy để paging
	public List<Integer> findCategoryIDInListProduct(List<ProductModel> listProduct) {

		List<Integer> listCategoryID = new ArrayList<>();

		for (ProductModel pro : listProduct) {
			int cateID = pro.getCategoryID();
			if (!listCategoryID.contains(cateID)) {
				listCategoryID.add(cateID);
			}
		}
		
		if(listCategoryID.isEmpty()) {
			return null;
		}
		return listCategoryID;
	}

}
