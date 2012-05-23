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
	
	/**
	 * validates the employee Id.
	 * @param id
	 * @throws InvalidInputException
	 */
	public static void validateEmpId(String id)throws InvalidInputException{
		if(!id.matches("^[a-zA-Z1-9-_]{4,50}$")){
			throw new InvalidInputException(resources.getString("emmIdInvalid"));
		}
	}
	
	/**
	 * validates employee name.
	 * @ param name
	 * @throws InvalidInputException 
	 */
	public static void validateEmpName(String name)throws InvalidInputException{
		if(!name.matches("^[a-zA-Z\\s]{4,50}$")){
			throw new InvalidInputException(resources.getString("empNameInvalid"));
		}
	}
	
	/**
	 * validates Employee designation.
	 * @param designation
	 * @throws InvalidInputException
	 */
	public static void validateEmpDesignation(String designation)throws InvalidInputException{
		if(!designation.matches("^[a-zA-Z]{4,50}$")){
			throw new InvalidInputException(resources.getString("empDesgInvalid"));
		}
	}
	
	/**
	 * validates Employee Default Password.
	 * @param defaultPassword
	 * @throws InvalidInputException
	 */
	public static void validateEmpDefaultPwd(String defaultPassword)throws InvalidInputException{
		if(!defaultPassword.matches("^[a-zA-Z1-9-_!@#$\\.\\*]{8,20}$")){
			throw new InvalidInputException(resources.getString("empDefaultPwdInvalid"));
		}
	}
	
	/**
	 * validates employee Retyped Default Password.
	 * @param retypeDefaultPassword
	 * @throws InvalidInputException
	 */
	public static void validateEmpRetypeDefaultPwd(String retypeDefaultPassword)throws InvalidInputException{
		if(!retypeDefaultPassword.matches("^[a-zA-Z1-9-_!@#$\\.\\*]{8,20}$")){
			throw new InvalidInputException(resources.getString("empRetypeDefaultPwdInvalid"));
		}
	}
	
	/**
	 * It compares the two given passwords for equality.
	 * @param one
	 * @param two
	 * @throws InvalidInputException
	 */
	public static void comparePasswords(String one,String two)throws InvalidInputException{
		if(!one.equals(two))
			throw new InvalidInputException(resources.getString("empComparePwds"));
	}
	
}
