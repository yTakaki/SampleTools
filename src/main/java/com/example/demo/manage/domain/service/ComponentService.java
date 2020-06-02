package com.example.demo.manage.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.manage.domain.model.Component;
import com.example.demo.manage.domain.repository.ComponentMapper;

@Transactional
@Service
public class ComponentService {

	@Autowired
	ComponentMapper mapper;

	public boolean insertComponent(Component component) {
		return mapper.insertComponent(component);
	}

	public Component selectOneComponent(String componentId) {
		return mapper.selectOneComponent(componentId);
	}

	public List<Component> selectAllComponent() {
		return mapper.selectAllComponent();
	}

	public boolean updateComponent(Component component) {
		return mapper.updateComponent(component);
	}

	public boolean deleteComponent(String componentId) {
		return mapper.deleteComponent(componentId);
	}

	public List<Component> searchComponent(String id,String cd,String name,boolean flag,int status) {
		return mapper.searchComponent(id, cd, name, flag, status);
	}

}
