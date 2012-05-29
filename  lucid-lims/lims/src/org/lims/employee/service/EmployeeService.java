/**
 * 
 */
package org.lims.employee.service;

import org.lims.admin.dto.RoleDto;
import org.lims.admin.service.AdminService;
import org.lims.admin.service.AdminServiceInter;
import org.lims.employee.dao.EmployeeDao;
import org.lims.employee.dao.EmployeeDaoInter;
import org.lims.employee.dto.EmployeeDto;
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

	
}
