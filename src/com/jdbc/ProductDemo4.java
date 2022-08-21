package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductDemo4 {
	
	private static final String url = "jdbc:mysql://localhost:3306/product";
	private static final String user = "root";
	private static final String pass = "root";
	
	public static void main(String[] args) {
		Connection connection=null;
		String query;
		ResultSet rs;
		Statement stm;
		PreparedStatement pstm;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, pass);
			query = "select * from Products";
			stm = connection.createStatement();
			rs = stm.executeQuery(query);
			
			
			while(rs.next()) {
				pstm=connection.prepareStatement("update Products set product_price=0 where product_category='Electronics' and product_price<1000");
				pstm=connection.prepareStatement("update Products set product_price=1 where product_category='Clothing' and product_price>1000");
				pstm.executeUpdate();
				
			}
			
			query = "select * from Products";
			stm = connection.createStatement();
			rs = stm.executeQuery(query);
			
			System.out.println("Table After Updating Values ");
			System.out.println("Product Code\tProduct Name\tProduct Price\tProduct Category");
			while(rs.next()) {
				System.out.print(rs.getInt(1)+"\t\t");
				System.out.print(rs.getString(2)+"\t\t");
				System.out.print(rs.getInt(3)+"\t\t");
				System.out.println(rs.getString(4)+"\t\t");
			}
		}catch(ClassNotFoundException e) {
			System.out.println(e);
		}catch(SQLException e) {
			System.out.println(e);
		}
	}

}
