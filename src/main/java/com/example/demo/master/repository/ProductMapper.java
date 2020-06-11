package com.example.demo.master.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.master.model.Product;

@Mapper
public interface ProductMapper {

	public boolean insertProduct(Product product);

	public Product selectOneProduct(String productId);

	public List<Product> selectAllProduct();

	public boolean updateProduct(Product product);

	public boolean deleteProduct(String productId);

	public List<Product> searchProduct(
			@Param("id")String id,@Param("cd")String cd,@Param("name")String name, @Param("flag1")boolean flag,
			@Param("flag2")boolean flag2, @Param("status")int status, @Param("compId")String compId);

}
