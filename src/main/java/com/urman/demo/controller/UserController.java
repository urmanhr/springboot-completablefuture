package com.urman.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.urman.demo.entity.User;
import com.urman.demo.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/users", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, produces = "application/json")
	public ResponseEntity saveUsers(@RequestParam(value = "files") MultipartFile[] files) {
		for (MultipartFile multipartFile : files) {
			File file = new File("C:/temp.json");
			try {
				multipartFile.transferTo(file);
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			userService.saveUser(file);
			file.delete();

		}

		return ResponseEntity.status(HttpStatus.CREATED).build();

	}

	@GetMapping(value = "/users", produces = "application/json")
	public CompletableFuture<ResponseEntity> findAllUsers() {
		return userService.findAllUsers().thenApply(ResponseEntity::ok);
	}

	@GetMapping(value = "/usersbythread", produces = "application/json")
	public ResponseEntity getUsers() {
		CompletableFuture<List<User>> users1 = userService.findAllUsers();
		CompletableFuture<List<User>> users2 = userService.findAllUsers();
		CompletableFuture<List<User>> users3 = userService.findAllUsers();
		CompletableFuture.allOf(users1, users2, users3).join();
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
