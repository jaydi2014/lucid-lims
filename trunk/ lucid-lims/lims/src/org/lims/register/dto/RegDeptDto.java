/**
 * 
 */
package org.lims.register.dto;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class RegDeptDto {

	private Integer regDeptId;
	private Integer deptId;
	private String deptName;
	private Integer empId;
	private String empName;
	
	
	/**
	 * @return the regDeptId
	 */
	public Integer getRegDeptId() {
		return regDeptId;
	}
	/**
	 * @param regDeptId the regDeptId to set
	 */
	public void setRegDeptId(Integer regDeptId) {
		this.regDeptId = regDeptId;
	}
	/**
	 * @return the deptId
	 */
	public Integer getDeptId() {
		return deptId;
	}
	/**
	 * @param deptId the deptId to set
	 */
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
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
	 * @return the empId
	 */
	public Integer getEmpId() {
		return empId;
	}
	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(Integer empId) {
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
}
