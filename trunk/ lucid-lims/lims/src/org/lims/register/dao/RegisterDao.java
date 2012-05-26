/**
 * 
 */
package org.lims.register.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.lims.register.dto.SampleDto;
import org.lims.register.dto.TestRegisterDto;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class RegisterDao implements RegisterDaoInter{

	
	/* (non-Javadoc)
	 * @see org.lims.register.dao.RegisterDaoInter#createRegisterEntry(org.lims.register.dto.TestRegisterDto)
	 */
	@Override
	public void createRegisterEntry(TestRegisterDto registerDto)
			throws Exception {
		createRegistratio(registerDto);
		registerSamples(registerDto);
		
	}
	
	/**
	 * This creates the initial registrations.
	 * @param registerDto
	 * @throws Exception
	 */
	private void createRegistratio(TestRegisterDto registerDto)throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="insert into testsampleregister(registration_number,date,customer_id," +
				"department_id,due_date,total_testing_charges,amount_paid,balance," +
				"payment_method,special_instructions,nature_sample_packing," +
				"original_date_time) values (?,?,?,?,?,?,?,?,?,?,?,?)";
		try{
			String pattern="dd-MM-yyyy";
			conn =Util.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 
			 pstmt.setString(1, registerDto.getRegNumber());
			 pstmt.setDate(2, Util.convertStringToSqlDate(registerDto.getDate(),pattern));
			 pstmt.setInt(3,registerDto.getCustomer().getCustId());
			 pstmt.setInt(4, registerDto.getDepartment().getDepartmentId());
			 pstmt.setDate(5, Util.convertStringToSqlDate(registerDto.getDueDate(),pattern));
			 pstmt.setString(6, registerDto.getTotalTestingChrgs());
			 pstmt.setString(7, registerDto.getAmountPaid());
			 pstmt.setString(8, registerDto.getBalance());
			 pstmt.setString(9, registerDto.getPaymentMeth());
			 pstmt.setString(10, registerDto.getSpecialInstrs());
			 pstmt.setString(11, registerDto.getPacking());
			 pstmt.setDate(12, Util.convertStringToSqlDate(registerDto.getOriginalDateTime(),pattern));
			 
			 
			 pstmt.executeUpdate();
			 
		}catch(Exception e){
			throw e;
		} finally {		      
		          pstmt.close();
		          conn.close();		      
		  }
	}
	
	/**
	 * It registers all the samples.
	 * @param registerDto
	 * @throws Exception
	 */
	private void registerSamples(TestRegisterDto registerDto) throws Exception {
		for(SampleDto sample:registerDto.getSamplesList()){
			registerSample(sample,registerDto.getRegNumber());
		}
		
	}
	
	/**
	 * This registers the sample.
	 * @param sample
	 * @throws Exception
	 */
	private void registerSample(SampleDto sample,String registrationNum)throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="insert into sampleparticulars(sample,tests,sample_quantity," +
				"registration_number) values(?,?,?,?);";
		try{			
			conn =Util.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 
			 pstmt.setString(1, sample.getSampleName());
			 pstmt.setString(2,sample.getSampleTests());
			 pstmt.setString(3,sample.getSampleQty());
			 pstmt.setString(4, registrationNum);			
			 
			 pstmt.executeUpdate();
			 
		}catch(Exception e){
			throw e;
		} finally {		      
		          pstmt.close();
		          conn.close();		      
		  }
	}

	/* (non-Javadoc)
	 * @see org.lims.register.dao.RegisterDaoInter#checkRegNumExist(java.lang.String)
	 */
	@Override
	public Boolean checkRegNumExist(String regNumber) throws Exception {
		Boolean exist=false;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select customer_id from testsampleregister where registration_number=?";
		try{
			
			 conn =Util.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, regNumber);
			 rs=pstmt.executeQuery();
			 if(rs.next()){
				exist=true;				
			 }
			 
		}catch(Exception e){
			throw e;
		} finally {
			  rs.close() ;
	          pstmt.close();
	          conn.close();		      
		  }
		return exist;
	}

	

}
