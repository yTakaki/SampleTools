package com.example.demo.manage.domain.model;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ItemSearchForm {

	@NotNull
	private String itemId;

	@NotNull
	private String itemCd;

	@NotNull
	private String itemName;

	private boolean compositeFlag;

	private int itemStatus;

	private String compId;
}
