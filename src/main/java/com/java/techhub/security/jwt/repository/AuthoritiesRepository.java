/**
 * 
 */
package com.java.techhub.security.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.techhub.security.jwt.model.Authorities;

/**
 * @author mahes
 *
 */
public interface AuthoritiesRepository extends JpaRepository<Authorities, String> {

	Authorities findByUsername(String username);
}
