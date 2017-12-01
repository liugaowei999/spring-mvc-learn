package com.liugw.learn.impl;

import com.liugw.learn.intf.HelloApi;

public class HelloImpl implements HelloApi {

	static {
		System.out.println("HelloImpl static .... Object created!");
	}

	public HelloImpl() {
		System.out.println("HelloImpl constructor .... Object created!");
	}

	public void sayHello() {
		// TODO Auto-generated method stub
		System.out.println("HelloImpl ------ hello world!");
	}

}
