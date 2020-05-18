package com.example.demo.login.controller;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
public class HomeControllerTest {

	@Autowired
	private MockMvc mock;

	@WithMockUser
	@Test
	void homeへのGETリクエストに対するhomeLayout画面表示の確認テスト() throws Exception {
		this.mock.perform(get("/home"))
		.andExpect(status().isOk())
		.andExpect(view().name("login/homeLayout"))
		.andExpect(content().string(containsString("ホーム画面")));
	}

	@WithMockUser
	@Test
	void logoutへのPOSTリクエストに対するlogin画面表示の確認テスト() throws Exception {
		this.mock.perform(post("/logout"))
		.andExpect(status().isFound())
		.andExpect(redirectedUrlPattern("/login*"));
	}
}
