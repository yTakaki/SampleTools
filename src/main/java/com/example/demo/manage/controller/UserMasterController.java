package com.example.demo.manage.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;
import com.example.demo.manage.domain.model.UserSearchForm;

@Controller
public class UserMasterController {

	@Autowired
	private UserService service;

	@GetMapping("/userMaster")
	public String getUserMaster(@ModelAttribute UserSearchForm form,Model model) {
		model.addAttribute("contents","manage/userMaster :: userMaster_contents");
		return "login/homeLayout";
	}

	@PostMapping("/userSearch")
	public String postUserSearch(@ModelAttribute UserSearchForm form,BindingResult bind,Model model) {
		if (bind.hasErrors()) {
			return getUserMaster(form,model);
		}
		List<User> userList = new ArrayList<>();
		if (form.getUserId()==null && form.getUserName()==null) {
			userList = service.selectAllUser();
		} else if (form.getUserId()==null) {
			userList = service.searchUserId(form.getUserId());
		} else if (form.getUserName()==null) {
			userList = service.searchUserName(form.getUserName());
		} else {
			userList = service.searchUser(form.getUserId(), form.getUserName());
		}
		model.addAttribute("userList", userList);
		return "redirect:/userMaster";
	}
}
