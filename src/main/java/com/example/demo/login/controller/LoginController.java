package com.example.demo.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.LoginUser;
import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.service.LoginUserService;

@Controller
public class LoginController {

	@Autowired
	private LoginUserService service;

	@GetMapping("/login")
	public String getLogin(Model model) {

		return "login/login";
	}

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

		LoginUser user = new LoginUser(form.getUserId(),form.getPassword(),form.getUserName());
		boolean result = service.insertUser(user);
		if (result) {
			System.out.println("insert success");
		} else {
			System.out.println("insert failure");
		}

		return "redirect:/login";
	}

}
