package com.deposits.exception;

public class DepositNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DepositNotFoundException (Integer id) {
		super ("Could not find deposit " + id);
	}

}
