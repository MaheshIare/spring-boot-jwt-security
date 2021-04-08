/**
 * 
 */
package com.java.techhub.security.jwt.config;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.java.techhub.security.jwt.model.Authorities;
import com.java.techhub.security.jwt.model.Users;
import com.java.techhub.security.jwt.repository.AuthoritiesRepository;
import com.java.techhub.security.jwt.repository.UsersRepository;

/**
 * @author mahes
 * This is a custom UserDetailsService backed up by H2 datasource, used for retrieving the user details from database
 */
@Service
public class JdbcUserDetailsService implements UserDetailsService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private AuthoritiesRepository authoritiesRepository;

	/**
	 * Method to load the user details from database for the logged in user
	 *@return UserDetails 
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Users> optionalUser = usersRepository.findById(username);
		if (optionalUser.isPresent()) {
			Users user = optionalUser.get();
			Authorities auth = authoritiesRepository.findByUsername(user.getUsername());
			User authUser = new User(user.getUsername(), user.getPassword(), Arrays.asList(auth));
			return authUser;
		}
		return null;
	}

}
