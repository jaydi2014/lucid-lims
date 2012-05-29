/**
 * 
 */
package org.lims.employee.service;

import java.util.HashMap;

import org.lims.employee.dto.EmployeeDto;

/**
 * @author Muralidhar Yaragalla
 *
 */
public interface EmployeeServiceInter {
	
	/**
	 * This holds the error messages of the service layer.
	 */
	HashMap<String,String> exceptions=new HashMap<String,String>();

	/**
	 * Retrives Employee details when given Employee Id.
	 * @param empId
	 * @return EmployeeDto.
	 * @throws Exception
	 */
	public EmployeeDto getEmployee(String empId)throws Exception;
	
	/**
	 * updates the employee updatable fields.
	 * @param employee
	 * @throws Exception
	 */
	public void updateEmployee(EmployeeDto employee)throws Exception;
	
	/**
	 * Retrieves employee current password.
	 * @param empId
	 * @return password.
	 * @throws Exception
	 */
	public String getCurrentPassword(String empId)throws Exception;
	
	/**
	 * updates the password.
	 * @param newPassword
	 * @param empId
	 * @throws Exception
	 */
	public void updatePassword(String newPassword,String empId)throws Exception;
	
	/**
	 * Changes the employee password.
	 * @param empId
	 * @param password
	 * @param newPassword
	 * @param retypedPassword
	 * @throws Exception
	 */
	public void changePassword(String empId,String password,String newPassword,
			String retypedPassword)throws Exception;
}
