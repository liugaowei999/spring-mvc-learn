package com.liugw.timetask;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartMain {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(
				"spring/spring-applicationcontext.xml");
	}
}
