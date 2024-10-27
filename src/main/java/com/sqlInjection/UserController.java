package com.sqlInjection;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping("/register")
	public String Register(@RequestBody UserEntity entity) {

		return service.Register(entity);

	}

	@GetMapping("/{name}")
	public List<UserEntity> users(@PathVariable("name") String name) {
		List<UserEntity> user = service.getUserByName(name);
		return user;
	}

	@GetMapping("/demo/user")
	public UserEntity userByName(@RequestParam("name") String name) {
		UserEntity user = service.getUserByName1(name);
		return user;
	}

	@GetMapping("/user")
	public UserEntity userByName2(@RequestParam("name") String name) {
		UserEntity user = service.getUserByName2(name);
		return user;
	}

}
