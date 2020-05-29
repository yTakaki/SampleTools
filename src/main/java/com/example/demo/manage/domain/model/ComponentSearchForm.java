package com.example.demo.manage.domain.model;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ComponentSearchForm {

	@NotNull
	private String componentId;

	@NotNull
	private String componentCd;

	@NotNull
	private String componentName;

	private boolean foodFlag;

	private int componentStatus;
}
