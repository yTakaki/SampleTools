package com.example.demo.master.domain.model;

import lombok.Data;

@Data
public class Product {

	private String productId;

	private String productCd;

	private String productName;

	private boolean compositeFlag;

	private boolean foodFlag;

	private int productStatus;

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

	public Product() {
	}

	public Product(String id,String cd,String name,boolean flag1,boolean flag2,int status,
			String comp1,String comp2,String comp3,String comp4,String comp5,
			String comp6,String comp7,String comp8,String comp9,String comp10) {
		this.setProductId(id);
		this.setProductCd(cd);
		this.setProductName(name);
		this.setCompositeFlag(flag1);
		this.setFoodFlag(flag2);
		this.setProductStatus(status);
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
