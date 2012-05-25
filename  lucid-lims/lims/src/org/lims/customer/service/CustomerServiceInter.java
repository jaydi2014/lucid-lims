/**
 * 
 */
package org.lims.customer.service;

import java.util.HashMap;

import org.lims.customer.dto.CustomerDto;

/**
 * @author Muralidhar Yaragalla
 *
 */
public interface CustomerServiceInter {
	
	/**
	 * This holds the error messages of the service layer.
	 */
	HashMap<String,String> exceptions=new HashMap<String,String>();
	
	/**
	 * This adds the customer to the system.
	 * @param customer
	 * @throws Exception
	 */
	public void addCustomer(CustomerDto customerdto)throws Exception;
	
	/**
	 * This checks whether the customer name already exist.
	 * @param CustName
	 * @return Boolean
	 * @throws Exception
	 */
	public Boolean checkCustomerExist(String custName)throws Exception;

}
