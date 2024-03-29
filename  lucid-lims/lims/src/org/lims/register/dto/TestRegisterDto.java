/**
 * 
 */
package org.lims.register.dto;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.lims.customer.dto.ContactPersonDto;
import org.lims.customer.dto.CustomerDto;
import org.lims.employee.dto.EmployeeDto;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class TestRegisterDto {
	
	private String regNumber;
	private String date;
	private CustomerDto customer=new CustomerDto();	
	private String dueDate;
	private String dispatchDate;
	private String dispatchMethod;
	private String totalTestingChrgs;
	private String amountPaid;
	private String balance;
	private String paymentMeth;
	private String specialInstrs;
	private String packing;
	private String reportNumber;
	private EmployeeDto employee=new EmployeeDto();
	private List<SampleDto> samplesList;
	private String originalDateTime;
	private Boolean dispatchLock=false;
	private Boolean billingLocked=false;
	private String timezoneId;
	private Date regTime;
	private ContactPersonDto ctPerson=new ContactPersonDto();
	private Integer sampleCollectionMethodId;
	private String sampleCollectionMethod;
	private Date labDueDate;
	private String crNumber;
	private Date crDate;
	private InputStream crFile;
	private String crFileExt;
	private List<RegDeptDto> depts;
	private ContractReviewDto contractReview=new ContractReviewDto();
	private String chequeNumber;
	private Date chequeDate;
	
	
	/**
	 * @return the regNumber
	 */
	public String getRegNumber() {
		return regNumber;
	}
	/**
	 * @param regNumber the regNumber to set
	 */
	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the customer
	 */
	public CustomerDto getCustomer() {
		return customer;
	}
	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(CustomerDto customer) {
		this.customer = customer;
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
	 * @return the dispatchDate
	 */
	public String getDispatchDate() {
		return dispatchDate;
	}
	/**
	 * @param dispatchDate the dispatchDate to set
	 */
	public void setDispatchDate(String dispatchDate) {
		this.dispatchDate = dispatchDate;
	}
	/**
	 * @return the dispatchMethod
	 */
	public String getDispatchMethod() {
		return dispatchMethod;
	}
	/**
	 * @param dispatchMethod the dispatchMethod to set
	 */
	public void setDispatchMethod(String dispatchMethod) {
		this.dispatchMethod = dispatchMethod;
	}
	/**
	 * @return the totalTestingChrgs
	 */
	public String getTotalTestingChrgs() {
		return totalTestingChrgs;
	}
	/**
	 * @param totalTestingChrgs the totalTestingChrgs to set
	 */
	public void setTotalTestingChrgs(String totalTestingChrgs) {
		this.totalTestingChrgs = totalTestingChrgs;
	}
	/**
	 * @return the amountPaid
	 */
	public String getAmountPaid() {
		return amountPaid;
	}
	/**
	 * @param amountPaid the amountPaid to set
	 */
	public void setAmountPaid(String amountPaid) {
		this.amountPaid = amountPaid;
	}
	/**
	 * @return the balance
	 */
	public String getBalance() {
		return balance;
	}
	/**
	 * @param balance the balance to set
	 */
	public void setBalance(String balance) {
		this.balance = balance;
	}
	/**
	 * @return the paymentMeth
	 */
	public String getPaymentMeth() {
		return paymentMeth;
	}
	/**
	 * @param paymentMeth the paymentMeth to set
	 */
	public void setPaymentMeth(String paymentMeth) {
		this.paymentMeth = paymentMeth;
	}
	/**
	 * @return the specialInstrs
	 */
	public String getSpecialInstrs() {
		return specialInstrs;
	}
	/**
	 * @param specialInstrs the specialInstrs to set
	 */
	public void setSpecialInstrs(String specialInstrs) {
		this.specialInstrs = specialInstrs;
	}
	/**
	 * @return the packing
	 */
	public String getPacking() {
		return packing;
	}
	/**
	 * @param packing the packing to set
	 */
	public void setPacking(String packing) {
		this.packing = packing;
	}
	/**
	 * @return the reportNumber
	 */
	public String getReportNumber() {
		return reportNumber;
	}
	/**
	 * @param reportNumber the reportNumber to set
	 */
	public void setReportNumber(String reportNumber) {
		this.reportNumber = reportNumber;
	}
	/**
	 * @return the employee
	 */
	public EmployeeDto getEmployee() {
		return employee;
	}
	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(EmployeeDto employee) {
		this.employee = employee;
	}
	/**
	 * @return the samplesList
	 */
	public List<SampleDto> getSamplesList() {
		return samplesList;
	}
	/**
	 * @param samplesList the samplesList to set
	 */
	public void setSamplesList(List<SampleDto> samplesList) {
		this.samplesList = samplesList;
	}
	/**
	 * @return the originalDateTime
	 */
	public String getOriginalDateTime() {
		return originalDateTime;
	}
	/**
	 * @param originalDateTime the originalDateTime to set
	 */
	public void setOriginalDateTime(String originalDateTime) {
		this.originalDateTime = originalDateTime;
	}
	/**
	 * @return the dispatchLock
	 */
	public Boolean getDispatchLock() {
		return dispatchLock;
	}
	/**
	 * @param dispatchLock the dispatchLock to set
	 */
	public void setDispatchLock(Boolean dispatchLock) {
		this.dispatchLock = dispatchLock;
	}
	/**
	 * @return the billingLocked
	 */
	public Boolean getBillingLocked() {
		return billingLocked;
	}
	/**
	 * @param billingLocked the billingLocked to set
	 */
	public void setBillingLocked(Boolean billingLocked) {
		this.billingLocked = billingLocked;
	}
	/**
	 * @return the timezoneId
	 */
	public String getTimezoneId() {
		return timezoneId;
	}
	/**
	 * @param timezoneId the timezoneId to set
	 */
	public void setTimezoneId(String timezoneId) {
		this.timezoneId = timezoneId;
	}
	/**
	 * @return the regTime
	 */
	public Date getRegTime() {
		return regTime;
	}
	/**
	 * @param regTime the regTime to set
	 */
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	/**
	 * @return the ctPerson
	 */
	public ContactPersonDto getCtPerson() {
		return ctPerson;
	}
	/**
	 * @param ctPerson the ctPerson to set
	 */
	public void setCtPerson(ContactPersonDto ctPerson) {
		this.ctPerson = ctPerson;
	}
	/**
	 * @return the sampleCollectionMethodId
	 */
	public Integer getSampleCollectionMethodId() {
		return sampleCollectionMethodId;
	}
	/**
	 * @param sampleCollectionMethodId the sampleCollectionMethodId to set
	 */
	public void setSampleCollectionMethodId(Integer sampleCollectionMethodId) {
		this.sampleCollectionMethodId = sampleCollectionMethodId;
	}
	/**
	 * @return the sampleCollectionMethod
	 */
	public String getSampleCollectionMethod() {
		return sampleCollectionMethod;
	}
	/**
	 * @param sampleCollectionMethod the sampleCollectionMethod to set
	 */
	public void setSampleCollectionMethod(String sampleCollectionMethod) {
		this.sampleCollectionMethod = sampleCollectionMethod;
	}
	/**
	 * @return the labDueDate
	 */
	public Date getLabDueDate() {
		return labDueDate;
	}
	/**
	 * @param labDueDate the labDueDate to set
	 */
	public void setLabDueDate(Date labDueDate) {
		this.labDueDate = labDueDate;
	}
	/**
	 * @return the crNumber
	 */
	public String getCrNumber() {
		return crNumber;
	}
	/**
	 * @param crNumber the crNumber to set
	 */
	public void setCrNumber(String crNumber) {
		this.crNumber = crNumber;
	}
	/**
	 * @return the crDate
	 */
	public Date getCrDate() {
		return crDate;
	}
	/**
	 * @param crDate the crDate to set
	 */
	public void setCrDate(Date crDate) {
		this.crDate = crDate;
	}
	
	/**
	 * @return the crFileExt
	 */
	public String getCrFileExt() {
		return crFileExt;
	}
	/**
	 * @param crFileExt the crFileExt to set
	 */
	public void setCrFileExt(String crFileExt) {
		this.crFileExt = crFileExt;
	}
	/**
	 * @return the depts
	 */
	public List<RegDeptDto> getDepts() {
		return depts;
	}
	/**
	 * @param depts the depts to set
	 */
	public void setDepts(List<RegDeptDto> depts) {
		this.depts = depts;
	}
	/**
	 * @return the contractReview
	 */
	public ContractReviewDto getContractReview() {
		return contractReview;
	}
	/**
	 * @param contractReview the contractReview to set
	 */
	public void setContractReview(ContractReviewDto contractReview) {
		this.contractReview = contractReview;
	}
	/**
	 * @return the chequeNumber
	 */
	public String getChequeNumber() {
		return chequeNumber;
	}
	/**
	 * @param chequeNumber the chequeNumber to set
	 */
	public void setChequeNumber(String chequeNumber) {
		this.chequeNumber = chequeNumber;
	}
	/**
	 * @return the chequeDate
	 */
	public Date getChequeDate() {
		return chequeDate;
	}
	/**
	 * @param chequeDate the chequeDate to set
	 */
	public void setChequeDate(Date chequeDate) {
		this.chequeDate = chequeDate;
	}
	/**
	 * @return the crFile
	 */
	public InputStream getCrFile() {
		return crFile;
	}
	/**
	 * @param crFile the crFile to set
	 */
	public void setCrFile(InputStream crFile) {
		this.crFile = crFile;
	}

}
