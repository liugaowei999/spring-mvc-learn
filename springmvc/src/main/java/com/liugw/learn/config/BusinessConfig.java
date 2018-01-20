package com.liugw.learn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.liugw.learn.zhbusiness.Business;
import com.liugw.learn.zhbusiness.Customer;

@Configuration
@ComponentScan("com.liugw.learn.zhbusiness")
public class BusinessConfig {
	@Bean
	public Customer getCustomer() {
		Customer customer = new Customer();
		customer.setName("f9ds8af98ew");
		return customer;
	}

	@Bean(name = "busi")
	public Business getBusiness() {
		Business business = new Business(getCustomer());
		return business;
	}
}
