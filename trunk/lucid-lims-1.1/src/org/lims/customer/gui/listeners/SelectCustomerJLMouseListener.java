/**
 * 
 */
package org.lims.customer.gui.listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;

import org.apache.log4j.Logger;
import org.lims.customer.dto.CustomerDto;
import org.lims.customer.gui.SelectCustDialog;
import org.lims.customer.service.CustomerService;
import org.lims.customer.service.CustomerServiceInter;
import org.lims.register.gui.RegisterSamplesDialog;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class SelectCustomerJLMouseListener extends MouseAdapter{
	
	private SelectCustDialog selectCustDialog;
	private Logger log=Logger.getLogger(SelectCustomerJLMouseListener.class);
	private CustomerServiceInter service=new CustomerService();	
	
	public SelectCustomerJLMouseListener(SelectCustDialog selectCustDialog){
		this.selectCustDialog=selectCustDialog;
	}

	public void mouseClicked(MouseEvent event){
        if(event.getClickCount()==2){ 
        	selectCustDialog.dispose();
        	JList custList=(JList)event.getSource();
        	String custName=(String)custList.getSelectedValue();
        	try{
        		CustomerDto customer=service.getCustomer(custName,false);
        		setCustomerPanel(customer);
        	}catch(Exception e){
        		log.debug(e.getMessage(), e);
        	}
        }
	}
	
	/**
	 * sets the customer fields in sample reg dialog.
	 * @param customer
	 */
	private void setCustomerPanel(CustomerDto customer){
		RegisterSamplesDialog rsDialog=selectCustDialog.getRsDialog();
		rsDialog.getCustTF().setText(customer.getCustName());
		rsDialog.getCustAddressTA().setText(customer.getAddress());
		rsDialog.getCustPhoneTF().setText(customer.getPhoneNumber());
		rsDialog.getCustFaxTF().setText(customer.getFaxNumber());
		rsDialog.getCustEmailTF().setText(customer.getEmail());
	}
	
}
