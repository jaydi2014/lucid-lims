/**
 * 
 */
package org.lims.register.dao;

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

}
