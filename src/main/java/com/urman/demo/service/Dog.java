package com.urman.demo.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class Dog implements Animal{

	@Override
	public String chars() {
		return "bark";
	}

}
