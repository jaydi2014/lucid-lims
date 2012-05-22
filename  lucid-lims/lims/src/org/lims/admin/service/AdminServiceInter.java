/**
 * 
 */
package org.lims.admin.service;

import java.util.HashMap;

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

}
