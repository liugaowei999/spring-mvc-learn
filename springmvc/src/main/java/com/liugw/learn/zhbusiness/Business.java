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
		System.out.println("��ǰ����Ŀͻ�����Ϊ:" + customer.getName());
	}
}
