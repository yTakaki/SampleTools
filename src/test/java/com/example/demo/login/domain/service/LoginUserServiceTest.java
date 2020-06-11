package com.example.demo.login.domain.service;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.example.demo.login.model.LoginUser;
import com.example.demo.login.repository.LoginUserMapper;
import com.example.demo.login.service.LoginUserService;

@SpringJUnitConfig(classes = LoginUserServiceTest.Config.class)
public class LoginUserServiceTest {

	@ComponentScan({
		"com.example.demo.login.domain.service"
	})
	static class Config {
	}

	@Autowired
	private LoginUserService sut;

	@MockBean
	private LoginUserMapper mapper;

	@MockBean
	private PasswordEncoder encoder;

	@Test
	void passwordをエンコード処理してinsertUserが実行されること() throws Exception {
		LoginUser user = new LoginUser("testdata@sample.com","password","testuser");
		when(mapper.insertUser(user)).thenReturn(true);
		sut.insertUser(user);

		verify(mapper,times(1)).insertUser(any());
		verify(encoder,times(1)).encode(anyString());
	}

	@Test
	void selectOneUserが実行されること() throws Exception {
		when(mapper.selectOneUser("testdata@sample.com")).thenReturn(any());
		sut.selectOneUser("testdata@sample.com");

		verify(mapper,times(1)).selectOneUser(anyString());
	}

	@Test
	void selectAllUserが実行されること() throws Exception {
		LoginUser user = new LoginUser("testdata@sample.com","pass","testuser");
		when(mapper.selectAllUser()).thenReturn(List.of(user));
		sut.selectAllUser();

		verify(mapper,times(1)).selectAllUser();
	}

	@Test
	void updateUserが実行されること() throws Exception {
		LoginUser user = new LoginUser("testdata@sample.com","password","testuser2");
		when(mapper.updateUser(user)).thenReturn(true);
		sut.updateUser(user);

		verify(mapper,times(1)).updateUser(any());
	}

	@Test
	void deleteUserが実行されること() throws Exception {
		when(mapper.deleteUser(any())).thenReturn(true);
		sut.deleteUser(any());

		verify(mapper,times(1)).deleteUser(any());
	}
}
