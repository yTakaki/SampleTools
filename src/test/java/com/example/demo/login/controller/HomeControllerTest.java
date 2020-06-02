package com.example.demo.login.controller;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers=HomeController.class)
public class HomeControllerTest {

	@Autowired
	private MockMvc mock;

	@WithMockUser
	@Test
	void ホーム画面へのリクエストに対して正常な画面が返されること() throws Exception {
		mock.perform(get("/home"))
		.andExpect(status().isOk())
		.andExpect(view().name("login/homeLayout"))
		.andExpect(content().string(containsString("ホーム画面")));
	}

	@WithMockUser
	@Test
	void ホーム画面にてログアウトボタンを押すとログイン画面へリダイレクトが返されること() throws Exception {
		mock.perform(post("/logout"))
		.andExpect(status().isFound())
		.andExpect(redirectedUrlPattern("/login*"));
	}
}
