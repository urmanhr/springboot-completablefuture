package com.urman.demo.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.urman.demo.entity.User;
import com.urman.demo.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public CompletableFuture<List<User>> saveUser(File file) {
		long startTime = System.currentTimeMillis();
		final ObjectMapper mapper = new ObjectMapper();

		try {
			/*
			 * final List<User> users = mapper.readValue(new File("users.json"), new
			 * TypeReference<List<User>>() { });
			 */
			final List<User> users = mapper.readValue(file, new TypeReference<List<User>>() {
			});
			userRepository.saveAll(users);
			long endTime = System.currentTimeMillis();
			log.info("total time : {}", endTime - startTime);
//			return CompletableFuture.supplyAsync(() -> users);
			return CompletableFuture.completedFuture(users);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public CompletableFuture<List<User>> findAllUsers() {
		log.info("getting list of users" + Thread.currentThread().getName());
		List<User> users = userRepository.findAll();
		return CompletableFuture.completedFuture(users);
	}
}
