package com.example.demo.master.model;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ProductSearchForm {

	@NotNull
	private String productId;

	@NotNull
	private String productCd;

	@NotNull
	private String productName;

	private boolean isSetup;

	private boolean isFood;

	private int productStatus;

	private String compId;
}
