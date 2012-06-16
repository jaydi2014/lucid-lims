/**
 * 
 */
package org.lims.register.service.validate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import org.lims.common.exceptions.InvalidInputException;
import org.lims.register.service.RegisterService;
import org.lims.register.service.RegisterServiceInter;
import org.lims.util.Constants;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class RegisterValidation {

	private static ResourceBundle resources=Util.getResources();
	private static RegisterServiceInter service=new RegisterService();
	
	/**
	 * validates registration number.
	 * @param regNumber
	 * @throws InvalidInputException
	 */
	public static void validateRegNumber(String regNumber)throws Exception{
		if(!regNumber.matches("^[a-zA-Z0-9/\\\\.\\s_-]{3,45}$")){
			throw new InvalidInputException(resources.getString("regNumberInvalid"));
		}
		
		if(service.checkRegNumExist(regNumber)){
			throw new InvalidInputException(resources.getString("regNumberAlreadyExist"));
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
			String errrorMsg=resources.getString("regDateInvalid");
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
			String errrorMsg=resources.getString("dueDateInvalid");
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
		if(!paymentMeth.matches("[a-zA-Z0-9,\\.\\s_-]{2,45}")){
			throw new InvalidInputException(resources.getString("paymentMethodInvalid"));
		}
	}
	
	/**
	 * validates special instructions.
	 * @param specialInstrs
	 * @throws InvalidInputException
	 */
	public static void validateSpecialInstrs(String specialInstrs)throws InvalidInputException{
		if(!specialInstrs.matches("[a-zA-Z0-9\\.\\s,-]{0,1000}")){
			throw new InvalidInputException(resources.getString("specialInstructionsInvalid"));
		}
	}
	
	/**
	 * validates the nature of sample packing.
	 * @param samplePacking
	 * @throws InvalidInputException
	 */
	public static void validateSamplePacking(String samplePacking)throws InvalidInputException{
		if(!samplePacking.matches("[a-zA-Z\\.\\s,]{0,100}")){
			throw new InvalidInputException(resources.getString("samplePackingInvalid"));
		}
	}	
	
	/**
	 * validates dispatch date.
	 * @param dispatchDate
	 * @throws InvalidInputException
	 */
	public static void validateDispatchDate(String dispatchDate)throws InvalidInputException{
		DateFormat dateFormat=new SimpleDateFormat(Constants.DATE_PATTERN);
		try{
			if(dispatchDate!=null)
				dateFormat.parse(dispatchDate);
		}catch(ParseException pe){
			String errrorMsg=resources.getString("dispatchDateInvalid");
			throw new InvalidInputException(errrorMsg);
		}		
	}
	
	/**
	 * validates dispatch method.
	 * @param dispatchMeth
	 * @throws InvalidInputException
	 */
	public static void validateDispatchMethod(String dispatchMeth)throws InvalidInputException{
		if(!dispatchMeth.matches("[a-zA-Z\\.\\s,]{3,50}")){
			throw new InvalidInputException(resources.getString("dispatchMethInvalid"));
		}
	}
	
	/**
	 * validates page size. 
	 * @param pagesize
	 * @throws InvalidInputException
	 */
	public static void validatePageSize(String pagesize)throws InvalidInputException{
		if(!pagesize.matches("[0-9]{2,3}")){
			throw new InvalidInputException(resources.getString("pageSizeInvalid"));
		}
	}
	
	/**
	 * validates view registration's from date.
	 * @param fromDate
	 * @throws InvalidInputException
	 */
	public static void validateViewRegFromDate(Date fromDate)throws InvalidInputException{		
		String errrorMsg=resources.getString("viewRegFromDateInvalid");
		if(fromDate ==null){
			throw new InvalidInputException(errrorMsg);
		}			
	}
	
	public static void validateViewRegToDate(Date toDate)throws InvalidInputException{		
		String errrorMsg=resources.getString("viewRegToDateInvalid");
		if(toDate ==null){
			throw new InvalidInputException(errrorMsg);
		}			
	}
	
}
