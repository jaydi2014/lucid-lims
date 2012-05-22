/**
 * 
 */
package org.lims.admin.service;

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
	public void addDepartment(String deptName, String desc) throws ValidationErrorsException {
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
			e.printStackTrace();
		}
		
	}
	
	

}
