package com.example.demo.manage.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.manage.domain.model.Item;
import com.example.demo.manage.domain.repository.ItemMapper;

@Service
public class ItemService {

	@Autowired
	private ItemMapper mapper;

	public boolean insertItem(Item item) {
		return mapper.insertItem(item);
	}

	public Item selectOneItem(String itemId) {
		return mapper.selectOneItem(itemId);
	}

	public List<Item> selectAllItem() {
		return mapper.selectAllItem();
	}

	public boolean updateItem(Item item) {
		return mapper.updateItem(item);
	}

	public boolean deleteItem(String itemId) {
		return mapper.deleteItem(itemId);
	}

	public List<Item> searchItem(String id,String cd,String name,boolean flag,int status) {
		return mapper.searchItem(id, cd, name, flag, status);
	}
}
