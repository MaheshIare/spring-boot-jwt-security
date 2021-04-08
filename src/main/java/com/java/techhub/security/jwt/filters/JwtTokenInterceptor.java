/**
 * 
 */
package com.java.techhub.security.jwt.filters;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.techhub.security.jwt.service.UtilService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author mahes
 *
 */
@Slf4j
@Component
public class JwtTokenInterceptor extends OncePerRequestFilter {

	@Autowired
	private UtilService utilService;

	@Autowired
	private ObjectMapper objectMapper;

	@SuppressWarnings("deprecation")
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String bearerToken = null;
		String username = null;
		String jwtToken = null;
		try {
			//Get the Authorization bearer token from the current request
			bearerToken = request.getHeader("Authorization");
			if (bearerToken != null && bearerToken.startsWith("Bearer")) {
				jwtToken = bearerToken.substring(7);
				log.info("Extracting the user from JWT token");
				//Extract the username from the JWT token
				username = utilService.extractUsername(jwtToken);
			}

			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				log.info("Fetching user details from the database for the authenticated user");
				//Load user details from database for the extracted user
				UserDetails userDetails = utilService.loadUserByUsername(username);
				
				//Validate the token for user data and expiry
				if (utilService.validateToken(jwtToken, userDetails)) {
					log.info("Token is valid and user is authenticated");
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
					usernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			}
			//Continuing the process of the filter chain
			filterChain.doFilter(request, response);
		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException
				| IllegalArgumentException ex) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
			Map<String, Object> data = new HashMap<>();
			data.put("timestamp", Calendar.getInstance().getTime());
			data.put("exception", ex.getMessage());
			response.getOutputStream().println(objectMapper.writeValueAsString(data));
		}

	}

}
