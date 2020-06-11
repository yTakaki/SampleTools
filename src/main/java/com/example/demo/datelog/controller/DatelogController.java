package com.example.demo.datelog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.datelog.model.CompDatelog;
import com.example.demo.datelog.model.ProductDatelogForm;
import com.example.demo.datelog.model.ProductReferenceForm;
import com.example.demo.datelog.service.DatelogService;
import com.example.demo.master.model.Product;

@Controller
public class DatelogController {

	@Autowired
	private DatelogService service;

	// 賞味期限管理ホーム画面へのリクエスト
	@GetMapping("/datelog")
	public String getDatelogSearch(Model model) {

		model.addAttribute("contents","datelog/datelogHome :: datelogHome_contents");
		return "login/homeLayout";
	}

	// 日付登録画面へのリクエスト
	@GetMapping("/datelogRegistProduct")
	public String getDatelogRegistProduct(@ModelAttribute ProductReferenceForm form,
			@ModelAttribute ProductDatelogForm form2,Model model) {
		// initialization
		//form.setWorkingDate(LocalDate.now());
		//form.setShipmentDate(LocalDate.now().plusDays(1));
		//form.setWorkQuantity(1);
		// configuration view
		model.addAttribute("contents","datelog/datelogRegistProduct :: datelogRegistProduct_contents");
		return "login/homeLayout";
	}

	// 日付登録画面の抽出ボタンを押す
	@PostMapping("/productReference")
	public String postProductReference(@ModelAttribute @Validated ProductReferenceForm form,BindingResult bind,
			@ModelAttribute ProductDatelogForm form2,BindingResult bind2,Model model) {
		if (bind.hasErrors()) {
			getDatelogRegistProduct(form,form2,model);
		}
		if (bind2.hasErrors()) {
			getDatelogRegistProduct(form,form2,model);
		}
		// execution
		Product product = service.selectOneProduct(form.getProductId());
		form.setProductCd(product.getProductCd());
		form.setProductName(product.getProductName());
		form2.setCompositeFlag(product.isCompositeFlag());
		form2.setFoodFlag(product.isFoodFlag());
		if (product.isFoodFlag()==false) {
			model.addAttribute("searchResult","入力した商品IDは食品ではありません。再度、商品IDを入力してください。");
		}
		form2.setPermitDate(form.getShipmentDate().plusDays(product.getPermitPeriod()));
		List<CompDatelog> compList = service.selectCompList(form.getProductId(), form.getShipmentDate());
		model.addAttribute("compList",compList);
		// configuration view
		model.addAttribute("contents","datelog/datelogRegistProduct :: datelogRegistProduct_contents");
		return "login/homeLayout";
	}

	// 日付登録画面の計算実行ボタンを押す
	@PostMapping("/calcCompDate")
	public String postCalcCompDate(@ModelAttribute @Validated ProductReferenceForm form,BindingResult bind,
			@ModelAttribute ProductDatelogForm form2,BindingResult bind2,Model model) {
		if (bind.hasErrors()) {
			getDatelogRegistProduct(form,form2,model);
		}
		if (bind2.hasErrors()) {
			getDatelogRegistProduct(form,form2,model);
		}
		System.out.println(form);
		System.out.println(form2);
		// execution

		//System.out.println(compList);
		// configuration view
		model.addAttribute("contents","datelog/datelogRegistProduct :: datelogRegistProduct_contents");
		return "login/homeLayout";
	}

	// 日付登録画面の登録ボタンを押す


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
