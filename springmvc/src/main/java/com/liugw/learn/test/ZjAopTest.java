package com.liugw.learn.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.liugw.learn.intf.IHelloWorldService;
import com.liugw.learn.intf.IIntroductionService;
import com.liugw.learn.intf.IZJHelloService;

/**
 * AspectJ-AOP 注解方式
 * 
 * @author liugaowei
 *
 */
public class ZjAopTest {

	@Test
	public void testHelloWorld() {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-applicationcontext-aopzj.xml");
		IZJHelloService helloWorldService = context.getBean("zjhelloService", IZJHelloService.class);
		// helloWorldService.zjsayHello();
		helloWorldService.zjsayBefore("*** sayBefore param vlaue ***", 99);
		// helloWorldService.zjsayAfterReturning();

		// helloWorldService.zjsayAround("[AROUND]");
		// helloWorldService.zjsayAfterFinally();
		// helloWorldService.zjsayHello();
		// helloWorldService.zjsayAfterThrowing();
	}

	@Test
	/**
	 * AOP 引入 新的接口
	 */
	public void testImportNewIntface() {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-applicationcontext-aopzj.xml");
		IIntroductionService introduction = context.getBean("zjhelloService", IIntroductionService.class);
		introduction.induct();
	}

	@Test
	public void testSchemaAdvisor() {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-applicationcontext.xml");
		IHelloWorldService helloWorldService = context.getBean("helloWorldService", IHelloWorldService.class);
		helloWorldService.sayAdvisorBefore("[advisor before]");
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-applicationcontext-aopzj.xml");
		IZJHelloService helloWorldService = context.getBean("zjhelloService", IZJHelloService.class);
		helloWorldService.zjsayAfterFinally();
		helloWorldService.zjsayAround("[AROUND]");
		// helloWorldService.zjsayBefore("*** sayBefore param vlaue ***");

		IIntroductionService introduction = context.getBean("zjhelloService", IIntroductionService.class);
		introduction.induct();
	}
}
