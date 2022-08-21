package com.jdbc;

import java.util.Scanner;

public class ProductDemo5 {
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		
		ProductDAO pdo = new ProductDAO();
		
		System.out.println("Enter product details -  ");
		System.out.println("\nEnter Product Code : ");
		int code = scanner.nextInt();
		System.out.println("Enter product name ");
		String name = scanner.next();
		System.out.println("Enter price ");
		int price = scanner.nextInt();
		System.out.println("Enter category ");
		String category = scanner.next();
		
		Product p = new Product(code,name,price,category);
		boolean isInserted = pdo.insertProduct(p);
		if(isInserted)
			System.out.println("Product Inserted Successfully ! ");
		else
			System.out.println("Failed Insertion");
		
		System.out.println("-----------------------------------");
		System.out.println("Enter the code for which you wish to get details ");
		int code1 = scanner.nextInt();
		Product product = null;
		try {
			product = pdo.getProduct(code1);
		} catch (InvalidIdException e) {
			
			System.out.println(e.getMessage());
		}
		System.out.println("Code  : "+product.getProduct_code());
		System.out.println("Name  : "+product.getProduct_name());
		System.out.println("Price : "+product.getProduct_price());
		System.out.println("Category : "+product.getProduct_category());
		scanner.close();
	}

}
