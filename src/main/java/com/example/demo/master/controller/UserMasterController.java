package com.example.demo.master.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.model.LoginUser;
import com.example.demo.login.service.LoginUserService;
import com.example.demo.master.model.UpdateUserForm;
import com.example.demo.master.model.UserSearchForm;

@Controller
public class UserMasterController {

	@Autowired
	private LoginUserService service;

	@GetMapping("/userMaster")
	public String getUserMaster(@ModelAttribute UserSearchForm form,Model model) {
		model.addAttribute("contents","master/userMaster :: userMaster_contents");
		return "login/homeLayout";
	}

	@PostMapping("/userSearch")
	public String postUserSearch(@ModelAttribute @Validated UserSearchForm form,BindingResult bind,Model model) {
		if (bind.hasErrors()) {
			return getUserMaster(form,model);
		}
		List<LoginUser> userList = service.searchUser(form.getUserId(), form.getUserName());
		model.addAttribute("userList", userList);
		model.addAttribute("result", "合計で"+userList.size()+"件のユーザー情報を取得しました。");
		model.addAttribute("contents", "master/userMaster :: userMaster_contents");
		return "login/homeLayout";
	}

	@GetMapping("/updateUser/{id:.+}")
	public String getUpdateUser(@ModelAttribute UpdateUserForm form,Model model,
			@PathVariable("id") String userId) {
		System.out.println("userId="+userId);
		model.addAttribute("contents", "master/updateUser :: updateUser_contents");
		if (userId!=null && userId.length()>0) {
			LoginUser user = service.selectOneUser(userId);
			form.setUserId(user.getUserId());
			form.setUserName(user.getUserName());
			model.addAttribute("updateUserForm", form);
		}
		return "login/homeLayout";
	}

	@PostMapping("/updateUser")
	public String postUpdateUser(@ModelAttribute @Validated UpdateUserForm form,BindingResult bind,Model model) {
		if (bind.hasErrors()) {
			return getUpdateUser(form,model,form.getUserId());
		}
		System.out.println(form);
		model.addAttribute("contents", "master/userMaster :: userMaster_contents");
		model.addAttribute("userSearchForm",new UserSearchForm());

		LoginUser user = service.selectOneUser(form.getUserId());
		user.setUserName(form.getUserName());
		boolean result = service.updateUser(user);
		if (result==true) {
			System.out.println("update success.");
			model.addAttribute("result","ユーザー情報を1件更新しました。");
		} else {
			System.out.println("update failure.");
			model.addAttribute("result","ユーザー情報の更新に失敗しました。");
		}
		return "login/homeLayout";
	}

	@PostMapping("/deleteUser/{id:.+}")
	public String postDeleteUser(Model model,@PathVariable("id") String userId) {
		System.out.println("userId="+userId);
		model.addAttribute("contents","master/userMaster :: userMaster_contents");
		model.addAttribute("userSearchForm",new UserSearchForm());

		boolean result = service.deleteUser(userId);
		if (result==true) {
			System.out.println("delete success.");
			model.addAttribute("result","ユーザー情報を1件削除しました。");
		} else {
			System.out.println("delete failure.");
			model.addAttribute("result","ユーザー情報の削除に失敗しました。");
		}
		return "login/homeLayout";
	}
}
