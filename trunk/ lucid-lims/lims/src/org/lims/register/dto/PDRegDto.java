/**
 * 
 */
package org.lims.register.dto;

import java.util.Date;
import java.util.List;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class PDRegDto {
	
	private Date startDate;
	private Date endDate;
	private Integer limit;
	private Integer offset;
	private List<TestRegisterDto> regs;
	private Integer totalResults;
	private Integer pageNumber;
	private String pageSize;
	
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the limit
	 */
	public Integer getLimit() {
		return limit;
	}
	/**
	 * @param limit the limit to set
	 */
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	/**
	 * @return the offset
	 */
	public Integer getOffset() {
		return offset;
	}
	/**
	 * @param offset the offset to set
	 */
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	/**
	 * @return the regs
	 */
	public List<TestRegisterDto> getRegs() {
		return regs;
	}
	/**
	 * @param regs the regs to set
	 */
	public void setRegs(List<TestRegisterDto> regs) {
		this.regs = regs;
	}
	/**
	 * @return the totalResults
	 */
	public Integer getTotalResults() {
		return totalResults;
	}
	/**
	 * @param totalResults the totalResults to set
	 */
	public void setTotalResults(Integer totalResults) {
		this.totalResults = totalResults;
	}
	/**
	 * @return the pageNumber
	 */
	public Integer getPageNumber() {
		return pageNumber;
	}
	/**
	 * @param pageNumber the pageNumber to set
	 */
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	/**
	 * @return the pageSize
	 */
	public String getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	
	

}
