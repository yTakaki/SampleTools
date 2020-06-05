package com.example.demo.login.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.LoginUser;
import com.example.demo.login.domain.repository.LoginUserMapper;

@Transactional
@Service
public class LoginUserService {

	@Autowired
	LoginUserMapper mapper;

	//rawDataのパスワードは渡すことができないので、暗号化
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	public boolean insertUser(LoginUser user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return mapper.insertUser(user);
	}

	public LoginUser selectOneUser(String userId) {
		return mapper.selectOneUser(userId);
	}

	public List<LoginUser> selectAllUser() {
		return mapper.selectAllUser();
	}

	public boolean updateUser(LoginUser user) {
		return mapper.updateUser(user);
	}

	public boolean deleteUser(String userId) {
		return mapper.deleteUser(userId);
	}

	public List<LoginUser> searchUser(String userId,String userName) {
		return mapper.searchUser(userId,userName);
	}

}
