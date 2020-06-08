package com.example.demo.master.domain.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
