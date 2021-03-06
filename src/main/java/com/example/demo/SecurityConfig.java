package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.login.service.UserDetailsServiceImpl;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private UserDetailsServiceImpl service;

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/webjars/**","/css/**");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {

		// 直リンク設定（ログイン不要）
		http.authorizeRequests().antMatchers("/webjars/**").permitAll()
		.antMatchers("/css/**").permitAll()
		.antMatchers("/login").permitAll()
		.antMatchers("/signup").permitAll() //.antMatchers("/**").permitAll() // ログイン実装までの許可
		.anyRequest().authenticated();

		// ログイン処理
		http.formLogin()
		.loginProcessingUrl("/login")
		.loginPage("/login")
		.failureUrl("/login")
		.usernameParameter("userId")
		.passwordParameter("password")
		.defaultSuccessUrl("/home", true);

		// ログアウト処理
		http.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutUrl("/logout")
		.logoutSuccessUrl("/login");

	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(service).passwordEncoder(passwordEncoder());
	}

}
