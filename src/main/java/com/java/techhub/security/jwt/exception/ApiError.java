package com.java.techhub.security.jwt.exception;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {

	private HttpStatus status;
	private String message;
	private List<String> errors;

	public ApiError(HttpStatus status, String message, String... error) {
		super();
		this.status = status;
		this.message = message;
		errors = Arrays.asList(error);
	}
}