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

	/**
	 * Save the vehicle info to DB 
	 * @param input vehicle object
	 * @return saved vehicle object
	 */
	@Override
	public Vehicle addVehicle(Vehicle vehicle) {
		return vehicleRepository.saveAndFlush(vehicle);
	}

	/**
	 * Retrieve the vehicle information for the specific vehicleId
	 * @param vehicleId
	 * @return Retrieved vehicle object
	 */
	@Override
	public Vehicle getVehicle(Integer vehicleId) {
		Optional<Vehicle> optional = vehicleRepository.findById(vehicleId);
		return optional.isPresent() ? optional.get() : optional.orElse(null);
	}

	/**
	 * Retrieve all the vehicles information
	 * @return List of vehicles
	 */
	@Override
	public List<Vehicle> getAllVehicles() {
		return vehicleRepository.findAll();
	}

	/**
	 * Update the vehicle information for the specific vehicle id
	 * @param vehicleId 
	 * @param vehicle
	 * @return Updated vehicle object
	 */
	@Override
	public Vehicle updateVehicle(Integer vehicleId, Vehicle vehicle) {
		vehicle.setVehicleId(vehicleId);
		return vehicleRepository.saveAndFlush(vehicle);
	}

	/**
	 * Delete the vehicle information from DB for the specific vehicleId
	 * @param vehicleId
	 * @return String message with status of the deletion
	 */
	@Override
	public String deleteVehicle(Integer vehicleId) {
		String response = "Failed to delete the vehicle with id: ";
		try {
			vehicleRepository.deleteById(vehicleId);
			response = "Successfully deleted the vehicle with id: ";
		} catch (Exception e) {
			log.error("Exception occured while deleting the vehicle due to: {}", e);
		}
		return response + vehicleId;
	}

}
