package com.example.demo.datelog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DatelogController {

	// 賞味期限管理画面へのリクエスト
		@GetMapping("/datelog")
		public String getDatelogSearch(Model model) {

			model.addAttribute("contents","datelog/datelogSearch :: datelogSearch_contents");
			return "login/homeLayout";
		}

	// 賞味期限記録画面へのリクエスト
	@GetMapping("/datelogRegist")
	public String getDatelogRegist(Model model) {

		model.addAttribute("contents","datelog/datelogRegist :: datelogRegist_contents");
		return "login/homeLayout";
	}


}
