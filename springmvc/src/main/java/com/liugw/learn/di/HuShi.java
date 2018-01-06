package com.liugw.learn.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HuShi {

	@Autowired
	private Man human;

	public Human getHuman() {
		return human;
	}

	public void setHuman(Man human) {
		this.human = human;
	}

	//private String what;

	public HuShi() {
	}

	public HuShi(Man human) {
		this.human = human;
	}

	//	public String getWhat() {
	//		return what;
	//	}
	//
	//	public void setWhat(String what) {
	//		this.what = what;
	//	}

	public void doWork() {
		System.out.println("================== start work ==================");
		human.sayHello();
		System.out.println(" I do the work:");
		System.out.println("================== end work ==================");
	}
}
