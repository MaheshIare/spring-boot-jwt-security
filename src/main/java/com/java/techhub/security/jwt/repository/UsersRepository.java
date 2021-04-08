/**
 * 
 */
package com.java.techhub.security.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.techhub.security.jwt.model.Users;

/**
 * @author mahes
 *
 */
public interface UsersRepository extends JpaRepository<Users, String> {

}
