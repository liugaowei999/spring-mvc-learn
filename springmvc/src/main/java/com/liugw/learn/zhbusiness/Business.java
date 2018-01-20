package com.liugw.learn.zhbusiness;

//@Controller("aaa")
public class Business {
	//	@Autowired
	Customer customer;

	public Business() {

	}

	public Business(Customer customer) {
		this.customer = customer;
	}

	public void doBusiness() throws Throwable {
		System.out.println("当前处理的客户名称为:" + customer.getName());
	}
}
