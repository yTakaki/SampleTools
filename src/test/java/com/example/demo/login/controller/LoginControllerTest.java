package com.example.demo.login.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

//@SpringBootTest
//@AutoConfigureMockMvc
//@TestPropertySource(locations = "classpath:/test.properties")
@WebMvcTest(controllers = LoginController.class)
public class LoginControllerTest {

	@Autowired
	private MockMvc mock;

	@MockBean
	private DataSource data;

	@Test
	void ログイン画面へのリクエストに対して正常な画面が返されること() throws Exception {
		mock.perform(get("/login"))
		.andExpect(status().isOk())
		.andExpect(view().name("login/login"));
	}

	@Test
	@WithMockUser
	void ログイン画面にて適正なユーザー情報を入力してログインボタンを押すとログイン処理が実行されること() throws Exception {
		mock.perform(formLogin("/login"))
		.andExpect(status().isOk())
		.andExpect(view().name("login/homeLayout"));
	}
}

