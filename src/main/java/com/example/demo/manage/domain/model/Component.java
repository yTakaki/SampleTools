package com.example.demo.manage.domain.model;

import lombok.Data;

@Data
public class Component {

	private String componentId;

	private String componentCd;

	private String componentName;

	private boolean foodFlag;

	private int componentStatus;

	public Component(String id,String cd,String name,boolean flag,int status) {
		this.setComponentId(id);
		this.setComponentCd(cd);
		this.setComponentName(name);
		this.setFoodFlag(flag);
		this.setComponentStatus(status);
	}
}
