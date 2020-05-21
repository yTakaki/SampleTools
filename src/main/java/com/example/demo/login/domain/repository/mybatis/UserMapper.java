package com.example.demo.login.domain.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.login.domain.model.User;

@Mapper
public interface UserMapper {

	public boolean insertUser(User user);

	public User selectOneUser(String userId);

	public List<User> selectAllUser();

	public boolean updateUser(User user);

	public boolean deleteUser(String userId);

}
