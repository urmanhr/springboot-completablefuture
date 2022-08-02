package com.urman.demo.service;

import org.springframework.stereotype.Service;

@Service
public class Cat implements Animal{

	@Override
	public String chars() {
		return "meow";
	}

}
