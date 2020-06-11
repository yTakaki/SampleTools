package com.example.demo.master.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.master.model.Product;
import com.example.demo.master.model.ProductSearchForm;
import com.example.demo.master.repository.ProductMapper;

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

	public List<Product> searchProduct(ProductSearchForm form) {
		List<Product> list = mapper.selectAllProduct();
		// TODO:条件に基づいてListの中身を絞り込む処理を記述する

		return list;
	}

}
