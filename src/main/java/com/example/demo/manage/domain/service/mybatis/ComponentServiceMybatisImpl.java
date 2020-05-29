package com.example.demo.manage.domain.service.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.manage.domain.model.Component;
import com.example.demo.manage.domain.repository.mybatis.ComponentMapper;
import com.example.demo.manage.domain.service.ComponentService;

@Transactional
@Service("ComponentServiceMybatisImpl")
public class ComponentServiceMybatisImpl implements ComponentService {

	@Autowired
	ComponentMapper mapper;

	@Override
	public boolean insertComponent(Component component) {
		return mapper.insertComponent(component);
	}

	@Override
	public Component selectOneComponent(String componentId) {
		return mapper.selectOneComponent(componentId);
	}

	@Override
	public List<Component> selectAllComponent() {
		return mapper.selectAllComponent();
	}

	@Override
	public boolean updateComponent(Component component) {
		return mapper.updateComponent(component);
	}

	@Override
	public boolean deleteComponent(String componentId) {
		return mapper.deleteComponent(componentId);
	}

	@Override
	public List<Component> searchComponent(String id,String cd,String name,boolean flag,int status) {
		return mapper.searchComponent(id, cd, name, flag, status);
	}

}
