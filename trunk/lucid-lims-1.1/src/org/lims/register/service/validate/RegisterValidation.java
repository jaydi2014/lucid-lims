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
	 * validates customer name.
	 * @param custName
	 * @throws Exception
	 */
	public static void validateCustomerName(String custName)throws Exception{
		if(!custName.matches("[a-zA-Z0-9\\/&\\s]{5,45}")){
			throw new InvalidInputException(resources.getString("custNameInvalid"));
		}		
	}
	
	/**
	 * validates contact person name.
	 * @param ctPersonName
	 * @throws Exception
	 */
	public static void validateContactPersonName(String ctPersonName)throws Exception{
		if(!ctPersonName.matches("[a-zA-Z\\/\\.\\s]{4,45}")){
			throw new InvalidInputException(resources.getString("contactPersonNameInvalid"));
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
		}catch(Exception pe){
			String errorMsg=resources.getString("dueDateInvalid");
			throw new InvalidInputException(errorMsg);
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
	
	/**
	 * validates view registrations to date field.
	 * @param toDate
	 * @throws InvalidInputException
	 */
	public static void validateViewRegToDate(Date toDate)throws InvalidInputException{		
		String errrorMsg=resources.getString("viewRegToDateInvalid");
		if(toDate ==null){
			throw new InvalidInputException(errrorMsg);
		}			
	}
	
	/**
	 * validates lab due date.
	 * @param labDueDate
	 * @throws InvalidInputException
	 */
	public static void validateLabDueDate(Date labDueDate)throws InvalidInputException{		
		String errrorMsg=resources.getString("labDueDateInvalid");
		if(labDueDate ==null){
			throw new InvalidInputException(errrorMsg);
		}			
	}
	
	/**
	 * validates customer reference number.
	 * @param customerRefNo
	 * @throws InvalidInputException
	 */
	public static void validateCustomerRefNo(String customerRefNo)throws InvalidInputException{
		if(!customerRefNo.matches("[a-zA-Z0-9\\.\\s,-]{0,50}")){
			throw new InvalidInputException(resources.getString("customerReferenceNumberInvalid"));
		}
	}
	
	
	
	/**
	 * validates quantity adequate field.
	 * @param qtyAdequate
	 * @throws InvalidInputException
	 */
	public static void validateQtyAdequate(String qtyAdequate)throws InvalidInputException{
		if(!qtyAdequate.matches("[a-zA-Z0-9\\.\\s,-]{0,50}")){
			throw new InvalidInputException(resources.getString("quantityAdequateInvalid"));
		}
	}
	
	/**
	 * validates quantity inadequate field.
	 * @param qtyInAdequate
	 * @throws InvalidInputException
	 */
	public static void validateQtyInAdequate(String qtyInAdequate)throws InvalidInputException{
		if(!qtyInAdequate.matches("[a-zA-Z0-9\\.\\s,-]{0,50}")){
			throw new InvalidInputException(resources.getString("quantityInAdequateInvalid"));
		}
	}
	
	/**
	 * validates storage condition room temperature field.
	 * @param storageConditionRoomTemp
	 * @throws InvalidInputException
	 */
	public static void validateStorageConditionRoomTemp(String storageConditionRoomTemp)throws InvalidInputException{
		if(!storageConditionRoomTemp.matches("[a-zA-Z0-9\\.\\s,-]{0,50}")){
			throw new InvalidInputException(resources.getString("storageConditionRoomTempInvalid"));
		}
	}
	
	/**
	 * validates storage condition customer request field.
	 * @param storageConditionCustomerRequest
	 * @throws InvalidInputException
	 */
	public static void validateStorageConditionCustomerRequest(String storageConditionCustomerRequest)throws InvalidInputException{
		if(!storageConditionCustomerRequest.matches("[a-zA-Z0-9\\.\\s,-]{0,50}")){
			throw new InvalidInputException(resources.getString("storageConditionCustomerRequestInvalid"));
		}
	}
	
	/**
	 * validates conditions on arrival acceptable field.
	 * @param coaAcceptable
	 * @throws InvalidInputException
	 */
	public static void validateCoaAcceptable(String coaAcceptable)throws InvalidInputException{
		if(!coaAcceptable.matches("[a-zA-Z0-9\\.\\s,-]{0,50}")){
			throw new InvalidInputException(resources.getString("coaAcceptableInvalid"));
		}
	}
	
	/**
	 * validates conditions on arrival not acceptable field.
	 * @param coaNotAcceptable
	 * @throws InvalidInputException
	 */
	public static void validateCoaNotAcceptable(String coaNotAcceptable)throws InvalidInputException{
		if(!coaNotAcceptable.matches("[a-zA-Z0-9\\.\\s,-]{0,50}")){
			throw new InvalidInputException(resources.getString("coaNotAcceptableInvalid"));
		}
	}
	
	/**
	 * validates customer seal intact field.
	 * @param sealIntact
	 * @throws InvalidInputException
	 */
	public static void validateSealIntact(String sealIntact)throws InvalidInputException{
		if(!sealIntact.matches("[a-zA-Z0-9\\.\\s,-]{0,50}")){
			throw new InvalidInputException(resources.getString("customerSealIntactInvalid"));
		}
	}
	
	/**
	 * validates seal not intact field.
	 * @param sealNotIntact
	 * @throws InvalidInputException
	 */
	public static void validateSealNotIntact(String sealNotIntact)throws InvalidInputException{
		if(!sealNotIntact.matches("[a-zA-Z0-9\\.\\s,-]{0,50}")){
			throw new InvalidInputException(resources.getString("customerSealNotIntactInvalid"));
		}
	}
	
	/**
	 * validates test method available field.
	 * @param testMethodAvailable
	 * @throws InvalidInputException
	 */
	public static void validateTestMethodAvailable(String testMethodAvailable)throws InvalidInputException{
		if(!testMethodAvailable.matches("[a-zA-Z0-9\\.\\s,-]{0,50}")){
			throw new InvalidInputException(resources.getString("testMethodAvailableInvalid"));
		}
	}
	
	/**
	 * validates test method not available field. 
	 * @param testMethodNotAvailable
	 * @throws InvalidInputException
	 */
	public static void validateTestMethodNotAvailable(String testMethodNotAvailable)throws InvalidInputException{
		if(!testMethodNotAvailable.matches("[a-zA-Z0-9\\.\\s,-]{0,50}")){
			throw new InvalidInputException(resources.getString("testMethodNotAvailableInvalid"));
		}
	}
	
	/**
	 * validates cheque number field.
	 * @param chequeNumber
	 * @throws InvalidInputException
	 */
	public static void validateChequeNumber(String chequeNumber)throws InvalidInputException{
		if(!chequeNumber.matches("[a-zA-Z0-9\\.\\s,-]{0,50}")){
			throw new InvalidInputException(resources.getString("chequeNumberInvalid"));
		}
	}	
	
}
