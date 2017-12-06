package com.liugw.learn.impl;

import com.liugw.learn.intf.IZJHelloService;

public class ZJHelloService implements IZJHelloService {

	@Override
	public void zjsayHello() {
		System.out.println("******************** hello world *********************");
	}

	@Override
	public void zjsayBefore(String param, int age) {
		System.out.println("============say " + param + ", your age=" + age);
	}

	@Override
	public String zjsayAfterReturning() {
		System.out.println("============after returning");
		return "[RETURN_VALUE]";
	}

	@Override
	public void zjsayAfterThrowing() {
		System.out.println("============before throwing");
		throw new RuntimeException();
	}

	@Override
	public boolean zjsayAfterFinally() {
		System.out.println("============before finally");
		// throw new RuntimeException();
		return false;
	}

	@Override
	public void zjsayAround(String param) {
		System.out.println("============around param:" + param);
	}

	@Override
	public void zjsayAdvisorBefore(String param) {
		System.out.println("============service Advisor say " + param);
	}

}
