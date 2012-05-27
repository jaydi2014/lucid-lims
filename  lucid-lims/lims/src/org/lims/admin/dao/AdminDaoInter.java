/**
 * 
 */
package org.lims.admin.dao;

import java.util.List;

import org.lims.admin.dto.EmployeeDto;
import org.lims.register.dto.DepartmentDto;

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
	
	/**
	 * This checks whether department name already exist.
	 * @param deptName
	 * @return Boolean
	 * @throws Exception
	 */
	public Boolean checkDeptNameExist(String deptName)throws Exception;
	
	/**
	 * This checks whether the role name already exist.
	 * @param roleName
	 * @return Boolean
	 * @throws Exception
	 */
	public Boolean checkRoleNameExist(String roleName)throws Exception;
	
	/**
	 * This checks whether employee display name already exist.
	 * @param displayName
	 * @return Boolean
	 * @throws Exception
	 */
	public Boolean checkEmpDisplayNameExist(String displayName)throws Exception;
	
	/**
	 * This checks whether user name already exist.
	 * @param userName
	 * @return Boolean
	 * @throws Exception
	 */
	public Boolean checkUserNameExist(String userName)throws Exception;
	
	/**
	 * Returns Department Id when given Department name.
	 * @param deptName
	 * @return Integer
	 * @throws Exception
	 */
	public Integer getDepartmentId(String deptName)throws Exception;
	
	/**
	 * Retrieves Department details for the given Department id.
	 * @param id
	 * @return DepartmentDto
	 * @throws Exception
	 */
	public DepartmentDto getDepartment(Integer id)throws Exception;
}
