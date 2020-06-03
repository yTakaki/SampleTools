package com.example.demo.login.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserMapper;

@Transactional
@Service
public class UserService {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserMapper mapper;

	public boolean insertUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return mapper.insertUser(user);
	}

	public User selectOneUser(String userId) {
		return mapper.selectOneUser(userId);
	}

	public List<User> selectAllUser() {
		return mapper.selectAllUser();
	}

	public boolean updateUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return mapper.updateUser(user);
	}

	public boolean deleteUser(String userId) {
		return mapper.deleteUser(userId);
	}

	public List<User> searchUser(String userId,String userName) {
		return mapper.searchUser(userId,userName);
	}

}
