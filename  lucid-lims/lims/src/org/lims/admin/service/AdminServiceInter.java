/**
 * 
 */
package org.lims.admin.service;

import java.util.HashMap;
import java.util.List;

import org.lims.admin.dto.EmployeeDto;

/**
 * @author Muralidhar Yaragalla
 *
 */
public interface AdminServiceInter {
	
	/**
	 * This holds the error messages of the service layer.
	 */
	HashMap<String,String> exceptions=new HashMap<String,String>();
	
	/**
	 * This method adds a department.
	 * @param deptName
	 * @param desc
	 * @throws Exception
	 */
	public void addDepartment(String deptName,String desc)throws Exception;
	
	/**
	 * This adds a role to the system.
	 * @param name
	 * @param desc
	 * @throws Exception
	 */
	public void addRole(String name,String desc)throws Exception;

	/**
	 * This will retrieve all the roles from system.
	 * @throws Exception
	 * @return roles list.
	 */
	public List<String> getRoles()throws Exception;
	
	/**
	 * This returns departments from the system.
	 * @return department List.
	 * @throws Exception
	 */
	public List<String> getDepartments()throws Exception;
	
	/**
	 * This adds an employee to the system.
	 * @param empDto
	 * @throws Exception
	 */
	public void addEmployee(EmployeeDto empDto)throws Exception;
}
