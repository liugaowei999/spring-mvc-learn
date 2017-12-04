package com.liugw.learn.intf;

public interface IHelloWorldService {

	public void sayHello();

	public void sayBefore(String param);

	public String sayAfterReturning();

	public void sayAfterThrowing();

	public boolean sayAfterFinally();

	public void sayAround(String param);

	public void sayAdvisorBefore(String param);
}
