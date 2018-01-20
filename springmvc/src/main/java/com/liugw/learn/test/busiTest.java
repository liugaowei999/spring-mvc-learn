package com.liugw.learn.test;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.liugw.learn.zhbusiness.Business;

public class busiTest {

	@Test
	public void doTest() throws Throwable {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(
				"spring-zh.xml");

		Business business = (Business) classPathXmlApplicationContext.getBean("business");

		business.doBusiness();

	}
	/*
		public static void main(String[] args) throws Throwable {
			ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(
					"spring-zh.xml");
			final Business business = (Business) classPathXmlApplicationContext.getBean("business");
	
			Thread thread = new Thread(new Runnable() {
	
				@Override
				public void run() {
					// TODO Auto-generated method stub
					//				ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(
					//						"spring-zh.xml");
					//				Business business = (Business) classPathXmlApplicationContext.getBean("business");
					try {
						business.doBusiness();
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
	
			thread.start();
			thread.sleep(3000);
	
			business.doBusiness();
	
		}*/

	public static void main(String[] args) throws Throwable {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(
				"spring-zh.xml");
		Business business = classPathXmlApplicationContext.getBean(Business.class);
		//		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(
		//				com.liugw.learn.config.BusinessConfig.class);
		//		Business business = (Business) annotationConfigApplicationContext.getBean("busi");
		business.doBusiness();
	}

}
