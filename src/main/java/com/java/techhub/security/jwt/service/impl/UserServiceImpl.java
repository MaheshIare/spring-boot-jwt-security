/**
 * 
 */
package com.java.techhub.security.jwt.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.techhub.security.jwt.exception.InvalidUserException;
import com.java.techhub.security.jwt.exception.InvalidUserRoleException;
import com.java.techhub.security.jwt.model.Authorities;
import com.java.techhub.security.jwt.model.UserRequest;
import com.java.techhub.security.jwt.model.Users;
import com.java.techhub.security.jwt.repository.AuthoritiesRepository;
import com.java.techhub.security.jwt.repository.UsersRepository;
import com.java.techhub.security.jwt.service.UserService;
import com.java.techhub.security.jwt.util.UserRole;

/**
 * @author mahes
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private AuthoritiesRepository authoritiesRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public String saveUser(UserRequest userRequest) throws InvalidUserRoleException, InvalidUserException {
		if (!UserRole.isValidRole(userRequest.getRole())) {
			throw new InvalidUserRoleException("Invalid role given for the user");
		}
		
		if(isExistingUser(userRequest)) {
			throw new InvalidUserException("User already exists");
		}
		
		return saveUserData(userRequest);
	}

	private boolean isExistingUser(UserRequest userRequest) {
		Optional<Users> users = usersRepository.findById(userRequest.getUsername());
		if(users.isPresent())
			return true;
		return false;
	}

	@Override
	@Transactional
	public String saveUserData(UserRequest userRequest) {
		String encodedPassword = passwordEncoder.encode(userRequest.getPassword());
		Users user = usersRepository.saveAndFlush(new Users(userRequest.getUsername(), encodedPassword, 1));
		Authorities authority = authoritiesRepository
				.saveAndFlush(new Authorities(user.getUsername(), userRequest.getRole()));
		if (user != null && authority != null) {
			return "SUCCESS";
		}
		return "FAILURE";
	}

	@Override
	public List<UserRole> userRoles() {
		return Arrays.asList(UserRole.values());
	}

	
}
