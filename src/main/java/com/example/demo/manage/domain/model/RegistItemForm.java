package com.example.demo.manage.domain.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RegistItemForm {

	@NotBlank
	@Size(max=8)
	private String itemId;

	@NotBlank
	@Size(max=20)
	private String itemCd;

	@NotBlank
	@Size(max=50)
	private String itemName;

	private boolean compositeFlag;

	private int itemStatus;

	private String comp1;

	private String comp2;

	private String comp3;

	private String comp4;

	private String comp5;

	private String comp6;

	private String comp7;

	private String comp8;

	private String comp9;

	private String comp10;

}
