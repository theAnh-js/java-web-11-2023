package com.fuku.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fuku.connection.DBConnect;
import com.fuku.model.UserModel;

public class LoginDAO {
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public UserModel checkUserByUsernameAndPassword(String username, String password) {
		
//		UserModel user = new UserModel();
		conn = DBConnect.getConnection();
		String sql = "select * from users where Username=? and Password=?;";
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, username);
			ps.setString(2, password);
		
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				UserModel user = new UserModel();
				
				user.setUserID(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setSeller(rs.getInt(4));
				user.setAdmin(rs.getInt(5));
				
				return user;
			}
		} catch (SQLException e) {
			return null;
		}
		
		return null;
	}
	

}
