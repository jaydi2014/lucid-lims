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
	public void registerSamples(TestRegisterDto registerDto)throws Exception;

}
