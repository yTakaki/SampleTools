package com.example.demo.master.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.master.domain.model.Product;
import com.example.demo.master.domain.model.ProductSearchForm;
import com.example.demo.master.domain.model.RegistProductForm;
import com.example.demo.master.domain.service.ProductService;

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
		model.addAttribute("contents","master/productMaster :: productMaster_contents");
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
		model.addAttribute("contents","master/registProduct :: registProduct_contents");
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
			model.addAttribute("contents","master/productMaster :: productMaster_contents");
			model.addAttribute("productSearchForm",new ProductSearchForm());

		} catch (DuplicateKeyException e) {
			model.addAttribute("result","入力された商品IDはすでに登録されています。");
			model.addAttribute("contents","master/registProduct :: registProduct_contents");
		}

		return "login/homeLayout";
	}

	@PostMapping(value="/productSearch",name="search")
	public String postProductSearch(@ModelAttribute @Validated ProductSearchForm form,BindingResult bind,Model model) {
		if (bind.hasErrors()) {
			getProductMaster(form,model);
		}
		// initialization
		radioCompositeFlag = initRadioCompositeFlag();
		model.addAttribute("radioCompositeFlag",radioCompositeFlag);
		radioFoodFlag = initRadioFoodFlag();
		model.addAttribute("radioFoodFlag",radioFoodFlag);
		radioProductStatus = initRadioProductStatus();
		model.addAttribute("radioProductStatus",radioProductStatus);
		// execution

		// configuration view
		model.addAttribute("contents","master/productMaster :: productMaster_contents");
		return "login/homeLayout";
	}

	@PostMapping(value="/productSearch",name="selectall")
	public String postProductAllSearch
	(@ModelAttribute @Validated ProductSearchForm form,BindingResult bind,Model model) {
		if (bind.hasErrors()) {
			getProductMaster(form,model);
		}
		// initialization
		radioCompositeFlag = initRadioCompositeFlag();
		model.addAttribute("radioCompositeFlag",radioCompositeFlag);
		radioFoodFlag = initRadioFoodFlag();
		model.addAttribute("radioFoodFlag",radioFoodFlag);
		radioProductStatus = initRadioProductStatus();
		model.addAttribute("radioProductStatus",radioProductStatus);
		// execution
		List<Product> productList = service.selectAllProduct();
		model.addAttribute("productList",productList);
		model.addAttribute("result","合計："+productList.size()+"件の商品データを抽出しました。");
		// configuration view
		model.addAttribute("contents","master/productMaster :: productMaster_contents");
		return "login/homeLayout";
	}

	@GetMapping("/productDetail/{id}")
	public String getProductDetail(Model model,@PathVariable("id") String productId) {
		// initialization
				radioCompositeFlag = initRadioCompositeFlag();
				model.addAttribute("radioCompositeFlag",radioCompositeFlag);
				radioFoodFlag = initRadioFoodFlag();
				model.addAttribute("radioFoodFlag",radioFoodFlag);
				radioProductStatus = initRadioProductStatus();
				model.addAttribute("radioProductStatus",radioProductStatus);
				// execution
				Product product = service.selectOneProduct(productId);
				// configuration view
				model.addAttribute("contents","master/productMaster :: productMaster_contents");
				return "login/homeLayout";
	}

}
