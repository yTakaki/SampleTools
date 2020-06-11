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

import com.example.demo.master.model.Product;
import com.example.demo.master.model.ProductSearchForm;
import com.example.demo.master.model.RegistProductForm;
import com.example.demo.master.service.ProductService;

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
		radio.put("取扱可", 0);
		radio.put("終売予定", 1);
		radio.put("終了", 2);
		return radio;
	}

	// 商品閲覧画面へのリクエスト
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

	// 商品登録画面へのリクエスト
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

	// 商品登録ボタンを押す
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
				,form.isCompositeFlag(),form.isFoodFlag(),form.getProductStatus(),form.getPermitPeriod()
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

	// 商品閲覧画面にて検索ボタンを押す
	@PostMapping(value="/productSearch",params="search")
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
		List<Product> productList = service.searchProduct(form.getProductId(),form.getProductCd(),
				form.getProductName(),form.isCompositeFlag(),form.isFoodFlag(),
				form.getProductStatus(),form.getCompId());
		model.addAttribute("productList",productList);
		model.addAttribute("result","合計："+productList.size()+"件の商品データを抽出しました。");
		// configuration view
		model.addAttribute("contents","master/productMaster :: productMaster_contents");
		return "login/homeLayout";
	}

	// 商品閲覧画面にて一覧ボタンを押す
	@PostMapping(value="/productSearch",params="selectall")
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

	// 商品編集画面へのリクエスト
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
		Product p = service.selectOneProduct(productId);
		RegistProductForm form = new RegistProductForm(p.getProductId(),p.getProductCd(),p.getProductName(),
				p.isCompositeFlag(),p.isFoodFlag(),p.getProductStatus(),p.getPermitPeriod(),
				p.getComp1(),p.getComp2(),p.getComp3(),p.getComp4(),p.getComp5(),
				p.getComp6(),p.getComp7(),p.getComp8(),p.getComp9(),p.getComp10());
		model.addAttribute("registProductForm",form);
		// configuration view
		model.addAttribute("contents","master/productDetail :: productDetail_contents");
		return "login/homeLayout";
	}

	// 商品編集画面にて更新ボタンを押す
	@PostMapping(value="/productDetail",params="update")
	public String postUpdateProduct(@ModelAttribute @Validated RegistProductForm form,BindingResult bind,Model model) {
		if (bind.hasErrors()) {
			getProductDetail(model,form.getProductId());
		}
		// initialization
		radioCompositeFlag = initRadioCompositeFlag();
		model.addAttribute("radioCompositeFlag",radioCompositeFlag);
		radioFoodFlag = initRadioFoodFlag();
		model.addAttribute("radioFoodFlag",radioFoodFlag);
		radioProductStatus = initRadioProductStatus();
		model.addAttribute("radioProductStatus",radioProductStatus);
		// execution
		Product p = new Product(form.getProductId(),form.getProductCd(),form.getProductName(),
				form.isCompositeFlag(),form.isFoodFlag(),form.getProductStatus(),form.getPermitPeriod(),
				form.getComp1(),form.getComp2(),form.getComp3(),
				form.getComp4(),form.getComp5(),form.getComp6(),
				form.getComp7(),form.getComp8(),form.getComp9(),form.getComp10());
		boolean result = service.updateProduct(p);
		if (result) {
			model.addAttribute("result","商品データを1件登録しました。");
		} else {
			model.addAttribute("result","商品データの登録に失敗しました。");
		}
		// configuration view
		model.addAttribute("contents","master/productMaster :: productMaster_contents");
		model.addAttribute("productSearchForm",new ProductSearchForm());
		return "login/homeLayout";
	}

	// 商品編集画面にて削除ボタンを押す
	@PostMapping(value="/productDetail",params="delete")
	public String postDeleteProduct(@ModelAttribute RegistProductForm form,BindingResult bind,Model model) {
		if (bind.hasErrors()) {
			getProductDetail(model,form.getProductId());
		}
		// initialization
		radioCompositeFlag = initRadioCompositeFlag();
		model.addAttribute("radioCompositeFlag",radioCompositeFlag);
		radioFoodFlag = initRadioFoodFlag();
		model.addAttribute("radioFoodFlag",radioFoodFlag);
		radioProductStatus = initRadioProductStatus();
		model.addAttribute("radioProductStatus",radioProductStatus);
		// execution
		boolean result = service.deleteProduct(form.getProductId());
		if (result) {
			model.addAttribute("result","商品データを1件削除しました。");
		} else {
			model.addAttribute("result","商品データの削除に失敗しました。");
		}
		// configuration view
		model.addAttribute("contents","master/productMaster :: productMaster_contents");
		model.addAttribute("productSearchForm",new ProductSearchForm());
		return "login/homeLayout";
	}

}
