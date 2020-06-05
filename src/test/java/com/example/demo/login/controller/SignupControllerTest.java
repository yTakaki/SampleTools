package com.example.demo.login.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.login.domain.service.LoginUserService;
import com.example.demo.login.domain.service.UserDetailsServiceImpl;

@WebMvcTest(controllers = SignupController.class)
public class SignupControllerTest {

	@Autowired
	private MockMvc mock;

	@MockBean
	private UserDetailsServiceImpl user;

	@MockBean
	private LoginUserService service;

	@Test
	void ユーザー登録ページへのリクエストに対して正常に画面が返されること() throws Exception {
		mock.perform(get("/signup"))
		.andExpect(status().isOk())
		.andExpect(view().name("login/signup"));
	}

	@Test
	void ユーザー登録ページにて適正にフォーム入力して登録ボタンを押すとログイン画面へリダイレクトが返されること() throws Exception {
		mock.perform(post("/signup").with(csrf()).param("userId", "testdata@sample.com")
				.param("password", "pass").param("userName", "testuser"))
		.andExpect(status().is3xxRedirection())
		.andExpect(model().hasNoErrors())
		.andExpect(view().name("redirect:/login"));

		verify(service,times(1)).insertUser(any());
	}

	@Test
	void ユーザー登録フォームのユーザーIDがNullのとき例外情報が画面に表示されること() throws Exception {
		mock.perform(post("/signup").with(csrf())
				.param("password", "pass").param("userName", "testuser"))
		.andExpect(status().isOk())
		.andExpect(model().hasErrors())
		.andExpect(view().name("login/signup"));
	}

	@Test
	void ユーザー登録フォームのユーザーIDが空文字のとき例外情報が画面に表示されること() throws Exception {
		mock.perform(post("/signup").with(csrf()).param("userId", "")
				.param("password", "pass").param("userName", "testuser"))
		.andExpect(status().isOk())
		.andExpect(model().hasErrors())
		.andExpect(view().name("login/signup"));
	}

	@Test
	void ユーザー登録フォームのユーザーIDが空白のとき例外情報が画面に表示されること() throws Exception {
		mock.perform(post("/signup").with(csrf()).param("userId"," ")
				.param("password", "pass").param("userName", "testuser"))
		.andExpect(status().isOk())
		.andExpect(model().hasErrors())
		.andExpect(view().name("login/signup"));
	}

	@Test
	void ユーザー登録フォームのユーザーIDがEメール形式でないとき例外情報が画面に表示されること() throws Exception {
		mock.perform(post("/signup").with(csrf()).param("userId", "sample")
				.param("password", "pass").param("userName", "testuser"))
		.andExpect(status().isOk())
		.andExpect(model().hasErrors())
		.andExpect(view().name("login/signup"));
	}

	@Test
	void ユーザー登録フォームのパスワードがNullのとき例外情報が画面に表示されること() throws Exception {
		mock.perform(post("/signup").with(csrf()).param("userId", "testdata@sample.com").param("userName", "testuser"))
		.andExpect(status().isOk())
		.andExpect(model().hasErrors())
		.andExpect(view().name("login/signup"));
	}

	@Test
	void ユーザー登録フォームのパスワードが空文字のとき例外情報が画面に表示されること() throws Exception {
		mock.perform(post("/signup").with(csrf()).param("userId", "testdata@sample.com")
				.param("password", "").param("userName", "testuser"))
		.andExpect(status().isOk())
		.andExpect(model().hasErrors())
		.andExpect(view().name("login/signup"));
	}

	@Test
	void ユーザー登録フォームのパスワードが空白のとき例外情報が画面に表示されること() throws Exception {
		mock.perform(post("/signup").with(csrf()).param("userId","testdata@sample.com")
				.param("password", " ").param("userName", "testuser"))
		.andExpect(status().isOk())
		.andExpect(model().hasErrors())
		.andExpect(view().name("login/signup"));
	}

	@Test
	void ユーザー登録フォームのパスワードが3文字のとき例外情報が画面に表示されること() throws Exception {
		mock.perform(post("/signup").with(csrf()).param("userId", "testdata@sample.com")
				.param("password", "pas").param("userName", "testuser"))
		.andExpect(status().isOk())
		.andExpect(model().hasErrors())
		.andExpect(view().name("login/signup"));
	}

	@Test
	void ユーザー登録フォームのパスワードが17文字のとき例外情報が画面に表示されること() throws Exception {
		mock.perform(post("/signup").with(csrf()).param("userId", "testdata@sample.com")
				.param("password", "abcdefghijklmnopq").param("userName", "testuser"))
		.andExpect(status().isOk())
		.andExpect(model().hasErrors())
		.andExpect(view().name("login/signup"));
	}

	@Test
	void ユーザー登録フォームのユーザー名がNullのとき例外情報が画面に表示されること() throws Exception {
		mock.perform(post("/signup").with(csrf()).param("userId", "testdata@sample.com").param("password", "pass"))
		.andExpect(status().isOk())
		.andExpect(model().hasErrors())
		.andExpect(view().name("login/signup"));
	}

	@Test
	void ユーザー登録フォームのユーザー名が空文字のとき例外情報が画面に表示されること() throws Exception {
		mock.perform(post("/signup").with(csrf()).param("userId", "testdata@sample.com")
				.param("password", "pass").param("userName", ""))
		.andExpect(status().isOk())
		.andExpect(model().hasErrors())
		.andExpect(view().name("login/signup"));
	}

	@Test
	void ユーザー登録フォームのユーザー名が空白のとき例外情報が画面に表示されること() throws Exception {
		mock.perform(post("/signup").with(csrf()).param("userId","testdata@sample.com")
				.param("password", "pass").param("userName", " "))
		.andExpect(status().isOk())
		.andExpect(model().hasErrors())
		.andExpect(view().name("login/signup"));
	}

	@Test
	void ユーザー登録フォームのユーザー名が21文字のとき例外情報が画面に表示されること() throws Exception {
		mock.perform(post("/signup").with(csrf()).param("userId", "testdata@sample.com")
				.param("password", "pass").param("userName", "abcdefghijklmnopqrstu"))
		.andExpect(status().isOk())
		.andExpect(model().hasErrors())
		.andExpect(view().name("login/signup"));
	}

}
