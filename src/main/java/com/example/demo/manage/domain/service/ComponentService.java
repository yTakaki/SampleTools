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

	public List<Component> searchComp1(String id,boolean flag,int status);

	public List<Component> searchComp2(String cd,boolean flag,int status);

	public List<Component> searchComp3(String name,boolean flag,int status);

	public List<Component> searchComp4(String cd,String name,boolean flag,int status);

	public List<Component> searchComp5(String id,String name,boolean flag,int status);

	public List<Component> searchComp6(String id,String cd,boolean flag,int status);

}
