package com.example.demo.login.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.repository.mybatis.UserMapper;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class SignupControllerTest {

	@Autowired
	private MockMvc mock;

	@Autowired
	private UserMapper mapper;

	@BeforeEach
	void beforeTest() throws Exception {
		mapper.deleteAllUser();
	}

	@Test
	void getSignupTest() throws Exception {

		this.mock.perform(get("/signup"))
		.andExpect(status().isOk())
		.andExpect(view().name("login/signup"));
	}

	@Test
	void postSignupTest() throws Exception {
		this.mock.perform(post("/signup").param("userId", "testdata@sample.com")
				.param("password", "pass").param("userName", "testuser"))
		.andExpect(status().is3xxRedirection())
		.andExpect(model().hasNoErrors())
		.andExpect(view().name("redirect:/login"));
	}

}
