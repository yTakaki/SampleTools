package com.example.demo.manage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserMasterController {

	@GetMapping("/userMaster")
	public String getUserMaster(Model model) {
		model.addAttribute("contents","manage/userMaster :: userMaster_contents");
		return "login/homeLayout";
	}

	@PostMapping("/userSearch")
	public String postUserSearch(Model model) {

		return "redirect:/userMaster";
	}
}
