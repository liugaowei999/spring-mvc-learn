package com.liugw.timetask;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class QuartzDemoJob implements Job {
	static {
		System.out.println("QuartzDemoJob ..................2");
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println(new Date() + "FXP");
	}

}