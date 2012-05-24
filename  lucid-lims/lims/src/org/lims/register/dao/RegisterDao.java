/**
 * 
 */
package org.lims.register.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.lims.register.dto.TestRegisterDto;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class RegisterDao implements RegisterDaoInter{

	/* (non-Javadoc)
	 * @see org.lims.register.dao.RegisterDaoInter#registerSamples(org.lims.register.dto.TestRegisterDto)
	 */
	@Override
	public void registerSamples(TestRegisterDto registerDto) throws Exception {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="insert into testsampleregister(registration_number,date,customer_id," +
				"department_id,due_date,total_testing_charges,amount_paid,balance,payment_method)" +
				" values (?,?,?,?,?,?,?,?,?);";
		try{
			
			conn =Util.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 
			 pstmt.setString(1, registerDto.getRegNumber());
			 pstmt.setString(2, registerDto.getDate());
			 pstmt.setInt(3,registerDto.getCustomer().getCustId());
			 pstmt.setInt(4, registerDto.getDepartment().getDepartmentId());
			 pstmt.setString(5, registerDto.getDueDate());
			 pstmt.setString(6, registerDto.getTotalTestingChrgs());
			 pstmt.setString(7, registerDto.getAmountPaid());
			 pstmt.setString(8, registerDto.getBalance());
			 pstmt.setString(9, registerDto.getPaymentMeth());
			 
			 
			 pstmt.executeUpdate();
			 
		}catch(Exception e){
			throw e;
		} finally {		      
		          pstmt.close();
		          conn.close();		      
		  }
		
	}
	
	

}
