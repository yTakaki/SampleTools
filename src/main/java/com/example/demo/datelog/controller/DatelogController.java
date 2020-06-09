package com.example.demo.datelog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DatelogController {

	// 賞味期限管理ホーム画面へのリクエスト
	@GetMapping("/datelog")
	public String getDatelogSearch(Model model) {

		model.addAttribute("contents","datelog/datelogHome :: datelogHome_contents");
		return "login/homeLayout";
	}

	// セットアップ品用日付登録画面へのリクエスト
	@GetMapping("/datelogRegistProduct")
	public String getDatelogRegistProduct(Model model) {

		model.addAttribute("contents","datelog/datelogRegistProduct :: datelogRegistProduct_contents");
		return "login/homeLayout";
	}

	// 既製品用日付登録画面へのリクエスト
	@GetMapping("/datelogRegistComp")
	public String getDatelogRegistComp(Model model) {

		model.addAttribute("contents","datelog/datelogRegistComp :: datelogRegistComp_contents");
		return "login/homeLayout";
	}
	// セットアップ品用履歴検索画面へのリクエスト
	@GetMapping("/datelogSearchProduct")
	public String getDatelogSearchProduct(Model model) {

		model.addAttribute("contents","datelog/datelogSearchProduct :: datelogSearchProduct_contents");
		return "login/homeLayout";
	}

	// 既製品用履歴検索画面へのリクエスト
	@GetMapping("/datelogSearchComp")
	public String getDatelogSearchComp(Model model) {

		model.addAttribute("contents","datelog/datelogSearchComp :: datelogSearchComp_contents");
		return "login/homeLayout";
	}

}
