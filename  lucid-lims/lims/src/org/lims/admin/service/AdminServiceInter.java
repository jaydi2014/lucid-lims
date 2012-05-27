/**
 * 
 */
package org.lims.admin.service;

import java.util.HashMap;
import java.util.List;

import org.lims.admin.dto.EmployeeDto;
import org.lims.register.dto.DepartmentDto;

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
