package com.example.demo.login.domain.service;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.example.demo.login.model.LoginUser;
import com.example.demo.login.repository.LoginUserMapper;
import com.example.demo.login.service.UserDetailsServiceImpl;

@SpringJUnitConfig(classes = LoginUserServiceTest.Config.class)
public class UserDetailsServiceImplTest {

	@ComponentScan({
		"com.example.demo.login.domain.service"
	})
	static class Config {
	}

	@Autowired
	private UserDetailsServiceImpl sut;

	@MockBean
	private LoginUserMapper mapper;

	@MockBean
	private PasswordEncoder encoder;

	@Test
	void 登録済みデータに対してuserDetailsが正しく返されること() throws Exception {
		LoginUser expect = new LoginUser("testdata@sample.com","password","testuser");
		when(mapper.selectOneUser(anyString())).thenReturn(expect);
		UserDetails actual = sut.loadUserByUsername(anyString());
		assertThat(actual.getUsername(),is(expect.getUserId()));
		assertThat(actual.getPassword(),is(expect.getPassword()));

		verify(mapper,times(1)).selectOneUser(anyString());
	}

	@Test
	void 未登録データに対してUsernameNotFoundExceptionが返されること() throws Exception {
		when(mapper.selectOneUser(anyString())).thenReturn(null);
		assertThrows(UsernameNotFoundException.class,() -> sut.loadUserByUsername(anyString()));

		verify(mapper,times(1)).selectOneUser(anyString());
	}
}
