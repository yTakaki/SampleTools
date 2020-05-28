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

import com.example.demo.manage.domain.model.Component;
import com.example.demo.manage.domain.repository.mybatis.ComponentMapper;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class ComponentMasterControllerTest {

	@Autowired
	private MockMvc mock;

	@Autowired
	private ComponentMapper mapper;

	@BeforeEach
	void beforeTest() throws Exception {
		mapper.deleteAllComponent();
		mapper.insertComponent(new Component("99999999","TEST","testdata",true,0));
	}

	@Test
	@WithMockUser
	void getComponentMasterTest() throws Exception {
		this.mock.perform(get("/componentMaster"))
		.andExpect(status().isOk())
		.andExpect(view().name("login/homeLayout"))
		.andExpect(content().string(containsString("構成品検索画面")));
	}

	@Test
	@WithMockUser
	void getComponentListTest() throws Exception {
		this.mock.perform(get("/componentList"))
		.andExpect(status().isOk())
		.andExpect(view().name("login/homeLayout"))
		.andExpect(model().attribute("result", "合計1件の構成品を抽出しました。"))
		.andExpect(content().string(containsString("testdata")))
		.andExpect(content().string(containsString("食品")))
		.andExpect(content().string(containsString("取扱い可能")));
	}

	@Test
	@WithMockUser
	void getRegistComponentTest() throws Exception {
		this.mock.perform(get("/registComponent"))
		.andExpect(status().isOk())
		.andExpect(view().name("login/homeLayout"))
		.andExpect(content().string(containsString("構成品登録画面")));
	}
}
