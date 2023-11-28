package com.fuku.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

	public static Connection getConnection() {

		final String url = "jdbc:mysql://localhost:3306/BanQuanAo";
		final String user = "root";
		final String password = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				return DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {

		Connection connection = getConnection();
		try {
			System.out.println("Hello mysql");
			System.out.println(connection);
		} catch (Exception e) {

		}
	}

//	private final String serverName = "localhost";
//	private final String dbName = "BanQuanAo";
//	private final String portName = "1433";
//	private final String instance = "SQLEXPRESS";
//	private final String userID = "sa";
//	private final String password = "sa123456";
//	
//	public Connection getConnection() throws Exception {
//		String url = "jdbc:sqlserver://" + serverName + ":" + portName +  "\\" + instance +  ";databaseName="+ dbName;
//		if(instance == null || instance.trim().isEmpty()) {
//			System.out.println("instance null");
//			url = "jdbc:sqlserver://" + serverName + ":" + portName + ";databaseName=" + dbName;
//		}
//		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//		
//		return DriverManager.getConnection(url, userID, password);
//	}

}
