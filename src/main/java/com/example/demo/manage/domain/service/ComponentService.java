package com.example.demo.manage.domain.service;

import java.util.List;

import com.example.demo.manage.domain.model.Component;

public interface ComponentService {

	public boolean insertComponent(Component component);

	public Component selectOneComponent(String componentId);

	public List<Component> selectAllComponent();

	public boolean updateComponent(Component component);

	public boolean deleteComponent(String componentId);

	public List<Component> searchComponent(String id,String cd,String name,boolean flag,int status);

}
