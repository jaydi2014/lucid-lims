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
	
	/**
	 * validates the department name.
	 * @param deptName
	 * @throws InvalidInputException
	 */
	public static void validateDeptName(String deptName)throws InvalidInputException{
		if(!deptName.matches("[a-zA-Z\\s]{2,45}")){
			throw new InvalidInputException(resources.getString("deptNameInvalid"));
		}
	}	
	
	/**
	 * validates the department description.
	 * @param deptDesc
	 * @throws InvalidInputException
	 */
	public static void validateDeptDesc(String deptDesc)throws InvalidInputException{
		if(!deptDesc.matches("^[a-zA-Z,\\.\\s]{0,500}$")){
			throw new InvalidInputException(resources.getString("departmentDescInvalid"));
		}
	}
	
	/**
	 * validates the role name.
	 * @param roleName
	 * @throws InvalidInputException
	 */
	public static void validateRoleName(String roleName)throws InvalidInputException{
		if(!roleName.matches("[a-zA-Z\\s]{2,45}")){
			throw new InvalidInputException(resources.getString("roleNameInvalid"));
		}
	}	
	
	/**
	 * validates the role description.
	 * @param roleDesc
	 * @throws InvalidInputException
	 */
	public static void validateRoleDesc(String roleDesc)throws InvalidInputException{
		if(!roleDesc.matches("^[a-zA-Z,\\.\\s]{0,500}$")){
			throw new InvalidInputException(resources.getString("roleDescInvalid"));
		}
	}
	
}
