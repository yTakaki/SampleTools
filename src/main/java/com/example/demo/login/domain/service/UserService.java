package com.example.demo.login.domain.service;

import java.util.List;

import com.example.demo.login.domain.model.User;

public interface UserService {

	public boolean insertUser(User user);

	public User selectOneUser(String userId);

	public List<User> selectAllUser();

	public boolean updateUser(User user);

	public boolean deleteUser(String userId);

	public List<User> searchUser(String userId,String userName);

	public List<User> searchUserId(String userId);

	public List<User> searchUserName(String userName);
}
