/**
 * 
 */
package com.java.techhub.security.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.techhub.security.jwt.model.Vehicle;

/**
 * @author mahes
 *
 */
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

}
