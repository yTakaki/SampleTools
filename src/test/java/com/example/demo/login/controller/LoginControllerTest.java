package com.example.demo.login.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers=LoginController.class)
public class LoginControllerTest {

	@Autowired
	private MockMvc mock;

	@Test
	void ログイン画面へのリクエストに対して正常な画面が返されること() throws Exception {
		mock.perform(get("/login"))
		.andExpect(status().isOk())
		.andExpect(view().name("login/login"));
	}
}
