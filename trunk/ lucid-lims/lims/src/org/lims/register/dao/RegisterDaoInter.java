/**
 * 
 */
package org.lims.register.dao;

import java.util.List;

import org.lims.register.dto.PDRegDto;
import org.lims.register.dto.PRegDto;
import org.lims.register.dto.TestRegisterDto;

/**
 * @author Muralidhar Yaragalla
 *
 */
public interface RegisterDaoInter {
	
	/**
	 * This adds register entry into the database.
	 * @param registerDto
	 * @throws Exception
	 */
	public void createRegisterEntry(TestRegisterDto registerDto)throws Exception;
	
	/**
	 * Checks whether given registration number already exist.
	 * @param regNumber
	 * @return Boolean
	 * @throws Exception
	 */
	public Boolean checkRegNumExist(String regNumber)throws Exception;
	
	/**
	 * It pulls the Registry Entry for the given registration number.
	 * @param regNum
	 * @return TestRegisterDto
	 * @throws Exception
	 */
	public TestRegisterDto getRegisterEntry(String regNum)throws Exception;
	
	/**
	 * Adds and updates dispatch and billing details.
	 * @param registerDto
	 * @throws Exception
	 */
	public void updateBillingandDispatch(TestRegisterDto registerDto)throws Exception;
	
	/**
	 * Retrieves all the pending registrations.
	 * @return pending registrations.
	 * @throws Exception
	 */
	public List<PRegDto> getPendingRegistrations()throws Exception;
	
	/**
	 * Returns registrations filtered by department.
	 * @param deptName
	 * @return list of pending regs.
	 * @throws Exception
	 */
	public List<PRegDto> getPendingRegistrations(String deptName)throws Exception;
	
	/**
	 * Deletes the register entry for the given registration number.
	 * @param RegNumber
	 * @throws Exception
	 */
	public void deleteRegistration(String regNumber)throws Exception;
	
	/**
	 * This returns the list of registrations for the given date range.
	 * @param prregdto
	 * @return pagination details reg dto.
	 * @throws Exception
	 */
	public PDRegDto getRegistrations(PDRegDto pdregdto)throws Exception;

}
