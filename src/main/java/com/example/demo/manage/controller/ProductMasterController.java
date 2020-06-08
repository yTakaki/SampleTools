package com.example.demo.manage.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.manage.domain.model.Product;
import com.example.demo.manage.domain.model.ProductSearchForm;
import com.example.demo.manage.domain.model.RegistProductForm;
import com.example.demo.manage.domain.service.ProductService;

@Controller
public class ProductMasterController {

	@Autowired
	private ProductService service;

	private Map<String,Boolean> radioCompositeFlag;
	private Map<String,Boolean> radioFoodFlag;
	private Map<String,Integer> radioProductStatus;

	private Map<String,Boolean> initRadioCompositeFlag() {
		Map<String,Boolean> radio = new LinkedHashMap<>();
		radio.put("セットアップ品", true);
		radio.put("既製品", false);
		return radio;
	}

	private Map<String,Boolean> initRadioFoodFlag() {
		Map<String,Boolean> radio = new LinkedHashMap<>();
		radio.put("食品", true);
		radio.put("食品以外", false);
		return radio;
	}

	private Map<String,Integer> initRadioProductStatus() {
		Map<String,Integer> radio = new LinkedHashMap<>();
		radio.put("取扱い可能", 0);
		radio.put("終売予定", 1);
		radio.put("終了", 2);
		return radio;
	}

	@GetMapping("/productMaster")
	public String getProductMaster(@ModelAttribute ProductSearchForm form,Model model) {
		// initialization
		radioCompositeFlag = initRadioCompositeFlag();
		model.addAttribute("radioCompositeFlag",radioCompositeFlag);
		radioFoodFlag = initRadioFoodFlag();
		model.addAttribute("radioFoodFlag",radioFoodFlag);
		radioProductStatus = initRadioProductStatus();
		model.addAttribute("radioProductStatus",radioProductStatus);
		// configuration view
		model.addAttribute("contents","manage/productMaster :: productMaster_contents");
		return "login/homeLayout";
	}

	@GetMapping("/registProduct")
	public String getRegistProduct(@ModelAttribute RegistProductForm form,Model model) {
		// initialization
		radioCompositeFlag = initRadioCompositeFlag();
		model.addAttribute("radioCompositeFlag",radioCompositeFlag);
		radioFoodFlag = initRadioFoodFlag();
		model.addAttribute("radioFoodFlag",radioFoodFlag);
		radioProductStatus = initRadioProductStatus();
		model.addAttribute("radioProductStatus",radioProductStatus);
		// configuration view
		model.addAttribute("contents","manage/registProduct :: registProduct_contents");
		return "login/homeLayout";
	}

	@PostMapping("/registProduct")
	public String postRegistProduct(@ModelAttribute @Validated RegistProductForm form,
			BindingResult bind,Model model) {
		if (bind.hasErrors()) {
			getRegistProduct(form,model);
		}
		System.out.println(form);
		// initialization
		radioCompositeFlag = initRadioCompositeFlag();
		model.addAttribute("radioCompositeFlag",radioCompositeFlag);
		radioFoodFlag = initRadioFoodFlag();
		model.addAttribute("radioFoodFlag",radioFoodFlag);
		radioProductStatus = initRadioProductStatus();
		model.addAttribute("radioProductStatus",radioProductStatus);
		// execution
		try {
			Product product = new Product(form.getProductId(),form.getProductCd(),form.getProductName()
				,form.isCompositeFlag(),form.isFoodFlag(),form.getProductStatus()
				,form.getComp1(),form.getComp2(),form.getComp3(),form.getComp4()
				,form.getComp5(),form.getComp6(),form.getComp7(),form.getComp8()
				,form.getComp9(),form.getComp10());
			boolean result = service.insertProduct(product);
			if (result==true) {
				model.addAttribute("result","商品データを1件登録しました。");
			} else {
				model.addAttribute("result","商品データの登録に失敗しました。");
			}

			// configuration view
			model.addAttribute("contents","manage/productMaster :: productMaster_contents");
			model.addAttribute("productSearchForm",new ProductSearchForm());

		} catch (DuplicateKeyException e) {
			model.addAttribute("result","入力された商品IDはすでに登録されています。");
			model.addAttribute("contents","manage/registProduct :: registProduct_contents");
		}

		return "login/homeLayout";
	}

}
