package com.liugw.learn.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.liugw.learn.pojo.User;

@RestController
@RequestMapping("/user")
public class UserController {

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public User view(@PathVariable("id") Long id) {
		User user = new User();
		user.setId(id);
		user.setName("zhang");
		System.out.println("user=" + user);
		return user;
	}
}
