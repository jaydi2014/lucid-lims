/**
 * 
 */
package org.lims.admin.dao;

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
}
