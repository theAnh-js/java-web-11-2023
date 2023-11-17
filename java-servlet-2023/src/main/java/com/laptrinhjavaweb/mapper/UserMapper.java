package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.model.RoleModel;
import com.laptrinhjavaweb.model.UserModel;

public class UserMapper implements RowMapper<UserModel> {

	@Override
	public UserModel mapRow(ResultSet resultSet) {

		try {
			UserModel user = new UserModel();

			user.setId(resultSet.getLong("id"));
			user.setFullName(resultSet.getString("fullname"));
			user.setUserName(resultSet.getString("username"));
			user.setPassword(resultSet.getString("password"));
			user.setStatus(resultSet.getInt("status"));

			// Vì trong trường hợp, nếu ta chỉ tương tác với bảng user thôi 
			// vì không có code và name để get nên cần check. Chỉ ok khi ta join 
			// bảng user với bảng role thoi.
			try {
				RoleModel role = new RoleModel();
				// 2 cột code và name nay là của bảng role
				// nhưng do ta dùng câu lênh join nên bảng role và bảng user gộp thành 1
				// Do đó, ta có thể: resultSet.getString("code") và resultSet.getString("name")
				// Và thật may vì bảng user khi chưa join không có 2 cột có tên là code, name
				// nên ko bị trùng.
				role.setCode(resultSet.getString("code"));
				role.setName(resultSet.getString("name"));
				user.setRole(role);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			return user;
		} catch (SQLException e) {
			return null;
		}
	}
}
