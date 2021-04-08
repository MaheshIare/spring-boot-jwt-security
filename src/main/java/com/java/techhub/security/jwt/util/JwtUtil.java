package com.java.techhub.security.jwt.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author mahes
 *
 */
@Service
public class JwtUtil {
	
	/**
	 * bcrypt hashed secret key for signing the jwt tokens
	 * Key was hashed with 16 rounds and with value 'spring-boot-jwt-security-secret'
	 */
	@Value("${spring.security.jwt.hash.key}")
	private String jwtHashKey;
	
	@Value("${spring.security.jwt.token.validity:60000}")
	private Integer jwtTokenValidity;

	
	/**
	 * Extract the username from JWT token
	 * @param token
	 * @return username
	 */
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	/**
	 * Extraction expiry time from the JWT token
	 * @param token
	 * @return expiry time
	 */
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(jwtHashKey).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	/**
	 * Generate the JWT token for the successfully authenticated user
	 * @param userDetails
	 * @return JWT Token
	 */
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userDetails.getUsername());
	}

	private String createToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + jwtTokenValidity))
				.signWith(SignatureAlgorithm.HS256, jwtHashKey).compact();
	}

	/**
	 * Validate token for the user and expiry
	 * @param token
	 * @param userDetails
	 * @return boolean flag specifying the validity of the token
	 */
	public boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}