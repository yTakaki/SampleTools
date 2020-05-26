package com.example.demo.login.domain.service;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.mybatis.UserMapper;

@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class UserServiceTest {

	@Autowired
	private UserService service;

	@Autowired
	private UserMapper mapper;

	@Autowired
	private NamedParameterJdbcOperations jdbc;

	@BeforeEach
	void beforeTest() throws Exception {
		mapper.deleteAllUser();
		User user = new User();
		user.setUserId("testdata@sample.com");
		user.setPassword("pass");
		user.setUserName("testuser");
		mapper.insertUser(user);
	}

	@Test
	void insertUserTest() throws Exception {
		User user = new User();
		user.setUserId("testdata@sample.com");
		user.setPassword("pass");
		user.setUserName("testdata");
		mapper.deleteAllUser();
		service.insertUser(user);
		User actual = jdbc.queryForObject("SELECT * FROM login_user WHERE user_id = :user_id",
				new MapSqlParameterSource("user_id",user.getUserId()),
				new BeanPropertyRowMapper<>(User.class));
		assertThat(actual.getPassword(),is("pass"));
		assertThat(actual.getUserName(),is("testdata"));
	}

	@Test
	void selectOneUserTest() throws Exception {
		String id = "testdata@sample.com";
		User actual = service.selectOneUser(id);
		assertThat(actual.getUserId(),is("testdata@sample.com"));
		assertThat(actual.getPassword(),is("pass"));
		assertThat(actual.getUserName(),is("testuser"));
	}

	@Test
	void selectAllUserTest() throws Exception {
		List<User> actual = service.selectAllUser();
		assertThat(actual.size(),is(1));
		assertThat(actual.get(0).getUserId(),is("testdata@sample.com"));
		assertThat(actual.get(0).getPassword(),is("pass"));
		assertThat(actual.get(0).getUserName(),is("testuser"));
	}

	@Test
	void updateUserTest() throws Exception {
		User user = new User();
		user.setUserId("testdata@sample.com");
		user.setPassword("pass");
		user.setUserName("user"); // change
		service.updateUser(user);
		User actual = jdbc.queryForObject("SELECT * FROM login_user WHERE user_id = :user_id",
				new MapSqlParameterSource("user_id",user.getUserId()),
				new BeanPropertyRowMapper<>(User.class));
		assertThat(actual.getUserName(),is("user"));
	}

	@Test
	void deleteUserTest() throws Exception {
		String id = "testdata@sample.com";
		boolean actual = service.deleteUser(id);
		assertThat(actual,is(true));
		assertThat(mapper.selectOneUser(id),is(nullValue()));
	}

	@Test
	void searchUserTest() throws Exception {
		String userId = "test";
		String userName = "test";
		List<User> actual = service.searchUser(userId,userName);
		assertThat(actual.size(),is(1));
		assertThat(actual.get(0).getUserId(),is("testdata@sample.com"));
		assertThat(actual.get(0).getPassword(),is("pass"));
		assertThat(actual.get(0).getUserName(),is("testuser"));
	}

	@Test
	void searchUserIdTest() throws Exception {
		String userId = "test";
		List<User> actual = service.searchUserId(userId);
		assertThat(actual.size(),is(1));
		assertThat(actual.get(0).getUserId(),is("testdata@sample.com"));
		assertThat(actual.get(0).getPassword(),is("pass"));
		assertThat(actual.get(0).getUserName(),is("testuser"));
	}

	@Test
	void searchUserNameTest() throws Exception {
		String userName = "test";
		List<User> actual = service.searchUserName(userName);
		assertThat(actual.size(),is(1));
		assertThat(actual.get(0).getUserId(),is("testdata@sample.com"));
		assertThat(actual.get(0).getPassword(),is("pass"));
		assertThat(actual.get(0).getUserName(),is("testuser"));
	}
}
