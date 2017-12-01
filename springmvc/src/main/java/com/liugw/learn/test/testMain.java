package com.liugw.learn.test;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.liugw.learn.intf.HelloApi;
import com.liugw.learn.pojo.DependentBean;
import com.liugw.learn.pojo.Printer;

public class testMain {

	public static void main(String[] args) throws IOException {
		System.out.println("========================= Start =========================");
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-applicationcontext.xml");

		// 注册销毁回调（退出钩子）， 否则我们定义的destroy-method
		// 不会执行，ClassPathXmlApplicationContext类型才会有。
		((ClassPathXmlApplicationContext) context).registerShutdownHook();

		DependentBean dependentBean = context.getBean("dependentBean", DependentBean.class);
		dependentBean.write("\nhello world ....\n fdjsalfda\n");

		System.out.println("-------------------------------------------------------");
		System.out.println("=======singleton sayHello======");
		HelloApi helloApi1 = context.getBean("helloApi1", HelloApi.class);
		helloApi1.sayHello();
		helloApi1 = context.getBean("helloApi1", HelloApi.class);
		helloApi1.sayHello();
		System.out.println("=======prototype sayHello======");
		HelloApi helloApi2 = context.getBean("helloApi2", HelloApi.class);
		helloApi2.sayHello();
		helloApi2 = context.getBean("helloApi2", HelloApi.class);
		helloApi2.sayHello();
		System.out.println("---------------------------------------------------------");

		Printer printer = context.getBean("printer", Printer.class);
		printer.print("我将被替换");
		System.out.println("========================= End ===========================");
	}
}
