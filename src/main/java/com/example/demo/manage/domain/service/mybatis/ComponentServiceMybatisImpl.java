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

	@Override
	public List<Component> searchComp1(String id,boolean flag,int status) {
		return mapper.searchComp1(id, flag, status);
	}

	@Override
	public List<Component> searchComp2(String cd,boolean flag,int status) {
		return mapper.searchComp2(cd, flag, status);
	}

	@Override
	public List<Component> searchComp3(String name,boolean flag,int status) {
		return mapper.searchComp3(name, flag, status);
	}

	@Override
	public List<Component> searchComp4(String cd,String name,boolean flag,int status) {
		return mapper.searchComp4(cd, name, flag, status);
	}

	@Override
	public List<Component> searchComp5(String id,String name,boolean flag,int status) {
		return mapper.searchComp5(id, name, flag, status);
	}

	@Override
	public List<Component> searchComp6(String id,String cd,boolean flag,int status) {
		return mapper.searchComp6(id, cd, flag, status);
	}

}
