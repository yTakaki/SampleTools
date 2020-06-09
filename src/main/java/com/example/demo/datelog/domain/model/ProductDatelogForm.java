package com.example.demo.datelog.domain.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ProductDatelogForm {

	@DateTimeFormat(pattern="uuuuMMdd")
	private LocalDate permitDate;

	@DateTimeFormat(pattern="uuuuMMdd")
	private LocalDate productDatelog;

}
