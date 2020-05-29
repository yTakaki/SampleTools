package com.example.demo.manage.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.manage.domain.model.Component;
import com.example.demo.manage.domain.model.ComponentSearchForm;
import com.example.demo.manage.domain.model.RegistComponentForm;
import com.example.demo.manage.domain.service.ComponentService;

@Controller
public class ComponentMasterController {

	@Autowired
	private ComponentService service;

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
		// initialization
		radioFoodFlag = initRadioFoodFlag();
		model.addAttribute("radioFoodFlag", radioFoodFlag);
		radioComponentStatus = initRadioComponentStatus();
		model.addAttribute("radioComponentStatus",radioComponentStatus);
		// configuration view
		model.addAttribute("contents","manage/componentMaster :: componentMaster_contents");
		return "login/homeLayout";
	}

	@PostMapping("/componentSearch")
	public String postComponentSearch(
			@ModelAttribute @Validated ComponentSearchForm form,BindingResult bind,Model model) {
		if (bind.hasErrors()) {
			getComponentMaster(form,model);
		}
		System.out.println(form);
		// initialization
		radioFoodFlag = initRadioFoodFlag();
		model.addAttribute("radioFoodFlag", radioFoodFlag);
		radioComponentStatus = initRadioComponentStatus();
		model.addAttribute("radioComponentStatus",radioComponentStatus);
		// configuration view
		model.addAttribute("contents","manage/componentMaster :: componentMaster_contents");
		// execution
		List<Component> componentList = service.searchComponent(form.getComponentId(), form.getComponentCd(),
				form.getComponentName(),form.isFoodFlag(),form.getComponentStatus());
		System.out.println(componentList);
		model.addAttribute("result","合計"+componentList.size()+"件の構成品を抽出しました。");
		model.addAttribute("componentList", componentList);
		return "login/homeLayout";
	}

	@GetMapping("/componentList")
	public String getComponentList(@ModelAttribute ComponentSearchForm form,Model model) {
		// initialization
		radioFoodFlag = initRadioFoodFlag();
		model.addAttribute("radioFoodFlag", radioFoodFlag);
		radioComponentStatus = initRadioComponentStatus();
		model.addAttribute("radioComponentStatus",radioComponentStatus);
		// execution
		List<Component> componentList = service.selectAllComponent();
		System.out.println(componentList);
		model.addAttribute("result","合計"+componentList.size()+"件の構成品を抽出しました。");
		model.addAttribute("componentList", componentList);
		// configuration view
		model.addAttribute("contents","manage/componentMaster :: componentMaster_contents");
		return "login/homeLayout";
	}
	@GetMapping("/registComponent")
	public String getRegistComponent(@ModelAttribute RegistComponentForm form,Model model) {
		// initialization
		radioFoodFlag = initRadioFoodFlag();
		model.addAttribute("radioFoodFlag", radioFoodFlag);
		radioComponentStatus = initRadioComponentStatus();
		model.addAttribute("radioComponentStatus",radioComponentStatus);
		// configuration view
		model.addAttribute("contents","manage/registComponent :: registComponent_contents");
		return "login/homeLayout";
	}

	@PostMapping("/registComponent")
	public String postRegistComponent(@ModelAttribute @Validated RegistComponentForm form,
			BindingResult bind,Model model) {
		if (bind.hasErrors()) {
			return getRegistComponent(form,model);
		}
		System.out.println(form);
		// initialization
		radioFoodFlag = initRadioFoodFlag();
		model.addAttribute("radioFoodFlag", radioFoodFlag);
		radioComponentStatus = initRadioComponentStatus();
		model.addAttribute("radioComponentStatus",radioComponentStatus);
		// configuration view
		model.addAttribute("contents","manage/componentMaster :: componentMaster_contents");
		model.addAttribute("componentSearchForm", new ComponentSearchForm());
		// execution
		Component comp = new Component(form.getComponentId(),form.getComponentCd(),
				form.getComponentName(),form.isFoodFlag(),form.getComponentStatus());
		boolean result = service.insertComponent(comp);
		if (result) {
			model.addAttribute("result","構成品情報を1件登録しました。");
			System.out.println("insert success.");
		} else {
			model.addAttribute("result","構成品情報の登録に失敗しました。");
			System.out.println("insert failure.");
		}
		return "login/homeLayout";
	}

	@GetMapping("/componentDetail/{id}")
	public String getComponentDetail(@ModelAttribute RegistComponentForm form,Model model,
			@PathVariable("id") String componentId) {
		// initialization
		radioFoodFlag = initRadioFoodFlag();
		model.addAttribute("radioFoodFlag", radioFoodFlag);
		radioComponentStatus = initRadioComponentStatus();
		model.addAttribute("radioComponentStatus",radioComponentStatus);
		// configuration view
		model.addAttribute("contents","manage/componentDetail :: componentDetail_contents");
		// execution
		System.out.println("componentId="+componentId);
		if (componentId!=null && componentId.length()>0) {
			Component comp = service.selectOneComponent(componentId);
			form.setComponentId(comp.getComponentId());
			form.setComponentCd(comp.getComponentCd());
			form.setComponentName(comp.getComponentName());
			form.setFoodFlag(comp.isFoodFlag());
			form.setComponentStatus(comp.getComponentStatus());
			model.addAttribute("registComponentForm", form);
		}
		return "login/homeLayout";
	}

	@PostMapping(value="/componentDetail/{id}",params="update")
	public String postComponentDetailUpdate(@ModelAttribute @Validated RegistComponentForm form,
			BindingResult bind,Model model,@PathVariable("id") String componentId) {
		if (bind.hasErrors()) {
			return getComponentDetail(form,model,componentId);
		}
		System.out.println(form);
		// initialization
		radioFoodFlag = initRadioFoodFlag();
		model.addAttribute("radioFoodFlag", radioFoodFlag);
		radioComponentStatus = initRadioComponentStatus();
		model.addAttribute("radioComponentStatus",radioComponentStatus);
		// configuration view
		model.addAttribute("contents","manage/componentMaster :: componentMaster_contents");
		model.addAttribute("componentSearchForm", new ComponentSearchForm());
		// execution
		Component comp = new Component(form.getComponentId(),form.getComponentCd(),
				form.getComponentName(),form.isFoodFlag(),form.getComponentStatus());
		boolean result = service.updateComponent(comp);
		if (result) {
			model.addAttribute("result","構成品情報を1件更新しました。");
			System.out.println("update success.");
		} else {
			model.addAttribute("result","構成品情報の更新に失敗しました。");
			System.out.println("update failure.");
		}
		return "login/homeLayout";
	}

	@PostMapping(value="/componentDetail/{id}",params="delete")
	public String postComponentDetailDelete(Model model,@PathVariable("id") String componentId) {
		System.out.println("componentId="+componentId);
		// initialization
		radioFoodFlag = initRadioFoodFlag();
		model.addAttribute("radioFoodFlag", radioFoodFlag);
		radioComponentStatus = initRadioComponentStatus();
		model.addAttribute("radioComponentStatus",radioComponentStatus);
		// configuration view
		model.addAttribute("contents","manage/componentMaster :: componentMaster_contents");
		model.addAttribute("componentSearchForm", new ComponentSearchForm());
		// execution
		boolean result = service.deleteComponent(componentId);
		if (result) {
			model.addAttribute("result","構成品情報を1件削除しました。");
			System.out.println("delete success.");
		} else {
			model.addAttribute("result","構成品情報の削除に失敗しました。");
			System.out.println("delete failure.");
		}
		return "login/homeLayout";
	}
}
