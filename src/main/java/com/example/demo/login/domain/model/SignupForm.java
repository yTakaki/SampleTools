package com.example.demo.login.domain.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class SignupForm {

	@NotBlank
	@Email
	private String userId;

	@NotBlank
	@Size(min=4,max=12)
	private String password;

	@NotBlank
	@Size(min=1,max=20)
	private String userName;

}
