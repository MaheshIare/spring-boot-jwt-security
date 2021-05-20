/**
 * 
 */
package com.java.techhub.security.jwt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.techhub.security.jwt.model.AuthenticationRequest;
import com.java.techhub.security.jwt.service.UserService;
import com.java.techhub.security.jwt.service.UtilService;
import com.java.techhub.security.jwt.util.UserRole;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author mahes
 *
 */
@RestController
@RequestMapping("/auth")
@Api(description = "Token generation API")
public class AuthenticationTokenController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UtilService utilService;
	
	@Autowired
	private UserService userService;

	/**
	 * Method for generating the JWT token based on the user details passed in the
	 * request body
	 * 
	 * @param authenticationRequest
	 * @return JWT token
	 */
	@ApiOperation(value = "Generate a new JWT Token for the successfully authenticated user", response = Map.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully generated the JWT token"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Something went wrong while generating the JWT token") })
	@PostMapping("/token")
	public ResponseEntity<Map<String, String>> generateToken(@RequestBody AuthenticationRequest authenticationRequest) {
		Map<String, String> map = new HashMap<>();
		String username = null;
		try {
			username = authenticationRequest.getUsername();
			// To authenticate the passed in user object and returns a fully authenticated
			// object
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(username, authenticationRequest.getPassword()));
		} catch (AuthenticationException e) {
			map.put("message", "Bad credentials");
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

		// Load details of the logged in user from database
		UserDetails userDetails = utilService.loadUserByUsername(username);

		// Generate JWT Token for the successfully authenticated user
		String jwtToken = utilService.generateJwtToken(userDetails);
		map.put("username", username);
		map.put("token", jwtToken);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	/**
	 * Retrieve all the valid roles information
	 * 
	 * @return List of User Roles
	 */
	@ApiOperation(value = "Retrieve roles from the User roles database", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved the roles from the datasbase"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Something went wrong while retrieving the roles data") })
	@GetMapping("/role")
	public ResponseEntity<List<UserRole>> getUserRoles() {
		return new ResponseEntity<List<UserRole>>(userService.userRoles(), HttpStatus.OK);
	}

}
