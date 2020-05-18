package com.example.demo.login.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
public class SignupControllerTest {

	@Autowired
	private MockMvc mock;

	@Test
	void signupへのGETリクエストに対するsignupビューの画面表示テスト() throws Exception {

		this.mock.perform(get("/signup"))
		.andExpect(status().isOk())
		.andExpect(view().name("login/signup"));
	}
}
