package com.deposits.exception;

/**
 * Exception to be thrown when {@link com.deposits.entites.DepositEntity DepositEntity} with
 * a given id is not found.
 * @author Mokrushin Vladimir
 *
 */
public class DepositNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DepositNotFoundException (Integer id) {
		super ("Could not find deposit " + id);
	}

}
