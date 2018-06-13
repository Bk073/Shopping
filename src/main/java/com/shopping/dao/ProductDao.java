package com.shopping.dao;

import java.util.List;

import com.shopping.entity.Product;

public interface ProductDao {
	Product get(int productId);
	List<Product> list();	
	boolean add(Product product);
	boolean update(Product product);
	boolean delete(Integer productId);
	Product getProductsByName(String productName);
	boolean isProductExit(Product product);
}
