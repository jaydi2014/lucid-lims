/**
 * 
 */
package org.lims.register.service;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.lims.admin.service.AdminService;
import org.lims.admin.service.AdminServiceInter;
import org.lims.common.exceptions.InvalidInputException;
import org.lims.common.exceptions.ValidationErrorsException;
import org.lims.customer.dto.CustomerDto;
import org.lims.customer.service.CustomerService;
import org.lims.customer.service.CustomerServiceInter;
import org.lims.employee.service.EmployeeService;
import org.lims.employee.service.EmployeeServiceInter;
import org.lims.register.dao.RegisterDao;
import org.lims.register.dao.RegisterDaoInter;
import org.lims.register.dto.CrFileDto;
import org.lims.register.dto.PDRegDto;
import org.lims.register.dto.PRegDto;
import org.lims.register.dto.RegDeptDto;
import org.lims.register.dto.SampleCollectionMethodDto;
import org.lims.register.dto.TestRegisterDto;
import org.lims.register.service.validate.RegisterValidation;
import org.lims.util.Constants;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class RegisterService implements RegisterServiceInter{
	
	private RegisterDaoInter regdao=new RegisterDao();
	private CustomerServiceInter custService=new CustomerService();
	private AdminServiceInter adminService=new AdminService();
	private EmployeeServiceInter empService=new EmployeeService();

	/* (non-Javadoc)
	 * @see org.lims.register.service.RegisterServiceInter#createRegisterEntry(org.lims.register.dto.TestRegisterDto)
	 */
	@Override
	public void createRegisterEntry(TestRegisterDto registerDto)
			throws Exception {
		try{
			RegisterValidation.validateRegNumber(registerDto.getRegNumber());
		}catch(InvalidInputException ine){
			exceptions.put("REG_NUM",ine.getMessage());
		}
		
		try{
			RegisterValidation.validateRegDate(registerDto.getDate());
		}catch(InvalidInputException ine){
			exceptions.put("REG_DATE",ine.getMessage());
		}
		
		try{
			RegisterValidation.validateCustomerName(registerDto.getCustomer().getCustName());
		}catch(InvalidInputException ine){
			exceptions.put("CUST_NAME",ine.getMessage());
		}
		
		try{
			RegisterValidation.validateContactPersonName(registerDto.getCtPerson().getCtPersonName());
		}catch(InvalidInputException ine){
			exceptions.put("CONTACT_PERSON_NAME",ine.getMessage());
		}
		
		try{
			RegisterValidation.validateDueDate(registerDto.getDueDate());
		}catch(InvalidInputException ine){
			exceptions.put("REG_DUE_DATE",ine.getMessage());
		}
		
		try{
			RegisterValidation.validateTotalTestCharges(registerDto.getTotalTestingChrgs());
		}catch(InvalidInputException ine){
			exceptions.put("REG_TTC",ine.getMessage());
		}
		
		try{
			RegisterValidation.validateAmountPaid(registerDto.getAmountPaid());
		}catch(InvalidInputException ine){
			exceptions.put("REG_AMT_PAID",ine.getMessage());
		}
		
		try{
			RegisterValidation.validatePaymentMeth(registerDto.getPaymentMeth());
		}catch(InvalidInputException ine){
			exceptions.put("REG_PAY_METH",ine.getMessage());
		}
		
		try{
			RegisterValidation.validateSpecialInstrs(registerDto.getSpecialInstrs());
		}catch(InvalidInputException ine){
			exceptions.put("REG_SPCL_INSTR",ine.getMessage());
		}
		
		try{
			RegisterValidation.validateSamplePacking(registerDto.getPacking());
		}catch(InvalidInputException ine){
			exceptions.put("REG_PACK",ine.getMessage());
		}
		
		try{
			RegisterValidation.validateDispatchMethod(registerDto.getDispatchMethod());
		}catch(InvalidInputException ine){
			exceptions.put("DISPATCH_METH",ine.getMessage());
		}
		
		try{
			RegisterValidation.validateLabDueDate(registerDto.getLabDueDate());
		}catch(InvalidInputException ine){
			exceptions.put("LAB_DUE_DATE",ine.getMessage());
		}
		
		try{
			RegisterValidation.validateCustomerRefNo(registerDto.getCrNumber());
		}catch(InvalidInputException ine){
			exceptions.put("CUSTOMER_REF_NUMBER",ine.getMessage());
		}
		
		try{
			RegisterValidation.validateQtyAdequate(registerDto.getContractReview().getAdequateQty());
		}catch(InvalidInputException ine){
			exceptions.put("QUANTITY_ADEQUATE",ine.getMessage());
		}
		
		try{
			RegisterValidation.validateQtyInAdequate(registerDto.getContractReview().getInadequateQty());
		}catch(InvalidInputException ine){
			exceptions.put("QUANTITY_INADEQUATE",ine.getMessage());
		}
		
		try{
			RegisterValidation.validateStorageConditionRoomTemp(registerDto.getContractReview().getStrgCdtRoomTemp());
		}catch(InvalidInputException ine){
			exceptions.put("STRG_CONDT_ROOM_TEMP",ine.getMessage());
		}
		
		try{
			RegisterValidation.validateStorageConditionCustomerRequest(registerDto.getContractReview().getStrgCdtCustomerReq());
		}catch(InvalidInputException ine){
			exceptions.put("STRG_CONDT_CUSTOMER_REQUEST",ine.getMessage());
		}
		
		try{
			RegisterValidation.validateCoaAcceptable(registerDto.getContractReview().getCoaAcceptable());
		}catch(InvalidInputException ine){
			exceptions.put("COA_ACCEPTABLE",ine.getMessage());
		}
		
		try{
			RegisterValidation.validateCoaNotAcceptable(registerDto.getContractReview().getCoaNotAcceptable());
		}catch(InvalidInputException ine){
			exceptions.put("COA_NOT_ACCEPTABLE",ine.getMessage());
		}
		
		try{
			RegisterValidation.validateSealIntact(registerDto.getContractReview().getSealIntact());
		}catch(InvalidInputException ine){
			exceptions.put("SEAL_INTACT",ine.getMessage());
		}
		
		try{
			RegisterValidation.validateSealNotIntact(registerDto.getContractReview().getSealNotIntact());
		}catch(InvalidInputException ine){
			exceptions.put("SEAL_NOT_INTACT",ine.getMessage());
		}
		
		try{
			RegisterValidation.validateTestMethodAvailable(registerDto.getContractReview().getTestMethodAvailable());
		}catch(InvalidInputException ine){
			exceptions.put("TEST_METH_AVAILABLE",ine.getMessage());
		}
		
		try{
			RegisterValidation.validateTestMethodNotAvailable(registerDto.getContractReview().getTestmethodNotAvailable());
		}catch(InvalidInputException ine){
			exceptions.put("TEST_METH_NOT_AVAILABLE",ine.getMessage());
		}
		
		try{
			RegisterValidation.validateChequeNumber(registerDto.getChequeNumber());
		}catch(InvalidInputException ine){
			exceptions.put("CHEQUE_NUMBER",ine.getMessage());
		}
		
		if(!exceptions.isEmpty())
			throw new ValidationErrorsException();
		
		int custId=custService.getCustomerId(registerDto.getCustomer().getCustName());	
		int contactPersonId=custService.getContactPersonId(custId, registerDto.getCtPerson().getCtPersonName());
		for(RegDeptDto regDept:registerDto.getDepts()){
			int deptId=adminService.getDepartmentId(regDept.getDeptName());
			regDept.setDeptId(deptId);
			String empId=empService.getEmployeeIdByDisplayName(regDept.getEmpDisplayName());
			regDept.setEmpId(empId);
		}
		DateFormat format=new SimpleDateFormat(Constants.DATE_PATTERN);
		String currentDate=format.format(new Date());
		registerDto.getCustomer().setCustId(custId);
		registerDto.getCtPerson().setCtPersonId(contactPersonId);
		registerDto.setOriginalDateTime(currentDate);
		registerDto.setTimezoneId(Util.getDefaultTZId());
		regdao.createRegisterEntry(registerDto);
		
	}

	/* (non-Javadoc)
	 * @see org.lims.register.service.RegisterServiceInter#checkRegNumExist(java.lang.String)
	 */
	@Override
	public Boolean checkRegNumExist(String regNumber) throws Exception {
		Boolean exist=regdao.checkRegNumExist(regNumber);
		return exist;
	}

	/* (non-Javadoc)
	 * @see org.lims.register.service.RegisterServiceInter#getRegisterEntry(java.lang.String)
	 */
	@Override
	public TestRegisterDto getRegisterEntry(String regNum) throws Exception {
		TestRegisterDto registerDto=regdao.getRegisterEntry(regNum);		
		CustomerDto custdto=custService.getCustomer(registerDto.getCustomer().getCustId(),false);
		registerDto.setCustomer(custdto);
		if(registerDto.getDispatchDate() !=null && !registerDto.getDispatchDate().isEmpty())
			registerDto.setDispatchLock(true);
		if(registerDto.getTotalTestingChrgs().equals(registerDto.getAmountPaid()))
			registerDto.setBillingLocked(true);
		return registerDto;
	}

	/* (non-Javadoc)
	 * @see org.lims.register.service.RegisterServiceInter#updateBillingandDispatch(org.lims.register.dto.TestRegisterDto)
	 */
	@Override
	public void updateBillingandDispatch(TestRegisterDto registerDto)
			throws Exception {
		try{
			RegisterValidation.validateAmountPaid(registerDto.getAmountPaid());
		}catch(InvalidInputException ine){
			exceptions.put("REG_AMT_PAID",ine.getMessage());
		}
		
		try{
			RegisterValidation.validatePaymentMeth(registerDto.getPaymentMeth());
		}catch(InvalidInputException ine){
			exceptions.put("REG_PAY_METH",ine.getMessage());
		}
		
		try{
			RegisterValidation.validateDispatchDate(registerDto.getDispatchDate());
		}catch(InvalidInputException ine){
			exceptions.put("DISPATCH_DATE",ine.getMessage());
		}		
		
		
		if(!exceptions.isEmpty())
			throw new ValidationErrorsException();
		
		
		regdao.updateBillingandDispatch(registerDto);
		
	}

	/* (non-Javadoc)
	 * @see org.lims.register.service.RegisterServiceInter#getPendingRegistrations()
	 */
	@Override
	public List<PRegDto> getPendingRegistrations(String deptName) throws Exception {
		List<PRegDto> pendingRegs=regdao.getPendingRegistrations(deptName);
		Calendar currentCal=Calendar.getInstance();
		currentCal.setTime(new Date());
		for(PRegDto prDto:pendingRegs){
			Date dueDate=Util.convertStringToDate(prDto.getDueDate(), Constants.DATE_PATTERN);
			Calendar dueCal=Calendar.getInstance();
			dueCal.setTime(dueDate);			
			Long overDueDays=Util.daysBetween(dueCal,currentCal);
			prDto.setOverDueDays(overDueDays.toString());
		}
		return pendingRegs;
	}

	/* (non-Javadoc)
	 * @see org.lims.register.service.RegisterServiceInter#getPendingRegistrations(java.lang.String)
	 */
	@Override
	public List<PRegDto> getUnCompletedRegistrations(String deptName)throws Exception {
		List<PRegDto> pendingRegs=regdao.getUnCompletedRegistrations(deptName);
		Calendar currentCal=Calendar.getInstance();
		currentCal.setTime(new Date());
		for(PRegDto prDto:pendingRegs){
			Date dueDate=Util.convertStringToDate(prDto.getDueDate(), Constants.DATE_PATTERN);
			Calendar dueCal=Calendar.getInstance();
			dueCal.setTime(dueDate);			
			Long overDueDays=Util.daysBetween(dueCal,currentCal);
			prDto.setOverDueDays(overDueDays.toString());
		}
		return pendingRegs;
	}

	/* (non-Javadoc)
	 * @see org.lims.register.service.RegisterServiceInter#deleteRegistration(java.lang.String)
	 */
	@Override
	public void deleteRegistration(String regNumber) throws Exception {
		regdao.deleteRegistration(regNumber);
		
	}

	/* (non-Javadoc)
	 * @see org.lims.register.service.RegisterServiceInter#getRegistrations(org.lims.register.dto.PDRegDto)
	 */
	@Override
	public PDRegDto getRegistrations(PDRegDto pdregdto) throws Exception {
		
		try{
			RegisterValidation.validatePageSize(pdregdto.getPageSize());
		}catch(InvalidInputException ine){
			exceptions.put("REG_PAGE_SIZE",ine.getMessage());
		}
		
		try{
			RegisterValidation.validateViewRegFromDate(pdregdto.getStartDate());
		}catch(InvalidInputException ine){
			exceptions.put("REG_START_DATE",ine.getMessage());
		}
		
		try{
			RegisterValidation.validateViewRegToDate(pdregdto.getEndDate());
		}catch(InvalidInputException ine){
			exceptions.put("REG_END_DATE",ine.getMessage());
		}
		
		if(!exceptions.isEmpty())
			throw new ValidationErrorsException();
		
		pdregdto.setLimit(new Integer(pdregdto.getPageSize()));
		Integer offset=(pdregdto.getPageNumber()-1)*pdregdto.getLimit();
		pdregdto.setOffset(offset);
		PDRegDto spdregdto=regdao.getRegistrations(pdregdto);		
		
		return spdregdto;
	}

	/* (non-Javadoc)
	 * @see org.lims.register.service.RegisterServiceInter#getSampleCollectionMethods()
	 */
	@Override
	public List<SampleCollectionMethodDto> getSampleCollectionMethods()throws Exception {
		List<SampleCollectionMethodDto> scMethods=regdao.getSampleCollectionMethods();
		return scMethods;
	}

	/* (non-Javadoc)
	 * @see org.lims.register.service.RegisterServiceInter#displayCustRefFile(java.lang.String)
	 */
	@Override
	public void displayCustRefFile(String regNum) throws Exception {
		CrFileDto crFile=regdao.getCustRefFile(regNum);
		String fileName=UUID.randomUUID().toString()+"."+crFile.getFileExt();
		String absoluteFilePath=System.getProperty("java.io.tmpdir")+fileName;
		File file=new File(absoluteFilePath);
		InputStream inputStream= crFile.getCrFile();
	    OutputStream out=new FileOutputStream(file);
	    byte buf[]=new byte[1024];
	    int len;
	    while((len=inputStream.read(buf))>0)
	    	out.write(buf,0,len);
	    out.close();
	    inputStream.close();
	    Desktop desktop=Desktop.getDesktop();
	    desktop.open(file);
		
	}	
	
	
	
}
