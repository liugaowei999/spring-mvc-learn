package com.liugw.timetask;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class SpringUtils implements ApplicationContextAware {

	static {
		System.out.println("Create SpringUtils finish........");
	}
	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		SpringUtils.applicationContext = applicationContext;
		System.out.println("ApplicationContext installed!");
	}

	public static Object getBean(Class<?> cls) {
		System.out.println("getBean=" + cls.getName());
		return applicationContext.getBean(cls);
	}

}
