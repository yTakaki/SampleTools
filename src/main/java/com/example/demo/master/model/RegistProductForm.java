package com.example.demo.master.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class RegistProductForm {

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
	private boolean compositeFlag;

	@NotNull
	private boolean foodFlag;

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

	public RegistProductForm() {
	};

	public RegistProductForm(String id,String cd,String name,boolean flag1,boolean flag2,int status,int period,
			String comp1,String comp2,String comp3,String comp4,String comp5,String comp6,
			String comp7,String comp8,String comp9,String comp10) {
		this.productId = id;
		this.productCd = cd;
		this.productName = name;
		this.compositeFlag = flag1;
		this.foodFlag = flag2;
		this.productStatus = status;
		this.permitPeriod = period;
		this.comp1 = comp1;
		this.comp2 = comp2;
		this.comp3 = comp3;
		this.comp4 = comp4;
		this.comp5 = comp5;
		this.comp6 = comp6;
		this.comp7 = comp7;
		this.comp8 = comp8;
		this.comp9 = comp9;
		this.comp10 = comp10;
	}

}
