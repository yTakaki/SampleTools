package com.example.demo.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.manage.domain.model.UserSearchForm;

@Controller
public class UserMasterController {

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

		return "redirect:/userMaster";
	}
}
