/**
 * 
 */
package org.lims.register.service;

import java.util.HashMap;

import org.lims.register.dto.TestRegisterDto;

/**
 * @author Muralidhar Yaragalla
 *
 */
public interface RegisterServiceInter {

	/**
	 * This holds the error messages of the service layer.
	 */
	HashMap<String,String> exceptions=new HashMap<String,String>();
	
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
}
