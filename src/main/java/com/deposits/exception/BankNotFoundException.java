package com.deposits.exception;

/**
 * Exception to be thrown when {@link com.deposits.entites.BankEntity BankEntity} with
 * a given id is not found.
 * @author Mokrushin Vladimir
 *
 */
public class BankNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BankNotFoundException (Integer id) {
		super ("Could not find bank " + id);
	}
}
