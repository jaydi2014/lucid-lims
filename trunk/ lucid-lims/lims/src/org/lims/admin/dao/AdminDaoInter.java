/**
 * 
 */
package org.lims.admin.dao;

import java.util.List;

import org.lims.admin.dto.EmployeeDto;

/**
 * @author Muralidhar Yaragalla
 *
 */
public interface AdminDaoInter {

	/**
	 * This adds a department to the database.
	 * @param name
	 * @param desc
	 * @throws Exception
	 */
	public void addDepartment(String name,String desc)throws Exception;
	
	/**
	 * This adds a role to the database.
	 * @param name
	 * @param desc
	 * @throws Exception
	 */
	public void addRole(String name,String desc)throws Exception;
	
	/**
	 * This will retrieve all the roles from database.
	 * @throws Exception
	 * @return roles list.
	 */
	public List<String> getRoles()throws Exception;
	
	/**
	 * This returns departments from the database.
	 * @return department List.
	 * @throws Exception
	 */
	public List<String> getDepartments()throws Exception;
	
	/**
	 * This adds an employee to the database.
	 * @param empDto
	 * @throws Exception
	 */
	public void addEmployee(EmployeeDto empDto)throws Exception;
}
