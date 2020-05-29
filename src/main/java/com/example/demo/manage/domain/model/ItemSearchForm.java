package com.example.demo.manage.domain.model;

import lombok.Data;

@Data
public class ItemSearchForm {

	private String itemId;

	private String itemCd;

	private String itemName;

	private boolean compositeFlag;

	private int itemStatus;

	private String compId;
}
