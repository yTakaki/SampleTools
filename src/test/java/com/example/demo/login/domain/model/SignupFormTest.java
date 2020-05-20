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
	void userIdがnull() throws Exception {
		signupForm.setUserId(null);
		validator.validate(signupForm,bindingResult);
		assertThat(bindingResult.getFieldError().getField(),is("userId"));
		assertThat(bindingResult.getFieldError().getDefaultMessage(),is("must not be blank"));
	}
}
