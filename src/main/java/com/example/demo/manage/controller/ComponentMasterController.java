package com.example.demo.manage.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.manage.domain.model.ComponentSearchForm;

@Controller
public class ComponentMasterController {

	private Map<String,Boolean> radioFoodFlag;
	private Map<String,Integer> radioComponentStatus;

	private Map<String,Boolean> initRadioFoodFlag() {
		Map<String,Boolean> radio = new LinkedHashMap<>();
		radio.put("食品", true);
		radio.put("食品以外", false);
		return radio;
	}

	private Map<String,Integer> initRadioComponentStatus() {
		Map<String,Integer> radio = new LinkedHashMap<>();
		radio.put("取扱い可能", 0);
		radio.put("終売予定", 1);
		radio.put("終了", 2);
		return radio;
	}

	@GetMapping("/componentMaster")
	public String getComponentMaster(@ModelAttribute ComponentSearchForm form,Model model) {
		radioFoodFlag = initRadioFoodFlag();
		model.addAttribute("radioFoodFlag", radioFoodFlag);
		radioComponentStatus = initRadioComponentStatus();
		model.addAttribute("radioComponentStatus",radioComponentStatus);

		model.addAttribute("contents","manage/componentMaster :: componentMaster_contents");
		return "login/homeLayout";
	}

	@GetMapping("/registComponent")
	public String getRegistComponent(Model model) {
		radioFoodFlag = initRadioFoodFlag();
		model.addAttribute("radioFoodFlag", radioFoodFlag);
		radioComponentStatus = initRadioComponentStatus();
		model.addAttribute("radioComponentStatus",radioComponentStatus);

		model.addAttribute("contents","manage/registComponent :: componentRegist_contents");
		return "login/homeLayout";
	}
}
