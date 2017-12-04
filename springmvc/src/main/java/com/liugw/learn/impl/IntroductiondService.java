package com.liugw.learn.impl;

import com.liugw.learn.intf.IIntroductionService;

public class IntroductiondService implements IIntroductionService {

	@Override
	public void induct() {
		System.out.println("=========introduction");
	}

}
