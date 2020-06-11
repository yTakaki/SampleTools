package com.example.demo.master.model;

import lombok.Data;

@Data
public class Product {

	private String productId;

	private String productCd;

	private String productName;

	private boolean isSetup;

	private boolean isFood;

	private int productStatus;

	private int permitPeriod;

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

	public Product(RegisterProductForm f) {
		this.productId = f.getProductId();
		this.productCd = f.getProductCd();
		this.productName = f.getProductName();
		this.isSetup = f.isSetup();
		this.isFood = f.isFood();
		this.productStatus = f.getProductStatus();
		this.permitPeriod = f.getPermitPeriod();
		this.comp1 = f.getComp1();
		this.comp2 = f.getComp2();
		this.comp3 = f.getComp3();
		this.comp4 = f.getComp4();
		this.comp5 = f.getComp5();
		this.comp6 = f.getComp6();
		this.comp7 = f.getComp7();
		this.comp8 = f.getComp8();
		this.comp9 = f.getComp9();
		this.comp10 = f.getComp10();
	}

}
