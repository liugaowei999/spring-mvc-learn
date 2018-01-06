package com.liugw.learn.di;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DITest {

	public static void main(String[] args) {

		//		Women women = new Women();
		//		Man man = new Man();
		//
		//		HuShi huShi = new HuShi(man);
		//		huShi.doWork();
		//
		//		HuShi huShi1 = new HuShi(women);
		//		huShi1.doWork();

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:spring-applicationcontext-DI.xml");
		//		Women women = (Women) context.getBean("woman");
		//		women.sayHello();

		//HuShi huShi = (HuShi) context.getBean("nurse");
		HuShi nurse = context.getBean(HuShi.class);
		nurse.doWork();
	}
}
