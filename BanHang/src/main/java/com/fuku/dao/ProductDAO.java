package com.fuku.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fuku.connection.DBConnect;
import com.fuku.model.ProductModel;

public class ProductDAO {

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	// Hien thi 4 san pham moi nhat
	public List<ProductModel> getTop4Product() {

		// Khai bao list de luu danh sach san pham
		List<ProductModel> list = new ArrayList<>();

		// khai bao chuoi truy van
		String sql = "select * from product order by ProductId desc limit 4";

		try {
			// ket noi vơi database
			conn = DBConnect.getConnection();
			
			// truyen cau lenh truy van
			ps = conn.prepareStatement(sql);
			
			// chay query va nhan ket qua trong doi tuong ResultSet
			rs = ps.executeQuery();

			while (rs.next()) {

				ProductModel product = new ProductModel();

				product.setProductID(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setDescription(rs.getString(3));
				product.setPrice(rs.getInt(4));
				product.setImageLink(rs.getString(5));
				product.setCategoryID(rs.getInt(6));
				product.setSellerID(rs.getInt(7));
				product.setAmount(rs.getInt(8));

				list.add(product);
			}

		} catch (Exception e) {
			return null;
		}

		return list;
	}

	public ProductModel getBestSellerProduct() {

		String sql = "select * from product where Amount = (select MAX(Amount) from product)";
		try {
			conn = DBConnect.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				ProductModel product = new ProductModel();

				product.setProductID(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setDescription(rs.getString(3));
				product.setPrice(rs.getInt(4));
				product.setImageLink(rs.getString(5));
				product.setCategoryID(rs.getInt(6));
				product.setSellerID(rs.getInt(7));
				product.setAmount(rs.getInt(8));

				return product;
			}
		} catch (SQLException e) {
			return null;
		}

		return null;
	}

	public List<ProductModel> findTop4BestProduct() {
		List<ProductModel> list = new ArrayList<>();

		String sql = "select * from Product order by Amount desc limit 4";
		conn = DBConnect.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				ProductModel product = new ProductModel();
				product.setProductID(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setDescription(rs.getString(3));
				product.setPrice(rs.getInt(4));
				product.setImageLink(rs.getString(5));
				product.setCategoryID(rs.getInt(6));
				product.setSellerID(rs.getInt(7));
				product.setAmount(rs.getInt(8));

				list.add(product);
			}

			return list;
		} catch (SQLException e) {
			return null;
		}
	}

	public List<ProductModel> findAllProduct() {
		List<ProductModel> list = new ArrayList<>();

		String sql = "select * from Product";
		conn = DBConnect.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				ProductModel product = new ProductModel();
				product.setProductID(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setDescription(rs.getString(3));
				product.setPrice(rs.getInt(4));
				product.setImageLink(rs.getString(5));
				product.setCategoryID(rs.getInt(6));
				product.setSellerID(rs.getInt(7));
				product.setAmount(rs.getInt(8));

				list.add(product);
			}

			return list;
		} catch (SQLException e) {
			return null;
		}
	}

	public ProductModel findLastProduct() {

		String sql = "select * from product order by ProductID desc limit 1";
		try {
			conn = DBConnect.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				ProductModel product = new ProductModel();

				product.setProductID(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setDescription(rs.getString(3));
				product.setPrice(rs.getInt(4));
				product.setImageLink(rs.getString(5));
				product.setCategoryID(rs.getInt(6));
				product.setSellerID(rs.getInt(7));
				product.setAmount(rs.getInt(8));

				return product;
			}
		} catch (SQLException e) {
			return null;
		}

		return null;
	}

	public List<ProductModel> findAllProductByCategoryId(int id) {

		List<ProductModel> list = new ArrayList<>();
		String sql = "select * from product where CategoryID = ?";

		conn = DBConnect.getConnection();
		try {
			ps = conn.prepareStatement(sql);

			// Trước khi executeQuery() thì set giá trị vào tham số ?
			// vào sql.
			ps.setInt(1, id);

			rs = ps.executeQuery();
			while (rs.next()) {

				ProductModel product = new ProductModel();

				product.setProductID(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setDescription(rs.getString(3));
				product.setPrice(rs.getInt(4));
				product.setImageLink(rs.getString(5));
				product.setCategoryID(rs.getInt(6));
				product.setSellerID(rs.getInt(7));
				product.setAmount(rs.getInt(8));

				list.add(product);
			}

			return list;

		} catch (SQLException e) {
			return null;
		}
	}

	public ProductModel findProductById(int id) {

		ProductModel product = new ProductModel();
		String sql = "select * from product where ProductID = ?";

		conn = DBConnect.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			rs = ps.executeQuery();
			while (rs.next()) {
				product.setProductID(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setDescription(rs.getString(3));
				product.setPrice(rs.getInt(4));
				product.setImageLink(rs.getString(5));
				product.setCategoryID(rs.getInt(6));
				product.setSellerID(rs.getInt(7));
				product.setAmount(rs.getInt(8));
			}

			return product;

		} catch (SQLException e) {
			return null;
		}
	}

	public ProductModel findLastProductInEachCategory(List<ProductModel> listProduct) {

		if (listProduct.isEmpty()) {
			return null;
		}

		int maxID = listProduct.get(0).getProductID();

		for (ProductModel pro : listProduct) {
			int proID = pro.getProductID();
			maxID = Math.max(maxID, proID);
		}
		ProductModel product = findProductById(maxID);
		return product;
	}

	public List<ProductModel> findAllProductByString(String str) {

		List<ProductModel> list = new ArrayList<>();
		String sql = "SELECT * FROM product WHERE ProductName LIKE ?";
		// SELECT * FROM product WHERE ProductName LIKE '%nam%'

		conn = DBConnect.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			// Trước khi executeQuery() thì set giá trị vào tham số ?
			// vào sql.
			ps.setString(1, "%" + str + "%");
			rs = ps.executeQuery();
			while (rs.next()) {

				ProductModel product = new ProductModel();

				product.setProductID(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setDescription(rs.getString(3));
				product.setPrice(rs.getInt(4));
				product.setImageLink(rs.getString(5));
				product.setCategoryID(rs.getInt(6));
				product.setSellerID(rs.getInt(7));
				product.setAmount(rs.getInt(8));

				list.add(product);
			}

			return list;

		} catch (SQLException e) {
			return null;
		}
	}

	public int countAllProductByCategoryId(int id) {

		int productAmount = 0;
		String sql = "SELECT COUNT(*) AS totalProByID FROM product WHERE categoryID = ?";

		conn = DBConnect.getConnection();
		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, id);

			rs = ps.executeQuery();
			while (rs.next()) {

				productAmount = rs.getInt(1);
			}

			return productAmount;

		} catch (SQLException e) {
			return 0;
		}
	}

	public List<ProductModel> findAllProductForPaging(int page) {

		List<ProductModel> list = new ArrayList<>();
		String sql = "select * from product limit 3 offset ?"; // mỗi trang để limit 3 sản phẩm

		conn = DBConnect.getConnection();
		try {
			ps = conn.prepareStatement(sql);

			int offset = (page - 1) * 3; // tính ra thứ tự để lấy từ sản phẩm nào
			ps.setInt(1, offset);

			rs = ps.executeQuery();
			while (rs.next()) {
				ProductModel product = new ProductModel();
				product.setProductID(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setDescription(rs.getString(3));
				product.setPrice(rs.getInt(4));
				product.setImageLink(rs.getString(5));
				product.setCategoryID(rs.getInt(6));
				product.setSellerID(rs.getInt(7));
				product.setAmount(rs.getInt(8));

				list.add(product);
			}
			return list;
		} catch (SQLException e) {
			return null;
		}
	}

	// crud product
	public void edit(ProductModel product) {

		// xử lý logic CẦN NÉM SANG THẰNG SERVICE
		ProductModel oldProduct = findProductById(product.getProductID());

		oldProduct.setProductName(product.getProductName());
		if (product.getImageLink() != null) {
			// xoa anh cu
			String fileName = oldProduct.getImageLink();
			final String dir = "C:\\upload";
			File file = new File(dir + "/product/" + fileName);
			if (file.exists()) {
				file.delete();
			}

			oldProduct.setImageLink(product.getImageLink());
		} // xử lý logic

		String sql = "update product set ProductName = ?, Description = ?, Price = ?, imageLink = ?,"
				+ "CategoryID = ?, SellerID = ?, Amount = ? where productID  = ?";
		try {
			conn = DBConnect.getConnection();
			ps = conn.prepareStatement(sql);

			ps.setString(1, product.getProductName());
			ps.setString(2, product.getDescription());
			ps.setInt(3, product.getPrice());
			ps.setString(4, product.getImageLink());
			ps.setInt(5, product.getCategoryID());
			ps.setInt(6, product.getSellerID());
			ps.setInt(7, product.getAmount());
			ps.setInt(8, product.getProductID());

			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insert(ProductModel pro) {
		conn = DBConnect.getConnection();

		String sql = "insert into product(ProductName, Description, Price, imageLink, CategoryID,"
				+ "SellerID, Amount) values(?, ?, ?, ?, ?, ?, ?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, pro.getProductName());
			ps.setString(2, pro.getDescription());
			ps.setInt(3, pro.getPrice());
			ps.setString(4, pro.getImageLink());
			ps.setInt(5, pro.getCategoryID());
			ps.setInt(6, pro.getSellerID());
			ps.setInt(7, pro.getAmount());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	// để load lên sẵn 3 sản phẩm có id lớn nhất - nghĩa là 3 sản phẩm mới nhất
	public List<ProductModel> findTop6NewProduct() {
		List<ProductModel> list = new ArrayList<>();

		String sql = "select * from Product order by ProductID desc limit 6";
		conn = DBConnect.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				ProductModel product = new ProductModel();
				product.setProductID(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setDescription(rs.getString(3));
				product.setPrice(rs.getInt(4));
				product.setImageLink(rs.getString(5));
				product.setCategoryID(rs.getInt(6));
				product.setSellerID(rs.getInt(7));
				product.setAmount(rs.getInt(8));

				list.add(product);
			}

			return list;
		} catch (SQLException e) {
			return null;
		}
	}
	
	// for load ajax
	public List<ProductModel> getAllPage(int amount) {
		List<ProductModel> list = new ArrayList<>();
		String sql = "SELECT * FROM product\r\n"
				+ "		ORDER BY ProductID DESC\r\n"
				+ "		LIMIT 3 OFFSET ?"; // mỗi trang để limit 3 sản phẩm
		
		conn = DBConnect.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, amount);

			rs = ps.executeQuery();
			while (rs.next()) {
				ProductModel product = new ProductModel();
				product.setProductID(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setDescription(rs.getString(3));
				product.setPrice(rs.getInt(4));
				product.setImageLink(rs.getString(5));
				product.setCategoryID(rs.getInt(6));
				product.setSellerID(rs.getInt(7));
				product.setAmount(rs.getInt(8));

				list.add(product);
			}
			return list;
		} catch (SQLException e) {
			return null;
		}	
	}
	
	
	// for search and paging
	public List<ProductModel> productForSearchAndPaging(int currentPage, List<ProductModel> list, int limit){
		
		List<ProductModel> listResult = new ArrayList<>();
		
		int count = 0;
		int offset = (currentPage - 1) * limit;  // từ số trang và số sản phẩm giới hạn trong 1 trang tính ra số thứ tự của sản phẩm
												 // ứng với số trang đó.
		
		for(int i = 0; i < list.size(); i++) {
			if(i == offset) {  // lấy các sản phẩm từ i(từ offset)
				while(count != limit && i < list.size()) {
					listResult.add(list.get(i));
					i++;
					count++;
				}
				if(count == limit) {
					break;
				}
			}
		}
		return listResult;
	}

}
