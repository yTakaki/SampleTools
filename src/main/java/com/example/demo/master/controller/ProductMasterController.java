package com.example.demo.master.controller;

import java.util.List;

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
import com.example.demo.master.model.ProductRadio;
import com.example.demo.master.model.ProductSearchForm;
import com.example.demo.master.model.RegisterProductForm;
import com.example.demo.master.service.ProductService;

@Controller
public class ProductMasterController {

	@Autowired
	private ProductService service;

	@Autowired
	private ProductRadio radio;

	// 商品閲覧画面へのリクエスト
	@GetMapping("/productMaster")
	public String showProductMaster(@ModelAttribute ProductSearchForm form,Model model) {
		// model attribution
		model.addAttribute("radioSetup",radio.initSetup());
		model.addAttribute("radioFood",radio.initFood());
		model.addAttribute("radioProductStatus",radio.initProductStatus());
		model.addAttribute("contents","master/productMaster :: productMaster_contents");
		return "login/homeLayout";
	}

	// 商品登録画面へのリクエスト
	@GetMapping("/registerProduct")
	public String showRegisterProduct(@ModelAttribute RegisterProductForm form,Model model) {
		// model attribution
		model.addAttribute("radioSetup",radio.initSetup());
		model.addAttribute("radioFood",radio.initFood());
		model.addAttribute("radioProductStatus",radio.initProductStatus());
		// configuration view
		model.addAttribute("contents","master/registerProduct :: registerProduct_contents");
		return "login/homeLayout";
	}

	// 商品登録ボタンを押す
	@PostMapping("/registerProduct")
	public String registerProduct(@ModelAttribute @Validated RegisterProductForm form,
			BindingResult bind,Model model) {
		if (bind.hasErrors()) {
			showRegisterProduct(form,model);
		}
		System.out.println(form);
		// execution
		try {
			Product product = new Product(form);
			boolean result = service.insertProduct(product);
			if (result==true) {
				model.addAttribute("result","商品データを1件登録しました。");
			} else {
				model.addAttribute("result","商品データの登録に失敗しました。");
			}
		} catch (DuplicateKeyException e) {
			model.addAttribute("result","入力された商品IDはすでに登録されています。");
			model.addAttribute("contents","master/registerProduct :: registerProduct_contents");
		}
		// model attribution
		model.addAttribute("radioSetup",radio.initSetup());
		model.addAttribute("radioFood",radio.initFood());
		model.addAttribute("radioProductStatus",radio.initProductStatus());
		model.addAttribute("productSearchForm",new ProductSearchForm());
		model.addAttribute("contents","master/productMaster :: productMaster_contents");
		return "login/homeLayout";
	}

	// 商品閲覧画面にて検索ボタンを押す
	@GetMapping("/productSearch")
	public String searchProduct(@ModelAttribute @Validated ProductSearchForm form,BindingResult bind,Model model) {
		if (bind.hasErrors()) {
			showProductMaster(form,model);
		}
		// execution
		List<Product> productList = service.searchProduct(form);
		// model attribution
		model.addAttribute("radioSetup",radio.initSetup());
		model.addAttribute("radioFood",radio.initFood());
		model.addAttribute("radioProductStatus",radio.initProductStatus());
		model.addAttribute("productList",productList);
		model.addAttribute("result","合計："+productList.size()+"件の商品データを抽出しました。");
		model.addAttribute("contents","master/productMaster :: productMaster_contents");
		return "login/homeLayout";
	}

	// 商品閲覧画面にて一覧ボタンを押す
	@GetMapping("/productSearchAll")
	public String searchAllProduct(@ModelAttribute ProductSearchForm form,BindingResult bind,Model model) {
		if (bind.hasErrors()) {
			showProductMaster(form,model);
		}
		// execution
		List<Product> productList = service.selectAllProduct();
		// model attribution
		model.addAttribute("radioSetup",radio.initSetup());
		model.addAttribute("radioFood",radio.initFood());
		model.addAttribute("radioProductStatus",radio.initProductStatus());
		model.addAttribute("productList",productList);
		model.addAttribute("result","合計："+productList.size()+"件の商品データを抽出しました。");
		model.addAttribute("contents","master/productMaster :: productMaster_contents");
		return "login/homeLayout";
	}

	// 商品編集画面へのリクエスト
	@GetMapping("/productDetail/{id}")
	public String showProductDetail(Model model,@PathVariable("id") String productId) {
		// execution
		RegisterProductForm form = new RegisterProductForm(service.selectOneProduct(productId));
		// model attribution
		model.addAttribute("radioSetup",radio.initSetup());
		model.addAttribute("radioFood",radio.initFood());
		model.addAttribute("radioProductStatus",radio.initProductStatus());
		model.addAttribute("registProductForm",form);
		model.addAttribute("contents","master/productDetail :: productDetail_contents");
		return "login/homeLayout";
	}

	// 商品編集画面にて更新ボタンを押す
	@PostMapping(value="/productDetail",params="update")
	public String updateProduct(@ModelAttribute @Validated RegisterProductForm form,BindingResult bind,Model model) {
		if (bind.hasErrors()) {
			showProductDetail(model,form.getProductId());
		}
		// execution
		boolean result = service.updateProduct(new Product(form));
		if (result) {
			model.addAttribute("result","商品データを1件登録しました。");
		} else {
			model.addAttribute("result","商品データの登録に失敗しました。");
		}
		// model attribution
		model.addAttribute("radioSetup",radio.initSetup());
		model.addAttribute("radioFood",radio.initFood());
		model.addAttribute("radioProductStatus",radio.initProductStatus());
		model.addAttribute("productSearchForm",new ProductSearchForm());
		model.addAttribute("contents","master/productMaster :: productMaster_contents");
		return "login/homeLayout";
	}

	// 商品編集画面にて削除ボタンを押す
	@PostMapping(value="/productDetail",params="delete")
	public String deleteProduct(@ModelAttribute RegisterProductForm form,BindingResult bind,Model model) {
		if (bind.hasErrors()) {
			showProductDetail(model,form.getProductId());
		}
		// execution
		boolean result = service.deleteProduct(form.getProductId());
		if (result) {
			model.addAttribute("result","商品データを1件削除しました。");
		} else {
			model.addAttribute("result","商品データの削除に失敗しました。");
		}
		// model attribution
		model.addAttribute("radioSetup",radio.initSetup());
		model.addAttribute("radioFood",radio.initFood());
		model.addAttribute("radioProductStatus",radio.initProductStatus());
		model.addAttribute("productSearchForm",new ProductSearchForm());
		model.addAttribute("contents","master/productMaster :: productMaster_contents");
		return "login/homeLayout";
	}

}
