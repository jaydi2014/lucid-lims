/**
 * 
 */
package org.lims.register.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.lims.register.dto.PRegDto;
import org.lims.register.dto.SampleDto;
import org.lims.register.dto.TestRegisterDto;
import org.lims.util.Constants;
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
	public void createRegisterEntry(TestRegisterDto registerDto)throws Exception {
		Connection conn=Util.getConnection();
		try{
			conn.setAutoCommit(false);
			createRegistratio(registerDto,conn);
			registerSamples(registerDto,conn);
			conn.commit();
		}catch(Exception e){
			conn.rollback();
			throw e;
		}finally{
			conn.close();
		}
		
	}
	
	/**
	 * This creates the initial registrations.
	 * @param registerDto
	 * @throws Exception
	 */
	private void createRegistratio(TestRegisterDto registerDto,Connection conn)throws Exception{
		PreparedStatement pstmt=null;
		String sql="insert into testsampleregister(registration_number,date,customer_id," +
				"department_id,due_date,total_testing_charges,amount_paid,balance," +
				"payment_method,special_instructions,nature_sample_packing," +
				"original_date_time,dispatch_method) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try{
			 String pattern="dd-MM-yyyy";			
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
			 pstmt.setString(13, registerDto.getDispatchMethod());
			 
			 pstmt.executeUpdate();
			 
		}catch(Exception e){
			throw e;
		} finally {		      
		          pstmt.close();		          	      
		  }
	}
	
	/**
	 * It registers all the samples.
	 * @param registerDto
	 * @throws Exception
	 */
	private void registerSamples(TestRegisterDto registerDto,Connection conn) throws Exception {
		for(SampleDto sample:registerDto.getSamplesList()){
			registerSample(sample,registerDto.getRegNumber(),conn);
		}
		
	}
	
	/**
	 * This registers the sample.
	 * @param sample
	 * @throws Exception
	 */
	private void registerSample(SampleDto sample,String registrationNum,Connection conn)throws Exception{
		
		PreparedStatement pstmt=null;
		String sql="insert into sampleparticulars(sample,tests,sample_quantity," +
				"registration_number) values(?,?,?,?)";
		try{		
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

	/* (non-Javadoc)
	 * @see org.lims.register.dao.RegisterDaoInter#getRegisterEntry(java.lang.String)
	 */
	@Override
	public TestRegisterDto getRegisterEntry(String regNum) throws Exception {
		TestRegisterDto registerDto=getRegistration(regNum);
		List<SampleDto> samples=getSamples(regNum);
		registerDto.setSamplesList(samples);
		return registerDto;
	}
	
	/**
	 * It pulls all the registration info except samples details.
	 * @param regNum
	 * @return TestRegisterDto
	 * @throws Exception
	 */
	private TestRegisterDto getRegistration(String regNum)throws Exception{
		TestRegisterDto regdto=new TestRegisterDto();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select date,customer_id,department_id,due_date,dispatch_date," +
				"dispatch_method,total_testing_charges,amount_paid,balance," +
				"payment_method,special_instructions,nature_sample_packing " +
				"from testsampleregister where registration_number=?";
		try{
			
			 conn =Util.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, regNum);
			 rs=pstmt.executeQuery();
			 if(rs.next()){
				regdto.setRegNumber(regNum);
				regdto.setDate(Util.convertSqlDateToString(rs.getDate("date"), Constants.DATE_PATTERN));
				regdto.getCustomer().setCustId(rs.getInt("customer_id"));
				regdto.getDepartment().setDepartmentId(rs.getInt("department_id"));
				regdto.setDueDate(Util.convertSqlDateToString(rs.getDate("due_date"), Constants.DATE_PATTERN));
				regdto.setDispatchDate(Util.convertSqlDateToString(rs.getDate("dispatch_date"), Constants.DATE_PATTERN));
				regdto.setDispatchMethod(rs.getString("dispatch_method"));
				regdto.setTotalTestingChrgs(rs.getString("total_testing_charges"));
				regdto.setAmountPaid(rs.getString("amount_paid"));
				regdto.setBalance(rs.getString("balance"));
				regdto.setPaymentMeth(rs.getString("payment_method"));
				regdto.setSpecialInstrs(rs.getString("special_instructions"));
				regdto.setPacking(rs.getString("nature_sample_packing"));				
				
			 }
			 
		}catch(Exception e){
			throw e;
		} finally {
			  rs.close() ;
	          pstmt.close();
	          conn.close();		      
		  }
		return regdto;
	}

	/**
	 * Returns list of samples associated with the given registration number.
	 * @param regNum
	 * @return
	 * @throws Exception
	 */
	private List<SampleDto> getSamples(String regNum)throws Exception{
		List<SampleDto> samples=new ArrayList<SampleDto>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select sample_id,sample,tests,sample_quantity from " +
				"sampleparticulars  where registration_number=?";
		try{			
			 conn =Util.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, regNum);
			 rs=pstmt.executeQuery();
			 while(rs.next()){
				SampleDto sample=new SampleDto();
				sample.setSampleId(rs.getInt("sample_id"));
				sample.setSampleName(rs.getString("sample"));
				sample.setSampleTests(rs.getString("tests"));
				sample.setSampleQty(rs.getString("sample_quantity"));
				samples.add(sample);
			 }
			 
		}catch(Exception e){
			throw e;
		} finally {
			  rs.close() ;
	          pstmt.close();
	          conn.close();		      
		  }
		return samples;
	}

	/* (non-Javadoc)
	 * @see org.lims.register.dao.RegisterDaoInter#updateBillingandDispatch(org.lims.register.dto.TestRegisterDto)
	 */
	@Override
	public void updateBillingandDispatch(TestRegisterDto registerDto)
			throws Exception {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="update testsampleregister set dispatch_date=?," +
				"amount_paid=?,balance=?,payment_method=?,employee_id=? where " +
				"registration_number=?;";
		try{			
			conn =Util.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 
			 pstmt.setDate(1, Util.convertStringToSqlDate(registerDto.getDispatchDate(), Constants.DATE_PATTERN));
			 pstmt.setString(2,registerDto.getAmountPaid());
			 pstmt.setString(3, registerDto.getBalance());	
			 pstmt.setString(4, registerDto.getPaymentMeth());	
			 pstmt.setString(5, registerDto.getEmployee().getEmpId());
			 pstmt.setString(6, registerDto.getRegNumber());	
			 
			 pstmt.executeUpdate();
			 
		}catch(Exception e){
			throw e;
		} finally {		      
		          pstmt.close();
		          conn.close();		      
		  }		
	}

	/* (non-Javadoc)
	 * @see org.lims.register.dao.RegisterDaoInter#getPendingRegistrations(java.lang.String, java.lang.String)
	 */
	@Override
	public List<PRegDto> getPendingRegistrations()throws Exception {
		List<PRegDto> pendingRegs=new ArrayList<PRegDto>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String regNum=null;
		List<SampleDto> samples=null;
		PRegDto pendingReg=null;
		String sql="select a.registration_number,b.cust_name,d.sample,d.tests," +
				"a.original_date_time,a.due_date,c.department_name from " +
				"testsampleregister a, customer b,departments c,sampleparticulars d " +
				"where a.customer_id=b.customer_id and a.department_id=c.department_id " +
				"and d.registration_number=a.registration_number and a.dispatch_date is null " +
				"order by c.department_name,a.due_date;";
		try{			
			 conn =Util.getConnection();
			 pstmt = conn.prepareStatement(sql);			 
			 rs=pstmt.executeQuery();
			 while(rs.next()){
				SampleDto sample=new SampleDto();
				String regNumber=rs.getString("registration_number");
				if(!regNumber.equals(regNum)){
					regNum=regNumber;
					if(pendingReg!=null){
						pendingReg.setSamples(samples);
						pendingRegs.add(pendingReg);
					}
					pendingReg=new PRegDto();
					samples=new ArrayList<SampleDto>();
				}
				pendingReg.setRegNum(regNumber);
				pendingReg.setCustName(rs.getString("cust_name"));
				sample.setSampleName(rs.getString("sample"));
				sample.setSampleTests(rs.getString("tests"));
				String strOrgDate=Util.convertSqlDateToString(rs.getDate("original_date_time"), Constants.DATE_PATTERN);
				pendingReg.setRecievedDate(strOrgDate);
				String strDueDate=Util.convertSqlDateToString(rs.getDate("due_date"), Constants.DATE_PATTERN);
				pendingReg.setDueDate(strDueDate);
				pendingReg.setDeptName(rs.getString("department_name"));
				samples.add(sample);
				
			 }
			 if(pendingReg!=null){
				 pendingReg.setSamples(samples);
				 pendingRegs.add(pendingReg);
			 }
			 
		}catch(Exception e){
			throw e;
		} finally {
			  rs.close() ;
	          pstmt.close();
	          conn.close();		      
		  }
		return pendingRegs;
	}

	/* (non-Javadoc)
	 * @see org.lims.register.dao.RegisterDaoInter#getPendingRegistrations(java.lang.String)
	 */
	@Override
	public List<PRegDto> getPendingRegistrations(String deptName)throws Exception {
		List<PRegDto> pendingRegs=new ArrayList<PRegDto>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String regNum=null;
		List<SampleDto> samples=null;
		PRegDto pendingReg=null;
		String sql="select a.registration_number,b.cust_name,d.sample,d.tests," +
				"a.original_date_time,a.due_date,c.department_name from " +
				"testsampleregister a, customer b,departments c,sampleparticulars d " +
				"where a.customer_id=b.customer_id and a.department_id=c.department_id " +
				"and d.registration_number=a.registration_number and c.department_name=? " +
				"and a.dispatch_date is null order by c.department_name,a.due_date;";
		try{			
			 conn =Util.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1,deptName);
			 rs=pstmt.executeQuery();
			 while(rs.next()){
				SampleDto sample=new SampleDto();
				String regNumber=rs.getString("registration_number");
				if(!regNumber.equals(regNum)){
					regNum=regNumber;
					if(pendingReg!=null){
						pendingReg.setSamples(samples);
						pendingRegs.add(pendingReg);
					}
					pendingReg=new PRegDto();
					samples=new ArrayList<SampleDto>();
				}
				pendingReg.setRegNum(regNumber);
				pendingReg.setCustName(rs.getString("cust_name"));
				sample.setSampleName(rs.getString("sample"));
				sample.setSampleTests(rs.getString("tests"));
				String strOrgDate=Util.convertSqlDateToString(rs.getDate("original_date_time"), Constants.DATE_PATTERN);
				pendingReg.setRecievedDate(strOrgDate);
				String strDueDate=Util.convertSqlDateToString(rs.getDate("due_date"), Constants.DATE_PATTERN);
				pendingReg.setDueDate(strDueDate);
				pendingReg.setDeptName(rs.getString("department_name"));
				samples.add(sample);
				
			 }
			 if(pendingReg!=null){
				 pendingReg.setSamples(samples);
				 pendingRegs.add(pendingReg);
			 }
			 
		}catch(Exception e){
			throw e;
		} finally {
			  rs.close() ;
	          pstmt.close();
	          conn.close();		      
		  }
		return pendingRegs;
	}

	
	
	
}
