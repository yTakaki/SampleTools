package com.example.demo.manage.controller;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class ProductMasterControllerTest {

	@Autowired
	private MockMvc mock;

	@Test
	@WithMockUser
	void getItemMasterTest() throws Exception {
		this.mock.perform(get("/itemMaster"))
		.andExpect(status().isOk())
		.andExpect(view().name("login/homeLayout"))
		.andExpect(content().string(containsString("商品検索画面")));
	}
}
