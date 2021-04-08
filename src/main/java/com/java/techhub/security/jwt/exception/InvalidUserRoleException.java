/**
 * 
 */
package com.java.techhub.security.jwt.exception;

/**
 * @author mahes
 *
 */
public class InvalidUserRoleException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidUserRoleException() {
		super();
	}

	public InvalidUserRoleException(String message) {
		super(message);
	}

	public InvalidUserRoleException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidUserRoleException(Throwable cause) {
		super(cause);
	}
}
