package edu.smcm.util;

/**
 * An exception that is thrown when there is an error in the implementation. It
 * is used for defensive programming purposes.
 */
public class ImplementationException extends RuntimeException {

	/**
	 * To satisfy Eclipse
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * A constructor.
	 * 
	 * @param message identifying message
	 */
	public ImplementationException(String message) {
		super(message);
	}

}
