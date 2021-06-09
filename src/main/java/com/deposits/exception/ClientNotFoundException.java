package com.deposits.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception to be thrown when {@link com.deposits.entites.ClientEntity ClientEntity} with
 * a given id is not found.
 * @author Mokrushin Vladimir
 *
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No such client")
public class ClientNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClientNotFoundException(Integer id) {
		super ("Could not find client " + id);
	}
}
