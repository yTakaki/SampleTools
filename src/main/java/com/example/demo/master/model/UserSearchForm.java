package com.example.demo.master.model;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UserSearchForm {

	@NotNull
	private String userId;

	@NotNull
	private String userName;

}
