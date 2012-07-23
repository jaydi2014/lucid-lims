/**
 * 
 */
package org.lims.register.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.lims.customer.dto.CustomerDto;
import org.lims.register.dto.CrFileDto;
import org.lims.register.dto.PDRegDto;
import org.lims.register.dto.PRegDto;
import org.lims.register.dto.RegDeptDto;
import org.lims.register.dto.SampleCollectionMethodDto;
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
			Integer scMethodId=getSampleCollectionMethodId(registerDto.getSampleCollectionMethod());
			registerDto.setSampleCollectionMethodId(scMethodId);
			
			conn.setAutoCommit(false);
			createContractReview(registerDto,conn);			
			createRegistration(registerDto,conn);
			registerSamples(registerDto,conn);
			registerRegDept(registerDto,conn);
			conn.commit();
			Integer contractReviewId=getContractReviewId(conn);			
			updateRegContractReview(conn,contractReviewId,registerDto.getRegNumber());
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
	private void createRegistration(TestRegisterDto registerDto,Connection conn)throws Exception{
		PreparedStatement pstmt=null;
		String sql="insert into testsampleregister(registration_number,date,customer_id," +
				"due_date,total_testing_charges,amount_paid,balance," +
				"payment_method,special_instructions,nature_sample_packing," +
				"original_date_time,dispatch_method,timezone,reg_time,cust_contact_person_id," +
				"sample_collected_by,lab_due_date,customer_ref_num,customer_ref_date," +
				"customer_ref_letter,cheque_number,cheque_date,cust_ref_letter_ext) " +
				"values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try{
			 String pattern=Constants.DATE_PATTERN;			
			 pstmt = conn.prepareStatement(sql);
			 
			 pstmt.setString(1, registerDto.getRegNumber());
			 pstmt.setDate(2, Util.convertStringToSqlDate(registerDto.getDate(),pattern));
			 pstmt.setInt(3,registerDto.getCustomer().getCustId());			
			 pstmt.setDate(4, Util.convertStringToSqlDate(registerDto.getDueDate(),pattern));
			 pstmt.setString(5, registerDto.getTotalTestingChrgs());
			 pstmt.setString(6, registerDto.getAmountPaid());
			 pstmt.setString(7, registerDto.getBalance());
			 pstmt.setString(8, registerDto.getPaymentMeth());
			 pstmt.setString(9, registerDto.getSpecialInstrs());
			 pstmt.setString(10, registerDto.getPacking());
			 pstmt.setDate(11, Util.convertStringToSqlDate(registerDto.getOriginalDateTime(),pattern));			 
			 pstmt.setString(12, registerDto.getDispatchMethod());
			 pstmt.setString(13, registerDto.getTimezoneId());			 
			 pstmt.setTime(14,new Time(registerDto.getRegTime().getTime()));
			 pstmt.setInt(15, registerDto.getCtPerson().getCtPersonId());
			 pstmt.setInt(16, registerDto.getSampleCollectionMethodId());
			 pstmt.setDate(17,Util.convertDateToSqlDate(registerDto.getLabDueDate()));
			 pstmt.setString(18, registerDto.getCrNumber());
			 pstmt.setDate(19, Util.convertDateToSqlDate(registerDto.getCrDate()));
			 pstmt.setBlob(20, registerDto.getCrFile());			
			 pstmt.setString(21,registerDto.getChequeNumber());
			 pstmt.setDate(22, Util.convertDateToSqlDate(registerDto.getChequeDate()));
			 pstmt.setString(23,registerDto.getCrFileExt());
			 
			 pstmt.executeUpdate();
			 
		}catch(Exception e){
			throw e;
		} finally {		      
		          pstmt.close();		          	      
		  }
	}
	
	/**
	 * creates contract review entry.
	 * @param registerDto
	 * @param conn
	 * @throws Exception
	 */
	private void createContractReview(TestRegisterDto registerDto,Connection conn)throws Exception{
		PreparedStatement pstmt=null;
		String sql="INSERT INTO contract_review(`adequate_quantity`,`inadequate_quantity`," +
				"`strg_cdt_room_temp`,`strg_cdt_cust_request`,`coa_acceptable`," +
				"`coa_not_acceptable`,`seal_intact`,`seal_not_intact`,`test_method_available`," +
				"`test_method_not_available`) VALUES(?,?,?,?,?,?,?,?,?,?)";
		try{
			 		
			 pstmt = conn.prepareStatement(sql);
			 
			 pstmt.setString(1, registerDto.getContractReview().getAdequateQty());
			 pstmt.setString(2, registerDto.getContractReview().getInadequateQty());
			 pstmt.setString(3, registerDto.getContractReview().getStrgCdtRoomTemp());
			 pstmt.setString(4, registerDto.getContractReview().getStrgCdtCustomerReq());
			 pstmt.setString(5, registerDto.getContractReview().getCoaAcceptable());
			 pstmt.setString(6, registerDto.getContractReview().getCoaNotAcceptable());
			 pstmt.setString(7, registerDto.getContractReview().getSealIntact());
			 pstmt.setString(8, registerDto.getContractReview().getSealNotIntact());
			 pstmt.setString(9, registerDto.getContractReview().getTestMethodAvailable());
			 pstmt.setString(10, registerDto.getContractReview().getTestmethodNotAvailable());
			 
			 
			 pstmt.executeUpdate();
			 
		}catch(Exception e){
			throw e;
		} finally {		      
		          pstmt.close();		          	      
		  }
	}
	
	/**
	 * returns the currently entered contract review id.
	 * @param conn
	 * @return id
	 * @throws Exception
	 */
	private Integer getContractReviewId(Connection conn)throws Exception{
		Integer id=null;		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select max(Contract_review_id) as currentId from contract_review";
		try{
			
			 conn =Util.getConnection();
			 pstmt = conn.prepareStatement(sql);			 
			 rs=pstmt.executeQuery();
			 if(rs.next()){
				id=rs.getInt("currentId");				
			 }
			 
		}catch(Exception e){
			throw e;
		} finally {
			  rs.close() ;
	          pstmt.close();	          	      
		  }
		return id;
	}
	
	/**
	 * updates test sample register with contract review id.
	 * @param conn
	 * @param conractReviewId
	 * @param regNumber
	 * @throws Exception
	 */
	private void updateRegContractReview(Connection conn,Integer contractReviewId,String regNumber)throws Exception{
		PreparedStatement pstmt=null;
		String sql="update testsampleregister set contract_review_id=? where " +
				   "registration_number=?";
		try{			
			conn =Util.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 
			 pstmt.setInt(1, contractReviewId);
			 pstmt.setString(2,regNumber);	
			 
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
				"registration_number,batch_mfg_details,specification,test_method,serial_no)" +
				" values(?,?,?,?,?,?,?,?)";
		try{		
			 pstmt = conn.prepareStatement(sql);
			 
			 pstmt.setString(1, sample.getSampleName());
			 pstmt.setString(2,sample.getSampleTests());
			 pstmt.setString(3,sample.getSampleQty());
			 pstmt.setString(4, registrationNum);
			 pstmt.setString(5, sample.getBatchMfgDetails());
			 pstmt.setString(6, sample.getSpecification());
			 pstmt.setString(7, sample.getTestMethod());
			 pstmt.setString(8, sample.getSerialNo());
			 
			 pstmt.executeUpdate();
			 
		}catch(Exception e){
			throw e;
		} finally {		      
		      pstmt.close();		          	      
		  }
	}
	
	
	/**
	 * This registers all the departments to the registration.
	 * @param registerDto
	 * @param conn
	 * @throws Exception
	 */
	private void registerRegDept(TestRegisterDto registerDto,Connection conn) throws Exception {
		for(RegDeptDto regDept:registerDto.getDepts()){
			registerRegDept(regDept,registerDto.getRegNumber(),conn);
		}
		
	}
	
	
	/**
	 * This registers a single department with the Registration.
	 * @param regDept
	 * @param registrationNum
	 * @param conn
	 * @throws Exception
	 */
	private void registerRegDept(RegDeptDto regDept,String registrationNum,Connection conn)throws Exception{
		PreparedStatement pstmt=null;
		String sql="INSERT INTO reg_dept (`dept_id`,`employee_id`,`registration_number`) " +
				"VALUES(?,?,?)";
		try{		
			 pstmt = conn.prepareStatement(sql);
			 
			 pstmt.setInt(1, regDept.getDeptId());
			 pstmt.setString(2,regDept.getEmpId());
			 pstmt.setString(3,registrationNum);
			 
			 
			 pstmt.executeUpdate();
			 
		}catch(Exception e){
			throw e;
		} finally {		      
		      pstmt.close();		          	      
		  }
	}
	
	/**
	 * returns sample collection method id when given method name.
	 * @param sampleCollectionMethodName
	 * @return id.
	 * @throws Exception
	 */
	private Integer getSampleCollectionMethodId(String sampleCollectionMethodName)throws Exception{
		Integer id=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select sample_collection_methods_id from sample_collection_methods where method_name=?";
		try{
			
			 conn =Util.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, sampleCollectionMethodName);
			 rs=pstmt.executeQuery();
			 if(rs.next()){
				id=rs.getInt("sample_collection_methods_id");				
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
		List<RegDeptDto> regDepts=getRegDepts(regNum);
		registerDto.setDepts(regDepts);
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
		String sql="select a.date,a.customer_id,a.due_date,a.dispatch_date," +
				"a.dispatch_method,a.total_testing_charges,a.amount_paid,a.balance," +
				"a.payment_method,a.special_instructions,a.nature_sample_packing," +
				"a.reg_time,a.cust_contact_person_id,a.sample_collected_by,a.lab_due_date," +
				"a.customer_ref_num,a.customer_ref_date,a.contract_review_id,a.cheque_number," +
				"a.cheque_date,b.method_name,c.contact_person,c.contact_person_mobile,c.contact_person_email," +
				"d.adequate_quantity,d.inadequate_quantity,d.strg_cdt_room_temp,d.strg_cdt_cust_request,d.coa_acceptable," +
				"d.coa_not_acceptable,d.seal_intact,d.seal_not_intact,d.test_method_available,d.test_method_not_available" +
				" from testsampleregister a,sample_collection_methods b," +
				"cust_contact_person c,contract_review d where a.sample_collected_by=b.sample_collection_methods_id " +
				"and a.cust_contact_person_id=c.cust_contact_person_id and a.contract_review_id=d.Contract_review_id and registration_number=?";
		try{
			
			 conn =Util.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, regNum);
			 rs=pstmt.executeQuery();
			 if(rs.next()){
				regdto.setRegNumber(regNum);
				regdto.setDate(Util.convertSqlDateToString(rs.getDate("date"), Constants.DATE_PATTERN));
				regdto.getCustomer().setCustId(rs.getInt("customer_id"));				
				regdto.setDueDate(Util.convertSqlDateToString(rs.getDate("due_date"), Constants.DATE_PATTERN));
				regdto.setDispatchDate(Util.convertSqlDateToString(rs.getDate("dispatch_date"), Constants.DATE_PATTERN));
				regdto.setDispatchMethod(rs.getString("dispatch_method"));
				regdto.setTotalTestingChrgs(rs.getString("total_testing_charges"));
				regdto.setAmountPaid(rs.getString("amount_paid"));
				regdto.setBalance(rs.getString("balance"));
				regdto.setPaymentMeth(rs.getString("payment_method"));
				regdto.setSpecialInstrs(rs.getString("special_instructions"));
				regdto.setPacking(rs.getString("nature_sample_packing"));				
				regdto.setRegTime(new java.util.Date(rs.getTime("reg_time").getTime()));
				regdto.getCtPerson().setCtPersonId(rs.getInt("cust_contact_person_id"));
				regdto.getCtPerson().setCtPersonName(rs.getString("contact_person"));
				regdto.getCtPerson().setCtPersonMobile(rs.getString("contact_person_mobile"));
				regdto.getCtPerson().setCtPersonEmail(rs.getString("contact_person_email"));
				regdto.setSampleCollectionMethodId(rs.getInt("sample_collected_by"));
				regdto.setLabDueDate(Util.sqldateToDate(rs.getDate("lab_due_date")));
				regdto.setCrNumber(rs.getString("customer_ref_num"));
				regdto.setCrDate(rs.getDate("customer_ref_date"));
				regdto.getContractReview().setContractReviewId(rs.getInt("contract_review_id"));
				regdto.getContractReview().setAdequateQty(rs.getString("adequate_quantity"));
				regdto.getContractReview().setInadequateQty(rs.getString("inadequate_quantity"));
				regdto.getContractReview().setStrgCdtRoomTemp(rs.getString("strg_cdt_room_temp"));
				regdto.getContractReview().setStrgCdtCustomerReq(rs.getString("strg_cdt_cust_request"));
				regdto.getContractReview().setCoaAcceptable(rs.getString("coa_acceptable"));
				regdto.getContractReview().setCoaNotAcceptable(rs.getString("coa_not_acceptable"));
				regdto.getContractReview().setSealIntact(rs.getString("seal_intact"));
				regdto.getContractReview().setSealNotIntact(rs.getString("seal_not_intact"));
				regdto.getContractReview().setTestMethodAvailable(rs.getString("test_method_available"));
				regdto.getContractReview().setTestmethodNotAvailable(rs.getString("test_method_not_available"));				
				regdto.setChequeNumber(rs.getString("cheque_number"));
				regdto.setChequeDate(Util.sqldateToDate(rs.getDate("cheque_date")));
				regdto.setSampleCollectionMethod(rs.getString("method_name"));
				
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
	 * Retrieves departments that are associated with registration
	 * @param regNum
	 * @return list of departments
	 * @throws Exception
	 */
	private List<RegDeptDto> getRegDepts(String regNum)throws Exception{
		List<RegDeptDto> regdepts=new ArrayList<RegDeptDto>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select a.reg_dept_id,b.department_name,c.emp_display_name " +
				"from reg_dept a,departments b,employee c where a.dept_id=b.department_id " +
				"and a.employee_id=c.employee_id and registration_number=?";
		try{			
			 conn =Util.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, regNum);
			 rs=pstmt.executeQuery();
			 while(rs.next()){
				 RegDeptDto regDept=new RegDeptDto();
				 regDept.setRegDeptId(rs.getInt("reg_dept_id"));
				 regDept.setDeptName(rs.getString("department_name"));
				 regDept.setEmpDisplayName(rs.getString("emp_display_name"));
				
				 regdepts.add(regDept);
			 }
			 
		}catch(Exception e){
			throw e;
		} finally {
			  rs.close() ;
	          pstmt.close();
	          conn.close();		      
		  }
		return regdepts;
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
		String sql="select sample_id,sample,tests,sample_quantity," +
				"batch_mfg_details,specification,test_method,serial_no from " +
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
				sample.setBatchMfgDetails(rs.getString("batch_mfg_details"));
				sample.setSpecification(rs.getString("specification"));
				sample.setTestMethod(rs.getString("test_method"));
				sample.setSerialNo(rs.getString("serial_no"));
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

	/* (non-Javadoc)
	 * @see org.lims.register.dao.RegisterDaoInter#deleteRegistration(java.lang.String)
	 */
	@Override
	public void deleteRegistration(String regNumber) throws Exception {
		Connection conn=Util.getConnection();
		try{
			conn.setAutoCommit(false);
			deleteRegisterSamples(regNumber, conn);
			deleteRegistration(regNumber, conn);
			conn.commit();
		}catch(Exception e){
			conn.rollback();
			throw e;
		}finally{
			conn.close();
		}
		
	}

	/**
	 * It deletes all the smples registered against a particular registration number.
	 * @param regNumber
	 * @param conn
	 * @throws Exception
	 */
	private void deleteRegisterSamples(String regNumber,Connection conn)throws Exception{
		PreparedStatement pstmt=null;
		String sql="DELETE FROM sampleparticulars WHERE registration_number=?";
		try{		
			 pstmt = conn.prepareStatement(sql);			 
			 pstmt.setString(1, regNumber);			 
			 pstmt.executeUpdate();
			 
		}catch(Exception e){
			throw e;
		} finally {		      
		      pstmt.close();		          	      
		  }
	}
	
	/**
	 * It deletes the registration for the given register number.
	 * @param regNumber
	 * @param conn
	 */
	private void deleteRegistration(String regNumber,Connection conn)throws Exception{
		PreparedStatement pstmt=null;
		String sql="DELETE FROM testsampleregister WHERE registration_number=?";
		try{		
			 pstmt = conn.prepareStatement(sql);			 
			 pstmt.setString(1, regNumber);			 
			 pstmt.executeUpdate();
			 
		}catch(Exception e){
			throw e;
		} finally {		      
		      pstmt.close();		          	      
		  }
	}

	/* (non-Javadoc)
	 * @see org.lims.register.dao.RegisterDaoInter#getRegistrations(org.lims.register.dto.PDRegDto)
	 */
	@Override
	public PDRegDto getRegistrations(PDRegDto pdregdto)throws Exception {
		
		List<TestRegisterDto> regs=new ArrayList<TestRegisterDto>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;		
		
		String sql="select a.registration_number,(select count(registration_number) " +
				"from testsampleregister where date between ? and ? ) as totalRows," +
				"a.date,a.dispatch_date,a.due_date,b.cust_name from testsampleregister a," +
				"customer b where a.customer_id=b.customer_id and a.date between ? and ? " +
				"order by a.date desc,a.registration_number limit ? offset ?";
		try{			
			 conn =Util.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setDate(1, Util.convertDateToSqlDate(pdregdto.getStartDate()));
			 pstmt.setDate(2, Util.convertDateToSqlDate(pdregdto.getEndDate()));
			 pstmt.setDate(3, Util.convertDateToSqlDate(pdregdto.getStartDate()));
			 pstmt.setDate(4, Util.convertDateToSqlDate(pdregdto.getEndDate()));
			 pstmt.setInt(5, pdregdto.getLimit());
			 pstmt.setInt(6, pdregdto.getOffset());
			 rs=pstmt.executeQuery();
			 while(rs.next()){
				 if(rs.isFirst())
					 pdregdto.setTotalResults(rs.getInt("totalRows"));
				 TestRegisterDto regdto=new TestRegisterDto();
				 regdto.setRegNumber(rs.getString("registration_number"));
				 regdto.setDate(Util.convertSqlDateToString(rs.getDate("date"), Constants.DATE_PATTERN));
				 regdto.setDispatchDate(Util.convertSqlDateToString(rs.getDate("dispatch_date"), Constants.DATE_PATTERN));
				 regdto.setDueDate(Util.convertSqlDateToString(rs.getDate("due_date"), Constants.DATE_PATTERN));
				 CustomerDto cust =new CustomerDto();
				 cust.setCustName(rs.getString("cust_name"));
				 regdto.setCustomer(cust);				 
				 regs.add(regdto);
			 }
			 pdregdto.setRegs(regs);
			 
		}catch(Exception e){
			throw e;
		} finally {
			  rs.close() ;
	          pstmt.close();
	          conn.close();		      
		  }
		return pdregdto;
	}

	/* (non-Javadoc)
	 * @see org.lims.register.dao.RegisterDaoInter#getSampleCollectionMethods()
	 */
	@Override
	public List<SampleCollectionMethodDto> getSampleCollectionMethods()throws Exception {
		List<SampleCollectionMethodDto> scMethods=new ArrayList<SampleCollectionMethodDto>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select sample_collection_methods_id,method_name from " +
				   "sample_collection_methods";
		try{			
			 conn =Util.getConnection();
			 pstmt = conn.prepareStatement(sql);			
			 rs=pstmt.executeQuery();
			 while(rs.next()){
				 SampleCollectionMethodDto scMethod=new SampleCollectionMethodDto();
				 scMethod.setSampleCollectionMethodId(rs.getInt("sample_collection_methods_id"));
				 scMethod.setMethodName(rs.getString("method_name"));
				 scMethods.add(scMethod);
			 }
			 
		}catch(Exception e){
			throw e;
		} finally {
			  rs.close() ;
	          pstmt.close();
	          conn.close();		      
		  }
		return scMethods;
	}

	/* (non-Javadoc)
	 * @see org.lims.register.dao.RegisterDaoInter#getCustRefFile(java.lang.String)
	 */
	@Override
	public CrFileDto getCustRefFile(String regNumber) throws Exception {
		CrFileDto crFile=new CrFileDto();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select customer_ref_letter,cust_ref_letter_ext from testsampleregister where " +
				"registration_number=?";
		try{			
			 conn =Util.getConnection();
			 pstmt = conn.prepareStatement(sql);			
			 rs=pstmt.executeQuery();
			 if(rs.next()){				 
				 crFile.setCrFile(rs.getBlob("customer_ref_letter").getBinaryStream());
				 crFile.setFileExt(rs.getString("cust_ref_letter_ext"));				
			 }
			 
		}catch(Exception e){
			throw e;
		} finally {
			  rs.close() ;
	          pstmt.close();
	          conn.close();		      
		  }
		return crFile;
	}
	
	
	
}
