/**
 * 
 */
package org.lims.register.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import org.lims.customer.dto.CustomerDto;
import org.lims.customer.service.CustomerService;
import org.lims.customer.service.CustomerServiceInter;
import org.lims.register.gui.RegisterSamplesDialog;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class CustSelectComboListener implements ActionListener{

	private RegisterSamplesDialog rsDialog;
	private CustomerServiceInter custService=new CustomerService();
	
	public CustSelectComboListener(RegisterSamplesDialog rsDialog){
		this.rsDialog=rsDialog;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		JComboBox custbox=(JComboBox)event.getSource();
		
		try{
			CustomerDto custdto=custService.getCustomer((String)custbox.getSelectedItem());
			rsDialog.getCustAddressTA().setText(custdto.getAddress());
			rsDialog.getCustPhoneTF().setText(custdto.getPhoneNumber());
			rsDialog.getCustFaxTF().setText(custdto.getFaxNumber());
			rsDialog.getCustEmailTF().setText(custdto.getEmail());
			rsDialog.getCustCtPersonTF().setText(custdto.getContactPersonName());
			rsDialog.getCustCtPersonMobileTF().setText(custdto.getContactPersonMobile());
			rsDialog.getCustCtPersonEmailTF().setText(custdto.getContactPersonEmail());
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
}
