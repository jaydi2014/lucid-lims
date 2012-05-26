/**
 * 
 */
package org.lims.customer.dao;

import java.util.List;

import org.lims.customer.dto.CustomerDto;

/**
 * @author Muralidhar Yaragalla
 *
 */
public interface CustomerDaoInter {

	/**
	 * This adds the customer to the database.
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
	
	/**
	 * Retrieves all the customer names from the database.
	 * @return list of customer names.
	 * @throws Exception
	 */
	public List<String> getAllCustomerNames()throws Exception;
	
	/**
	 * Returms the cutomer details when given customer name.
	 * @param custName
	 * @return CustomerDto
	 * @throws Exception
	 */
	public CustomerDto getCustomer(String custName)throws Exception;
}
