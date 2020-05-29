package com.example.demo.manage.domain.service;

import java.util.List;

import com.example.demo.manage.domain.model.Item;

public interface ItemService {

	public boolean insertItem(Item item);

	public Item selectOneItem(String itemId);

	public List<Item> selectAllItem();

	public boolean updateItem(Item item);

	public boolean deleteItem(String itemId);

	public List<Item> searchItem(String id,String cd,String name,boolean flag,int status);
}
