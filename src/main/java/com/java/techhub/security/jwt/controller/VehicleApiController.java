/**
 * 
 */
package com.java.techhub.security.jwt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.techhub.security.jwt.model.Vehicle;
import com.java.techhub.security.jwt.service.VehicleService;

/**
 * @author mahes
 *
 */
@RestController
@RequestMapping("/api/v1/vehicle")
public class VehicleApiController {

	@Autowired
	private VehicleService vehicleService;

	/**
	 * Save the vehicle info to DB 
	 * @param input vehicle object
	 * @return saved vehicle object
	 */
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@PostMapping
	public ResponseEntity<Vehicle> addVehicle(@RequestBody(required = true) Vehicle vehicle) {
		return new ResponseEntity<>(vehicleService.addVehicle(vehicle), HttpStatus.OK);
	}

	/**
	 * Retrieve the vehicle information for the specific vehicleId
	 * @param vehicleId
	 * @return Retrieved vehicle object
	 */
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_OPERATOR')")
	@GetMapping("/{vehicleId}")
	public ResponseEntity<Vehicle> getVehicle(@PathVariable(name = "vehicleId", required = true) Integer vehicleId) {
		return new ResponseEntity<>(vehicleService.getVehicle(vehicleId), HttpStatus.OK);
	}

	/**
	 * Retrieve all the vehicles information
	 * @return List of vehicles
	 */
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_OPERATOR')")
	@GetMapping
	public ResponseEntity<List<Vehicle>> getVehicles() {
		return new ResponseEntity<List<Vehicle>>(vehicleService.getAllVehicles(), HttpStatus.OK);
	}

	/**
	 * Update the vehicle information for the specific vehicle id
	 * @param vehicleId 
	 * @param vehicle
	 * @return Updated vehicle object
	 */
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@PutMapping("/{vehicleId}")
	public ResponseEntity<Vehicle> updateVehicle(@PathVariable(name = "vehicleId", required = true) Integer vehicleId,
			@RequestBody(required = true) Vehicle vehicle) {
		return new ResponseEntity<>(vehicleService.updateVehicle(vehicleId, vehicle), HttpStatus.OK);
	}

	/**
	 * Delete the vehicle information from DB for the specific vehicleId
	 * @param vehicleId
	 * @return String message with status of the deletion
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{vehicleId}")
	public ResponseEntity<String> deleteVehicle(@PathVariable(name = "vehicleId", required = true) Integer vehicleId) {
		return new ResponseEntity<>(vehicleService.deleteVehicle(vehicleId), HttpStatus.OK);
	}

}
