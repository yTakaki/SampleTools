package com.example.demo.login.domain.model;

import lombok.Data;

@Data
public class User {

	private String userId;

	private String password;

	private String userName;

	public User() {
	}

	public User(String id,String pass,String name) {
		this.userId = id;
		this.password = pass;
		this.userName = name;
	}
}
