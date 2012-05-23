/**
 * 
 */
package org.lims.admin.dto;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class EmployeeDto {
	
	private String empId;
	private String empName;
	private String empDesignation;
	private String empDepartment;
	private String empRole;
	private String password;
	private String retypedPwd;
	
	
	/**
	 * @return the empId
	 */
	public String getEmpId() {
		return empId;
	}
	
	
	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	
	
	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}
	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	/**
	 * @return the empDesignation
	 */
	public String getEmpDesignation() {
		return empDesignation;
	}
	/**
	 * @param empDesignation the empDesignation to set
	 */
	public void setEmpDesignation(String empDesignation) {
		this.empDesignation = empDesignation;
	}
	/**
	 * @return the empDepartment
	 */
	public String getEmpDepartment() {
		return empDepartment;
	}
	/**
	 * @param empDepartment the empDepartment to set
	 */
	public void setEmpDepartment(String empDepartment) {
		this.empDepartment = empDepartment;
	}
	/**
	 * @return the empRole
	 */
	public String getEmpRole() {
		return empRole;
	}
	/**
	 * @param empRole the empRole to set
	 */
	public void setEmpRole(String empRole) {
		this.empRole = empRole;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the retypedPwd
	 */
	public String getRetypedPwd() {
		return retypedPwd;
	}
	/**
	 * @param retypedPwd the retypedPwd to set
	 */
	public void setRetypedPwd(String retypedPwd) {
		this.retypedPwd = retypedPwd;
	}

}
