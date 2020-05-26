package com.example.demo.login.domain.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.login.domain.model.User;

@Mapper
public interface UserMapper {

	public boolean insertUser(User user);

	public User selectOneUser(String userId);

	public List<User> selectAllUser();

	public boolean updateUser(User user);

	public boolean deleteUser(String userId);

	public List<User> searchUser(@Param("userId")String userId,@Param("userName")String userName);

	public List<User> searchUserId(String userId);

	public List<User> searchUserName(String userName);

	public boolean deleteAllUser(); // for junit test.

}
