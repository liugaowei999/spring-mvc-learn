package com.liugw.learn.intf;

public interface IZJHelloService {

	public void zjsayHello();

	public void zjsayBefore(String param, int age);

	public String zjsayAfterReturning();

	public void zjsayAfterThrowing();

	public boolean zjsayAfterFinally();

	public void zjsayAround(String param);

	public void zjsayAdvisorBefore(String param);
}
