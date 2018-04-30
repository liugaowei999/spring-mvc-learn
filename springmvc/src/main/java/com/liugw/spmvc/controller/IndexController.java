package com.liugw.spmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liugw.spmvc.entity.User;
import com.liugw.spmvc.service.UserService;

@Controller
public class IndexController {

	@Autowired
	UserService userService;

	static {
		System.out.println("IndexController...............................................");
	}

	@RequestMapping("index")
	public String index(Model model) {
		User user = userService.getUserById(1);
		System.out.println(user.getName());
		model.addAttribute("user", user);
		return "views/index";
	}
}
