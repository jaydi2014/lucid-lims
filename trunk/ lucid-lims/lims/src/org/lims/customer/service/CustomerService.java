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
			CustomerValidation.validateContactPersonName(customerdto.getContactPersonName());
		}catch(InvalidInputException ine){
			exceptions.put("CONTACT_PER_NAME",ine.getMessage());
		}
		
		try{
			CustomerValidation.validateCustomerFaxNo(customerdto.getContactPersonMobile());
		}catch(InvalidInputException ine){
			exceptions.put("CONTACT_PER_MOBILE",ine.getMessage());
		}
		
		try{
			CustomerValidation.validateContactPersonEmail(customerdto.getContactPersonEmail());
		}catch(InvalidInputException ine){
			exceptions.put("CONTACT_PER_EMAIL",ine.getMessage());
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
	public CustomerDto getCustomer(String custName) throws Exception {
		CustomerDto customer=custdao.getCustomer(custName);
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
	
	
	
	

	
}
