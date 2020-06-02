package com.example.demo.login.domain.service;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserMapper;

@SpringJUnitConfig(classes = UserServiceTest.Config.class)
public class UserServiceTest {

	@ComponentScan({
		"com.example.demo.login.domain.service"
	})
	static class Config {
	}

	@Autowired
	private UserService sut;

	@MockBean
	private UserMapper mapper;

	@Test
	void insertUserが実行されること() throws Exception {
		when(mapper.insertUser(any())).thenReturn(true);
		sut.insertUser(any());

		verify(mapper,times(1)).insertUser(any());
	}

	@Test
	void selectOneUserが実行されること() throws Exception {
		when(mapper.selectOneUser("testdata@sample.com")).thenReturn(any());
		sut.selectOneUser("testdata@sample.com");

		verify(mapper,times(1)).selectOneUser(anyString());
	}

	@Test
	void selectAllUserが実行されること() throws Exception {
		User user = new User("testdata@sample.com","pass","testuser");
		when(mapper.selectAllUser()).thenReturn(List.of(user));
		sut.selectAllUser();

		verify(mapper,times(1)).selectAllUser();
	}

	@Test
	void updateUserが実行されること() throws Exception {
		when(mapper.updateUser(any())).thenReturn(true);
		sut.updateUser(any());

		verify(mapper,times(1)).updateUser(any());
	}

	@Test
	void deleteUserが実行されること() throws Exception {
		when(mapper.deleteUser(any())).thenReturn(true);
		sut.deleteUser(any());

		verify(mapper,times(1)).deleteUser(any());
	}
}
