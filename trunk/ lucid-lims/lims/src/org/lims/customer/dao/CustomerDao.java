/**
 * 
 */
package org.lims.customer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.lims.customer.dto.ContactPersonDto;
import org.lims.customer.dto.CustomerDto;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class CustomerDao implements CustomerDaoInter{

	/* (non-Javadoc)
	 * @see org.lims.customer.dao.CustomerDaoInter#addCustomer(org.lims.customer.dto.CustomerDto)
	 */
	@Override
	public void addCustomer(CustomerDto customerdto) throws Exception {
		addCustomerOnly(customerdto);
		Integer custId=getCustomerId(customerdto.getCustName());
		customerdto.setCustId(custId);
		List<ContactPersonDto> contactPersons=customerdto.getContactPersons();
		for(ContactPersonDto contactPerson:contactPersons){
			addContactPerson(contactPerson, custId);
		}
	}
	
	private void addCustomerOnly(CustomerDto customerdto)throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="insert into customer(cust_name,address,phone_number,fax_number," +
				"email) values(?,?,?,?,?,?,?,?)";
		try{			
			 conn =Util.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 
			 pstmt.setString(1, customerdto.getCustName());
			 pstmt.setString(2, customerdto.getAddress());
			 pstmt.setString(3, customerdto.getPhoneNumber());
			 pstmt.setString(4, customerdto.getFaxNumber()); 
			 pstmt.setString(5, customerdto.getEmail());			 
			 
			 pstmt.executeUpdate();
			 
		}catch(Exception e){
			throw e;
		} finally {		      
		          pstmt.close();
		          conn.close();		      
		  }		
	}
	
	private void addContactPerson(ContactPersonDto contactPerson,Integer custId)throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="insert into cust_contact_person(contact_person,contact_person_mobile," +
				"contact_person_email,customer_id) values(?,?,?,?)";

		try{			
			 conn =Util.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 
			 pstmt.setString(1, contactPerson.getCtPersonName());
			 pstmt.setString(2, contactPerson.getCtPersonMobile());
			 pstmt.setString(3,contactPerson.getCtPersonEmail());
			 pstmt.setInt(4, custId); 						 
			 
			 pstmt.executeUpdate();
			 
		}catch(Exception e){
			throw e;
		} finally {		      
		          pstmt.close();
		          conn.close();		      
		  }	
	}

	/* (non-Javadoc)
	 * @see org.lims.customer.dao.CustomerDaoInter#checkCustomerExist(java.lang.String)
	 */
	@Override
	public Boolean checkCustomerExist(String custName) throws Exception {
		Boolean exist=false;
		Integer id=getCustomerId(custName);
		if(id.intValue()!=0)
			exist=true;		
		return exist;
	}

	/**
	 * This returns the customer id when given customer name.
	 * @param custName
	 * @return
	 * @throws Exception
	 */
	public Integer getCustomerId(String custName)throws Exception{
		Integer id=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select customer_id from customer where cust_name=?";
		try{			
			 conn =Util.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, custName);
			 rs=pstmt.executeQuery();
			 if(rs.next()){
				id= rs.getInt("customer_id");				
			 }
			 
		}catch(Exception e){
			throw e;
		} finally {
			  rs.close() ;
	          pstmt.close();
	          conn.close();		      
		  }
		return id;
	}

	/* (non-Javadoc)
	 * @see org.lims.customer.dao.CustomerDaoInter#getAllCustomerNames()
	 */
	@Override
	public List<String> getAllCustomerNames() throws Exception {
		List<String> names=new ArrayList<String>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select cust_name from customer order by cust_name";
		try{			
			 conn =Util.getConnection();
			 pstmt = conn.prepareStatement(sql);			
			 rs=pstmt.executeQuery();
			 while(rs.next()){
				String name= rs.getString("cust_name");	
				names.add(name);
			 }
			 
		}catch(Exception e){
			throw e;
		} finally {
			  rs.close() ;
	          pstmt.close();
	          conn.close();		      
		  }
		return names;
	}

	/* (non-Javadoc)
	 * @see org.lims.customer.dao.CustomerDaoInter#getCustomer(java.lang.String)
	 */
	@Override
	public CustomerDto getCustomer(String custName,Boolean contacts) throws Exception {
		CustomerDto customer=getCustomerOnly(custName);
		if(contacts){
			List<ContactPersonDto> contactPersons=getCustContactPersons(customer.getCustId());
			customer.setContactPersons(contactPersons);
		}
		return customer;
	}
	
	/**
	 * Retrieves just the customer details.
	 * @param custName
	 * @return customer Dto.
	 * @throws Exception
	 */
	private CustomerDto getCustomerOnly(String custName)throws Exception{
		CustomerDto customer=new CustomerDto();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select customer_id,address,phone_number,fax_number,email from customer where " +
				"cust_name=?";
		try{			
			 conn =Util.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, custName);
			 rs=pstmt.executeQuery();
			 if(rs.next()){
				customer.setCustId(rs.getInt("customer_id")) ;
				customer.setCustName(custName);
				customer.setAddress(rs.getString("address"));
				customer.setPhoneNumber(rs.getString("phone_number"));
				customer.setFaxNumber(rs.getString("fax_number"));
				customer.setEmail(rs.getString("email"));
				
			 }
			 
		}catch(Exception e){
			throw e;
		} finally {
			  rs.close() ;
	          pstmt.close();
	          conn.close();		      
		  }
		return customer;
	}
	
	/**
	 * Retrieves the list of contact persons for the given customer id.
	 * @param custId
	 * @return list of contact persons.
	 * @throws Exception
	 */
	private List<ContactPersonDto> getCustContactPersons(Integer custId)throws Exception{
		List<ContactPersonDto> contactPersons=new ArrayList<ContactPersonDto>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select cust_contact_person_id,contact_person,contact_person_mobile," +
				"contact_person_email from cust_contact_person where customer_id=?";
		try{			
			 conn =Util.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setInt(1, custId);
			 rs=pstmt.executeQuery();
			 while(rs.next()){
				 ContactPersonDto contactPerson=new ContactPersonDto();
				 contactPerson.setCtPersonId(rs.getInt("cust_contact_person_id"));
				 contactPerson.setCtPersonName(rs.getString("contact_person"));
				 contactPerson.setCtPersonMobile(rs.getString("contact_person_mobile"));
				 contactPerson.setCtPersonEmail(rs.getString("contact_person_email"));
				 contactPersons.add(contactPerson);
			 }
			 
		}catch(Exception e){
			throw e;
		} finally {
			  rs.close() ;
	          pstmt.close();
	          conn.close();		      
		  }
		return contactPersons;
	}

	/* (non-Javadoc)
	 * @see org.lims.customer.dao.CustomerDaoInter#getCustomer(java.lang.Integer)
	 */
	@Override
	public CustomerDto getCustomer(Integer id,Boolean contacts) throws Exception {
		CustomerDto customer=getCustomerOnly(id);
		if(contacts){
			List<ContactPersonDto> contactPersons=getCustContactPersons(id);
			customer.setContactPersons(contactPersons);
		}
		return customer;
	}
	
	/**
	 * Returns the customer info for the given customer id.
	 * @param id
	 * @return customer dto.
	 * @throws Exception
	 */
	private CustomerDto getCustomerOnly(Integer id)throws Exception{
		CustomerDto customer=new CustomerDto();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select cust_name,address,phone_number,fax_number,email from customer where " +
				"customer_id=?";
		try{			
			 conn =Util.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setInt(1, id);
			 rs=pstmt.executeQuery();
			 if(rs.next()){
				customer.setCustId(id);
				customer.setCustName(rs.getString("cust_name"));
				customer.setAddress(rs.getString("address"));
				customer.setPhoneNumber(rs.getString("phone_number"));
				customer.setFaxNumber(rs.getString("fax_number"));
				customer.setEmail(rs.getString("email"));				
			 }
			 
		}catch(Exception e){
			throw e;
		} finally {
			  rs.close() ;
	          pstmt.close();
	          conn.close();		      
		  }
		return customer;
	}
	
	
}
