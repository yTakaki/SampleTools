package com.example.demo.manage.domain.service.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.manage.domain.model.Item;
import com.example.demo.manage.domain.repository.mybatis.ItemMapper;
import com.example.demo.manage.domain.service.ItemService;

@Service("ItemServiceMybatisImpl")
public class ItemServiceMybatisImpl implements ItemService {

	@Autowired
	private ItemMapper mapper;

	@Override
	public boolean insertItem(Item item) {
		return mapper.insertItem(item);
	}

	@Override
	public Item selectOneItem(String itemId) {
		return mapper.selectOneItem(itemId);
	}

	@Override
	public List<Item> selectAllItem() {
		return mapper.selectAllItem();
	}

	@Override
	public boolean updateItem(Item item) {
		return mapper.updateItem(item);
	}

	@Override
	public boolean deleteItem(String itemId) {
		return mapper.deleteItem(itemId);
	}

	@Override
	public List<Item> searchItem(String id,String cd,String name,boolean flag,int status) {
		return mapper.searchItem(id, cd, name, flag, status);
	}
}
