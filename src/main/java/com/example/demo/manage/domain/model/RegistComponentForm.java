package com.example.demo.manage.domain.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RegistComponentForm {

	@NotBlank
	@Size(max=8)
	private String componentId;

	@NotBlank
	@Size(max=20)
	private String componentCd;

	@NotBlank
	@Size(max=50)
	private String componentName;

	private boolean foodFlag;

	private int componentStatus;

}
