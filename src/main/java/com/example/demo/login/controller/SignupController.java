package com.example.demo.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.mybatis.UserMapper;

@Controller
public class SignupController {

	@Autowired
	private UserMapper userMapper;

	@GetMapping("/signup")
	public String getSignup(@ModelAttribute SignupForm form,Model model) {

		return "login/signup";
	}

	@PostMapping("/signup")
	public String postSignup(@ModelAttribute @Validated SignupForm form,BindingResult bindingResult,Model model) {
		if (bindingResult.hasErrors()) {
			return getSignup(form,model);
		}
		System.out.println(form);

		User user = new User();

		user.setUserId(form.getUserId());
		user.setPassword(form.getPassword());
		user.setUserName(form.getUserName());
		boolean result = userMapper.insertUser(user);

		System.out.println(result);

		return "redirect:/login";
	}
}
