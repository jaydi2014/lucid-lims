/**
 * 
 */
package org.lims.register.dto;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class CustomerDto {

	private int custId;
	private String custName;
	private String address;
	private int phoneNumber;
	private int faxNumber;
	private String email;
	private String contactPersonName;
	private int contactPersonMobile;
	private String contactPersonEmail;
	
	
	/**
	 * @return the custId
	 */
	public int getCustId() {
		return custId;
	}
	/**
	 * @param custId the custId to set
	 */
	public void setCustId(int custId) {
		this.custId = custId;
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
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the phoneNumber
	 */
	public int getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return the faxNumber
	 */
	public int getFaxNumber() {
		return faxNumber;
	}
	/**
	 * @param faxNumber the faxNumber to set
	 */
	public void setFaxNumber(int faxNumber) {
		this.faxNumber = faxNumber;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the contactPersonName
	 */
	public String getContactPersonName() {
		return contactPersonName;
	}
	/**
	 * @param contactPersonName the contactPersonName to set
	 */
	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}
	/**
	 * @return the contactPersonMobile
	 */
	public int getContactPersonMobile() {
		return contactPersonMobile;
	}
	/**
	 * @param contactPersonMobile the contactPersonMobile to set
	 */
	public void setContactPersonMobile(int contactPersonMobile) {
		this.contactPersonMobile = contactPersonMobile;
	}
	/**
	 * @return the contactPersonEmail
	 */
	public String getContactPersonEmail() {
		return contactPersonEmail;
	}
	/**
	 * @param contactPersonEmail the contactPersonEmail to set
	 */
	public void setContactPersonEmail(String contactPersonEmail) {
		this.contactPersonEmail = contactPersonEmail;
	}
}
