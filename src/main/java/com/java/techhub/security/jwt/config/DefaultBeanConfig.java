/**
 * 
 */
package com.java.techhub.security.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author mahes
 *
 */
@Configuration
public class DefaultBeanConfig {

	/**
	 * Password Encoder for encoding passwords
	 * @return passwordEncoder
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	/**
	 * Object mapper for data serialization
	 * @return objectMapper
	 */
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
}
