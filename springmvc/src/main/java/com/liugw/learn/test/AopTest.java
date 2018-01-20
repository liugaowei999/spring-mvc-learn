package com.liugw.learn.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.liugw.learn.intf.IHelloWorldService;
import com.liugw.learn.intf.IIntroductionService;

public class AopTest {

	@Test
	public void testHelloWorld() {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-applicationcontext.xml");
		IHelloWorldService helloWorldService = context.getBean("helloWorldService", IHelloWorldService.class);
		helloWorldService.sayHello();
		// helloWorldService.sayBefore("*** sayBefore param vlaue ***");
		// helloWorldService.sayAfterReturning();
		// helloWorldService.sayAfterThrowing();
		// helloWorldService.sayAfterFinally();
		//		helloWorldService.sayAround("[AROUND]");
	}

	@Test
	/**
	 * AOP 引入 新的接口
	 */
	public void testImportNewIntface() {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-applicationcontext.xml");
		IIntroductionService introduction = context.getBean("helloWorldService", IIntroductionService.class);
		introduction.induct();
	}

	@Test
	public void testSchemaAdvisor() {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-applicationcontext.xml");
		IHelloWorldService helloWorldService = context.getBean("helloWorldService", IHelloWorldService.class);
		helloWorldService.sayAdvisorBefore("[advisor before]");
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-applicationcontext.xml");
		IHelloWorldService helloWorldService = context.getBean("helloWorldService", IHelloWorldService.class);
		// helloWorldService.sayHello();
		// helloWorldService.sayBefore("*** sayBefore param vlaue ***");
		helloWorldService.sayAfterFinally();
	}
}
