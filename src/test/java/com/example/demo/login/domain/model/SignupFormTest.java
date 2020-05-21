package com.example.demo.login.domain.model;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

@SpringBootTest
public class SignupFormTest {

	@Autowired
	Validator validator;

	private SignupForm signupForm = new SignupForm();
	private BindingResult bindingResult = new BindException(signupForm,"SignupForm");

	@BeforeEach
	public void beforeTest() {
		signupForm.setUserId("test@sample.co.jp");
		signupForm.setPassword("password");
		signupForm.setUserName("testuser");
	}

	@Test
	void noError() throws Exception {
		validator.validate(signupForm,bindingResult);
		assertThat(bindingResult.getFieldError(),is(nullValue()));
	}

	@Test
	void userIdが空文字() throws Exception {
		signupForm.setUserId("");
		validator.validate(signupForm,bindingResult);
		assertThat(bindingResult.getFieldError().getField(),is("userId"));
		assertThat(bindingResult.getFieldError().getDefaultMessage(),is("must not be blank"));
	}

	@Test
	void userIdがEmail形式でない() throws Exception {
		signupForm.setUserId("sample");
		validator.validate(signupForm,bindingResult);
		assertThat(bindingResult.getFieldError().getField(),is("userId"));
		assertThat(bindingResult.getFieldError().getDefaultMessage(),is("must be a well-formed email address"));
	}

	@Test
	void passwordがnull() throws Exception {
		signupForm.setPassword(null);
		validator.validate(signupForm,bindingResult);
		assertThat(bindingResult.getFieldError().getField(),is("password"));
		assertThat(bindingResult.getFieldError().getDefaultMessage(),is("must not be blank"));
	}

	@Test
	void passwordが3文字() throws Exception {
		signupForm.setPassword("abc");
		validator.validate(signupForm,bindingResult);
		assertThat(bindingResult.getFieldError().getField(),is("password"));
		assertThat(bindingResult.getFieldError().getDefaultMessage(),is("size must be between 4 and 16"));
	}

	@Test
	void passwordが17文字() throws Exception {
		signupForm.setPassword("abcdefghijklmnopq");
		validator.validate(signupForm,bindingResult);
		assertThat(bindingResult.getFieldError().getField(),is("password"));
		assertThat(bindingResult.getFieldError().getDefaultMessage(),is("size must be between 4 and 16"));
	}

	@Test
	void userNameが半角スペース() throws Exception {
		signupForm.setUserName("  ");
		validator.validate(signupForm,bindingResult);
		assertThat(bindingResult.getFieldError().getField(),is("userName"));
		assertThat(bindingResult.getFieldError().getDefaultMessage(),is("must not be blank"));
	}

	@Test
	void userNameが21文字() throws Exception {
		signupForm.setUserName("abcdefghijklmnopqrstu");
		validator.validate(signupForm,bindingResult);
		assertThat(bindingResult.getFieldError().getField(),is("userName"));
		assertThat(bindingResult.getFieldError().getDefaultMessage(),is("size must be between 0 and 20"));
	}

}
