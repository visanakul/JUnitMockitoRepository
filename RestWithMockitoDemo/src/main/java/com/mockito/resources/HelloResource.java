package com.mockito.resources;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mockito.model.User;

@RestController
public class HelloResource {
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello World!!!";
	}

	@PostMapping(value = "/login", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public User getUserJson(@RequestParam("uname") String uname) {
		if (uname.equals("admin")) {
			User user = new User();
			user.setUname(uname);
			user.setPass("admin123");
			return user;
		} else {
			throw new RuntimeException("User does not exist");
		}
	}
}
