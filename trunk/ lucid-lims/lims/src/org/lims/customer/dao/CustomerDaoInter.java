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
	 * Returns the cutomer details when given customer name.
	 * @param custName
	 * @return CustomerDto
	 * @throws Exception
	 */
	public CustomerDto getCustomer(String custName,Boolean contacts)throws Exception;
	
	/**
	 * Retrieves customer id when given customer name.
	 * @param custName
	 * @return
	 * @throws Exception
	 */
	public Integer getCustomerId(String custName)throws Exception;
	
	/**
	 * Returns the customer details when given customer id.
	 * @param id
	 * @param contacts.
	 * @return
	 * @throws Exception
	 */
	public CustomerDto getCustomer(Integer id,Boolean contacts)throws Exception; 
	
	/**
	 * Checks whether the the contact person with the same name exist for that customer.
	 * @param customer
	 * @throws Exception
	 */
	public Boolean isContactPersonExist(CustomerDto customer)throws Exception;
	
	/**
	 * adds a contact person to the given customer.
	 * @param customer
	 * @throws Exception
	 */
	public void addContactPerson(CustomerDto customer)throws Exception;
}
