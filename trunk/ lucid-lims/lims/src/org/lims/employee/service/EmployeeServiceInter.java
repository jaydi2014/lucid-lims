/**
 * 
 */
package org.lims.employee.service;

import org.lims.employee.dto.EmployeeDto;

/**
 * @author Muralidhar Yaragalla
 *
 */
public interface EmployeeServiceInter {

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
}
