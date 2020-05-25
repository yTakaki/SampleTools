package com.example.demo.manage.controller;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class UserMasterControllerTest {

	@Autowired
	private MockMvc mock;

	@Autowired
	private UserService service;

	@BeforeEach
	void beforeTest() throws Exception {
		User user = new User();
		user.setUserId("testdata@sample.com");
		user.setPassword("pass");
		user.setUserName("testuser");
		service.deleteUser("testdata@sample.com");
		service.insertUser(user);
	}

	@Test
	@WithMockUser
	void userMasterへのGETリクエストに対する画面表示テスト() throws Exception {
		this.mock.perform(get("/userMaster"))
		.andExpect(status().isOk())
		.andExpect(view().name("login/homeLayout"))
		.andExpect(content().string(containsString("ユーザー閲覧")));
	}

	@Test
	@WithMockUser
	void userSearchへのPOSTリクエストに対する画面表示テスト() throws Exception {
		this.mock.perform(post("/userSearch"))
		.andExpect(status().isOk())
		.andExpect(model().hasNoErrors())
		.andExpect(view().name("login/homeLayout"));
	}
}
