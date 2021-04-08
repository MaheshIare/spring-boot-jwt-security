/**
 * 
 */
package com.java.techhub.security.jwt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author mahes
 *
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "VEHICLE_INFO")
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "VEHICLE_ID")
	@ApiModelProperty(name = "Vehicle ID", required = false, notes = "The database generated vehicle ID", allowEmptyValue = false, dataType = "java.lang.Integer", hidden = true)
	private Integer vehicleId;
	
	@ApiModelProperty(name = "Vehicle Name", required = true, notes = "Name of the vehicle", allowEmptyValue = true, dataType = "java.lang.String")
	@Column(name = "VEHICLE_NAME")
	private String name;
	
	@ApiModelProperty(name = "Vehicle Type", required = true, notes = "Type of the vehicle", allowEmptyValue = true, dataType = "java.lang.String")
	@Column(name = "VEHICLE_TYPE")
	private String type;
	
	@ApiModelProperty(name = "Vehicle Model", required = true, notes = "Model of the vehicle", allowEmptyValue = true, dataType = "java.lang.String")
	@Column(name = "VEHICLE_MODEL")
	private String model;
	
	@ApiModelProperty(name = "Vehicle Manufacturer", required = true, notes = "Manufacturer of the vehicle", allowEmptyValue = true, dataType = "java.lang.String")
	@Column(name = "VEHICLE_MANUFACTURER")
	private String manufacturer;

	/**
	 * @param name
	 * @param type 
	 * @param model
	 * @param manufacturer
	 */
	public Vehicle(String name, String type, String model, String manufacturer) {
		super();
		this.name = name;
		this.type = type;
		this.model = model;
		this.manufacturer = manufacturer;
	}
	
	
}
