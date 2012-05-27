/**
 * 
 */
package org.lims.admin.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.lims.admin.dao.AdminDao;
import org.lims.admin.dao.AdminDaoInter;
import org.lims.admin.dto.EmployeeDto;
import org.lims.admin.service.validate.AdminValidation;
import org.lims.common.exceptions.InvalidInputException;
import org.lims.common.exceptions.ValidationErrorsException;
import org.lims.register.dto.DepartmentDto;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class AdminService implements AdminServiceInter{

	private AdminDaoInter adminDao=new AdminDao();
	Logger log=Logger.getLogger(AdminService.class);
	
	@Override
	public void addDepartment(String deptName, String desc) throws Exception {
		try{
			AdminValidation.validateDeptName(deptName);
		}catch(InvalidInputException ine){			
			exceptions.put("DEPT",ine.getMessage());
		}
		
		try{
			AdminValidation.validateDeptDesc(desc);
		}catch(InvalidInputException ine){
			exceptions.put("DEPT_DESC",ine.getMessage());
		}
		
		if(!exceptions.isEmpty())
			throw new ValidationErrorsException();
		
		
		adminDao.addDepartment(deptName, desc);
		
		
	}

	@Override
	public void addRole(String name, String desc) throws Exception {
		try{
			AdminValidation.validateRoleName(name);
		}catch(InvalidInputException ine){
			exceptions.put("ROLE",ine.getMessage());
		}
		
		try{
			AdminValidation.validateRoleDesc(desc);
		}catch(InvalidInputException ine){
			exceptions.put("ROLE_DESC",ine.getMessage());
		}
		
		if(!exceptions.isEmpty())
			throw new ValidationErrorsException();
		
		try{
			adminDao.addRole(name, desc);
		}catch(Exception e){
			exceptions.put("OTHER_ROLE",e.getMessage());
			throw e;
		}
		
	}

	@Override
	public List<String> getRoles() throws Exception {
		List<String> list=adminDao.getRoles();
		return list;
	}

	@Override
	public List<String> getDepartments() throws Exception {
		List<String> list=adminDao.getDepartments();
		return list;
	}

	/* (non-Javadoc)
	 * @see org.lims.admin.service.AdminServiceInter#addEmployee(org.lims.admin.dto.EmployeeDto)
	 */
	@Override
	public void addEmployee(EmployeeDto empDto) throws Exception {
		
		try{
			AdminValidation.validateEmpId(empDto.getEmpId());
		}catch(InvalidInputException ine){
			exceptions.put("EMP_ID",ine.getMessage());
		}
		
		try{
			AdminValidation.validateEmpName(empDto.getEmpName());
		}catch(InvalidInputException ine){
			exceptions.put("EMP_NAME",ine.getMessage());
		}
		
		try{
			AdminValidation.validateEmpDesignation(empDto.getEmpDesignation());
		}catch(InvalidInputException ine){
			exceptions.put("EMP_DESG",ine.getMessage());
		}
		
		try{
			AdminValidation.validateEmpDefaultPwd(empDto.getPassword());
		}catch(InvalidInputException ine){
			exceptions.put("EMP_PWD",ine.getMessage());
		}
		
		try{
			AdminValidation.validateEmpRetypeDefaultPwd(empDto.getRetypedPwd());
		}catch(InvalidInputException ine){
			exceptions.put("EMP_REPWD",ine.getMessage());
		}
		
		try{
			AdminValidation.comparePasswords(empDto.getPassword(), empDto.getRetypedPwd());
		}catch(InvalidInputException ine){
			exceptions.put("EMP_PWD_EQL",ine.getMessage());
		}
		
		try{
			AdminValidation.validateEmpDisplayName(empDto.getEmpDisplayName());
		}catch(InvalidInputException ine){
			exceptions.put("EMP_DISPLAY_NAME",ine.getMessage());
		}
		
		try{
			AdminValidation.validateEmpUserName(empDto.getUserName());
		}catch(InvalidInputException ine){
			exceptions.put("EMP_USER_NAME",ine.getMessage());
		}
		
		if(!exceptions.isEmpty())
			throw new ValidationErrorsException();
		
		
			adminDao.addEmployee(empDto);
		
	}

	/* (non-Javadoc)
	 * @see org.lims.admin.service.AdminServiceInter#checkDeptNameExist(java.lang.String)
	 */
	@Override
	public Boolean checkDeptNameExist(String deptName) throws Exception {
		Boolean exist=adminDao.checkDeptNameExist(deptName);
		return exist;
	}

	/* (non-Javadoc)
	 * @see org.lims.admin.service.AdminServiceInter#checkRoleNameExist(java.lang.String)
	 */
	@Override
	public Boolean checkRoleNameExist(String roleName) throws Exception {
		Boolean exist=adminDao.checkRoleNameExist(roleName);
		return exist;
	}

	/* (non-Javadoc)
	 * @see org.lims.admin.service.AdminServiceInter#checkEmpDisplayNameExist(java.lang.String)
	 */
	@Override
	public Boolean checkEmpDisplayNameExist(String displayName)
			throws Exception {
		Boolean exist=adminDao.checkEmpDisplayNameExist(displayName);
		return exist;
	}

	/* (non-Javadoc)
	 * @see org.lims.admin.service.AdminServiceInter#checkUserNameExist(java.lang.String)
	 */
	@Override
	public Boolean checkUserNameExist(String userName) throws Exception {
		Boolean exist=adminDao.checkUserNameExist(userName);
		return exist;
	}

	/* (non-Javadoc)
	 * @see org.lims.admin.service.AdminServiceInter#getDepartmentId(java.lang.String)
	 */
	@Override
	public Integer getDepartmentId(String deptName) throws Exception {
		Integer deptId=adminDao.getDepartmentId(deptName);
		return deptId;
	}

	/* (non-Javadoc)
	 * @see org.lims.admin.service.AdminServiceInter#getDepartment(java.lang.Integer)
	 */
	@Override
	public DepartmentDto getDepartment(Integer id) throws Exception {
		DepartmentDto department=adminDao.getDepartment(id);
		return department;
	}
	
	

}
