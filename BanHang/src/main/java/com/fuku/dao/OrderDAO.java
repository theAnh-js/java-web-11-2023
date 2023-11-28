package com.fuku.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.fuku.connection.DBConnect;
import com.fuku.model.CartModel;
import com.fuku.model.ItemModel;
import com.fuku.model.UserModel;

public class OrderDAO {
	
	// insert order vào bảng order
	// lấy ra id của order vừa insert
	// add order đó vào orderdetail
	
	public void insertOrder(UserModel user, CartModel cart) {
		LocalDate currentDate = LocalDate.now();
		String date = currentDate.toString();
		
		String sql = "INSERT orders(date, cid, totalmoney) VALUES(?, ?, ?)";
		Connection conn = DBConnect.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, date);
			ps.setInt(2, user.getUserID());
			ps.setDouble(3, cart.getTotalMoney());

			ps.executeUpdate();			
			System.out.println("dong 35");
			// lay ra id cua order do luon
			String sql2 = "SELECT * FROM orders ORDER BY id DESC LIMIT 1";
			PreparedStatement ps2 = conn.prepareStatement(sql2);	
			ResultSet rs2 = ps2.executeQuery();
			
			if(rs2.next()) {	
				int orderID = rs2.getInt(1);
				System.out.println("dong 43");
				// insert các item trong cart của user đó vào orderdetail
				String sql3 = "INSERT orderdetail(OrderID, ProductID, Quantity, Price) VALUES(?, ?, ?, ?)";	
				for(ItemModel item : cart.getListItem() ) {
					PreparedStatement ps3 = conn.prepareStatement(sql3);
					ps3.setInt(1, orderID);
					ps3.setInt(2, item.getProduct().getProductID());
					ps3.setInt(3, item.getQuantity());
					ps3.setDouble(4, item.getPrice());
					
					ps3.executeUpdate();				
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
