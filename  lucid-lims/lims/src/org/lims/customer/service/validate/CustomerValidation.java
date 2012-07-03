/**
 * 
 */
package org.lims.customer.service.validate;

import java.util.ResourceBundle;

import org.lims.common.exceptions.InvalidInputException;
import org.lims.customer.dto.CustomerDto;
import org.lims.customer.service.CustomerService;
import org.lims.customer.service.CustomerServiceInter;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class CustomerValidation {

	private static ResourceBundle resources=Util.getResources();
	private static CustomerServiceInter service=new CustomerService();
	
	/**
	 * validates customer name.
	 * @param custName
	 * @throws Exception
	 */
	public static void validateCustomerName(String custName)throws Exception{
		if(!custName.matches("[a-zA-Z0-9\\/&\\s]{5,45}")){
			throw new InvalidInputException(resources.getString("custNameInvalid"));
		}
		
		if(service.checkCustomerExist(custName)){
			throw new InvalidInputException(resources.getString("custNameExist"));
		}
	}
	
	/**
	 * This validates the customer address.
	 * @param custAddr
	 * @throws InvalidInputException
	 */
	public static void validateCustomerAddr(String custAddr)throws InvalidInputException{
		if(!custAddr.matches("^[a-zA-Z0-9-\\/,#\\.\\s]{10,200}$")){
			throw new InvalidInputException(resources.getString("custAddrInvalid"));
		}
	}
	
	/**
	 * validates customer phone number.
	 * @param phoneNumber
	 * @throws Exception
	 */
	public static void validateCustomerPhoneNo(String phoneNumber)throws Exception{
		if(!phoneNumber.matches("[0-9-]{5,30}")){
			throw new InvalidInputException(resources.getString("custPhoneNumberInvalid"));
		}		
	}
	
	/**
	 * validates customer fax number.
	 * @param phoneNumber
	 * @throws Exception
	 */
	public static void validateCustomerFaxNo(String faxNumber)throws Exception{
		if(!faxNumber.matches("[0-9-]{0,30}")){
			throw new InvalidInputException(resources.getString("custFaxInvalid"));
		}		
	}
	
	/**
	 * validates customer email.
	 * @param custEmail
	 * @throws Exception
	 */
	public static void validateCustomerEmail(String custEmail)throws Exception{
		if(!custEmail.matches("[a-z0-9\\/\\.@_-]{0,50}")){
			throw new InvalidInputException(resources.getString("custEmailInvalid"));
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
	 * validates contact person mobile number.
	 * @param mobileNumber
	 * @throws Exception
	 */
	public static void validateContactPersonMobileNo(String ctMobileNumber)throws Exception{
		if(!ctMobileNumber.matches("[0-9-]{5,40}")){
			throw new InvalidInputException(resources.getString("contactPersonMobileInvalid"));
		}		
	}
	
	/**
	 * validates contact person email.
	 * @param ctPersonEmail
	 * @throws Exception
	 */
	public static void validateContactPersonEmail(String ctPersonEmail)throws Exception{
		if(!ctPersonEmail.matches("[a-z0-9\\/\\.@_-]{5,50}")){
			throw new InvalidInputException(resources.getString("contactPersonEmailInvalid"));
		}
	}
	
	/**
	 * validates customer name for add contact person.
	 * @param custName
	 * @throws Exception
	 */
	public static void validateAddCtPersonCustName(String custName)throws Exception{
		if(custName==null ||custName.isEmpty())
			throw new InvalidInputException(resources.getString("customerNameNotEmpty"));
	}
	
	/**
	 * validates contact person name to add to a customer.
	 * @param ctPersonName
	 * @param customer
	 * @throws Exception
	 */
	public static void validateCustContactPersonName(String ctPersonName,CustomerDto customer)throws Exception{
		if(!ctPersonName.matches("[a-zA-Z\\/\\.\\s]{4,45}")){
			throw new InvalidInputException(resources.getString("contactPersonNameInvalid"));
		}
		
		if(service.isContactPersonExist(customer)){
			throw new InvalidInputException(resources.getString("contactPersonNameExist"));
		}
	}
}
