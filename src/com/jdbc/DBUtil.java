package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	private static final String url="jdbc:mysql://localhost:3306/product";
	private static final String user="root";
	private static final String pass="root";

	
	public static Connection getConnection() {
		Connection connection = null;
		try {
			
			connection = DriverManager.getConnection(url, user, pass);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return connection;
	}
}
