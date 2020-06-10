package com.example.demo.datelog.domain.model;

import java.time.LocalDate;

import javax.validation.constraints.AssertTrue;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ProductDatelogForm {

	@AssertTrue
	private boolean foodFlag;

	private boolean compositeFlag;

	@DateTimeFormat(pattern="uuuuMMdd")
	private LocalDate permitDate;

	@DateTimeFormat(pattern="uuuuMMdd")
	private LocalDate productDatelog;

}
