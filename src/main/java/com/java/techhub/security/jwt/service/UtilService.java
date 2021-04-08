/**
 * 
 */
package com.java.techhub.security.jwt.service;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author mahes
 *
 */
public interface UtilService {

	UserDetails loadUserByUsername(String username);
	
	String generateJwtToken(UserDetails userDetails);

	String extractUsername(String jwtToken);
	
	boolean validateToken(String jwtToken, UserDetails userDetails);
}
