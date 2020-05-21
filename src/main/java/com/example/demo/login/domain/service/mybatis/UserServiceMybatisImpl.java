package com.example.demo.login.domain.service.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.mybatis.UserMapper;
import com.example.demo.login.domain.service.UserService;

@Transactional
@Service("UserServiceMybatisImpl")
public class UserServiceMybatisImpl implements UserService {

	@Autowired
	UserMapper userMapper;

	@Override
	public boolean insertUser(User user) {
		return userMapper.insertUser(user);
	}

	@Override
	public User selectOneUser(String userId) {
		return userMapper.selectOneUser(userId);
	}

	@Override
	public List<User> selectAllUser() {
		return userMapper.selectAllUser();
	}

	@Override
	public boolean updateUser(User user) {
		return userMapper.updateUser(user);
	}

	@Override
	public boolean deleteUser(String userId) {
		return userMapper.deleteUser(userId);
	}
}
