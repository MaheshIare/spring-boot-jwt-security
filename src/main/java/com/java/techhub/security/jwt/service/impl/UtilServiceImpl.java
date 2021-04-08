/**
 * 
 */
package com.java.techhub.security.jwt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.java.techhub.security.jwt.config.JdbcUserDetailsService;
import com.java.techhub.security.jwt.service.UtilService;
import com.java.techhub.security.jwt.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @author mahes
 *
 */
@Slf4j
@Service
public class UtilServiceImpl implements UtilService {
	
	@Autowired
	private JdbcUserDetailsService jdbcUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;

	/**
	 * To load the user details from database for the logged in user
	 * @return UserDetails
	 */
	@Override
	public UserDetails loadUserByUsername(String username) {
		return jdbcUserDetailsService.loadUserByUsername(username);
	}

	/**
	 * To generate the JWT token with authenticated user details object
	 * @return JWT token
	 */
	@Override
	public String generateJwtToken(UserDetails userDetails) {
		return jwtUtil.generateToken(userDetails);
	}

	/**
	 * Extract the username from the JWT token
	 * @return Username 
	 */
	@Override
	public String extractUsername(String jwtToken) {
		return jwtUtil.extractUsername(jwtToken);
	}

	/**
	 * Validate the token for user details and expiry
	 * @return boolean: status of token validity
	 */
	@Override
	public boolean validateToken(String jwtToken, UserDetails userDetails) {
		log.info("Validating the JWT token");
		return jwtUtil.validateToken(jwtToken, userDetails);
	}

}
