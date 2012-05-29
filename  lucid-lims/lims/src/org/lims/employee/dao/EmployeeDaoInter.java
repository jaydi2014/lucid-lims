/**
 * 
 */
package org.lims.employee.dao;

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
}
