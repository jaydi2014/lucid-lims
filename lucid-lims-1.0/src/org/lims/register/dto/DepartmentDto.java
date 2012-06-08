/**
 * 
 */
package org.lims.register.dto;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class DepartmentDto {
 
	private int departmentId;
	private String deptName;
	private String deptDesc;
	
	
	/**
	 * @return the departmentId
	 */
	public int getDepartmentId() {
		return departmentId;
	}
	/**
	 * @param departmentId the departmentId to set
	 */
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * @return the deptDesc
	 */
	public String getDeptDesc() {
		return deptDesc;
	}
	/**
	 * @param deptDesc the deptDesc to set
	 */
	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}
	
	
}
