/**
 * 
 */
package org.lims.employee.service.validate;

import java.util.ResourceBundle;

import org.lims.common.exceptions.InvalidInputException;
import org.lims.employee.service.EmployeeService;
import org.lims.employee.service.EmployeeServiceInter;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class EmployeeValidation {
	
	private static ResourceBundle resources=Util.getResources();
	private static EmployeeServiceInter service=new EmployeeService();
	
	/**
	 * validates employee current password.
	 * @param password
	 * @param empId
	 * @throws Exception
	 */
	public static void validateEmpPwd(String password,String empId)throws Exception{
		if(!password.matches("^[a-zA-Z0-9-_!@#$\\.\\*]{8,20}$")){
			throw new InvalidInputException(resources.getString("currentPasswordInvalid"));
		}
		
		String currentPwd=service.getCurrentPassword(empId);
		if(!password.equals(currentPwd)){
			throw new InvalidInputException(resources.getString("currentPasswordWrong"));
		}
	}
	
	/**
	 * validates employee new password.
	 * @param newPassword
	 * @throws Exception
	 */
	public static void validateEmpNewPwd(String newPassword)throws Exception{
		if(! newPassword.matches("^[a-zA-Z0-9-_!@#$\\.\\*]{8,20}$")){
			throw new InvalidInputException(resources.getString("newPasswordInvalid"));
		}
		
	}
	
	/**
	 * validates employee retyped password.
	 * @param retypedPassword
	 * @throws Exception
	 */
	public static void validateEmpRePwd(String retypedPassword)throws Exception{
		if(!retypedPassword.matches("^[a-zA-Z0-9-_!@#$\\.\\*]{8,20}$")){
			throw new InvalidInputException(resources.getString("retypedNewPasswordInvalid"));
		}		
	}
	
	/**
	 * validates whether new Password and retyped passwords are the same.
	 * @param newPassword
	 * @param retypedNewPassword
	 * @throws Exception
	 */
	public static void validateNewPasswords(String newPassword,String retypedNewPassword )throws Exception{
		if(!newPassword.equals(retypedNewPassword)){
			throw new InvalidInputException(resources.getString("passwordsDidNotMatch"));
		}
	}

}
