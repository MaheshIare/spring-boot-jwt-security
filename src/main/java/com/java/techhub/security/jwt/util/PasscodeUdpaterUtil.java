/**
 * 
 */
package com.java.techhub.security.jwt.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.java.techhub.security.jwt.model.Users;
import com.java.techhub.security.jwt.repository.UsersRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author mahes
 *
 */
@Slf4j
@Component
public class PasscodeUdpaterUtil {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UsersRepository usersRepository;

	@Transactional
	@EventListener(value = { ApplicationReadyEvent.class })
	public void onApplicationReady() {
		try {
			log.debug("Creating session");
			String encodedUser = passwordEncoder.encode("user");
			String encodedAdmin = passwordEncoder.encode("admin");
			String encodedOperator = passwordEncoder.encode("operator");
			log.debug("Encoded passcode for user: {}", encodedUser);
			log.debug("Encoded passcode for admin: {}", encodedAdmin);
			log.debug("Encoded passcode for operator: {}", encodedOperator);
			log.debug("Persisting users data started...");
			Users user = getUserObject("user", encodedUser);
			Users admin = getUserObject("admin", encodedAdmin);
			Users operator = getUserObject("operator", encodedOperator);
			usersRepository.saveAndFlush(user);
			usersRepository.saveAndFlush(admin);
			usersRepository.saveAndFlush(operator);
			log.debug("Persisting users data finished...");
		} catch (Exception e) {
			log.error("Exception while udpating passcodes due to: {}", e);
		} 
	}

	private Users getUserObject(String key, String encodedUser) {
		Users user = usersRepository.findById(key).get();
		user.setPassword(encodedUser);
		return user;
	}

}
