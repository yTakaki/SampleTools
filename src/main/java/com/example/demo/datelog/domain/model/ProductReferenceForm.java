package com.example.demo.datelog.domain.model;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ProductReferenceForm {

	@NotNull
	@DateTimeFormat(pattern="uuuuMMdd")
	private LocalDate workingDate; // 作業日

	@NotNull
	@DateTimeFormat(pattern="uuuuMMdd")
	private LocalDate shipmentDate; // 出荷日

	@NotBlank
	private String productId; // 商品ID

	@Min(1)
	private int workQuantity; // 作業数量

	private String productCd; // 商品コード

	private String productName; // 商品名

	public ProductReferenceForm() {
		this.workingDate = LocalDate.now();
		this.shipmentDate = LocalDate.now().plusDays(1);
		this.workQuantity = 1;
	}

	public ProductReferenceForm(LocalDate d1,LocalDate d2,String id,int q,String cd,String name) {
		this.workingDate = d1;
		this.shipmentDate = d2;
		this.productId = id;
		this.workQuantity = q;
		this.productCd = cd;
		this.productName = name;
	}
}
