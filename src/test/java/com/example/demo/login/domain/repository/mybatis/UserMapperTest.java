package com.example.demo.login.domain.repository.mybatis;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import com.example.demo.login.domain.model.User;

@MybatisTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class UserMapperTest {

	@Autowired
	private UserMapper mapper;

	@Autowired
	private NamedParameterJdbcOperations jdbc;

	@Test
	void insertTest() throws Exception {
		// setup
		User user = new User();
		user.setUserId("testdata@sample.com");
		user.setPassword("password");
		user.setUserName("testuser");
		// execute
		mapper.insertUser(user);
		User actual = jdbc.queryForObject("SELECT * FROM login_user WHERE user_id = :user_id",
				new MapSqlParameterSource("user_id",user.getUserId()),
				new BeanPropertyRowMapper<>(User.class));
		// assertion
		assertThat(actual.getPassword(),is("password"));
		assertThat(actual.getUserName(),is("testuser"));
	}

	/*
	@Test
	@Sql(statements="INSERT INTO login_user(user_id,password,user_name)"
			+ " VALUES ('testdata@sample.com','password','testuser')")
	void selectOneUserTest() throws Exception {
		// setup
		String userId = "testdata@sample.com";
		// execution
		User actual = mapper.selectOneUser(userId);
		// assertion
		assertThat(actual.getUserId(),is("testdata@sample.com"));
		assertThat(actual.getPassword(),is("password"));
		assertThat(actual.getUserName(),is("testuser"));
	}
	*/
}
