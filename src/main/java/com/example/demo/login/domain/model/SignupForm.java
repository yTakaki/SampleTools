package com.example.demo.login.domain.model;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class SignupForm {

	@NotBlank
	@Email
	private String userId;

	@NotBlank
	@Length(min=4,max=16)
	private String password;

	@NotBlank
	@Length(min=1,max=20)
	private String userName;

}
