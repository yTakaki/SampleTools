package com.example.demo.login.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

//@WebMvcTest(controllers = LoginController.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test.properties")
public class LoginControllerTest {

	@Autowired
	private MockMvc mock;

	//@MockBean
	//private UserDetailsServiceImpl service;

	@Test
	void ログイン画面へのリクエストに対して正常な画面が返されること() throws Exception {
		mock.perform(get("/login"))
		.andExpect(status().isOk())
		.andExpect(view().name("login/login"));
	}

	// loginTestをしない場合は、@WebMvcTestでOK
	@Test
	void loginTest() throws Exception {
		mock.perform(formLogin("/login")
				.user("testdata@sample.com")
				.password("$2a$10$cPIC6VUsJfeSmWDG86k.6.lgMsQK.FMN.FWNLffcOxIPu.HTGv0XK")) // 現状、ユーザー情報が誤りという扱い
		.andExpect(status().isFound())
		.andExpect(redirectedUrl("/home"));
	}

}

