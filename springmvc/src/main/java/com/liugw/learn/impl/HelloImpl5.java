package com.liugw.learn.impl;

import com.liugw.learn.intf.HelloApi;
import com.liugw.learn.pojo.Printer;

public abstract class HelloImpl5 implements HelloApi {

	private Printer printer;

	public void sayHello() {
		printer.print("setter");
		createPrototypePrinter().print("prototype");
		createSingletonPrinter().print("singleton");
	}

	public abstract Printer createPrototypePrinter();

	public Printer createSingletonPrinter() {
		System.out.println("该方法不会被执行，如果输出就错了");
		return new Printer();
	}

	public void setPrinter(Printer printer) {
		this.printer = printer;
	}

}
