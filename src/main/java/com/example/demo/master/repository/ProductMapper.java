package com.example.demo.master.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.master.model.Product;

@Mapper
public interface ProductMapper {

	public boolean insertProduct(Product product);

	public Product selectOneProduct(String productId);

	public List<Product> selectAllProduct();

	public boolean updateProduct(Product product);

	public boolean deleteProduct(String productId);

}
