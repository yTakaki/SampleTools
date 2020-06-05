package com.example.demo.login.domain.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.login.domain.model.LoginUser;

@Mapper
public interface LoginUserMapper {

	public boolean insertUser(LoginUser user);

	public LoginUser selectOneUser(String userId);

	public List<LoginUser> selectAllUser();

	public boolean updateUser(LoginUser user);

	public boolean deleteUser(String userId);

	public List<LoginUser> searchUser(@Param("userId")String userId,@Param("userName")String userName);

}
