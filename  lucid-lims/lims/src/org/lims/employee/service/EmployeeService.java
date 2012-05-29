/**
 * 
 */
package org.lims.employee.service;

import org.lims.admin.dto.RoleDto;
import org.lims.admin.service.AdminService;
import org.lims.admin.service.AdminServiceInter;
import org.lims.common.exceptions.InvalidInputException;
import org.lims.common.exceptions.ValidationErrorsException;
import org.lims.employee.dao.EmployeeDao;
import org.lims.employee.dao.EmployeeDaoInter;
import org.lims.employee.dto.EmployeeDto;
import org.lims.employee.service.validate.EmployeeValidation;
import org.lims.register.dto.DepartmentDto;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class EmployeeService implements EmployeeServiceInter{
	
	private AdminServiceInter adminService=new AdminService();
	private EmployeeDaoInter empdao=new EmployeeDao();

	/* (non-Javadoc)
	 * @see org.lims.employee.service.EmployeeServiceInter#getEmployee(java.lang.String)
	 */
	@Override
	public EmployeeDto getEmployee(String empId) throws Exception {
		EmployeeDto employee=empdao.getEmployee(empId);
		DepartmentDto dept=adminService.getDepartment(employee.getEmpDepartmentId());
		RoleDto role=adminService.getRole(employee.getEmpRoleId());
		employee.setEmpDepartment(dept.getDeptName());
		employee.setEmpRole(role.getRoleName());
		return employee;
	}

	/* (non-Javadoc)
	 * @see org.lims.employee.service.EmployeeServiceInter#updateEmployee(org.lims.employee.dto.EmployeeDto)
	 */
	@Override
	public void updateEmployee(EmployeeDto employee) throws Exception {
		empdao.updateEmployee(employee);
		
	}

	/* (non-Javadoc)
	 * @see org.lims.employee.service.EmployeeServiceInter#getCurrentPassword(java.lang.String)
	 */
	@Override
	public String getCurrentPassword(String empId) throws Exception {
		String password=empdao.getCurrentPassword(empId);
		return password;
	}

	/* (non-Javadoc)
	 * @see org.lims.employee.service.EmployeeServiceInter#updatePassword(java.lang.String, java.lang.String)
	 */
	@Override
	public void updatePassword(String newPassword, String empId)
			throws Exception {
		empdao.updatePassword(newPassword, empId);
		
	}

	/* (non-Javadoc)
	 * @see org.lims.employee.service.EmployeeServiceInter#changePassword(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void changePassword(String empId, String password, String newPassword,String retypedPassword)
			throws Exception {
		
		try{
			EmployeeValidation.validateEmpPwd(password, empId);
		}catch(InvalidInputException ine){
			exceptions.put("EMP_PWD",ine.getMessage());
		}
		
		try{
			EmployeeValidation.validateEmpNewPwd(newPassword);
		}catch(InvalidInputException ine){
			exceptions.put("EMP_NEW_PWD",ine.getMessage());
		}
		
		try{
			EmployeeValidation.validateEmpRePwd(retypedPassword);
		}catch(InvalidInputException ine){
			exceptions.put("EMP_RETYPED_PWD",ine.getMessage());
		}
		
		try{
			EmployeeValidation.validateNewPasswords(newPassword, retypedPassword);
		}catch(InvalidInputException ine){
			exceptions.put("EMP_BOTH_PWD",ine.getMessage());
		}
		
		if(!exceptions.isEmpty())
			throw new ValidationErrorsException();
		
		updatePassword(newPassword, empId);
		
	}

	
	
	
}
