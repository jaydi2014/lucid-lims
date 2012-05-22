/**
 * 
 */
package org.lims.admin.service.validate;

import java.util.ResourceBundle;

import org.lims.common.exceptions.InvalidInputException;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class AdminValidation {

	private static ResourceBundle resources=Util.getResources();
	
	public static void validateDeptName(String deptName)throws InvalidInputException{
		if(!deptName.matches("[a-zA-Z]{5,45}")){
			throw new InvalidInputException(resources.getString("deptNameInvalid"));
		}
	}	
	
	public static void validateDeptDesc(String deptDesc)throws InvalidInputException{
		if(!deptDesc.matches("^[a-zA-Z,\\.\\s]{0,500}$")){
			throw new InvalidInputException(resources.getString("departmentDescInvalid"));
		}
	}
}
