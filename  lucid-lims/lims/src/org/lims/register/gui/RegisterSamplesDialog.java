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

import org.lims.admin.service.AdminService;
import org.lims.admin.service.AdminServiceInter;
import org.lims.gui.util.GuiUtil;
import org.lims.util.Util;

import com.toedter.calendar.JDateChooser;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class RegisterSamplesDialog extends JDialog{

	private static final long serialVersionUID = 4868849677949476639L;
	private ResourceBundle resources=Util.getResources();
	private AdminServiceInter adminService=new AdminService();
	
	private JLabel regNoLabel;
	private JTextField regNoTF;
	private JLabel dateLabel;
	private JDateChooser dateDC;
	private JLabel deptLabel;
	private JComboBox deptCB;
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
	 */
	public RegisterSamplesDialog(Frame owner, String title, boolean modal,String actionCommand) {
		super(owner,title,modal);		
		getContentPane().setLayout(new BorderLayout());
		JPanel centerPanel=createCentralPanel();
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
	private JPanel createCentralPanel(){
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
			e.printStackTrace();
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
		
		JButton registerButton=new JButton(resources.getString("register.dialog.button.register"));
		registerButton.setBounds(400, 870, 200, 40);
		panel.add(registerButton);
		
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
		custNameTF=new JTextField();
		panel.add(custNameTF);
		
		custAddressLabel=GuiUtil.displayLabel(resources.getString("register.dialog.label.custAddr"));
		panel.add(custAddressLabel);
		custAddressTA=new JTextArea();
		JScrollPane scrollPane=new JScrollPane(custAddressTA);
		panel.add(scrollPane);
		
		custPhoneLabel=GuiUtil.displayLabel(resources.getString("register.dialog.label.custPhone"));
		panel.add(custPhoneLabel);
		custPhoneTF=new JTextField();
		panel.add(custPhoneTF);
		
		custFaxLabel=GuiUtil.displayLabel(resources.getString("register.dialog.label.custFax"));
		panel.add(custFaxLabel);
		custFaxTF=new JTextField();
		panel.add(custFaxTF);
		
		custEmailLabel=GuiUtil.displayLabel(resources.getString("register.dialog.label.custEmail"));
		panel.add(custEmailLabel);
		custEmailTF=new JTextField();
		panel.add(custEmailTF);
		
		custCtPersonLabel=GuiUtil.displayLabel(resources.getString("register.dialog.label.custCtPersonName"));
		panel.add(custCtPersonLabel);
		custCtPersonTF=new JTextField();
		panel.add(custCtPersonTF);
		
		custCtPersonMobileLabel=GuiUtil.displayLabel(resources.getString("register.dialog.label.custCtPersonMobile"));
		panel.add(custCtPersonMobileLabel);
		custCtPersonMobileTF=new JTextField();
		panel.add(custCtPersonMobileTF);
		
		custCtPersonEmailLabel=GuiUtil.displayLabel(resources.getString("register.dialog.label.custCtPersonEmail"));
		panel.add(custCtPersonEmailLabel);
		custCtPersonEmailTF=new JTextField();
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
		panel.add(balanceTF);
		
		Border titledBorder=BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
                resources.getString("register.dialog.border.billing.title"), 
                TitledBorder.LEFT, TitledBorder.TOP);
		panel.setBorder(titledBorder);
		return panel;
	}

}
