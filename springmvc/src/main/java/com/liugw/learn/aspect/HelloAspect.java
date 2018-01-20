package com.liugw.learn.aspect;

import org.aspectj.lang.annotation.Aspect;

@Aspect
public class HelloAspect {

	private ThreadLocal<Long> TIME_THREADLOCAL1 = new ThreadLocal<Long>();
	private ThreadLocal<Long> TIME_THREADLOCAL2 = new ThreadLocal<Long>();

	long endTime;

	public void beforeWriteLog() {
		System.out.println("执行开始时间:" + Thread.currentThread().getName() + System.currentTimeMillis());
		TIME_THREADLOCAL1.set(System.currentTimeMillis());
	}

	public void afterWriteLog() {
		System.out.println("执行结束时间:" + Thread.currentThread().getName() + System.currentTimeMillis());
		TIME_THREADLOCAL2.set(System.currentTimeMillis());
		System.out.println("执行时长：" + (TIME_THREADLOCAL2.get() - TIME_THREADLOCAL1.get()));

	}
}
