package com.example.demo.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ComponentMasterController {

	@GetMapping("/componentMaster")
	public String getComponentMaster(Model model) {
		model.addAttribute("contents","manage/componentMaster :: componentMaster_contents");
		return "login/homeLayout";
	}
}
