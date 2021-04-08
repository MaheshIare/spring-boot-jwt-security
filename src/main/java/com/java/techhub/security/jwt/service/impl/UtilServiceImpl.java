/**
 * 
 */
package com.java.techhub.security.jwt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.java.techhub.security.jwt.config.JdbcUserDetailsService;
import com.java.techhub.security.jwt.service.UtilService;
import com.java.techhub.security.jwt.util.HeaderUtil;
import com.java.techhub.security.jwt.util.JwtUtil;

/**
 * @author mahes
 *
 */
@Service
public class UtilServiceImpl implements UtilService {
	
	@Autowired
	private HeaderUtil headerUtil;
	
	@Autowired
	private JdbcUserDetailsService jdbcUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public String[] extractCredentials(String authHeader) {
		return headerUtil.extractUserDetails(authHeader);
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		return jdbcUserDetailsService.loadUserByUsername(username);
	}

	@Override
	public String generateJwtToken(UserDetails userDetails) {
		return jwtUtil.generateToken(userDetails);
	}

	@Override
	public String extractUsername(String jwtToken) {
		return jwtUtil.extractUsername(jwtToken);
	}

	@Override
	public boolean validateToken(String jwtToken, UserDetails userDetails) {
		return jwtUtil.validateToken(jwtToken, userDetails);
	}

}
