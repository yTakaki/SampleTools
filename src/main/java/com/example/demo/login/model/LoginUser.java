package com.example.demo.login.model;

import lombok.Data;

@Data
public class LoginUser {

	private String userId;

	private String password;

	private String userName;

	public LoginUser() {
	}

	public LoginUser(String id,String pass,String name) {
		this.userId = id;
		this.password = pass;
		this.userName = name;
	}
}
