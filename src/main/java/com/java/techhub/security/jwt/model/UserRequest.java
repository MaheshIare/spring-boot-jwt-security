/**
 * 
 */
package com.java.techhub.security.jwt.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

	@ApiModelProperty(name = "Username", required = true, notes = "Username to be used for token generation", allowEmptyValue = false, dataType = "java.lang.String")
	private String username;
	@ApiModelProperty(name = "Password", required = true, notes = "Password to be used for token generation", allowEmptyValue = false, dataType = "java.lang.String")
	private String password;
	@ApiModelProperty(name = "Role", required = true, notes = "Role to be associated with the user", allowEmptyValue = false, dataType = "java.lang.String")
	private String role;
}
