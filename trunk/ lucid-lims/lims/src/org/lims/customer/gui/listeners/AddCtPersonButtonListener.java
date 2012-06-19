/**
 * 
 */
package org.lims.customer.gui.listeners;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.lims.common.exceptions.ValidationErrorsException;
import org.lims.customer.dto.ContactPersonDto;
import org.lims.customer.dto.CustomerDto;
import org.lims.customer.gui.AddCtPersonDialog;
import org.lims.customer.service.CustomerService;
import org.lims.customer.service.CustomerServiceInter;
import org.lims.gui.util.ErrorsDisplayJPanel;
import org.lims.gui.util.GuiUtil;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class AddCtPersonButtonListener implements ActionListener{

	private AddCtPersonDialog addCtPersonDialog;
	
	private Logger log=Logger.getLogger(AddCtPersonButtonListener.class);
	
	private CustomerServiceInter service=new CustomerService();
	
	private ErrorsDisplayJPanel errorMsgPanel;
	
	private JPanel successPanel;
	
	public AddCtPersonButtonListener(AddCtPersonDialog addCtPersonDialog){
		this.addCtPersonDialog=addCtPersonDialog;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		cleanup();
		CustomerDto custdto=setCustomerDto();
		
		try{
			service.addContactPerson(custdto);
			
			successPanel=GuiUtil.getSuccessMsgPanel(Util.getResources().getString("ctPersonAddedSuccessfully"));
			addCtPersonDialog.add(successPanel,BorderLayout.NORTH);
			addCtPersonDialog.validate();
			addCtPersonDialog.repaint();
			postServiceCleanup();
		}catch(Exception e){			
			if(e instanceof ValidationErrorsException){
				HashMap<String,String> exceptions=CustomerServiceInter.exceptions;
				errorMsgPanel = new ErrorsDisplayJPanel(exceptions.size());
				if(exceptions.containsKey("CUST_NAME")){					
					String errMsg=exceptions.remove("CUST_NAME");
					errorMsgPanel.addErrMsg(errMsg);
				}
				
				if(exceptions.containsKey("CONTACT_NAME")){
					addCtPersonDialog.getCtPersonLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("CONTACT_NAME");
					errorMsgPanel.addErrMsg(errMsg);
				}
				
				if(exceptions.containsKey("CONTACT_MOBILE")){
					addCtPersonDialog.getCtPersonMobileLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("CONTACT_MOBILE");
					errorMsgPanel.addErrMsg(errMsg);
				}
				
				if(exceptions.containsKey("CONTACT_EMAIL")){
					addCtPersonDialog.getCtPersonEmailLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("CONTACT_EMAIL");
					errorMsgPanel.addErrMsg(errMsg);
				}
				
				
				addCtPersonDialog.add(errorMsgPanel,BorderLayout.NORTH);
				addCtPersonDialog.validate();
				addCtPersonDialog.repaint();
			}else{								
				log.debug(e.getMessage(),e);
			}
			
			
		}

		
	}
	
	/**
	 * Pre service cleanup.
	 */
	private void cleanup(){
		if(errorMsgPanel!=null){
			addCtPersonDialog.getCtPersonLabel().setForeground(Color.BLACK);
			addCtPersonDialog.getCtPersonMobileLabel().setForeground(Color.BLACK);
			addCtPersonDialog.getCtPersonEmailLabel().setForeground(Color.BLACK);
			
			addCtPersonDialog.remove(errorMsgPanel);
			addCtPersonDialog.validate();
			addCtPersonDialog.repaint();
			
		}
		
		if(successPanel!=null){
			addCtPersonDialog.remove(successPanel);
			addCtPersonDialog.validate();
			addCtPersonDialog.repaint();
		}
		
	}
	
	/**
	 * This performs the post service cleanup of GUI.
	 */
	private void postServiceCleanup(){		
		addCtPersonDialog.getCtPersonTF().setText("");
		addCtPersonDialog.getCtPersonMobileTF().setText("");
		addCtPersonDialog.getCtPersonEmialTF().setText("");
		
	}
	
	private CustomerDto setCustomerDto(){
		CustomerDto customer=new CustomerDto();
		customer.setCustName(addCtPersonDialog.getRsd().getCustTF().getText());
		List<ContactPersonDto> ctPersons=new ArrayList<ContactPersonDto>();
		ContactPersonDto ctPerson=new ContactPersonDto();
		ctPerson.setCtPersonName(addCtPersonDialog.getCtPersonTF().getText());
		ctPerson.setCtPersonMobile(addCtPersonDialog.getCtPersonMobileTF().getText());
		ctPerson.setCtPersonEmail(addCtPersonDialog.getCtPersonEmialTF().getText());
		ctPersons.add(ctPerson);
		customer.setContactPersons(ctPersons);
		return customer;
	}
}
