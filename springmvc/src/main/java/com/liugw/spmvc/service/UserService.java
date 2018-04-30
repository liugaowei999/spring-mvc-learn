package com.liugw.spmvc.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.liugw.spmvc.entity.User;

@Service
public class UserService {
	private static Map<Integer, User> userMap = new HashMap<>();
	static {
		System.out.println("创建User列表 .................................");
		userMap.put(1, new User(1, "jason", 20));
		userMap.put(2, new User(2, "jack", 30));
		userMap.put(3, new User(3, "helen", 25));
	}

	public User getUserById(int id) {
		return userMap.get(id);
	}
}
