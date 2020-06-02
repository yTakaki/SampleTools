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

import com.example.demo.manage.domain.model.Component;
import com.example.demo.manage.domain.repository.ComponentMapper;

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
	void postComponentSearchTest() throws Exception {
		this.mock.perform(post("/componentSearch")
				.param("componentId", "9999")
				.param("componentCd", "TE")
				.param("componentName", "test")
				.param("foodFlag", "true")
				.param("componentStatus", "0"))
		.andExpect(status().isOk())
		.andExpect(view().name("login/homeLayout"))
		.andExpect(model().attribute("result", "合計1件の構成品を抽出しました。"))
		.andExpect(content().string(containsString("testdata")))
		.andExpect(content().string(containsString("食品")))
		.andExpect(content().string(containsString("取扱い可能")));
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

	@Test
	@WithMockUser
	void postRegistComponentTest() throws Exception {
		this.mock.perform(post("/registComponent")
				.param("componentId","12345678")
				.param("componentCd","TEST2")
				.param("componentName","testdata2")
				.param("foodFlag", "true")
				.param("componentStatus","0"))
		.andExpect(status().isOk())
		.andExpect(view().name("login/homeLayout"))
		.andExpect(model().hasNoErrors())
		.andExpect(model().attribute("result",is("構成品情報を1件登録しました。")));
		assertThat(mapper.selectOneComponent("12345678").getComponentName(),is("testdata2"));
	}

	@Test
	@WithMockUser
	void getComponentDetailTest() throws Exception {
		this.mock.perform(get("/componentDetail/99999999"))
		.andExpect(status().isOk())
		.andExpect(view().name("login/homeLayout"))
		.andExpect(content().string(containsString("99999999")));
	}

	@Test
	@WithMockUser
	void postComponentDetailUpdateTest() throws Exception {
		this.mock.perform(post("/componentDetail/99999999")
				.param("componentId", "99999999")
				.param("componentCd", "TEST")
				.param("componentName","testdata2") // change
				.param("foodFlag","true")
				.param("componentStatus", "0")
				.param("update","update"))
		.andExpect(status().isOk())
		.andExpect(model().hasNoErrors())
		.andExpect(model().attribute("result", "構成品情報を1件更新しました。"))
		.andExpect(view().name("login/homeLayout"));
		assertThat(mapper.selectOneComponent("99999999").getComponentName(),is("testdata2"));
	}

	@Test
	@WithMockUser
	void postComponentDetailDeleteTest() throws Exception {
		this.mock.perform(post("/componentDetail/99999999").param("componentId","99999999").param("delete","delete"))
		.andExpect(status().isOk())
		.andExpect(model().hasNoErrors())
		.andExpect(model().attribute("result", "構成品情報を1件削除しました。"))
		.andExpect(view().name("login/homeLayout"));
		assertThat(mapper.selectOneComponent("99999999"),is(nullValue()));
	}
}
