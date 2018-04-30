package com.liugw.timetask;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.liugw.spmvc.service.UserService;

public class QuartzTestJob implements Job {

	private static UserService userService;
	static {
		System.out.println("QuartzTestJob ..................1");
		userService = (UserService) SpringUtils.getBean(UserService.class);
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println(new Date() + "HL");

		if (userService == null) {
			System.out.println("userService is null");
			// userService = new UserService();
		}
		System.out.println("HL, name=" + userService.getUserById(1).getName());
	}

}
