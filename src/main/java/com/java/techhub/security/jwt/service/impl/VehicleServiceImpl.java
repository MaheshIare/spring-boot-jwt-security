/**
 * 
 */
package com.java.techhub.security.jwt.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.techhub.security.jwt.model.Vehicle;
import com.java.techhub.security.jwt.repository.VehicleRepository;
import com.java.techhub.security.jwt.service.VehicleService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author mahes
 *
 */
@Slf4j
@Service
public class VehicleServiceImpl implements VehicleService {
	
	@Autowired
	private VehicleRepository vehicleRepository;

	@Override
	public Vehicle addVehicle(Vehicle vehicle) {
		return vehicleRepository.saveAndFlush(vehicle);
	}

	@Override
	public Vehicle getVehicle(Integer vehicleId) {
		Optional<Vehicle> optional = vehicleRepository.findById(vehicleId);
		return optional.isPresent() ? optional.get() : optional.orElse(null);
	}

	@Override
	public List<Vehicle> getAllVehicles() {
		return vehicleRepository.findAll();
	}

	@Override
	public Vehicle updateVehicle(Integer vehicleId, Vehicle vehicle) {
		vehicle.setVehicleId(vehicleId);
		return vehicleRepository.saveAndFlush(vehicle);
	}

	@Override
	public String deleteVehicle(Integer vehicleId) {
		String response = "Failed to delete the user";
		try {
			vehicleRepository.deleteById(vehicleId);
			response = "Successfully deleted the user with id: " + vehicleId;
		} catch (Exception e) {
			log.error("Exception occured while deleting the user due to: {}", e);
		}
		return response;
	}

}
