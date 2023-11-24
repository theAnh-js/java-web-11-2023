package com.fuku.dao;

import java.io.File;
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
		String sql = "select * from category";
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

	// HÀM NÀY NÊN ĐỂ Ở SERVICE KHI LÀM THEO MÔ HÌNH THREE TIER LAYER, để tạm ở đây
	// đã :))
	// Lấy ra danh sách các categoryID của các sản phẩm trong danh sách sản phẩm
	// được lấy để paging
	public List<Integer> findCategoryIDInListProduct(List<ProductModel> listProduct) {

		List<Integer> listCategoryID = new ArrayList<>();

		for (ProductModel pro : listProduct) {
			int cateID = pro.getCategoryID();
			if (!listCategoryID.contains(cateID)) {
				listCategoryID.add(cateID);
			}
		}

		if (listCategoryID.isEmpty()) {
			return null;
		}
		return listCategoryID;
	}
	
	
	public CategoryModel get(int id) {

		conn = DBConnect.getConnection();
		String sql = "select * from category where CategoryID = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			rs = ps.executeQuery();
			while (rs.next()) {
				CategoryModel categoryModel = new CategoryModel();
				categoryModel.setCategoryID(rs.getInt(1));
				categoryModel.setCategoryName(rs.getString(2));
				categoryModel.setIcon(rs.getString(3));

				return categoryModel;
			}
		} catch (SQLException e) {
			return null;
		}

		return null;
	}

	public void insert(CategoryModel category) {

		conn = DBConnect.getConnection();
		String sql = "insert into category(CategoryName, icon) values(?, ?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, category.getCategoryName());
			ps.setString(2, category.getIcon());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.getMessage();
		}
	}

	public void edit(CategoryModel category) {

		// xử lý logic CẦN NÉM SANG THẰNG SERVICE
		CategoryModel oldCategory = get(category.getCategoryID());

		oldCategory.setCategoryName(category.getCategoryName());
		if (category.getIcon() != null) {
			// xoa anh cu
			String fileName = oldCategory.getIcon();
			final String dir = "C:\\upload";
			File file = new File(dir + "/category/" + fileName);
			if (file.exists()) {
				file.delete();
			}

			oldCategory.setIcon(category.getIcon());
		} // xử lý logic

		// giao tiếp với database
		String sql = "update category set CategoryName = ?, icon = ? where CategoryID = ?";
		try {
			conn = DBConnect.getConnection();
			ps = conn.prepareStatement(sql);

			ps.setString(1, oldCategory.getCategoryName());
			ps.setString(2, oldCategory.getIcon());
			ps.setInt(3, oldCategory.getCategoryID());

			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void delete(int id) {
		String sql = "delete from category where CategoryID=?";
		try {
			conn = DBConnect.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
