/**
 * 
 */
package org.lims.customer.gui.listeners;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.lims.customer.dto.CustomerDto;
import org.lims.customer.gui.ViewCustDialog;
import org.lims.customer.service.CustomerService;
import org.lims.customer.service.CustomerServiceInter;
import org.lims.gui.util.GuiUtil;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class CustomerJListMouseListener extends MouseAdapter{
	
	private ViewCustDialog viewCustDialog;
	private Logger log=Logger.getLogger(CustomerJListMouseListener.class);
	private CustomerServiceInter service=new CustomerService();
	private ResourceBundle resources=Util.getResources();
	
	private JLabel custNameLabel;
	private JTextField custNameTF;
	private JLabel custAddressLabel;
	private JTextArea custAddressTA;
	private JLabel custPhoneLabel;
	private JTextField custPhoneTF;
	private JLabel custFaxLabel;
	private JTextField custFaxTF;
	private JLabel custEmailLabel;
	private JTextField custEmailTF;
	private JLabel custCtPersonLabel;
	private JTextField custCtPersonTF;
	private JLabel custCtPersonMobileLabel;
	private JTextField custCtPersonMobileTF;
	private JLabel custCtPersonEmailLabel;
	private JTextField custCtPersonEmailTF;
	private JPanel customerPanel;
	
	public CustomerJListMouseListener(ViewCustDialog viewCustDialog){
		this.viewCustDialog=viewCustDialog;
	}

	public void mouseClicked(MouseEvent event){
        if(event.getClickCount()==2){  
        	if(customerPanel!=null){
        		viewCustDialog.remove(customerPanel);
        		viewCustDialog.validate();
        		viewCustDialog.repaint();
        	}
        	
        	JList custList=(JList)event.getSource();
        	String custName=(String)custList.getSelectedValue();
        	try{
        		CustomerDto customer=service.getCustomer(custName,true);
        		customerPanel=createCustomerPanel(customer);
        		viewCustDialog.add(customerPanel,BorderLayout.CENTER);
        		viewCustDialog.validate();
        		viewCustDialog.repaint();
        	}catch(Exception e){
        		log.debug(e.getMessage(), e);
        	}
        }
	}
	
	/**
	 * creates customer panel.
	 * @return
	 */
	private JPanel createCustomerPanel(CustomerDto customer){
		JPanel mainPanel=new JPanel();
		mainPanel.setLayout(null);
		
		GridBagLayout gbl=new GridBagLayout();
		JPanel panel=new JPanel(gbl);
		GridBagConstraints constraints = new GridBagConstraints();
		
		
		custNameLabel=GuiUtil.displayLabel(resources.getString("dialog.admin.label.viewCust.custNameMain"));
		constraints.gridx=0;
		constraints.gridy=0;
		constraints.weightx=0.5;
		constraints.weighty=0.5;
		constraints.anchor=GridBagConstraints.NORTHWEST;
		panel.add(custNameLabel,constraints);
		custNameTF=new JTextField();
		custNameTF.setEditable(false);
		custNameTF.setText(customer.getCustName());
		custNameTF.setPreferredSize(new Dimension(150,30));
		constraints.gridx=1;
		constraints.gridy=0;
		
		constraints.anchor=GridBagConstraints.NORTHWEST;
		panel.add(custNameTF,constraints);
		
		custAddressLabel=GuiUtil.displayLabel(resources.getString("dialog.admin.label.viewCust.address"));
		constraints.gridx=0;
		constraints.gridy=1;
		panel.add(custAddressLabel,constraints);
		custAddressTA=new JTextArea();
		custAddressTA.setEditable(false);
		custAddressTA.setText(customer.getAddress());
		JScrollPane scrollPane=new JScrollPane(custAddressTA);
		scrollPane.setPreferredSize(new Dimension(150,100));
		constraints.gridx=1;
		constraints.gridy=1;
		
		panel.add(scrollPane,constraints);
		
		custPhoneLabel=GuiUtil.displayLabel(resources.getString("dialog.admin.label.viewCust.phone"));
		constraints.gridx=0;
		constraints.gridy=2;
		constraints.weightx=0.5;
		panel.add(custPhoneLabel,constraints);
		custPhoneTF=new JTextField();
		custPhoneTF.setEditable(false);
		custPhoneTF.setText(customer.getPhoneNumber());
		custPhoneTF.setPreferredSize(new Dimension(150,30));
		constraints.gridx=1;
		constraints.gridy=2;
		constraints.weightx=0.5;
		panel.add(custPhoneTF,constraints);
		
		custFaxLabel=GuiUtil.displayLabel(resources.getString("dialog.admin.label.viewCust.fax"));
		constraints.gridx=0;
		constraints.gridy=3;		
		panel.add(custFaxLabel,constraints);
		custFaxTF=new JTextField();
		custFaxTF.setEditable(false);
		custFaxTF.setText(customer.getFaxNumber());
		custFaxTF.setPreferredSize(new Dimension(150,30));
		constraints.gridx=1;
		constraints.gridy=3;		
		panel.add(custFaxTF,constraints);
		
		custEmailLabel=GuiUtil.displayLabel(resources.getString("dialog.admin.label.viewCust.email"));
		constraints.gridx=0;
		constraints.gridy=4;		
		panel.add(custEmailLabel,constraints);
		custEmailTF=new JTextField();
		custEmailTF.setEditable(false);
		custEmailTF.setText(customer.getEmail());
		custEmailTF.setPreferredSize(new Dimension(150,30));
		constraints.gridx=1;
		constraints.gridy=4;		
		panel.add(custEmailTF,constraints);
		
		/*custCtPersonLabel=GuiUtil.displayLabel(resources.getString("dialog.admin.label.viewCust.ctpName"));
		constraints.gridx=0;
		constraints.gridy=5;		
		panel.add(custCtPersonLabel,constraints);
		custCtPersonTF=new JTextField();
		custCtPersonTF.setEditable(false);
		custCtPersonTF.setText(customer.getContactPersonName());
		custCtPersonTF.setPreferredSize(new Dimension(150,30));
		constraints.gridx=1;
		constraints.gridy=5;		
		panel.add(custCtPersonTF,constraints);
		
		custCtPersonMobileLabel=GuiUtil.displayLabel(resources.getString("dialog.admin.label.viewCust.ctpMobile"));
		constraints.gridx=0;
		constraints.gridy=6;		
		panel.add(custCtPersonMobileLabel,constraints);
		custCtPersonMobileTF=new JTextField();
		custCtPersonMobileTF.setEditable(false);
		custCtPersonMobileTF.setText(customer.getContactPersonMobile());
		custCtPersonMobileTF.setPreferredSize(new Dimension(150,30));
		constraints.gridx=1;
		constraints.gridy=6;		
		panel.add(custCtPersonMobileTF,constraints);
		
		custCtPersonEmailLabel=GuiUtil.displayLabel(resources.getString("dialog.admin.label.viewCust.ctpEmail"));
		constraints.gridx=0;
		constraints.gridy=7;		
		panel.add(custCtPersonEmailLabel,constraints);
		custCtPersonEmailTF=new JTextField();
		custCtPersonEmailTF.setEditable(false);
		custCtPersonEmailTF.setText(customer.getContactPersonEmail());
		custCtPersonEmailTF.setPreferredSize(new Dimension(150,30));
		constraints.gridx=1;
		constraints.gridy=7;		
		panel.add(custCtPersonEmailTF,constraints);	*/
		
		panel.setBounds(50, 50, 400, 330);
		mainPanel.add(panel);		
	    
		return mainPanel;
	}
	
	
}
