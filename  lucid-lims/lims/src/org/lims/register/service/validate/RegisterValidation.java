/**
 * 
 */
package org.lims.register.service.validate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import org.lims.common.exceptions.InvalidInputException;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class RegisterValidation {

	private static ResourceBundle resources=Util.getResources();
	
	/**
	 * validates registration number.
	 * @param regNumber
	 * @throws InvalidInputException
	 */
	public static void validateRegNumber(String regNumber)throws InvalidInputException{
		if(!regNumber.matches("^[a-zA-Z1-9/\\\\.\\s_-]{5,45}$")){
			throw new InvalidInputException(resources.getString("regNumberInvalid"));
		}
	}
	
	/**
	 * validates registration date.
	 * @param regDate
	 * @throws InvalidInputException
	 */
	public static void validateRegDate(String regDate)throws InvalidInputException{
		DateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
		try{
			dateFormat.parse(regDate);
		}catch(ParseException pe){
			String errrorMsg=resources.getString("regDateInvalid")+pe.getMessage();
			throw new InvalidInputException(errrorMsg);
		}		
	}
	
	/**
	 * validates due date.
	 * @param dueDate
	 * @throws InvalidInputException
	 */
	public static void validateDueDate(String dueDate)throws InvalidInputException{
		DateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
		try{
			dateFormat.parse(dueDate);
		}catch(ParseException pe){
			String errrorMsg=resources.getString("dueDateInvalid")+pe.getMessage();
			throw new InvalidInputException(errrorMsg);
		}	
	}
	
	/**
	 * validates total test charges.
	 * @param testCharges
	 * @throws InvalidInputException
	 */
	public static void validateTotalTestCharges(String testCharges)throws InvalidInputException{
		if(!testCharges.matches("[0-9\\.]{1,15}")){
			throw new InvalidInputException(resources.getString("totalTestChargesInvalid"));
		}
	}
	
	/**
	 * validates Total Amount Paid.
	 * @param amountPaid
	 * @throws InvalidInputException
	 */
	public static void validateAmountPaid(String amountPaid)throws InvalidInputException{
		if(!amountPaid.matches("[0-9\\.]{1,15}")){
			throw new InvalidInputException(resources.getString("amountPaidInvalid"));
		}
	}
	
	/**
	 * validates payment method.
	 * @param paymentMeth
	 * @throws InvalidInputException
	 */
	public static void validatePaymentMeth(String paymentMeth)throws InvalidInputException{
		if(!paymentMeth.matches("[a-zA-Z0-9\\.\\s_-]{2,45}")){
			throw new InvalidInputException(resources.getString("paymentMethodInvalid"));
		}
	}
	
	
}
