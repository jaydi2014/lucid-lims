/**
 * 
 */
package org.lims.customer.dao;

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
}
