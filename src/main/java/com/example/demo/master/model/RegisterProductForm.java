package com.example.demo.master.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class RegisterProductForm {

	@NotBlank
	@Size(max=8)
	private String productId;

	@NotBlank
	@Size(max=20)
	private String productCd;

	@NotBlank
	@Size(max=50)
	private String productName;

	@NotNull
	private boolean isSetup;

	@NotNull
	private boolean isFood;

	@NotNull
	private int productStatus;

	@NotNull
	@Range(min=0,max=999)
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

	// デフォルトコンストラクタ
	public RegisterProductForm() {
	};

	// Mapperから受け取った商品情報をフォームへ流し込むためのコンストラクタ
	public RegisterProductForm(Product p) {
		this.productId = p.getProductId();
		this.productCd = p.getProductCd();
		this.productName = p.getProductName();
		this.isSetup = p.isSetup();
		this.isFood = p.isFood();
		this.productStatus = p.getProductStatus();
		this.permitPeriod = p.getPermitPeriod();
		this.comp1 = p.getComp1();
		this.comp2 = p.getComp2();
		this.comp3 = p.getComp3();
		this.comp4 = p.getComp4();
		this.comp5 = p.getComp5();
		this.comp6 = p.getComp6();
		this.comp7 = p.getComp7();
		this.comp8 = p.getComp8();
		this.comp9 = p.getComp9();
		this.comp10 = p.getComp10();
	}

}
