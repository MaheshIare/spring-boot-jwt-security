/**
 * 
 */
package com.java.techhub.security.jwt.service;

import com.java.techhub.security.jwt.exception.InvalidUserException;
import com.java.techhub.security.jwt.exception.InvalidUserRoleException;
import com.java.techhub.security.jwt.model.UserRequest;

/**
 * @author mahes
 *
 */
public interface UserService {

	String saveUser(UserRequest userRequest) throws InvalidUserRoleException, InvalidUserException;
}
