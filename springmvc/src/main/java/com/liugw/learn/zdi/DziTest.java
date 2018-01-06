package com.liugw.learn.zdi;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DziTest {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-applicationcontext-ZDI.xml");
		Animal bird = context.getBean(Animal.class);

		//Animal bird = (Animal) context.getBean("animal");
		bird.setName("bird");
		bird.eat();

	}
}
