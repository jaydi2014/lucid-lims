/**
 * 
 */
package org.lims.common.exceptions;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class InvalidInputException extends Exception {

	
	private static final long serialVersionUID = 3250433493651319379L;

	public InvalidInputException(String message, Throwable cause){
		super(message,cause);
	}
	
	public InvalidInputException(String message){
		super(message);
	}
}
