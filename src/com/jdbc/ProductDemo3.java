package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductDemo3 {

	private static final String url="jdbc:mysql://localhost:3306/product";
	private static final String user ="root";
	private static final String pass = "root";
	
	public static void main(String[] args) {
		Connection connection = null;
		Scanner scanner = new Scanner(System.in);
		String query="";
		PreparedStatement pstm;
		Statement s;
		ResultSet rs;
		
		ArrayList<Product> listOfProducts = new ArrayList<Product>();
		
		System.out.println("Enter the number of products : ");
		int number = scanner.nextInt();
		
		
		for(int i=1;i<=number;i++) {
			
			System.out.println("Enter details of product : "+i);
			int product_code = scanner.nextInt();
			String product_name = scanner.next();
			int product_price = scanner.nextInt();
			String product_category = scanner.next();
			
			Product p = new Product(product_code,product_name,product_price,product_category);
			listOfProducts.add(p);
		}
		
		//insert the products from ArrayList into Table
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, pass);
			query = "insert into products values (?,?,?,?)";
			pstm = connection.prepareStatement(query);
			
			for(Product product : listOfProducts) {
				pstm.setInt(1, product.getProduct_code());
				pstm.setString(2, product.getProduct_name());
				pstm.setInt(3, product.getProduct_price());
				pstm.setString(4, product.getProduct_category());
				
				pstm.executeUpdate();
			}
			
			//displaying the entire table
			query = "select * from Products";
			s = connection.createStatement();
			rs = s.executeQuery(query);
			System.out.println("Product Code\tProduct Name\tProduct Price\tProduct Category");
			while(rs.next()) {
				System.out.print(rs.getInt(1)+"\t\t");
				System.out.print(rs.getString(2)+"\t\t");
				System.out.print(rs.getInt(3)+"\t\t");
				System.out.println(rs.getString(4)+"\t\t");
			}
		}catch(ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			scanner.close();
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		

	}

}
