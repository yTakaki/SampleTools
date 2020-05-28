package com.example.demo.login.domain.repository.mybatis;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.test.context.jdbc.Sql;

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
		{
			// setup
			User user = new User();
			user.setUserId("testdata@sample.com");
			user.setPassword("password");
			user.setUserName("testuser");
			// execute
			mapper.deleteUser("testdata@sample.com");
			mapper.insertUser(user);
			User actual = jdbc.queryForObject("SELECT * FROM login_user WHERE user_id = :user_id",
					new MapSqlParameterSource("user_id",user.getUserId()),
					new BeanPropertyRowMapper<>(User.class));
			// assertion
			assertThat(actual.getPassword(),is("password"));
			assertThat(actual.getUserName(),is("testuser"));
		}
	}


	@Test
	@Sql(statements= {
			"DELETE FROM login_user",
			"INSERT INTO login_user VALUES ('testdata@sample.com','pass','testuser')"
	})
	void selectOneUserTest() throws Exception {
		{
			// setup
			String id = "testdata@sample.com";
			// execution
			User actual = mapper.selectOneUser(id);
			// assertion
			assertThat(actual.getUserId(),is("testdata@sample.com"));
			assertThat(actual.getPassword(),is("pass"));
			assertThat(actual.getUserName(),is("testuser"));
		}
	}

	@Test
	@Sql(statements= {
			"DELETE FROM login_user",
			"INSERT INTO login_user VALUES ('testdata@sample.com','pass','testuser')"
	})
	void selectAllUserTest() throws Exception {
		{
			// execution
			List<User> actual = mapper.selectAllUser();
			// assertion
			assertThat(actual.size(),is(1));
			assertThat(actual.get(0).getUserId(),is("testdata@sample.com"));
			assertThat(actual.get(0).getPassword(),is("pass"));
			assertThat(actual.get(0).getUserName(),is("testuser"));
		}
	}

	@Test
	@Sql(statements= {
			"DELETE FROM login_user",
			"INSERT INTO login_user VALUES ('testdata@sample.com','pass','testuser')"
	})
	void updateUserTest() throws Exception {
		{
			// setup
			User user = new User();
			user.setUserId("testdata@sample.com");
			user.setPassword("pass");
			user.setUserName("user"); // change
			// execution
			mapper.updateUser(user);
			User actual = jdbc.queryForObject("SELECT * FROM login_user WHERE user_id = :user_id",
					new MapSqlParameterSource("user_id",user.getUserId()),
					new BeanPropertyRowMapper<>(User.class));
			// assertion
			assertThat(actual.getUserName(),is("user"));
		}
	}

	@Test
	@Sql(statements= {
			"DELETE FROM login_user",
			"INSERT INTO login_user VALUES ('testdata@sample.com','pass','testuser')"
	})
	void deleteUserTest() throws Exception {
		{
			// setup
			String id = "testdata@sample.com";
			// execution
			boolean actual = mapper.deleteUser(id);
			// assertion
			assertThat(actual,is(true));
			assertThat(mapper.selectOneUser(id),is(nullValue()));
		}
	}

	@Test
	@Sql(statements={
			"DELETE FROM login_user",
			"INSERT INTO login_user VALUES ('testdata@sample.com','pass','testuser')"
	})
	void searchUserTest() throws Exception {
		{
			String userId = "test";
			String userName = "test";
			List<User> actual = mapper.searchUser(userId,userName);
			assertThat(actual.size(),is(1));
			assertThat(actual.get(0).getUserId(),is("testdata@sample.com"));
			assertThat(actual.get(0).getPassword(),is("pass"));
			assertThat(actual.get(0).getUserName(),is("testuser"));
		}
	}

	@Test
	@Sql(statements={
			"DELETE FROM login_user",
			"INSERT INTO login_user VALUES ('testdata@sample.com','pass','testuser')"
	})
	void searchUserIdTest() throws Exception {
		{
			String userId = "test";
			List<User> actual = mapper.searchUserId(userId);
			assertThat(actual.size(),is(1));
			assertThat(actual.get(0).getUserId(),is("testdata@sample.com"));
			assertThat(actual.get(0).getPassword(),is("pass"));
			assertThat(actual.get(0).getUserName(),is("testuser"));
		}
	}

	@Test
	@Sql(statements={
			"DELETE FROM login_user",
			"INSERT INTO login_user VALUES ('testdata@sample.com','pass','testuser')"
	})
	void searchUserNameTest() throws Exception {
		{
			String userName = "test";
			List<User> actual = mapper.searchUserName(userName);
			assertThat(actual.size(),is(1));
			assertThat(actual.get(0).getUserId(),is("testdata@sample.com"));
			assertThat(actual.get(0).getPassword(),is("pass"));
			assertThat(actual.get(0).getUserName(),is("testuser"));
		}
	}

	@Test
	@Sql(statements= {
			"DELETE FROM login_user",
			"INSERT INTO login_user VALUES ('testdata@sample.com','pass','testuser')"
	})
	void deleteAllUserTest() throws Exception {
		Boolean actual = mapper.deleteAllUser();
		assertThat(actual,is(true));
		assertThat(mapper.selectAllUser().size(),is(0));
	}

}
