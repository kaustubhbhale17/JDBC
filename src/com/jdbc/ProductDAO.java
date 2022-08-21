package com.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductDAO {
	
	Connection connection = DBUtil.getConnection();
	Statement stm;
	PreparedStatement pstm;
	String query;
	ResultSet rs;
	
	public boolean insertProduct(Product p) {
		query = "insert into Products values (?,?,?,?)";
		try {
			pstm = connection.prepareStatement(query);
			pstm.setInt(1, p.getProduct_code());
			pstm.setString(2, p.getProduct_name());
			pstm.setInt(3, p.getProduct_price());
			pstm.setString(4, p.getProduct_category());
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public Product getProduct(int productCode) throws InvalidIdException {
		Product product=null;
		query="select * from Products where product_code = ?";
		try {
			pstm=connection.prepareStatement(query);
			pstm.setInt(1, productCode);
			rs = pstm.executeQuery();
			
			
			if(rs.getFetchSize()==0) {
				throw new InvalidIdException("Invalid Id ");
			}
			while(rs.next()) {
				
				int code = rs.getInt(1);
				String name = rs.getString(2);
				int price = rs.getInt(3);
				String category = rs.getString(4);
				Product p= new Product(code,name,price,category);
				return p;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch(NullPointerException e) {
			System.out.println("Invalid Id");
		}
		return product;
	}
}
