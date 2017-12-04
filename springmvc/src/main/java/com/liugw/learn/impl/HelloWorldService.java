package com.liugw.learn.impl;

import com.liugw.learn.intf.IHelloWorldService;

public class HelloWorldService implements IHelloWorldService {

	@Override
	public void sayHello() {
		System.out.println("******************** hello world *********************");
	}

	@Override
	public void sayBefore(String param) {
		System.out.println("============say " + param);
	}

	@Override
	public String sayAfterReturning() {
		System.out.println("============after returning");
		return "[RETURN_VALUE]";
	}

	@Override
	public void sayAfterThrowing() {
		System.out.println("============before throwing");
		throw new RuntimeException();
	}

	@Override
	public boolean sayAfterFinally() {
		System.out.println("============before finally");
		throw new RuntimeException();
	}

	@Override
	public void sayAround(String param) {
		System.out.println("============around param:" + param);
	}

}
