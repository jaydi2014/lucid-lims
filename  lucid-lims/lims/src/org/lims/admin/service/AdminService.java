/**
 * 
 */
package org.lims.admin.service;

import java.util.List;

import org.lims.admin.dao.AdminDao;
import org.lims.admin.dao.AdminDaoInter;
import org.lims.admin.service.validate.AdminValidation;
import org.lims.common.exceptions.InvalidInputException;
import org.lims.common.exceptions.ValidationErrorsException;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class AdminService implements AdminServiceInter{

	private AdminDaoInter adminDao=new AdminDao();
	
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
		
		try{
			adminDao.addDepartment(deptName, desc);
		}catch(Exception e){
			exceptions.put("OTHER",e.getMessage());
			throw e;
		}
		
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
	
	

}
