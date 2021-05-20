/**
 * 
 */
package com.java.techhub.security.jwt.service;

import java.util.List;

import com.java.techhub.security.jwt.exception.InvalidUserException;
import com.java.techhub.security.jwt.exception.InvalidUserRoleException;
import com.java.techhub.security.jwt.model.UserRequest;
import com.java.techhub.security.jwt.util.UserRole;

/**
 * @author mahes
 *
 */
public interface UserService {

	String saveUser(UserRequest userRequest) throws InvalidUserRoleException, InvalidUserException;
	
	String saveUserData(UserRequest userRequest);
	
	List<UserRole> userRoles();
}
