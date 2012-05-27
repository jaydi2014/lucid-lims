/**
 * 
 */
package org.lims.register.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;
import org.lims.admin.service.AdminService;
import org.lims.admin.service.AdminServiceInter;
import org.lims.customer.service.CustomerService;
import org.lims.customer.service.CustomerServiceInter;
import org.lims.gui.util.GuiUtil;
import org.lims.register.gui.listeners.CustSelectComboListener;
import org.lims.util.Util;

import com.toedter.calendar.JDateChooser;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class RegisterSamplesDialog extends JDialog{

	private static final long serialVersionUID = 4868849677949476639L;
	private Logger log=Logger.getLogger(RegisterSamplesDialog.class);
	private ResourceBundle resources=Util.getResources();
	private AdminServiceInter adminService=new AdminService();
	private CustomerServiceInter custService=new CustomerService();
	
	private JLabel regNoLabel;
	private JTextField regNoTF;
	private JLabel dateLabel;
	private JDateChooser dateDC;
	private JLabel deptLabel;
	private JComboBox deptCB;
	private JLabel custNameLabel;
	private JComboBox custNameCB;
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
	private JLabel dueDateLabel;
	private JDateChooser dueDateDC;
	private JLabel dispatchDateLabel;
	private JDateChooser dispatchDateDC;
	private JLabel dispatchMethLabel;
	private JTextField dispatchMethTF;
	private JLabel totalTestingChrgsLabel;
	private JTextField totalTestingChrgsTF;
	private JLabel amountPaidLabel;
	private JTextField amountPaidTF;
	private JLabel paymentMethLabel;
	private JTextField paymentMethTF;
	private JLabel balanceLabel;
	private JTextField balanceTF;
	private JLabel specialInstrLabel;
	private JTextArea specialInstrTA;
	private JLabel samplePackingLabel;
	private JTextArea samplePackingTA;
	JTable samplesTable;
	
	
	/**
	 * This is register samples dialog.
	 * @param owner
	 * @param title
	 * @param modal
	 * @param actionCommand
	 */
	public RegisterSamplesDialog(Frame owner, String title, boolean modal,String actionCommand) {
		super(owner,title,modal);			
		getContentPane().setLayout(new BorderLayout());
		JPanel centerPanel=createCentralPanel(actionCommand);
		centerPanel.setPreferredSize(new Dimension(1000,1000));
		JScrollPane scrollPane=new JScrollPane(centerPanel);
		scrollPane.setPreferredSize(new Dimension(1000,600));
		scrollPane.setBounds(0,0,1000, 600);
		add(scrollPane,BorderLayout.CENTER);
		GuiUtil.CompCenterCords cords=GuiUtil.getCompCenterCords(1000, 600);
		setBounds(cords.getX(), cords.getY(), 1000, 600);		
		setVisible(true);
	}
	
	/**
	 * creates panel that sets in the center of the dialog.
	 * @return JPanel.
	 */
	private JPanel createCentralPanel(String actionCommand){
		JPanel panel=new JPanel();
		panel.setLayout(null);
		
		regNoLabel=GuiUtil.displayLabel(resources.getString("register.dialog.label.regNo"));
		regNoLabel.setBounds(10, 30, 130, 30);
		panel.add(regNoLabel);
		regNoTF=new JTextField();
		regNoTF.setBounds(140, 30, 150,30);
		panel.add(regNoTF);
		
		dateLabel=GuiUtil.displayLabel(resources.getString("register.dialog.label.date"));
		dateLabel.setBounds(330, 30, 50, 30);
		panel.add(dateLabel);
		dateDC=new JDateChooser(new Date(),"dd-MM-yyyy");
		dateDC.setBounds(380, 30, 130, 30);
		panel.add(dateDC);
		
		deptLabel=GuiUtil.displayLabel(resources.getString("register.dialog.label.department"));
		deptLabel.setBounds(550,30, 100, 30);		
		panel.add(deptLabel);
		Object[] depts=null;
		try{
			List<String> deptList=adminService.getDepartments();
			depts=deptList.toArray();
		}catch(Exception e){
			log.debug(e.getMessage(), e);
		}
		deptCB=new JComboBox(depts);
		deptCB.setBounds(650, 30, 150,30);
        panel.add(deptCB);
        
        JSeparator firstSep=new JSeparator();
		firstSep.setBounds(0, 80, 1000, 2);
		panel.add(firstSep);
		
		JPanel custPanel=createCustomerPanel();
		custPanel.setBounds(10, 100, 400, 400);
		panel.add(custPanel);
		
		JPanel samplesPanel=createSampleTestsPanel();
		samplesPanel.setBounds(440, 100, 500, 400);
		panel.add(samplesPanel);
		
		JSeparator secondtSep=new JSeparator();
		secondtSep.setBounds(0, 520, 1000, 2);
		panel.add(secondtSep);
		
		JPanel dispatchPanel=createDispatchPanel();
		dispatchPanel.setBounds(10, 540, 300, 100);
		panel.add(dispatchPanel);
		
		JPanel billingPanel=createBillingDetailsPanel();
		billingPanel.setBounds(360, 540, 300, 150);
		panel.add(billingPanel);
		
		JSeparator thirdSep=new JSeparator();
		thirdSep.setBounds(0, 710, 1000, 2);
		panel.add(thirdSep);
		
		specialInstrLabel=GuiUtil.displayLabel(resources.getString("register.dialog.label.specialInstructions"));
		specialInstrLabel.setBounds(10, 730, 150, 30);
		panel.add(specialInstrLabel);
		specialInstrTA=new JTextArea();
		JScrollPane scrollPane=new JScrollPane(specialInstrTA);
		scrollPane.setBounds(160, 730, 200, 100);
		panel.add(scrollPane);
		
		samplePackingLabel=GuiUtil.displayLabel(resources.getString("register.dialog.label.samplePacking"));
		samplePackingLabel.setBounds(380, 730, 200, 30);
		panel.add(samplePackingLabel);
		samplePackingTA=new JTextArea();
		JScrollPane packingScrollPane=new JScrollPane(samplePackingTA);
		packingScrollPane.setBounds(580, 730, 200,100);
		panel.add(packingScrollPane);
		
		JSeparator fourthSep=new JSeparator();
		fourthSep.setBounds(0, 850, 1000, 2);
		panel.add(fourthSep);
		if(actionCommand.equals("REG")){
			setDiabledFieldsRegistration();
			JButton registerButton=new JButton(resources.getString("register.dialog.button.register"));
			registerButton.setBounds(400, 870, 200, 40);
			panel.add(registerButton);
		}
		
		return panel;
		
	}
	
	
	
	/**
	 * creates customer panel.
	 * @return
	 */
	private JPanel createCustomerPanel(){
		
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(8,2));
		
		custNameLabel=GuiUtil.displayLabel(resources.getString("register.dialog.label.custName"));
		panel.add(custNameLabel);
		List<String> custNames=null;
		try{
			custNames=custService.getAllCustomerNames();
			
		}catch(Exception e){
			log.debug(e.getMessage(), e);
		}
		custNameCB=new JComboBox(custNames.toArray());		
		custNameCB.addActionListener(new CustSelectComboListener(this));
		panel.add(custNameCB);
		
		custAddressLabel=GuiUtil.displayLabel(resources.getString("register.dialog.label.custAddr"));
		panel.add(custAddressLabel);
		custAddressTA=new JTextArea();
		custAddressTA.setEditable(false);
		JScrollPane scrollPane=new JScrollPane(custAddressTA);
		panel.add(scrollPane);
		
		custPhoneLabel=GuiUtil.displayLabel(resources.getString("register.dialog.label.custPhone"));
		panel.add(custPhoneLabel);
		custPhoneTF=new JTextField();
		custPhoneTF.setEditable(false);
		panel.add(custPhoneTF);
		
		custFaxLabel=GuiUtil.displayLabel(resources.getString("register.dialog.label.custFax"));
		panel.add(custFaxLabel);
		custFaxTF=new JTextField();
		custFaxTF.setEditable(false);
		panel.add(custFaxTF);
		
		custEmailLabel=GuiUtil.displayLabel(resources.getString("register.dialog.label.custEmail"));
		panel.add(custEmailLabel);
		custEmailTF=new JTextField();
		custEmailTF.setEditable(false);
		panel.add(custEmailTF);
		
		custCtPersonLabel=GuiUtil.displayLabel(resources.getString("register.dialog.label.custCtPersonName"));
		panel.add(custCtPersonLabel);
		custCtPersonTF=new JTextField();
		custCtPersonTF.setEditable(false);
		panel.add(custCtPersonTF);
		
		custCtPersonMobileLabel=GuiUtil.displayLabel(resources.getString("register.dialog.label.custCtPersonMobile"));
		panel.add(custCtPersonMobileLabel);
		custCtPersonMobileTF=new JTextField();
		custCtPersonMobileTF.setEditable(false);
		panel.add(custCtPersonMobileTF);
		
		custCtPersonEmailLabel=GuiUtil.displayLabel(resources.getString("register.dialog.label.custCtPersonEmail"));
		panel.add(custCtPersonEmailLabel);
		custCtPersonEmailTF=new JTextField();
		custCtPersonEmailTF.setEditable(false);
		panel.add(custCtPersonEmailTF);		
		
		Border titledBorder=BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
				                    resources.getString("register.dialog.cust.panel.title"), 
				                    TitledBorder.LEFT, TitledBorder.TOP);
		panel.setBorder(titledBorder);
		return panel;
	}
	
	
	/**
	 * creates sample tests panel.
	 * @return JPanel.
	 */
	private JPanel createSampleTestsPanel(){
		JPanel mainPanel=new JPanel();
		mainPanel.setLayout(null);
		JPanel samplesPanel=new JPanel();
		samplesPanel.setLayout(new BorderLayout());
		//samplesPanel.setPreferredSize(new Dimension(400,800));
		//JScrollPane samplesScroll=new JScrollPane(samplesPanel);
		//samplesScroll.setPreferredSize(new Dimension(200,200));
		//samplesScroll.setBounds(20, 20, 460, 330);
		
		String col1=resources.getString("register.dialog.table.sampleName");
		String col2=resources.getString("register.dialog.table.tests");
		String col3=resources.getString("register.dialog.table.sampleQty");	
		Object[] columns={col1,col2,col3};
		final DefaultTableModel tableModel=new DefaultTableModel(columns,10);		
		samplesTable=new JTable(tableModel);
		samplesTable.setRowHeight(50);
		samplesTable.setRowSelectionAllowed(true);
		JScrollPane tableScrollPane=new JScrollPane(samplesTable);
		samplesPanel.add(tableScrollPane,BorderLayout.CENTER);
		samplesPanel.setBounds(20, 20, 460, 330);
		mainPanel.add(samplesPanel);
		
		JButton addRowButton=new JButton(resources.getString("register.dialog.button.samples.addRow"));
		addRowButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Object[] rowdata={"","",""};
				tableModel.addRow(rowdata);
			}
		});
		addRowButton.setBounds(220, 360, 100, 30);
		mainPanel.add(addRowButton);		
		
		Border titledBorder=BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
                resources.getString("register.dialog.border.samples.title"), 
                TitledBorder.LEFT, TitledBorder.TOP);
		mainPanel.setBorder(titledBorder);
		return mainPanel;
	}
	
	
	
	/**
	 * creates dispatch panel.
	 * @return JPael
	 */
	private JPanel createDispatchPanel(){
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(3,2));
		
		dueDateLabel=GuiUtil.displayLabel(resources.getString("register.dialog.label.dueDate"));
		panel.add(dueDateLabel);
		dueDateDC=new JDateChooser(new Date(),"dd-MM-yyyy");
		panel.add(dueDateDC);
		
		dispatchDateLabel=GuiUtil.displayLabel(resources.getString("register.dialog.label.dispatchDate"));
		panel.add(dispatchDateLabel);
		dispatchDateDC=new JDateChooser(new Date(),"dd-MM-yyyy");
		panel.add(dispatchDateDC);
		
		dispatchMethLabel=GuiUtil.displayLabel(resources.getString("register.dialog.label.dispatchMeth"));
		panel.add(dispatchMethLabel);
		dispatchMethTF=new JTextField();
		panel.add(dispatchMethTF);
		
		Border titledBorder=BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
                resources.getString("register.dialog.border.dispatch.title"), 
                TitledBorder.LEFT, TitledBorder.TOP);
		panel.setBorder(titledBorder);
		return panel;
	}
	
	
	
	/**
	 * creates billing details panel.
	 * @return JPanel.
	 */
	private JPanel createBillingDetailsPanel(){
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(4,2));
		
		totalTestingChrgsLabel=GuiUtil.displayLabel(resources.getString("register.dialog.label.testingChrgs"));
		panel.add(totalTestingChrgsLabel);
		totalTestingChrgsTF=new JTextField();
		panel.add(totalTestingChrgsTF);
		
		amountPaidLabel=GuiUtil.displayLabel(resources.getString("register.dialog.label.amountPaid"));
		panel.add(amountPaidLabel);
		amountPaidTF=new JTextField();
		panel.add(amountPaidTF);
		
		paymentMethLabel=GuiUtil.displayLabel(resources.getString("register.dialog.label.paymentMeth"));
		panel.add(paymentMethLabel);
		paymentMethTF=new JTextField();
		panel.add(paymentMethTF);
		
		balanceLabel=GuiUtil.displayLabel(resources.getString("register.dialog.label.moneyBalance"));
		panel.add(balanceLabel);
		balanceTF=new JTextField();
		balanceTF.setEditable(false);
		panel.add(balanceTF);
		
		Border titledBorder=BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
                resources.getString("register.dialog.border.billing.title"), 
                TitledBorder.LEFT, TitledBorder.TOP);
		panel.setBorder(titledBorder);
		return panel;
	}
	
	/**
	 * sets some fields disabled which are not required at registration time..
	 */
	private void setDiabledFieldsRegistration(){
		dispatchDateDC.setEnabled(false);
		dispatchMethTF.setEnabled(false);
	}

	/**
	 * @return the regNoLabel
	 */
	public JLabel getRegNoLabel() {
		return regNoLabel;
	}

	/**
	 * @param regNoLabel the regNoLabel to set
	 */
	public void setRegNoLabel(JLabel regNoLabel) {
		this.regNoLabel = regNoLabel;
	}

	/**
	 * @return the regNoTF
	 */
	public JTextField getRegNoTF() {
		return regNoTF;
	}

	/**
	 * @param regNoTF the regNoTF to set
	 */
	public void setRegNoTF(JTextField regNoTF) {
		this.regNoTF = regNoTF;
	}

	/**
	 * @return the dateLabel
	 */
	public JLabel getDateLabel() {
		return dateLabel;
	}

	/**
	 * @param dateLabel the dateLabel to set
	 */
	public void setDateLabel(JLabel dateLabel) {
		this.dateLabel = dateLabel;
	}

	/**
	 * @return the dateDC
	 */
	public JDateChooser getDateDC() {
		return dateDC;
	}

	/**
	 * @param dateDC the dateDC to set
	 */
	public void setDateDC(JDateChooser dateDC) {
		this.dateDC = dateDC;
	}

	/**
	 * @return the deptLabel
	 */
	public JLabel getDeptLabel() {
		return deptLabel;
	}

	/**
	 * @param deptLabel the deptLabel to set
	 */
	public void setDeptLabel(JLabel deptLabel) {
		this.deptLabel = deptLabel;
	}

	/**
	 * @return the deptCB
	 */
	public JComboBox getDeptCB() {
		return deptCB;
	}

	/**
	 * @param deptCB the deptCB to set
	 */
	public void setDeptCB(JComboBox deptCB) {
		this.deptCB = deptCB;
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
	 * @return the custNameCB
	 */
	public JComboBox getCustNameCB() {
		return custNameCB;
	}

	/**
	 * @param custNameCB the custNameCB to set
	 */
	public void setCustNameCB(JComboBox custNameCB) {
		this.custNameCB = custNameCB;
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

	/**
	 * @return the dueDateLabel
	 */
	public JLabel getDueDateLabel() {
		return dueDateLabel;
	}

	/**
	 * @param dueDateLabel the dueDateLabel to set
	 */
	public void setDueDateLabel(JLabel dueDateLabel) {
		this.dueDateLabel = dueDateLabel;
	}

	/**
	 * @return the dueDateDC
	 */
	public JDateChooser getDueDateDC() {
		return dueDateDC;
	}

	/**
	 * @param dueDateDC the dueDateDC to set
	 */
	public void setDueDateDC(JDateChooser dueDateDC) {
		this.dueDateDC = dueDateDC;
	}

	/**
	 * @return the dispatchDateLabel
	 */
	public JLabel getDispatchDateLabel() {
		return dispatchDateLabel;
	}

	/**
	 * @param dispatchDateLabel the dispatchDateLabel to set
	 */
	public void setDispatchDateLabel(JLabel dispatchDateLabel) {
		this.dispatchDateLabel = dispatchDateLabel;
	}

	/**
	 * @return the dispatchDateDC
	 */
	public JDateChooser getDispatchDateDC() {
		return dispatchDateDC;
	}

	/**
	 * @param dispatchDateDC the dispatchDateDC to set
	 */
	public void setDispatchDateDC(JDateChooser dispatchDateDC) {
		this.dispatchDateDC = dispatchDateDC;
	}

	/**
	 * @return the dispatchMethLabel
	 */
	public JLabel getDispatchMethLabel() {
		return dispatchMethLabel;
	}

	/**
	 * @param dispatchMethLabel the dispatchMethLabel to set
	 */
	public void setDispatchMethLabel(JLabel dispatchMethLabel) {
		this.dispatchMethLabel = dispatchMethLabel;
	}

	/**
	 * @return the dispatchMethTF
	 */
	public JTextField getDispatchMethTF() {
		return dispatchMethTF;
	}

	/**
	 * @param dispatchMethTF the dispatchMethTF to set
	 */
	public void setDispatchMethTF(JTextField dispatchMethTF) {
		this.dispatchMethTF = dispatchMethTF;
	}

	/**
	 * @return the totalTestingChrgsLabel
	 */
	public JLabel getTotalTestingChrgsLabel() {
		return totalTestingChrgsLabel;
	}

	/**
	 * @param totalTestingChrgsLabel the totalTestingChrgsLabel to set
	 */
	public void setTotalTestingChrgsLabel(JLabel totalTestingChrgsLabel) {
		this.totalTestingChrgsLabel = totalTestingChrgsLabel;
	}

	/**
	 * @return the totalTestingChrgsTF
	 */
	public JTextField getTotalTestingChrgsTF() {
		return totalTestingChrgsTF;
	}

	/**
	 * @param totalTestingChrgsTF the totalTestingChrgsTF to set
	 */
	public void setTotalTestingChrgsTF(JTextField totalTestingChrgsTF) {
		this.totalTestingChrgsTF = totalTestingChrgsTF;
	}

	/**
	 * @return the amountPaidLabel
	 */
	public JLabel getAmountPaidLabel() {
		return amountPaidLabel;
	}

	/**
	 * @param amountPaidLabel the amountPaidLabel to set
	 */
	public void setAmountPaidLabel(JLabel amountPaidLabel) {
		this.amountPaidLabel = amountPaidLabel;
	}

	/**
	 * @return the amountPaidTF
	 */
	public JTextField getAmountPaidTF() {
		return amountPaidTF;
	}

	/**
	 * @param amountPaidTF the amountPaidTF to set
	 */
	public void setAmountPaidTF(JTextField amountPaidTF) {
		this.amountPaidTF = amountPaidTF;
	}

	/**
	 * @return the paymentMethLabel
	 */
	public JLabel getPaymentMethLabel() {
		return paymentMethLabel;
	}

	/**
	 * @param paymentMethLabel the paymentMethLabel to set
	 */
	public void setPaymentMethLabel(JLabel paymentMethLabel) {
		this.paymentMethLabel = paymentMethLabel;
	}

	/**
	 * @return the paymentMethTF
	 */
	public JTextField getPaymentMethTF() {
		return paymentMethTF;
	}

	/**
	 * @param paymentMethTF the paymentMethTF to set
	 */
	public void setPaymentMethTF(JTextField paymentMethTF) {
		this.paymentMethTF = paymentMethTF;
	}

	/**
	 * @return the balanceLabel
	 */
	public JLabel getBalanceLabel() {
		return balanceLabel;
	}

	/**
	 * @param balanceLabel the balanceLabel to set
	 */
	public void setBalanceLabel(JLabel balanceLabel) {
		this.balanceLabel = balanceLabel;
	}

	/**
	 * @return the balanceTF
	 */
	public JTextField getBalanceTF() {
		return balanceTF;
	}

	/**
	 * @param balanceTF the balanceTF to set
	 */
	public void setBalanceTF(JTextField balanceTF) {
		this.balanceTF = balanceTF;
	}

	/**
	 * @return the specialInstrLabel
	 */
	public JLabel getSpecialInstrLabel() {
		return specialInstrLabel;
	}

	/**
	 * @param specialInstrLabel the specialInstrLabel to set
	 */
	public void setSpecialInstrLabel(JLabel specialInstrLabel) {
		this.specialInstrLabel = specialInstrLabel;
	}

	/**
	 * @return the specialInstrTA
	 */
	public JTextArea getSpecialInstrTA() {
		return specialInstrTA;
	}

	/**
	 * @param specialInstrTA the specialInstrTA to set
	 */
	public void setSpecialInstrTA(JTextArea specialInstrTA) {
		this.specialInstrTA = specialInstrTA;
	}

	/**
	 * @return the samplePackingLabel
	 */
	public JLabel getSamplePackingLabel() {
		return samplePackingLabel;
	}

	/**
	 * @param samplePackingLabel the samplePackingLabel to set
	 */
	public void setSamplePackingLabel(JLabel samplePackingLabel) {
		this.samplePackingLabel = samplePackingLabel;
	}

	/**
	 * @return the samplePackingTA
	 */
	public JTextArea getSamplePackingTA() {
		return samplePackingTA;
	}

	/**
	 * @param samplePackingTA the samplePackingTA to set
	 */
	public void setSamplePackingTA(JTextArea samplePackingTA) {
		this.samplePackingTA = samplePackingTA;
	}

	/**
	 * @return the samplesTable
	 */
	public JTable getSamplesTable() {
		return samplesTable;
	}

	/**
	 * @param samplesTable the samplesTable to set
	 */
	public void setSamplesTable(JTable samplesTable) {
		this.samplesTable = samplesTable;
	}

}
