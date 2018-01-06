package com.liugw.learn.di;

import org.springframework.stereotype.Service;

@Service
public class Man implements Human {

	@Override
	public void sayHello() {
		// TODO Auto-generated method stub
		System.out.println("Man working......");
	}

}
