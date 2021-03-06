/**
 * 
 */
package com.java.techhub.security.jwt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author mahes
 *
 */
@RestController
@RequestMapping("/api/v1/vehicle")
@Api(description = "Operations pertaining to vehicles in Vehicle Store")
public class VehicleApiController {

	@Autowired
	private VehicleService vehicleService;

	/**
	 * Save the vehicle info to DB
	 * 
	 * @param input vehicle object
	 * @return saved vehicle object
	 */
	@ApiOperation(value = "Add a new vehicle to the Vehicle database", response = Vehicle.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully saved the vehicle to the datasbase"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Something went wrong while saving the vehicle data") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@PostMapping
	public ResponseEntity<Vehicle> addVehicle(@RequestBody(required = true) Vehicle vehicle) {
		return new ResponseEntity<>(vehicleService.addVehicle(vehicle), HttpStatus.OK);
	}

	/**
	 * Retrieve the vehicle information for the specific vehicleId
	 * 
	 * @param vehicleId
	 * @return Retrieved vehicle object
	 */
	@ApiOperation(value = "Retrieve a vehicle from the Vehicles database", response = Vehicle.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved the vehicle from the datasbase"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Something went wrong while retrieving the vehicle data") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_OPERATOR')")
	@GetMapping("/{vehicleId}")
	public ResponseEntity<Vehicle> getVehicle(@PathVariable(name = "vehicleId", required = true) Integer vehicleId) {
		return new ResponseEntity<>(vehicleService.getVehicle(vehicleId), HttpStatus.OK);
	}

	/**
	 * Retrieve all the vehicles information
	 * 
	 * @return List of vehicles
	 */
	@ApiOperation(value = "Retrieve vehicles from the Vehicles database", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved the vehicles from the datasbase"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Something went wrong while retrieving the vehicles data") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_OPERATOR')")
	@GetMapping
	public ResponseEntity<List<Vehicle>> getVehicles() {
		return new ResponseEntity<List<Vehicle>>(vehicleService.getAllVehicles(), HttpStatus.OK);
	}

	/**
	 * Update the vehicle information for the specific vehicle id
	 * 
	 * @param vehicleId
	 * @param vehicle
	 * @return Updated vehicle object
	 */
	@ApiOperation(value = "Update a vehicle in the Vehicles database", response = Vehicle.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated the vehicle in the datasbase"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Something went wrong while updating the vehicle data") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@PutMapping("/{vehicleId}")
	public ResponseEntity<Vehicle> updateVehicle(@PathVariable(name = "vehicleId", required = true) Integer vehicleId,
			@RequestBody(required = true) Vehicle vehicle) {
		return new ResponseEntity<>(vehicleService.updateVehicle(vehicleId, vehicle), HttpStatus.OK);
	}

	/**
	 * Delete the vehicle information from DB for the specific vehicleId
	 * 
	 * @param vehicleId
	 * @return String message with status of the deletion
	 */
	@ApiOperation(value = "Delete a vehicle info from the Vehicles database", response = Map.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully deleted the vehicle from the datasbase"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Something went wrong while deleting the vehicle data") })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{vehicleId}")
	public ResponseEntity<Map<String, String>> deleteVehicle(
			@PathVariable(name = "vehicleId", required = true) Integer vehicleId) {
		Map<String, String> map = new HashMap<>();
		String result = vehicleService.deleteVehicle(vehicleId);
		map.put("message", result);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

}
