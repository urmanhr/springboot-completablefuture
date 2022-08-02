package com.urman.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urman.demo.service.Animal;

@RestController
@RequestMapping("autowired")
public class AutoWiredController {

//	@Autowired
//	@Qualifier("cat")
//	private Animal animal;
	
//	@Autowired
//	private Animal dog;
	
//	@Autowired
//	private Animal animal;
	
//	@Autowired
//	@Qualifier("cat")
//	private Animal cat;
	
	@Autowired
	@Qualifier("cat")
	private Animal dog;
	
	@GetMapping
	public String chars() {
		//return animal.chars();
		return dog.chars();
		//return cat.chars();
	}
}
