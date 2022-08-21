package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class ProductDemo2 {
	
	private static final String url ="jdbc:mysql://localhost:3306/product";
	private static final String user= "root";
	private static final String pass="root";

	public static void main(String[] args) {
		Connection connection = null;
		Scanner scanner = new Scanner(System.in);
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, pass);
			
			System.out.println("Enterthe product code : ");
			String code = scanner.nextLine();
			
			String query = "select * from Products where product_code = ?";
			PreparedStatement pstm = connection.prepareStatement(query);
			pstm.setString(1, code);
			
			ResultSet rs= pstm.executeQuery();
			while(rs.next()) {
				System.out.println("Code 	 : "+rs.getInt(1));
				System.out.println("Name 	 : "+rs.getString(2));
				System.out.println("Price 	 : "+rs.getInt(3));
				System.out.println("Category : "+rs.getString(4));
			}
			
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
				try {
					connection.close();
				} catch (SQLException e) {
				e.printStackTrace();
				}
		}
		scanner.close();
	}

}
