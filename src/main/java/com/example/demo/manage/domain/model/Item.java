package com.example.demo.manage.domain.model;

import lombok.Data;

@Data
public class Item {

	private String itemId;

	private String itemCd;

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

	public Item() {
	}

	public Item(String id,String cd,String name,boolean flag,int status,
			String comp1,String comp2,String comp3,String comp4,String comp5,
			String comp6,String comp7,String comp8,String comp9,String comp10) {
		this.setItemId(id);
		this.setItemCd(cd);
		this.setItemName(name);
		this.setCompositeFlag(flag);
		this.setItemStatus(status);
		this.setComp1(comp1);
		this.setComp2(comp2);
		this.setComp3(comp3);
		this.setComp4(comp4);
		this.setComp5(comp5);
		this.setComp6(comp6);
		this.setComp7(comp7);
		this.setComp8(comp8);
		this.setComp9(comp9);
		this.setComp10(comp10);
	}
}
