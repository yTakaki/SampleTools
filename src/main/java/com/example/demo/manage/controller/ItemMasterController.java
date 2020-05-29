package com.example.demo.manage.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.manage.domain.model.ItemSearchForm;

@Controller
public class ItemMasterController {

	private Map<String,Boolean> radioCompositeFlag;
	private Map<String,Integer> radioItemStatus;

	private Map<String,Boolean> initRadioCompositeFlag() {
		Map<String,Boolean> radio = new LinkedHashMap<>();
		radio.put("セットアップ品", true);
		radio.put("既製品", false);
		return radio;
	}

	private Map<String,Integer> initRadioItemStatus() {
		Map<String,Integer> radio = new LinkedHashMap<>();
		radio.put("取扱い可能", 0);
		radio.put("終売予定", 1);
		radio.put("終了", 2);
		return radio;
	}

	@GetMapping("/itemMaster")
	public String getItemMaster(@ModelAttribute ItemSearchForm form,Model model) {
		// initialization
		radioCompositeFlag = initRadioCompositeFlag();
		model.addAttribute("radioCompositeFlag",radioCompositeFlag);
		radioItemStatus = initRadioItemStatus();
		model.addAttribute("radioItemStatus",radioItemStatus);
		// configuration view
		model.addAttribute("contents","manage/itemMaster :: itemMaster_contents");
		return "login/homeLayout";
	}

}
