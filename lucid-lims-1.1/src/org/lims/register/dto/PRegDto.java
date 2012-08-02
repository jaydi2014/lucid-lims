/**
 * 
 */
package org.lims.register.dto;

import java.util.List;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class PRegDto {
	
	private String regNum;
	private String custName;
	private String recievedDate;
	private String dueDate;
	private String overDueDays;
	private String deptName;
	private List<SampleDto> samples;
	
	
	/**
	 * @return the regNum
	 */
	public String getRegNum() {
		return regNum;
	}
	/**
	 * @param regNum the regNum to set
	 */
	public void setRegNum(String regNum) {
		this.regNum = regNum;
	}
	/**
	 * @return the custName
	 */
	public String getCustName() {
		return custName;
	}
	/**
	 * @param custName the custName to set
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}
	/**
	 * @return the recievedDate
	 */
	public String getRecievedDate() {
		return recievedDate;
	}
	/**
	 * @param recievedDate the recievedDate to set
	 */
	public void setRecievedDate(String recievedDate) {
		this.recievedDate = recievedDate;
	}
	/**
	 * @return the dueDate
	 */
	public String getDueDate() {
		return dueDate;
	}
	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	/**
	 * @return the overDueDays
	 */
	public String getOverDueDays() {
		return overDueDays;
	}
	/**
	 * @param overDueDays the overDueDays to set
	 */
	public void setOverDueDays(String overDueDays) {
		this.overDueDays = overDueDays;
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
	 * @return the samples
	 */
	public List<SampleDto> getSamples() {
		return samples;
	}
	/**
	 * @param samples the samples to set
	 */
	public void setSamples(List<SampleDto> samples) {
		this.samples = samples;
	}

}
