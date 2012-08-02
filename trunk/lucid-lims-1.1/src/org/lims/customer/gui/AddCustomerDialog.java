/**
 * 
 */
package org.lims.customer.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.lims.customer.gui.listeners.AddCustomerButtonListener;
import org.lims.gui.util.GuiUtil;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class AddCustomerDialog extends JDialog{

	private static final long serialVersionUID = 1111053748734590168L;
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
	
	/**
	 * creates add customer dialog object.
	 * @param owner
	 * @param title
	 * @param modal
	 */
	public AddCustomerDialog(JDialog owner, String title, boolean modal) {
		super(owner,title,modal);		
		getContentPane().setLayout(new BorderLayout());
		JPanel centerPanel=createCustomerPanel();
		add(centerPanel,BorderLayout.CENTER);
		GuiUtil.CompCenterCords cords=GuiUtil.getCompCenterCords(500, 600);
		setBounds(cords.getX(), cords.getY(), 500, 600);	
		setVisible(true);		
	}
	
	
	/**
	 * creates customer panel.
	 * @return
	 */
	private JPanel createCustomerPanel(){
		JPanel mainPanel=new JPanel();
		mainPanel.setLayout(null);
		
		GridBagLayout gbl=new GridBagLayout();
		JPanel panel=new JPanel(gbl);
		GridBagConstraints constraints = new GridBagConstraints();
		
		
		custNameLabel=GuiUtil.displayLabel(resources.getString("customer.dialog.label.custName"));
		constraints.gridx=0;
		constraints.gridy=0;
		constraints.weightx=0.5;
		constraints.weighty=0.5;
		constraints.anchor=GridBagConstraints.NORTHWEST;
		panel.add(custNameLabel,constraints);
		custNameTF=new JTextField();
		custNameTF.setPreferredSize(new Dimension(150,30));
		constraints.gridx=1;
		constraints.gridy=0;
		
		constraints.anchor=GridBagConstraints.NORTHWEST;
		panel.add(custNameTF,constraints);
		
		custAddressLabel=GuiUtil.displayLabel(resources.getString("customer.dialog.label.custAddr"));
		constraints.gridx=0;
		constraints.gridy=1;
		
		panel.add(custAddressLabel,constraints);
		custAddressTA=new JTextArea();
		JScrollPane scrollPane=new JScrollPane(custAddressTA);
		scrollPane.setPreferredSize(new Dimension(150,100));
		constraints.gridx=1;
		constraints.gridy=1;
		
		panel.add(scrollPane,constraints);
		
		custPhoneLabel=GuiUtil.displayLabel(resources.getString("customer.dialog.label.custPhone"));
		constraints.gridx=0;
		constraints.gridy=2;
		constraints.weightx=0.5;
		panel.add(custPhoneLabel,constraints);
		custPhoneTF=new JTextField();
		custPhoneTF.setPreferredSize(new Dimension(150,30));
		constraints.gridx=1;
		constraints.gridy=2;
		constraints.weightx=0.5;
		panel.add(custPhoneTF,constraints);
		
		custFaxLabel=GuiUtil.displayLabel(resources.getString("customer.dialog.label.custFax"));
		constraints.gridx=0;
		constraints.gridy=3;		
		panel.add(custFaxLabel,constraints);
		custFaxTF=new JTextField();
		custFaxTF.setPreferredSize(new Dimension(150,30));
		constraints.gridx=1;
		constraints.gridy=3;		
		panel.add(custFaxTF,constraints);
		
		custEmailLabel=GuiUtil.displayLabel(resources.getString("customer.dialog.label.custEmail"));
		constraints.gridx=0;
		constraints.gridy=4;		
		panel.add(custEmailLabel,constraints);
		custEmailTF=new JTextField();
		custEmailTF.setPreferredSize(new Dimension(150,30));
		constraints.gridx=1;
		constraints.gridy=4;		
		panel.add(custEmailTF,constraints);
		
		custCtPersonLabel=GuiUtil.displayLabel(resources.getString("customer.dialog.label.custCtPersonName"));
		constraints.gridx=0;
		constraints.gridy=5;		
		panel.add(custCtPersonLabel,constraints);
		custCtPersonTF=new JTextField();
		custCtPersonTF.setPreferredSize(new Dimension(150,30));
		constraints.gridx=1;
		constraints.gridy=5;		
		panel.add(custCtPersonTF,constraints);
		
		custCtPersonMobileLabel=GuiUtil.displayLabel(resources.getString("customer.dialog.label.custCtPersonMobile"));
		constraints.gridx=0;
		constraints.gridy=6;		
		panel.add(custCtPersonMobileLabel,constraints);
		custCtPersonMobileTF=new JTextField();
		custCtPersonMobileTF.setPreferredSize(new Dimension(150,30));
		constraints.gridx=1;
		constraints.gridy=6;		
		panel.add(custCtPersonMobileTF,constraints);
		
		custCtPersonEmailLabel=GuiUtil.displayLabel(resources.getString("customer.dialog.label.custCtPersonEmail"));
		constraints.gridx=0;
		constraints.gridy=7;		
		panel.add(custCtPersonEmailLabel,constraints);
		custCtPersonEmailTF=new JTextField();
		custCtPersonEmailTF.setPreferredSize(new Dimension(150,30));
		constraints.gridx=1;
		constraints.gridy=7;		
		panel.add(custCtPersonEmailTF,constraints);	
		
		panel.setBounds(50, 50, 400, 330);
		mainPanel.add(panel);
		
		JButton addCustButton=new JButton(resources.getString("Customer.dialog.button.custAdd"));
		addCustButton.addActionListener(new AddCustomerButtonListener(this));
		addCustButton.setBounds(220, 400, 80, 30);
		mainPanel.add(addCustButton);
		
	    
		return mainPanel;
	}


	/**
	 * @return the custNameLabel
	 */
	public JLabel getCustNameLabel() {
		return custNameLabel;
	}


	/**
	 * @param custNameLabel the custNameLabel to set
	 */
	public void setCustNameLabel(JLabel custNameLabel) {
		this.custNameLabel = custNameLabel;
	}


	/**
	 * @return the custNameTF
	 */
	public JTextField getCustNameTF() {
		return custNameTF;
	}


	/**
	 * @param custNameTF the custNameTF to set
	 */
	public void setCustNameTF(JTextField custNameTF) {
		this.custNameTF = custNameTF;
	}


	/**
	 * @return the custAddressLabel
	 */
	public JLabel getCustAddressLabel() {
		return custAddressLabel;
	}


	/**
	 * @param custAddressLabel the custAddressLabel to set
	 */
	public void setCustAddressLabel(JLabel custAddressLabel) {
		this.custAddressLabel = custAddressLabel;
	}


	/**
	 * @return the custAddressTA
	 */
	public JTextArea getCustAddressTA() {
		return custAddressTA;
	}


	/**
	 * @param custAddressTA the custAddressTA to set
	 */
	public void setCustAddressTA(JTextArea custAddressTA) {
		this.custAddressTA = custAddressTA;
	}


	/**
	 * @return the custPhoneLabel
	 */
	public JLabel getCustPhoneLabel() {
		return custPhoneLabel;
	}


	/**
	 * @param custPhoneLabel the custPhoneLabel to set
	 */
	public void setCustPhoneLabel(JLabel custPhoneLabel) {
		this.custPhoneLabel = custPhoneLabel;
	}


	/**
	 * @return the custPhoneTF
	 */
	public JTextField getCustPhoneTF() {
		return custPhoneTF;
	}


	/**
	 * @param custPhoneTF the custPhoneTF to set
	 */
	public void setCustPhoneTF(JTextField custPhoneTF) {
		this.custPhoneTF = custPhoneTF;
	}


	/**
	 * @return the custFaxLabel
	 */
	public JLabel getCustFaxLabel() {
		return custFaxLabel;
	}


	/**
	 * @param custFaxLabel the custFaxLabel to set
	 */
	public void setCustFaxLabel(JLabel custFaxLabel) {
		this.custFaxLabel = custFaxLabel;
	}


	/**
	 * @return the custFaxTF
	 */
	public JTextField getCustFaxTF() {
		return custFaxTF;
	}


	/**
	 * @param custFaxTF the custFaxTF to set
	 */
	public void setCustFaxTF(JTextField custFaxTF) {
		this.custFaxTF = custFaxTF;
	}


	/**
	 * @return the custEmailLabel
	 */
	public JLabel getCustEmailLabel() {
		return custEmailLabel;
	}


	/**
	 * @param custEmailLabel the custEmailLabel to set
	 */
	public void setCustEmailLabel(JLabel custEmailLabel) {
		this.custEmailLabel = custEmailLabel;
	}


	/**
	 * @return the custEmailTF
	 */
	public JTextField getCustEmailTF() {
		return custEmailTF;
	}


	/**
	 * @param custEmailTF the custEmailTF to set
	 */
	public void setCustEmailTF(JTextField custEmailTF) {
		this.custEmailTF = custEmailTF;
	}


	/**
	 * @return the custCtPersonLabel
	 */
	public JLabel getCustCtPersonLabel() {
		return custCtPersonLabel;
	}


	/**
	 * @param custCtPersonLabel the custCtPersonLabel to set
	 */
	public void setCustCtPersonLabel(JLabel custCtPersonLabel) {
		this.custCtPersonLabel = custCtPersonLabel;
	}


	/**
	 * @return the custCtPersonTF
	 */
	public JTextField getCustCtPersonTF() {
		return custCtPersonTF;
	}


	/**
	 * @param custCtPersonTF the custCtPersonTF to set
	 */
	public void setCustCtPersonTF(JTextField custCtPersonTF) {
		this.custCtPersonTF = custCtPersonTF;
	}


	/**
	 * @return the custCtPersonMobileLabel
	 */
	public JLabel getCustCtPersonMobileLabel() {
		return custCtPersonMobileLabel;
	}


	/**
	 * @param custCtPersonMobileLabel the custCtPersonMobileLabel to set
	 */
	public void setCustCtPersonMobileLabel(JLabel custCtPersonMobileLabel) {
		this.custCtPersonMobileLabel = custCtPersonMobileLabel;
	}


	/**
	 * @return the custCtPersonMobileTF
	 */
	public JTextField getCustCtPersonMobileTF() {
		return custCtPersonMobileTF;
	}


	/**
	 * @param custCtPersonMobileTF the custCtPersonMobileTF to set
	 */
	public void setCustCtPersonMobileTF(JTextField custCtPersonMobileTF) {
		this.custCtPersonMobileTF = custCtPersonMobileTF;
	}


	/**
	 * @return the custCtPersonEmailLabel
	 */
	public JLabel getCustCtPersonEmailLabel() {
		return custCtPersonEmailLabel;
	}


	/**
	 * @param custCtPersonEmailLabel the custCtPersonEmailLabel to set
	 */
	public void setCustCtPersonEmailLabel(JLabel custCtPersonEmailLabel) {
		this.custCtPersonEmailLabel = custCtPersonEmailLabel;
	}


	/**
	 * @return the custCtPersonEmailTF
	 */
	public JTextField getCustCtPersonEmailTF() {
		return custCtPersonEmailTF;
	}


	/**
	 * @param custCtPersonEmailTF the custCtPersonEmailTF to set
	 */
	public void setCustCtPersonEmailTF(JTextField custCtPersonEmailTF) {
		this.custCtPersonEmailTF = custCtPersonEmailTF;
	}
}

