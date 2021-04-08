/**
 * 
 */
package com.java.techhub.security.jwt.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.techhub.security.jwt.exception.InvalidUserException;
import com.java.techhub.security.jwt.exception.InvalidUserRoleException;
import com.java.techhub.security.jwt.model.UserRequest;
import com.java.techhub.security.jwt.service.UserService;

/**
 * @author mahes
 *
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserApiController {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<Map<String, String>> addNewUser(@RequestBody UserRequest userRequest)
			throws InvalidUserRoleException, InvalidUserException {
		Map<String, String> map = new HashMap<>();
		String response = userService.saveUser(userRequest);
		if (response.equals("SUCCESS")) {
			map.put("message", "User details saved successfully");
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
