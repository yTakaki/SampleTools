package com.example.demo.datelog.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CompDatelog {

	private String productId; // 構成品の商品ID

	private String productCd; // 構成品の商品コード

	private String productName; // 構成品の商品名

	private boolean foodFlag; // 構成品の食品フラグ

	private LocalDate permitDate; // 構成品の使用許可期限

	private LocalDate compDate; // 構成品の賞味期限

	private int productDatelogId; // セットアップ品のログID

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
