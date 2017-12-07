package com.liugw.learn.jdbc;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class testConfigDb {
	@Test
	public void testBestPractice() {
		String[] configLocations = new String[] {
				"classpath:spring-applicationcontext.xml",
				"classpath:spring-applicationcontext-jdbc.xml" };
		ApplicationContext ctx = new ClassPathXmlApplicationContext(configLocations);
		IUserDao userDao = ctx.getBean(IUserDao.class);
		UserModel model = new UserModel();
		model.setMyName("test");
		userDao.save(model);
		System.out.println(userDao.countAll());
	}

	public static void main(String[] args) {
		// testBestPractice();
	}
}
