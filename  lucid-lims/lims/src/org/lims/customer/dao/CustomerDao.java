/**
 * 
 */
package org.lims.customer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

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
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="insert into customer(cust_name,address,phone_number,fax_number," +
				"email,contact_person,contact_person_mobile,contact_person_email) " +
				"values(?,?,?,?,?,?,?,?);";
		try{			
			 conn =Util.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 
			 pstmt.setString(1, customerdto.getCustName());
			 pstmt.setString(2, customerdto.getAddress());
			 pstmt.setString(3, customerdto.getPhoneNumber());
			 pstmt.setString(4, customerdto.getFaxNumber()); 
			 pstmt.setString(5, customerdto.getEmail());
			 pstmt.setString(6, customerdto.getContactPersonName());
			 pstmt.setString(7, customerdto.getContactPersonMobile()); 
			 pstmt.setString(8, customerdto.getContactPersonEmail());
			 
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
	private Integer getCustomerId(String custName)throws Exception{
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
	
}
