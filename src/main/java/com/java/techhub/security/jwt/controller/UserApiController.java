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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author mahes
 *
 */
@RestController
@RequestMapping("/api/v1/user")
@Api(description = "Operations pertaining to add new users to database for token generation")
public class UserApiController {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "Add a new user to the Users database", response = Map.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully saved the user to the datasbase"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Something went wrong while saving the user data") })
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
