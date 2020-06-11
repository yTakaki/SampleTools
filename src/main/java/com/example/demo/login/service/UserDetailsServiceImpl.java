package com.example.demo.login.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.login.model.LoginUser;
import com.example.demo.login.repository.LoginUserMapper;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private LoginUserMapper mapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		LoginUser user = mapper.selectOneUser(username);

		if (user == null) {
            throw new UsernameNotFoundException("User[" + username + "] was not found in the database");
        }

		//権限のリスト
        //AdminやUserなどが存在するが、今回は利用しないのでUSERのみで設定
        //権限を利用する場合は、DB上で権限テーブル、ユーザ権限テーブルを作成し管理が必要
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        GrantedAuthority authority = new SimpleGrantedAuthority("USER");
        grantList.add(authority);

        //UserDetailsはインタフェースなのでUserクラスのコンストラクタで生成したユーザオブジェクトをキャスト(パスワードは登録時に暗号化済み）
        UserDetails userDetails =
        		(UserDetails)new User(user.getUserId(),user.getPassword(),grantList);

        return userDetails;
	}
}
