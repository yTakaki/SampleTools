package com.example.demo.manage.domain.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.manage.domain.model.Component;

@Mapper
public interface ComponentMapper {

	public boolean insertComponent(Component component);

	public Component selectOneComponent(String componentId);

	public List<Component> selectAllComponent();

	public boolean updateComponent(Component component);

	public boolean deleteComponent(String componentId);

	public List<Component> searchComponent(
			@Param("id")String id,@Param("cd")String cd,@Param("name")String name,
			@Param("flag")boolean flag,@Param("status")int status);

	public boolean deleteAllComponent(); // for junit test.

}
