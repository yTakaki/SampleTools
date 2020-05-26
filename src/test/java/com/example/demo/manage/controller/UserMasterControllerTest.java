package com.example.demo.manage.controller;

import static org.hamcrest.MatcherAssert.*;
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
import com.example.demo.login.domain.repository.mybatis.UserMapper;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class UserMasterControllerTest {

	@Autowired
	private MockMvc mock;

	@Autowired
	private UserMapper mapper;

	@BeforeEach
	void beforeTest() throws Exception {
		User user = new User();
		user.setUserId("testdata@sample.com");
		user.setPassword("pass");
		user.setUserName("testuser");
		mapper.deleteAllUser();
		mapper.insertUser(user);
	}

	@Test
	@WithMockUser
	void getUserMasterTest() throws Exception {
		this.mock.perform(get("/userMaster"))
		.andExpect(status().isOk())
		.andExpect(view().name("login/homeLayout"))
		.andExpect(content().string(containsString("ユーザー閲覧")));
	}

	@Test
	@WithMockUser
	void postUserSearchTest() throws Exception {
		this.mock.perform(post("/userSearch"))
		.andExpect(status().isOk())
		.andExpect(model().hasNoErrors())
		.andExpect(model().attribute("result","合計で1件のユーザー情報を取得しました。"))
		.andExpect(view().name("login/homeLayout"))
		.andExpect(content().string(containsString("testdata@sample.com")));
	}

	@Test
	@WithMockUser
	void getUpdateUserTest() throws Exception {
		this.mock.perform(get("/updateUser/testdata@sample.com"))
		.andExpect(status().isOk())
		.andExpect(model().hasNoErrors())
		.andExpect(view().name("login/homeLayout"))
		.andExpect(content().string(containsString("testdata@sample.com")));
	}

	@Test
	@WithMockUser
	void postUpdateUserTest() throws Exception {
		this.mock.perform(post("/updateUser").param("userId", "testdata@sample.com").param("userName", "testuser2"))
		.andExpect(status().isOk())
		.andExpect(model().hasNoErrors())
		.andExpect(model().attribute("result", "ユーザー情報を1件更新しました。"))
		.andExpect(view().name("login/homeLayout"));
		assertThat(mapper.selectOneUser("testdata@sample.com").getUserName(),is("testuser2"));
	}

	@Test
	@WithMockUser
	void postDeleteUserTest() throws Exception {
		this.mock.perform(post("/deleteUser/testdata@sample.com").param("userId","testdata@sample.com"))
		.andExpect(status().isOk())
		.andExpect(model().hasNoErrors())
		.andExpect(model().attribute("result", "ユーザー情報を1件削除しました。"))
		.andExpect(view().name("login/homeLayout"));
		assertThat(mapper.selectOneUser("testdata@sample.com"),is(nullValue()));
	}
}
