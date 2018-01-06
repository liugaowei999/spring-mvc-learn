package com.liugw.learn.zdi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Animal {

	@Autowired
	private Leg leg;
	private String name;

	public Animal() {

	}

	public Animal(String name) {
		this.name = name;
	}

	public void eat() {
		System.out.println(name + " is eating");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
