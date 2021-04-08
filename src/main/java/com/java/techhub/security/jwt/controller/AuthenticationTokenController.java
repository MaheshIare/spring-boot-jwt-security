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

	@PostMapping
	public ResponseEntity<Map<String, String>> generateToken(@RequestBody AuthenticationRequest authenticationRequest) {
		Map<String, String> map = new HashMap<>();
		String username = null;
		try {
			username = authenticationRequest.getUsername();
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(username, authenticationRequest.getPassword()));
		} catch (AuthenticationException e) {
			map.put("message", "Bad credentials");
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

		UserDetails userDetails = utilService.loadUserByUsername(username);
		String jwtToken = utilService.generateJwtToken(userDetails);
		map.put("token", jwtToken);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

}
