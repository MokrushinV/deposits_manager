package com.deposits.exception;


public class BankNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BankNotFoundException (Integer id) {
		super ("Could not find bank " + id);
	}
}
