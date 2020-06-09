package com.example.demo.datelog.domain.model;

import java.time.LocalDate;

import javax.validation.constraints.Min;
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

	@NotNull
	private String productId; // 商品ID

	@Min(1)
	private int workQuantity; // 作業数量

	private String productCd; // 商品コード

	private String productName; // 商品名

}
