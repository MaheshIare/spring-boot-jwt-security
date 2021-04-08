/**
 * 
 */
package com.java.techhub.security.jwt.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.techhub.security.jwt.model.AuthenticationRequest;
import com.java.techhub.security.jwt.service.UtilService;

/**
 * @author mahes
 *
 */
@RestController
@RequestMapping("/auth/token")
public class AuthenticationTokenController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UtilService utilService;

	/**
	 * Method for generating the JWT token based on the user details passed in the request body
	 * @param authenticationRequest
	 * @return JWT token
	 */
	@PostMapping
	public ResponseEntity<Map<String, String>> generateToken(@RequestBody AuthenticationRequest authenticationRequest) {
		Map<String, String> map = new HashMap<>();
		String username = null;
		try {
			username = authenticationRequest.getUsername();
			//To authenticate the passed in user object and returns a fully authenticated object
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(username, authenticationRequest.getPassword()));
		} catch (AuthenticationException e) {
			map.put("message", "Bad credentials");
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

		//Load details of the logged in user from database
		UserDetails userDetails = utilService.loadUserByUsername(username);
		
		//Generate JWT Token for the successfully authenticated user
		String jwtToken = utilService.generateJwtToken(userDetails);
		map.put("token", jwtToken);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

}
