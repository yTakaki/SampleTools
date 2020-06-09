package com.example.demo.datelog.domain.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CompDatelog {

	private String productId;

	private String productCd;

	private String productName;

	private boolean foodFlag;

	private LocalDate permitDate;

	private LocalDate compDate;

	public CompDatelog() {
	}

	public CompDatelog(String id,String cd,String name,boolean flag,LocalDate pDate) {
		this.productId = id;
		this.productCd = cd;
		this.productName = name;
		this.foodFlag = flag;
		this.permitDate = pDate;
	}

}
