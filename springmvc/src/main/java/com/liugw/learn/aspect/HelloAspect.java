package com.liugw.learn.aspect;

import org.aspectj.lang.annotation.Aspect;

@Aspect
public class HelloAspect {

	private ThreadLocal<Long> TIME_THREADLOCAL1 = new ThreadLocal<Long>();
	private ThreadLocal<Long> TIME_THREADLOCAL2 = new ThreadLocal<Long>();

	long endTime;

	public void beforeWriteLog() {
		System.out.println("ִ�п�ʼʱ��:" + Thread.currentThread().getName() + System.currentTimeMillis());
		TIME_THREADLOCAL1.set(System.currentTimeMillis());
	}

	public void afterWriteLog() {
		System.out.println("ִ�н���ʱ��:" + Thread.currentThread().getName() + System.currentTimeMillis());
		TIME_THREADLOCAL2.set(System.currentTimeMillis());
		System.out.println("ִ��ʱ����" + (TIME_THREADLOCAL2.get() - TIME_THREADLOCAL1.get()));

	}
}
