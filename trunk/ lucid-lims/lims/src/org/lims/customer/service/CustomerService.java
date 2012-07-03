/**
 * 
 */
package org.lims.customer.service;

import java.util.List;

import org.lims.common.exceptions.InvalidInputException;
import org.lims.common.exceptions.ValidationErrorsException;
import org.lims.customer.dao.CustomerDao;
import org.lims.customer.dao.CustomerDaoInter;
import org.lims.customer.dto.CustomerDto;
import org.lims.customer.service.validate.CustomerValidation;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class CustomerService implements CustomerServiceInter{
	
	private CustomerDaoInter custdao=new CustomerDao();

	/* (non-Javadoc)
	 * @see org.lims.customer.service.CustomerServiceInter#addCustomer(org.lims.customer.dto.CustomerDto)
	 */
	@Override
	public void addCustomer(CustomerDto customerdto) throws Exception {
		try{
			CustomerValidation.validateCustomerName(customerdto.getCustName());
		}catch(InvalidInputException ine){
			exceptions.put("CUST_NAME",ine.getMessage());
		}
		
		try{
			CustomerValidation. validateCustomerAddr(customerdto.getAddress());
		}catch(InvalidInputException ine){
			exceptions.put("CUST_ADDR",ine.getMessage());
		}
		
		try{			
			CustomerValidation.validateCustomerPhoneNo(customerdto.getPhoneNumber());
		}catch(InvalidInputException ine){
			exceptions.put("CUST_PHONE",ine.getMessage());
		}
		
		try{
			CustomerValidation.validateCustomerFaxNo(customerdto.getFaxNumber());
		}catch(InvalidInputException ine){
			exceptions.put("CUST_FAX",ine.getMessage());
		}
		
		try{
			CustomerValidation.validateCustomerEmail(customerdto.getEmail());
		}catch(InvalidInputException ine){
			exceptions.put("CUST_EMAIL",ine.getMessage());
		}
		
		try{
			CustomerValidation. validateContactPersonName(customerdto.getContactPersons().get(0).getCtPersonName());
		}catch(InvalidInputException ine){
			exceptions.put("CONTACT_NAME",ine.getMessage());
		}
		
		try{
			CustomerValidation.validateContactPersonMobileNo(customerdto.getContactPersons().get(0).getCtPersonMobile());
		}catch(InvalidInputException ine){
			exceptions.put("CONTACT_MOBILE",ine.getMessage());
		}
		
		try{
			CustomerValidation.validateContactPersonEmail(customerdto.getContactPersons().get(0).getCtPersonEmail());
		}catch(InvalidInputException ine){
			exceptions.put("CONTACT_EMAIL",ine.getMessage());
		}
		
		if(!exceptions.isEmpty())
			throw new ValidationErrorsException();
		
		
		custdao.addCustomer(customerdto);

		
	}

	/* (non-Javadoc)
	 * @see org.lims.customer.service.CustomerServiceInter#checkCustomerExist(java.lang.String)
	 */
	@Override
	public Boolean checkCustomerExist(String custName) throws Exception {
		Boolean exist=custdao.checkCustomerExist(custName);
		return exist;
	}

	/* (non-Javadoc)
	 * @see org.lims.customer.service.CustomerServiceInter#getAllCustomerNames()
	 */
	@Override
	public List<String> getAllCustomerNames() throws Exception {
		List<String> names=custdao.getAllCustomerNames();
		return names;
	}

	/* (non-Javadoc)
	 * @see org.lims.customer.service.CustomerServiceInter#getCustomer(java.lang.String)
	 */
	@Override
	public CustomerDto getCustomer(String custName,Boolean contacts) throws Exception {
		CustomerDto customer=custdao.getCustomer(custName,contacts);
		return customer;
	}

	/* (non-Javadoc)
	 * @see org.lims.customer.service.CustomerServiceInter#getCustomerId(java.lang.String)
	 */
	@Override
	public Integer getCustomerId(String custName) throws Exception {
		Integer custId=custdao.getCustomerId(custName);
		return custId;
	}

	/* (non-Javadoc)
	 * @see org.lims.customer.service.CustomerServiceInter#getCustomer(java.lang.Integer)
	 */
	@Override
	public CustomerDto getCustomer(Integer id,Boolean contacts) throws Exception {
		CustomerDto customer=custdao.getCustomer(id,contacts);
		return customer;
	}

	/* (non-Javadoc)
	 * @see org.lims.customer.service.CustomerServiceInter#addContactPerson(org.lims.customer.dto.CustomerDto)
	 */
	@Override
	public void addContactPerson(CustomerDto customer) throws Exception {
		try{
			CustomerValidation.validateAddCtPersonCustName(customer.getCustName());
		}catch(InvalidInputException ine){
			exceptions.put("CUST_NAME",ine.getMessage());
		}
		
		try{
			CustomerValidation. validateCustContactPersonName(customer.getContactPersons().get(0).getCtPersonName(),
					                              customer);
		}catch(InvalidInputException ine){
			exceptions.put("CONTACT_NAME",ine.getMessage());
		}
		
		try{
			CustomerValidation.validateContactPersonMobileNo(customer.getContactPersons().get(0).getCtPersonMobile());
		}catch(InvalidInputException ine){
			exceptions.put("CONTACT_MOBILE",ine.getMessage());
		}
		
		try{
			CustomerValidation.validateContactPersonEmail(customer.getContactPersons().get(0).getCtPersonEmail());
		}catch(InvalidInputException ine){
			exceptions.put("CONTACT_EMAIL",ine.getMessage());
		}
		
		if(!exceptions.isEmpty())
			throw new ValidationErrorsException();
		
		custdao.addContactPerson(customer);
	}

	/* (non-Javadoc)
	 * @see org.lims.customer.service.CustomerServiceInter#isContactPersonExist(org.lims.customer.dto.CustomerDto)
	 */
	@Override
	public Boolean isContactPersonExist(CustomerDto customer) throws Exception {
		Boolean exist=custdao.isContactPersonExist(customer);
		return exist;
	}

	/* (non-Javadoc)
	 * @see org.lims.customer.service.CustomerServiceInter#getContactPersonId(java.lang.Integer, java.lang.String)
	 */
	@Override
	public Integer getContactPersonId(Integer custId, String contactPersonName)
			throws Exception {
		Integer contactPersonId=custdao.getContactPersonId(custId, contactPersonName);
		return contactPersonId;
	}
	
	
	
}
