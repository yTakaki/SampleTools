package com.example.demo.master.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UpdateUserForm {

	@NotBlank
	private String userId;

	@NotBlank
	@Size(max=20)
	private String userName;
}
