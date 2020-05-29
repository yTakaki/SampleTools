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
public class RegistItemFormTest {

	@Autowired
	Validator validator;

	private RegistItemForm form = new RegistItemForm();
	private BindingResult bind = new BindException(form,"RegistItemForm");

	@BeforeEach
	public void beforeTest() {
		form.setItemId("12345678"); // 8 letter
		form.setItemCd("ABCDEFGHIJKLMNOPQRST"); // 20 letter
		form.setItemName("testdatatestdatatestdatatestdatatestdatatestdatate"); // 50 letter
		form.setCompositeFlag(true);
		form.setItemStatus(0);
	}

	@Test
	void noError() throws Exception {
		validator.validate(form,bind);
		assertThat(bind.getFieldError(),is(nullValue()));
	}

	@Test
	void componentIdがnull() throws Exception {
		form.setItemId(null);
		validator.validate(form,bind);
		assertThat(bind.getFieldError().getField(),is("itemId"));
		assertThat(bind.getFieldError().getDefaultMessage(),is("must not be blank"));
	}

	@Test
	void componentIdが9文字() throws Exception {
		form.setItemId("123456789"); // 9 letter
		validator.validate(form,bind);
		assertThat(bind.getFieldError().getField(),is("itemId"));
		assertThat(bind.getFieldError().getDefaultMessage(),is("size must be between 0 and 8"));
	}

	@Test
	void componentCdが空文字() throws Exception {
		form.setItemCd("");
		validator.validate(form,bind);
		assertThat(bind.getFieldError().getField(),is("itemCd"));
		assertThat(bind.getFieldError().getDefaultMessage(),is("must not be blank"));
	}

	@Test
	void componentCdが21文字() throws Exception {
		form.setItemCd("ABCDEFGHIJKLMNOPQRSTU"); // 21 letter
		validator.validate(form,bind);
		assertThat(bind.getFieldError().getField(),is("itemCd"));
		assertThat(bind.getFieldError().getDefaultMessage(),is("size must be between 0 and 20"));
	}

	@Test
	void componentNameが半角スペースのみ() throws Exception {
		form.setItemName(" ");
		validator.validate(form,bind);
		assertThat(bind.getFieldError().getField(),is("itemName"));
		assertThat(bind.getFieldError().getDefaultMessage(),is("must not be blank"));
	}

	@Test
	void componentNameが51文字() throws Exception {
		form.setItemName("testdatatestdatatestdatatestdatatestdatatestdatates"); // 51 letter
		validator.validate(form,bind);
		assertThat(bind.getFieldError().getField(),is("itemName"));
		assertThat(bind.getFieldError().getDefaultMessage(),is("size must be between 0 and 50"));
	}
}
