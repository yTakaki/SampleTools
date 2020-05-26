package com.example.demo.manage.domain.model;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

@SpringBootTest
@Transactional
public class UpdateUserFormTest {

	@Autowired
	Validator validator;

	private UpdateUserForm form = new UpdateUserForm();
	private BindingResult bind = new BindException(form,"UpdateUserForm");

	@BeforeEach
	public void beforeTest() {
		form.setUserId("test@sample.co.jp");
		form.setUserName("testuser");
	}

	@Test
	void noError() throws Exception {
		validator.validate(form,bind);
		assertThat(bind.getFieldError(),is(nullValue()));
	}

	@Test
	void userIdが空文字() throws Exception {
		form.setUserId("");
		validator.validate(form,bind);
		assertThat(bind.getFieldError().getField(),is("userId"));
		assertThat(bind.getFieldError().getDefaultMessage(),is("must not be blank"));
	}

	@Test
	void userNameが半角スペース() throws Exception {
		form.setUserName("  ");
		validator.validate(form,bind);
		assertThat(bind.getFieldError().getField(),is("userName"));
		assertThat(bind.getFieldError().getDefaultMessage(),is("must not be blank"));
	}

	@Test
	void userNameが21文字() throws Exception {
		form.setUserName("abcdefghijklmnopqrstu");
		validator.validate(form,bind);
		assertThat(bind.getFieldError().getField(),is("userName"));
		assertThat(bind.getFieldError().getDefaultMessage(),is("size must be between 0 and 20"));
	}
}
