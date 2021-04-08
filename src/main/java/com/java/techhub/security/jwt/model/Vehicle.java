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
	private Integer vehicleId;
	
	@Column(name = "VEHICLE_NAME")
	private String name;
	
	@Column(name = "VEHICLE_TYPE")
	private String type;
	
	@Column(name = "VEHICLE_MODEL")
	private String model;
	
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
