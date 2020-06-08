package com.example.demo.manage.controller;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.login.domain.model.LoginUser;
import com.example.demo.login.domain.service.LoginUserService;
import com.example.demo.master.controller.UserMasterController;

@WebMvcTest(controllers = UserMasterController.class)
public class UserMasterControllerTest {

	@Autowired
	private MockMvc mock;

	@MockBean
	private LoginUserService service;

	@Test // getUserMaster
	@WithMockUser
	void ユーザー閲覧ページへのリクエストに対して正常な画面が返されること() throws Exception {
		mock.perform(get("/userMaster"))
		.andExpect(status().isOk())
		.andExpect(view().name("login/homeLayout"))
		.andExpect(content().string(containsString("ユーザー閲覧画面")));
	}

	@Test // postUserSearch
	@WithMockUser
	void ユーザー閲覧ページにて適正に検索フォームを入力して検索ボタンを押すと検索処理が実行されて正常に画面が返されること() throws Exception {
		mock.perform(post("/userSearch").param("userId", "").param("userName", ""))
		.andExpect(status().isOk())
		.andExpect(view().name("login/homeLayout"))
		.andExpect(content().string(containsString("ユーザー閲覧画面")))
		.andExpect(content().string(containsString("ユーザー情報を取得しました。")));

		verify(service,times(1)).searchUser(anyString(),anyString());
	}

	@Test // postUserSearch -> Error
	@WithMockUser
	void ユーザー閲覧ページの検索フォームにてユーザーIDがNullのとき検索ボタンを押すと例外情報が画面に返されること() throws Exception {
		mock.perform(post("/userSearch").param("userName", ""))
		.andExpect(status().isOk())
		.andExpect(model().hasErrors())
		.andExpect(view().name("login/homeLayout"))
		.andExpect(content().string(containsString("ユーザー閲覧画面")));
	}

	@Test // postUserSearch -> Error
	@WithMockUser
	void ユーザー閲覧ページの検索フォームにてユーザー名がNullのとき検索ボタンを押すと例外情報が画面に返されること() throws Exception {
		mock.perform(post("/userSearch").param("userId", ""))
		.andExpect(status().isOk())
		.andExpect(model().hasErrors())
		.andExpect(view().name("login/homeLayout"))
		.andExpect(content().string(containsString("ユーザー閲覧画面")));
	}

	@Test // getUpdateUser
	@WithMockUser
	void ユーザー編集ページへのリクエストに対して正常に画面が返されること() throws Exception {
		when(service.selectOneUser(anyString())).thenReturn(new LoginUser("testdata@sample.com","pass","testuser"));
		mock.perform(get("/updateUser/{id}","testdata@sample.com"))
		.andExpect(status().isOk())
		.andExpect(view().name("login/homeLayout"))
		.andExpect(content().string(containsString("ユーザー編集画面")));

		verify(service,times(1)).selectOneUser(anyString());
	}

	@Test // postUpdateUser
	@WithMockUser
	void ユーザー編集ページのユーザー編集フォームに適正に入力して更新ボタンを押すと更新処理が実行されて正常に画面が返されること() throws Exception {
		when(service.selectOneUser(anyString())).thenReturn(new LoginUser("testdata@sample.com","pass","testuser"));
		when(service.updateUser(any())).thenReturn(true);
		mock.perform(post("/updateUser")
				.param("userId", "testdata@sample.com").param("password", "pass").param("userName","testuser2"))
		.andExpect(status().isOk())
		.andExpect(view().name("login/homeLayout"))
		.andExpect(content().string(containsString("ユーザー閲覧画面")))
		.andExpect(content().string(containsString("ユーザー情報を1件更新しました。")));

		verify(service,times(1)).updateUser(any());
	}

	@Test // postUpdateUser -> Error
	@WithMockUser
	void ユーザー編集ページのユーザー編集フォームにてユーザー名がNullのとき更新ボタンを押すと例外情報が画面に返されること() throws Exception {
		when(service.selectOneUser(anyString())).thenReturn(new LoginUser("testdata@sample.com","pass","testuser"));
		when(service.updateUser(any())).thenReturn(true);
		mock.perform(post("/updateUser")
				.param("userId", "testdata@sample.com").param("password", "pass"))
		.andExpect(status().isOk())
		.andExpect(model().hasErrors())
		.andExpect(view().name("login/homeLayout"));
	}

	@Test // postUpdateUser -> Error
	@WithMockUser
	void ユーザー編集ページのユーザー編集フォームにてユーザー名が空文字のとき更新ボタンを押すと例外情報が画面に返されること() throws Exception {
		when(service.selectOneUser(anyString())).thenReturn(new LoginUser("testdata@sample.com","pass","testuser"));
		when(service.updateUser(any())).thenReturn(true);
		mock.perform(post("/updateUser")
				.param("userId", "testdata@sample.com").param("password", "pass").param("userName",""))
		.andExpect(status().isOk())
		.andExpect(model().hasErrors())
		.andExpect(view().name("login/homeLayout"));
	}

	@Test // postUpdateUser -> Error
	@WithMockUser
	void ユーザー編集ページのユーザー編集フォームにてユーザー名が空白のとき更新ボタンを押すと例外情報が画面に返されること() throws Exception {
		when(service.selectOneUser(anyString())).thenReturn(new LoginUser("testdata@sample.com","pass","testuser"));
		when(service.updateUser(any())).thenReturn(true);
		mock.perform(post("/updateUser")
				.param("userId", "testdata@sample.com").param("password", "pass").param("userName"," "))
		.andExpect(status().isOk())
		.andExpect(model().hasErrors())
		.andExpect(view().name("login/homeLayout"));
	}

	@Test // postUpdateUser -> Error
	@WithMockUser
	void ユーザー編集ページのユーザー編集フォームにてユーザー名が21文字のとき更新ボタンを押すと例外情報が画面に返されること() throws Exception {
		when(service.selectOneUser(anyString())).thenReturn(new LoginUser("testdata@sample.com","pass","testuser"));
		when(service.updateUser(any())).thenReturn(true);
		mock.perform(post("/updateUser").param("userId", "testdata@sample.com")
				.param("password", "pass").param("userName","abcdefghijklmnopqrstu"))
		.andExpect(status().isOk())
		.andExpect(model().hasErrors())
		.andExpect(view().name("login/homeLayout"));
	}

	@Test
	@WithMockUser
	void ユーザー閲覧ページの削除ボタンを押すと削除処理が実行されて正常に画面が返されること() throws Exception {
		when(service.deleteUser(anyString())).thenReturn(true);
		mock.perform(post("/deleteUser/{id}","testdata@sample.com"))
		.andExpect(status().isOk())
		.andExpect(view().name("login/homeLayout"))
		.andExpect(content().string(containsString("ユーザー閲覧画面")))
		.andExpect(content().string(containsString("ユーザー情報を1件削除しました。")));

		verify(service,times(1)).deleteUser(anyString());
	}
}
