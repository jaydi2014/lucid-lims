/**
 * 
 */
package org.lims.employee.dao;

import java.util.List;

import org.lims.employee.dto.EmployeeDto;

/**
 * @author Muralidhar Yaragalla
 *
 */
public interface EmployeeDaoInter {

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
	 * Returns the employee list that matches the prefix.
	 * @param prefix
	 * @return employee list.
	 * @throws Exception
	 */
	public List<String> getEmployeeDisplayNames(String prefix)throws Exception;
	
	/**
	 * Returns Employee details when given employee name.
	 * @param empDisplayName
	 * @return EmployeeDto
	 * @throws Exception
	 */
	public EmployeeDto getEmployeeByDisplayName(String empDisplayName)throws Exception;
	
	/**
	 * Checks the employee username and password.
	 * @param userName
	 * @param password
	 * @return EmployeeDto
	 * @throws Exception
	 */
	public EmployeeDto checkUserNamePass(String userName,String password)throws Exception;
}
