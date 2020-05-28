package com.example.demo.manage.domain.repository.mybatis;

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

	public List<Component> searchComp1(@Param("id")String id,@Param("flag")boolean flag,@Param("status")int status);

	public List<Component> searchComp2(@Param("cd")String cd,@Param("flag")boolean flag,@Param("status")int status);

	public List<Component> searchComp3(@Param("name")String name,@Param("flag")boolean flag,@Param("status")int status);

	public List<Component> searchComp4(
			@Param("cd")String cd,@Param("name")String name,@Param("flag")boolean flag,@Param("status")int status);

	public List<Component> searchComp5(
			@Param("id")String id,@Param("name")String name,@Param("flag")boolean flag,@Param("status")int status);

	public List<Component> searchComp6(
			@Param("id")String id,@Param("cd")String cd,@Param("flag")boolean flag,@Param("status")int status);

	public boolean deleteAllComponent(); // for junit test.
}
