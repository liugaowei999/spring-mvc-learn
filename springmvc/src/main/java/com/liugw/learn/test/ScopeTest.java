package com.liugw.learn.test;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.liugw.learn.intf.HelloApi;

public class ScopeTest {
	public static void main(String[] args) throws IOException {
		System.out.println("========================= Start =========================");
		final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-applicationcontext.xml");

		// 注册销毁回调（退出钩子）， 否则我们定义的destroy-method
		// 不会执行，ClassPathXmlApplicationContext类型才会有。
		context.registerShutdownHook();

		HelloApi helloApi10 = context.getBean("helloApi10", HelloApi.class);
		HelloApi helloApi11 = context.getBean("helloApi11", HelloApi.class);
		HelloApi helloApi12 = context.getBean("helloApi12", HelloApi.class);
		helloApi10.sayHello();
		System.out.println("main thread: thread_scope____helloApi10.hashcode()=" + helloApi10.hashCode());
		System.out.println("main thread: prototype_scope_helloApi11.hashcode()=" + helloApi11.hashCode());
		System.out.println("main thread: singleton_scope_helloApi12.hashcode()=" + helloApi12.hashCode());

		Thread thread = new Thread() {

			@Override
			public void run() {
				HelloApi helloApi10 = context.getBean("helloApi10", HelloApi.class);
				HelloApi helloApi11 = context.getBean("helloApi11", HelloApi.class);
				HelloApi helloApi12 = context.getBean("helloApi12", HelloApi.class);
				System.out.println("Thread2: thread_scope____helloApi10.hashcode()=" + helloApi10.hashCode());
				System.out.println("Thread2: prototype_scope_helloApi11.hashcode()=" + helloApi11.hashCode());
				System.out.println("Thread2: singleton_scope_helloApi12.hashcode()=" + helloApi12.hashCode());
			}

		};
		thread.start();

		System.out.println("========================= End ===========================");
	}
}
