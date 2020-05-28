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
public class RegistComponentFormTest {

	@Autowired
	Validator validator;

	private RegistComponentForm form = new RegistComponentForm();
	private BindingResult bind = new BindException(form,"RegistComponentForm");

	@BeforeEach
	public void beforeTest() {
		form.setComponentId("12345678"); // 8 letter
		form.setComponentCd("ABCDEFGHIJKLMNOPQRST"); // 20 letter
		form.setComponentName("testdatatestdatatestdatatestdatatestdatatestdatate"); // 50 letter
		form.setFoodFlag(true);
		form.setComponentStatus(0);
	}

	@Test
	void noError() throws Exception {
		validator.validate(form,bind);
		assertThat(bind.getFieldError(),is(nullValue()));
	}

	@Test
	void componentIdがnull() throws Exception {
		form.setComponentId(null);
		validator.validate(form,bind);
		assertThat(bind.getFieldError().getField(),is("componentId"));
		assertThat(bind.getFieldError().getDefaultMessage(),is("must not be blank"));
	}

	@Test
	void componentIdが9文字() throws Exception {
		form.setComponentId("123456789"); // 9 letter
		validator.validate(form,bind);
		assertThat(bind.getFieldError().getField(),is("componentId"));
		assertThat(bind.getFieldError().getDefaultMessage(),is("size must be between 0 and 8"));
	}

	@Test
	void componentCdが空文字() throws Exception {
		form.setComponentCd("");
		validator.validate(form,bind);
		assertThat(bind.getFieldError().getField(),is("componentCd"));
		assertThat(bind.getFieldError().getDefaultMessage(),is("must not be blank"));
	}

	@Test
	void componentCdが21文字() throws Exception {
		form.setComponentCd("ABCDEFGHIJKLMNOPQRSTU"); // 21 letter
		validator.validate(form,bind);
		assertThat(bind.getFieldError().getField(),is("componentCd"));
		assertThat(bind.getFieldError().getDefaultMessage(),is("size must be between 0 and 20"));
	}

	@Test
	void componentNameが半角スペースのみ() throws Exception {
		form.setComponentName(" ");
		validator.validate(form,bind);
		assertThat(bind.getFieldError().getField(),is("componentName"));
		assertThat(bind.getFieldError().getDefaultMessage(),is("must not be blank"));
	}

	@Test
	void componentNameが51文字() throws Exception {
		form.setComponentName("testdatatestdatatestdatatestdatatestdatatestdatates"); // 51 letter
		validator.validate(form,bind);
		assertThat(bind.getFieldError().getField(),is("componentName"));
		assertThat(bind.getFieldError().getDefaultMessage(),is("size must be between 0 and 50"));
	}
}
