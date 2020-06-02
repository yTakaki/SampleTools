package com.example.demo.login.domain.repository;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.TestPropertySource;

import com.example.demo.login.domain.model.User;

@MybatisTest
@TestPropertySource(locations = "classpath:test.properties")
public class UserMapperTest {

	@Autowired
	private UserMapper sut;

	@Test // selectOneUser
	void 登録済みデータを指定検索できること() throws Exception {
		User expect = new User("testdata@sample.com","pass","testuser");
		User actual = sut.selectOneUser("testdata@sample.com");
		assertThat(actual,is(expect));
	}

	@Test // selectOneUser -> Error
	void 未登録データを指定検索するとNullを返すこと() throws Exception {
		User actual = sut.selectOneUser("NoData@sample.com");
		assertThat(actual,is(nullValue()));
	}

	@Test // selectOneUser -> Error
	void Nullで指定検索するとNullを返すこと() throws Exception {
		User actual = sut.selectOneUser(null);
		assertThat(actual,is(nullValue()));
	}

	@Test // selectAllUser
	void 登録済みデータを全件検索できること() throws Exception {
		List<User> actual = sut.selectAllUser();
		assertThat(actual.size(),is(1));
	}

	@Test // insertUser
	void 未登録データを新規登録できること() throws Exception {
		User expect = new User("testdata2@sample.com","pass","testuser2");
		sut.insertUser(expect);
		assertThat(sut.selectOneUser("testdata2@sample.com"),is(expect));
	}

	@Test // insertUser -> Error
	void 登録済みのユーザーIDで新規登録するとDuplicateKeyExceptionを返すこと() throws Exception {
		User expect = new User("testdata@sample.com","pass","testuser2");
		assertThrows(DuplicateKeyException.class,() -> sut.insertUser(expect));
	}

	@Test
	void Nullで登録しようとするとDataIntegrityViolationExceptionを返すこと() throws Exception {
		assertThrows(DataIntegrityViolationException.class,() -> sut.insertUser(null));
	}


	@Test // updateUser
	void 登録済みデータを更新できること() throws Exception {
		User expect = new User("testdata@sample.com","pass","testuser2");
		sut.updateUser(expect);
		User actual = sut.selectOneUser("testdata@sample.com");
		assertThat(actual,is(expect));
	}

	@Test // deleteUser
	void 登録済みデータを削除できること() throws Exception {
		sut.deleteUser("testdata@sample.com");
		User actual = sut.selectOneUser("testdata@sample.com");
		assertThat(actual,is(nullValue()));
	}

}
