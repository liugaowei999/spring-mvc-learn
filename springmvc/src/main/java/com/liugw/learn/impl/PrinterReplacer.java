package com.liugw.learn.impl;

import java.lang.reflect.Method;

import org.springframework.beans.factory.support.MethodReplacer;

public class PrinterReplacer implements MethodReplacer {

	public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
		System.out.println("Print Replacer");
		// ע��˴�������ͨ�����������,��������ѭ�����ã�֪���ڴ����
		// method.invoke(obj, new String[]{"hehe"});
		return null;
	}

}
