/**
 * 
 */
package org.lims.register.service;

import org.lims.common.exceptions.InvalidInputException;
import org.lims.register.dao.RegisterDao;
import org.lims.register.dao.RegisterDaoInter;
import org.lims.register.dto.TestRegisterDto;
import org.lims.register.service.validate.RegisterValidation;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class RegisterService implements RegisterServiceInter{
	
	private RegisterDaoInter regdao=new RegisterDao();

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
		
	}

	/* (non-Javadoc)
	 * @see org.lims.register.service.RegisterServiceInter#checkRegNumExist(java.lang.String)
	 */
	@Override
	public Boolean checkRegNumExist(String regNumber) throws Exception {
		Boolean exist=regdao.checkRegNumExist(regNumber);
		return exist;
	}
	
	

	
}
