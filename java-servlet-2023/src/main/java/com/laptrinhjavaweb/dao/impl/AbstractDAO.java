package com.laptrinhjavaweb.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.laptrinhjavaweb.dao.GenericDAO;
import com.laptrinhjavaweb.mapper.RowMapper;

public class AbstractDAO<T> implements GenericDAO<T> {

	public Connection getConnection() {
		
		ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
		
		String url = resourceBundle.getString("url");
		String user = resourceBundle.getString("user");
		String password = resourceBundle.getString("password");	
//		String url = "jdbc:mysql://localhost:3306/newservlet11month2023";
//		String user = "root";
//		String password = "";

		try {
			Class.forName(resourceBundle.getString("driverName"));
			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			return null;
		}
	}

	// Hàm để lấy lên danh sách T
	@Override
	public List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {

		List<T> list = new ArrayList<T>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(sql);

			// set parameters nếu có
			setParameter(preparedStatement, parameters);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				list.add(rowMapper.mapRow(resultSet));
			}

			return list;

		} catch (SQLException e) {
			return null;
		} finally {

			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				return null;
			}
		}
	}

	// Nếu có parameter trong câu lệnh sql thì set chúng vào đối tượng
	// PreparedStatement trước khi executeQuery()
	private void setParameter(PreparedStatement ps, Object... parameters) {

		try {
			for (int i = 0; i < parameters.length; i++) {

				Object parameter = parameters[i];
				int index = i + 1;
				if (parameter instanceof Long) {
					ps.setLong(index, (Long) parameter);
				} else if (parameter instanceof String) {
					ps.setString(index, (String) parameter);
				} else if (parameter instanceof Integer) {
					ps.setInt(index, (Integer) parameter);
				} else if (parameter instanceof Timestamp) {
					ps.setTimestamp(index, (Timestamp) parameter);
				} 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Hàm để update hoặc delete
	@Override
	public void update(String sql, Object... parameters) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(sql);

			setParameter(preparedStatement, parameters);

			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			if (connection != null) { // nếu connection == null thì khi gọi .roolback() -> error
				// nên ta kiểm tra trước.
				try {
					connection.rollback(); // lỗi xảy ra trong khối try thì reset về trạng thái ban đầu
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

		} finally { // khi sd xong thì đóng tài nguyên lại -> best practice
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

	// Hàm để thêm mới insert
	@Override
	public Long insert(String sql, Object... parameters) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Long id = null;

		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			setParameter(preparedStatement, parameters);

			preparedStatement.executeUpdate();

			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				id = resultSet.getLong(1);
			}

			connection.commit();
			return id;

		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}

		return null;
	}
	
	//Hàm chuyên đếm số lượng
	@Override
	public int count(String sql, Object... parameters) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			
			int count = 0;
			connection = getConnection();
			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1); 
				// vì khi ghi câu lệnh sql để count thì kết quả count sẽ
				// ở cột 1 nên ta getInt(1)
			}

			return count;

		} catch (SQLException e) {
			return 0;
		} finally {

			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				return 0;
			}
		}
	}
}
