package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductsDemo1 {
	private final static String url = "jdbc:mysql://localhost:3306/product";
	private final static String user = "root";
	private final static String pass = "root";
	
	public static void main(String[] args) {
		Connection con = null;
		String query = "select * from Products";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pass);
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query);
			
			System.out.println("Product Details");
			while(rs.next()) {
				System.out.print(rs.getInt(1)+"\t\t");
				System.out.print(rs.getString(2)+"\t\t");
				System.out.print(rs.getInt(3)+"\t\t");
				System.out.println(rs.getString(4));
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
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	}

}
