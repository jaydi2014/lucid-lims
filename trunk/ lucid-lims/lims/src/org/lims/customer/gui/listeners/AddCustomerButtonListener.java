/**
 * 
 */
package org.lims.customer.gui.listeners;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.lims.admin.service.AdminService;
import org.lims.common.exceptions.ValidationErrorsException;
import org.lims.customer.dto.CustomerDto;
import org.lims.customer.gui.AddCustomerDialog;
import org.lims.customer.service.CustomerService;
import org.lims.customer.service.CustomerServiceInter;
import org.lims.gui.util.ErrorsDisplayJPanel;
import org.lims.gui.util.GuiUtil;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class AddCustomerButtonListener implements ActionListener{
	
	private AddCustomerDialog addCustDialog;
	
	private Logger log=Logger.getLogger(AddCustomerButtonListener.class);
	
	private CustomerServiceInter service=new CustomerService();
	
	private ErrorsDisplayJPanel errorMsgPanel;
	
	private JPanel successPanel;
	
	public AddCustomerButtonListener(AddCustomerDialog addCustDialog){
		this.addCustDialog=addCustDialog;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		cleanup();
		CustomerDto custdto=setCustomerDto();
		
		try{
			service.addCustomer(custdto);
			
			successPanel=GuiUtil.getSuccessMsgPanel(Util.getResources().getString("custAddedSuccessfully"));
			addCustDialog.add(successPanel,BorderLayout.NORTH);
			addCustDialog.validate();
			addCustDialog.repaint();
			postServiceCleanup();
		}catch(Exception e){			
			if(e instanceof ValidationErrorsException){
				HashMap<String,String> exceptions=CustomerServiceInter.exceptions;
				errorMsgPanel = new ErrorsDisplayJPanel(exceptions.size());
				if(exceptions.containsKey("CUST_NAME")){
					addCustDialog.getCustNameLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("CUST_NAME");
					errorMsgPanel.addErrMsg(errMsg);
				}
				
				if(exceptions.containsKey("CUST_ADDR")){
					addCustDialog.getCustAddressLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("CUST_ADDR");
					errorMsgPanel.addErrMsg(errMsg);
				}
				
				if(exceptions.containsKey("CUST_PHONE")){
					addCustDialog.getCustPhoneLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("CUST_PHONE");
					errorMsgPanel.addErrMsg(errMsg);
				}
				
				if(exceptions.containsKey("CUST_FAX")){
					addCustDialog.getCustFaxLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("CUST_FAX");
					errorMsgPanel.addErrMsg(errMsg);
				}
				
				if(exceptions.containsKey("CUST_EMAIL")){
					addCustDialog.getCustEmailLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("CUST_EMAIL");
					errorMsgPanel.addErrMsg(errMsg);
				}
				
				if(exceptions.containsKey("CONTACT_PER_NAME")){					
					addCustDialog.getCustCtPersonLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("CONTACT_PER_NAME");
					errorMsgPanel.addErrMsg(errMsg);
					
				}
				
				if(exceptions.containsKey("CONTACT_PER_MOBILE")){
					addCustDialog.getCustCtPersonMobileLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("CONTACT_PER_MOBILE");
					errorMsgPanel.addErrMsg(errMsg);
					
				}
				
				if(exceptions.containsKey("CONTACT_PER_EMAIL")){
					addCustDialog.getCustCtPersonEmailLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("CONTACT_PER_EMAIL");
					errorMsgPanel.addErrMsg(errMsg);
					
				}
				addCustDialog.add(errorMsgPanel,BorderLayout.NORTH);
				addCustDialog.validate();
				addCustDialog.repaint();
			}else{								
				log.debug(e.getMessage(),e);
			}
			
			
		}
		
	}
	
	private void cleanup(){
		if(errorMsgPanel!=null){
			addCustDialog.getCustNameLabel().setForeground(Color.BLACK);
			addCustDialog.getCustAddressLabel().setForeground(Color.BLACK);
			addCustDialog.getCustPhoneLabel().setForeground(Color.BLACK);
			addCustDialog.getCustFaxLabel().setForeground(Color.BLACK);
			addCustDialog.getCustEmailLabel().setForeground(Color.BLACK);
			addCustDialog.getCustCtPersonLabel().setForeground(Color.BLACK);
			addCustDialog.getCustCtPersonMobileLabel().setForeground(Color.BLACK);
			addCustDialog.getCustCtPersonEmailLabel().setForeground(Color.BLACK);
			addCustDialog.remove(errorMsgPanel);
			addCustDialog.validate();
			addCustDialog.repaint();
			
		}
		
		if(successPanel!=null){
			addCustDialog.remove(successPanel);
			addCustDialog.validate();
			addCustDialog.repaint();
		}
		
	}
	
	/**
	 * This performs the post service cleanup of GUI.
	 */
	private void postServiceCleanup(){		
		addCustDialog.getCustNameTF().setText("");
		addCustDialog.getCustAddressTA().setText("");
		addCustDialog.getCustPhoneTF().setText("");
		addCustDialog.getCustFaxTF().setText("");
		addCustDialog.getCustEmailTF().setText("");	
		addCustDialog.getCustCtPersonTF().setText("");
		addCustDialog.getCustCtPersonMobileTF().setText("");
		addCustDialog.getCustCtPersonEmailTF().setText("");
	}

	
	/**
	 * sets the customer dto
	 * @return CustomerDto
	 */
	private CustomerDto setCustomerDto(){
		CustomerDto custdto=new CustomerDto();
		custdto.setCustName(addCustDialog.getCustNameTF().getText());
		custdto.setAddress(addCustDialog.getCustAddressTA().getText());
		custdto.setPhoneNumber(addCustDialog.getCustPhoneTF().getText());
		custdto.setFaxNumber(addCustDialog.getCustFaxTF().getText());
		custdto.setEmail(addCustDialog.getCustEmailTF().getText());
		custdto.setContactPersonName(addCustDialog.getCustCtPersonTF().getText());
		custdto.setContactPersonMobile(addCustDialog.getCustCtPersonMobileTF().getText());
		custdto.setContactPersonEmail(addCustDialog.getCustCtPersonEmailTF().getText());
		return custdto;
	}

}
