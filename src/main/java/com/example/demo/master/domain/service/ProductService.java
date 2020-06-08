package com.example.demo.master.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.master.domain.model.Product;
import com.example.demo.master.domain.repository.ProductMapper;

@Service
public class ProductService {

	@Autowired
	private ProductMapper mapper;

	public boolean insertProduct(Product product) {
		return mapper.insertProduct(product);
	}

	public Product selectOneProduct(String productId) {
		return mapper.selectOneProduct(productId);
	}

	public List<Product> selectAllProduct() {
		return mapper.selectAllProduct();
	}

	public boolean updateProduct(Product product) {
		return mapper.updateProduct(product);
	}

	public boolean deleteProduct(String productId) {
		return mapper.deleteProduct(productId);
	}

	public List<Product> searchProduct(String id,String cd,String name,boolean flag1,boolean flag2,int status) {
		return mapper.searchProduct(id, cd, name, flag1, flag2, status);
	}
}
