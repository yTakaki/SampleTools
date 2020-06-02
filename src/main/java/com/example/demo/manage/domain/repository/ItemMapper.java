package com.example.demo.manage.domain.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.manage.domain.model.Item;

@Mapper
public interface ItemMapper {

	public boolean insertItem(Item item);

	public Item selectOneItem(String itemId);

	public List<Item> selectAllItem();

	public boolean updateItem(Item item);

	public boolean deleteItem(String itemId);

	public List<Item> searchItem(
			@Param("id")String id,@Param("cd")String cd,@Param("name")String name,
			@Param("flag")boolean flag,@Param("status")int status);

	public boolean deleteAllItem(); // for junit test.

}
