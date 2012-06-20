/**
 * 
 */
package org.lims.register.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
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
import org.lims.customer.gui.AddCtPersonDialog;
import org.lims.customer.gui.AddCustomerDialog;
import org.lims.customer.gui.SelectCtPersonDialog;
import org.lims.customer.gui.SelectCustDialog;
import org.lims.gui.util.GuiUtil;
import org.lims.register.dto.SampleCollectionMethodDto;
import org.lims.register.dto.SampleDto;
import org.lims.register.dto.TestRegisterDto;
import org.lims.register.service.RegisterService;
import org.lims.register.service.RegisterServiceInter;
import org.lims.util.Constants;
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
	private RegisterServiceInter regService=new RegisterService();
	public static String dispatchRegNum;
	
	private JLabel regNoLabel;
	private JTextField regNoTF;
	private JLabel dateLabel;
	private JDateChooser dateDC;
	private JLabel deptLabel;
	private JComboBox deptCB;	
	private JLabel custAddressLabel;
	private JTextArea custAddressTA;
	private JLabel custPhoneLabel;
	private JTextField custPhoneTF;
	private JLabel custFaxLabel;
	private JTextField custFaxTF;
	private JLabel custEmailLabel;
	private JTextField custEmailTF;	
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
	private JTable samplesTable;
	private JLabel timeLabel;
	private JDateChooser timeDC;
	private JLabel custLabel;
	private JTextField custTF;
	private JLabel ctPersonLabel;
	private JTextField ctPersonTF;
	private RegisterSamplesDialog rsd;
	private JComboBox scMethodsCB;
	private JLabel labDueDateLabel;
	private JDateChooser labDueDateDC;
	private JLabel crNumberLabel;
	private JTextField crNumberTF;
	private JLabel crDateLabel;
	private JDateChooser crDC;
	private JTextField crFileTF;
	private JButton browseB;
	private JButton displayB;
	
	/**
	 * This is register samples dialog.
	 * @param owner
	 * @param title
	 * @param modal
	 * @param actionCommand
	 */
	public RegisterSamplesDialog(Frame owner, String title, boolean modal,String actionCommand) {
		super(owner,title,modal);	
		rsd=this;
		getContentPane().setLayout(new BorderLayout());
		JPanel centerPanel=createCentralPanel(actionCommand);
		centerPanel.setPreferredSize(new Dimension(1000,1500));
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
		dateDC=new JDateChooser(new Date(),Constants.DATE_PATTERN);
		dateDC.setBounds(380, 30, 130, 30);
		panel.add(dateDC);
		
		timeLabel=GuiUtil.displayLabel(resources.getString("register.dialog.label.time"));
		timeLabel.setBounds(550,30, 50, 30);
		panel.add(timeLabel);
		timeDC=new JDateChooser(new Date(),Constants.TIME_PATTERN);
		timeDC.setEnabled(false);
		timeDC.setBounds(600, 30, 150,30);
		panel.add(timeDC);
		/*deptLabel=GuiUtil.displayLabel(resources.getString("register.dialog.label.department"));
		deptLabel.setBounds(550,30, 100, 30);		
		panel.add(deptLabel);
		if(actionCommand.equals("REG")){
			Object[] depts=null;
			try{
				List<String> deptList=adminService.getDepartments();
				depts=deptList.toArray();
			}catch(Exception e){
				log.debug(e.getMessage(), e);
			}
			deptCB=new JComboBox(depts);
		}else{
			deptCB=new JComboBox();
		}
		deptCB.setBounds(650, 30, 150,30);
        panel.add(deptCB);*/
        
        JSeparator firstSep=new JSeparator();
		firstSep.setBounds(0, 80, 1000, 2);
		panel.add(firstSep);
		
		JPanel custPanel=createCustomerPanel(actionCommand);
		custPanel.setBounds(10, 100, 900, 250);
		panel.add(custPanel);		
		
		JSeparator secondtSep=new JSeparator();
		secondtSep.setBounds(0, 360, 1000, 2);
		panel.add(secondtSep);
		
		JPanel samplesPanel=createSampleTestsPanel();
		samplesPanel.setBounds(10, 370, 950, 400);
		panel.add(samplesPanel);
		
		JSeparator thirdSep=new JSeparator();
		thirdSep.setBounds(0, 780, 1000, 2);
		panel.add(thirdSep);
		
		JLabel collectedByLabel=GuiUtil.displayLabel(resources.getString("register.dialog.label.collectedby"));
		collectedByLabel.setBounds(10, 790, 150, 30);
		panel.add(collectedByLabel);
		Object[] scMethods=null;
		try{
			 List<SampleCollectionMethodDto> scMethodsList=regService.getSampleCollectionMethods();
			 List<String> methodNames=new ArrayList<String>();
			 for(SampleCollectionMethodDto method:scMethodsList){
				 methodNames.add(method.getMethodName());
			 }
			 scMethods=methodNames.toArray();
		}catch(Exception e){
			log.debug(e.getMessage(), e);
		}
		scMethodsCB=new JComboBox(scMethods);
		scMethodsCB.setBounds(160, 790, 150, 30);
		panel.add(scMethodsCB);
		
		JPanel dispatchPanel=createDispatchPanel();
		dispatchPanel.setBounds(410, 790, 300, 150);
		panel.add(dispatchPanel);
		
		JSeparator fourthSep=new JSeparator();
		fourthSep.setBounds(0, 950, 1000, 2);
		panel.add(fourthSep);
		
		JPanel custRefPanel=createCustRefPanel();
		custRefPanel.setBounds(10,960,950,70);
		panel.add(custRefPanel);
		
		/*JPanel billingPanel=createBillingDetailsPanel();
		billingPanel.setBounds(360, 540, 300, 150);
		panel.add(billingPanel);*/
		
		/*JSeparator thirdSep=new JSeparator();
		thirdSep.setBounds(0, 710, 1000, 2);
		panel.add(thirdSep);*/
		
		/*specialInstrLabel=GuiUtil.displayLabel(resources.getString("register.dialog.label.specialInstructions"));
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
		panel.add(packingScrollPane);*/
		
		
		/*if(actionCommand.equals("REG")){
			setDiabledFieldsRegistration();
			JButton registerButton=new JButton(resources.getString("register.dialog.button.register"));
			registerButton.addActionListener(new RegisterSamplesButtonListener(this));
			registerButton.setBounds(400, 870, 200, 40);
			panel.add(registerButton);
		}
		
		if(actionCommand.equals("DISPATCH")){
			try{
				TestRegisterDto registerDto=regService.getRegisterEntry(dispatchRegNum);
				setFieldsDispatch(registerDto);
				JButton updateDisBill=new JButton(resources.getString("register.dialog.button.dispatch.updateDisBill"));
				updateDisBill.addActionListener(new BillindDispatchButtonListener(this));
				updateDisBill.setBounds(300, 870, 300, 40);
				panel.add(updateDisBill);
				if(registerDto.getDispatchLock() && registerDto.getBillingLocked())
					updateDisBill.setEnabled(false);
			}catch(Exception e){
				log.debug(e.getMessage(), e);
			}			
		}*/
		
		return panel;
		
	}
	
	
	
	
	/**
	 * creates customer panel.
	 * @return
	 */
	private JPanel createCustomerPanel(String actionCommand){
		JPanel finalPanel=new JPanel();
		finalPanel.setLayout(null);
		GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        c.weightx=0.5;        
		c.weighty=0.5;
		c.anchor=GridBagConstraints.NORTHWEST;
		JPanel custPanel=new JPanel(gridbag);
		
		custLabel=new JLabel(resources.getString("register.dialog.label.custNameShort"));
		custPanel.add(custLabel,c);
		custTF=new JTextField();
		custTF.setPreferredSize(new Dimension(150,30));
		custPanel.add(custTF,c);		
		JButton selectCustB=new JButton(resources.getString("register.dialog.label.custName"));
		selectCustB.setToolTipText(resources.getString("register.dialog.tooltip.selectCustomer"));
		selectCustB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				SelectCustDialog scd=new SelectCustDialog(rsd,
						resources.getString("register.dialog.selectCust.title"),
						true);
			}
		});
		selectCustB.setPreferredSize(new Dimension(100,30));
		custPanel.add(selectCustB,c);
		JButton addCustB=new JButton(resources.getString("register.dialog.button.addcust"));
		addCustB.setToolTipText(resources.getString("register.dialog.tooltip.addCustomer"));
		addCustB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddCustomerDialog acd=new AddCustomerDialog(rsd,
						      resources.getString("customer.dialog.menuitem.addCust"),
						       true );
			}
		});
		addCustB.setPreferredSize(new Dimension(70,30));
		custPanel.add(addCustB,c);
		
		ctPersonLabel=new JLabel(resources.getString("register.dialog.label.custCtPersonName"));
		custPanel.add(ctPersonLabel,c);
		ctPersonTF=new JTextField();
		ctPersonTF.setPreferredSize(new Dimension(150,30));
	    custPanel.add(ctPersonTF,c);
	   
	    JButton selectCTPB=new JButton(resources.getString("register.dialog.button.selectCustCtPerson"));
	    selectCTPB.setToolTipText(resources.getString("register.dialog.tooltip.selectCtPerson"));
	    selectCTPB.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent event){
	    		SelectCtPersonDialog sctpd=new SelectCtPersonDialog(rsd,
	    				resources.getString("register.dialog.addCtPerson.title"),
	    				true);
	    	}
	    });
	    selectCTPB.setPreferredSize(new Dimension(100,30));
		custPanel.add(selectCTPB,c);
		
		 c.gridwidth = GridBagConstraints.REMAINDER;
		JButton addCustCtpB=new JButton(resources.getString("register.dialog.button.addcustCtp"));
		addCustCtpB.setToolTipText(resources.getString("register.dialog.tooltip.addCustCtp"));
		addCustCtpB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddCtPersonDialog addCtpDialog=new AddCtPersonDialog(rsd,
						resources.getString("register.dialog.addContactPerson.title"),
						true);
			}
		});
		addCustCtpB.setPreferredSize(new Dimension(70,30));
		custPanel.add(addCustCtpB,c);		
		custPanel.setBounds(0, 0, 900, 40);
		finalPanel.add(custPanel);
		
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(4,2));		
		
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
		
		Border titledBorder=BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
				                    resources.getString("register.dialog.cust.panel.title"), 
				                    TitledBorder.LEFT, TitledBorder.TOP);
		panel.setBorder(titledBorder);
		panel.setBounds(0,50, 300, 200);
		finalPanel.add(panel);
		
		JPanel ctPersonPanel=new JPanel(new GridLayout(2,2));		
		custCtPersonMobileLabel=GuiUtil.displayLabel(resources.getString("register.dialog.label.custCtPersonMobile"));
		ctPersonPanel.add(custCtPersonMobileLabel);
		custCtPersonMobileTF=new JTextField();
		custCtPersonMobileTF.setEditable(false);
		ctPersonPanel.add(custCtPersonMobileTF);
		
		custCtPersonEmailLabel=GuiUtil.displayLabel(resources.getString("register.dialog.label.custCtPersonEmail"));
		ctPersonPanel.add(custCtPersonEmailLabel);
		custCtPersonEmailTF=new JTextField();
		custCtPersonEmailTF.setEditable(false);
		ctPersonPanel.add(custCtPersonEmailTF);	
		
		Border ctptitledBorder=BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
                resources.getString("register.dialog.ctp.panel.title"), 
                TitledBorder.LEFT, TitledBorder.TOP);
		ctPersonPanel.setBorder(ctptitledBorder);
		ctPersonPanel.setBounds(480,50, 300, 100);
		finalPanel.add(ctPersonPanel);
		
		return finalPanel;
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
		String col1=resources.getString("register.dialog.table.sno");
		String col2=resources.getString("register.dialog.table.sampleName");
		String col3=resources.getString("register.dialog.table.sampleQty");
		String col4=resources.getString("register.dialog.table.batch");
		String col5=resources.getString("register.dialog.table.tests");
		String col6=resources.getString("register.dialog.table.spec");
		String col7=resources.getString("register.dialog.table.testmethod");
		Object[] columns={col1,col2,col3,col4,col5,col6,col7};
		final DefaultTableModel tableModel=new DefaultTableModel(columns,0);		
		samplesTable=new JTable(tableModel);
		samplesTable.setRowHeight(50);
		samplesTable.setRowSelectionAllowed(true);
		JScrollPane tableScrollPane=new JScrollPane(samplesTable);
		samplesPanel.add(tableScrollPane,BorderLayout.CENTER);
		samplesPanel.setBounds(20, 20, 910, 330);
		mainPanel.add(samplesPanel);
		
		JButton addRowButton=new JButton(resources.getString("register.dialog.button.samples.addRow"));
		addRowButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Object[] rowdata={"","","","","","",""};
				tableModel.addRow(rowdata);
			}
		});
		addRowButton.setBounds(400, 360, 100, 30);
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
		panel.setLayout(new GridLayout(4,2));
		
		dueDateLabel=GuiUtil.displayLabel(resources.getString("register.dialog.label.dueDate"));
		panel.add(dueDateLabel);
		dueDateDC=new JDateChooser(null,Constants.DATE_PATTERN);
		panel.add(dueDateDC);
		
		labDueDateLabel=GuiUtil.displayLabel(resources.getString("register.dialog.label.labDueDate"));
		panel.add(labDueDateLabel);
		labDueDateDC=new JDateChooser(null,Constants.DATE_PATTERN);
		panel.add(labDueDateDC);
		
		dispatchDateLabel=GuiUtil.displayLabel(resources.getString("register.dialog.label.dispatchDate"));
		panel.add(dispatchDateLabel);
		dispatchDateDC=new JDateChooser(null,Constants.DATE_PATTERN);
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
	 * Creates customer reference panel.
	 * @return JPanel
	 */
	private JPanel createCustRefPanel(){
		JPanel custRefPanel=new JPanel();
		custRefPanel.setLayout(null);
		
		crNumberLabel=GuiUtil.displayLabel(resources.getString("register.dialog.label.crNumber"));
		crNumberLabel.setBounds(10, 20, 100, 30);
		custRefPanel.add(crNumberLabel);
		crNumberTF=new JTextField();
		crNumberTF.setBounds(100,20, 150, 30);
		custRefPanel.add(crNumberTF);
		
		crDateLabel=GuiUtil.displayLabel(resources.getString("register.dialog.label.crDate"));
		crDateLabel.setBounds(260,20,50,30);
		custRefPanel.add(crDateLabel);
		crDC=new JDateChooser(null,Constants.DATE_PATTERN);
		crDC.setBounds(310, 20, 100, 30);
		custRefPanel.add(crDC);
		
		crFileTF=new JTextField();
		crFileTF.setBounds(430, 20, 150, 30);
		custRefPanel.add(crFileTF);
		browseB=new JButton(resources.getString("register.dialog.button.browse"));
		browseB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				JFileChooser chooser = new JFileChooser();			    
			    int returnVal = chooser.showOpenDialog(rsd);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	crFileTF.setText(chooser.getSelectedFile().getAbsolutePath());			       
			    }
			}
		});
		browseB.setBounds(580, 20, 100, 30);
		custRefPanel.add(browseB);
		
		displayB=new JButton(resources.getString("register.dialog.button.display"));
		displayB.setBounds(700,20, 100, 30);
	    custRefPanel.add(displayB);
		
		Border titledBorder=BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
                resources.getString("register.dialog.border.customerRef.title"), 
                TitledBorder.LEFT, TitledBorder.TOP);
		custRefPanel.setBorder(titledBorder);
		return custRefPanel;
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
		amountPaidTF.addFocusListener(new FocusAdapter(){
			public void focusLost(FocusEvent e){
				if(((JTextField)e.getSource()).getText().isEmpty()){
					balanceTF.setText(new Double("0").toString());
				}
				if(!totalTestingChrgsTF.getText().isEmpty() && !((JTextField)e.getSource()).getText().isEmpty()){
					try{
						Double totalTestChrgs=new Double(totalTestingChrgsTF.getText());
						Double amountPaid=new Double(((JTextField)e.getSource()).getText());
						Double balance=totalTestChrgs-amountPaid;
						balanceTF.setText(balance.toString());
					}catch(Exception ex){
						log.debug(ex.getMessage(), ex);
					}
				}
			}
		});
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
		
	}
	
	/**
	 * Sets the non editable fields which are not required for Dispatch.
	 */
	private void setFieldsDispatch(TestRegisterDto registerDto){
		regNoTF.setEditable(false);
		regNoTF.setText(registerDto.getRegNumber());
		totalTestingChrgsTF.setEditable(false);
		amountPaidTF.setText(registerDto.getAmountPaid());
		paymentMethTF.setText(registerDto.getPaymentMeth());
		balanceTF.setText(registerDto.getBalance());
		totalTestingChrgsTF.setText(registerDto.getTotalTestingChrgs());
		dateDC.setEnabled(false);
		try{
			dateDC.setDate(Util.convertStringToDate(registerDto.getDate(), Constants.DATE_PATTERN));
		}catch(Exception e){
			log.debug(e.getMessage(),e);
		}
		deptCB.setEnabled(false);
		deptCB.addItem(registerDto.getDepartment().getDeptName());		
		custAddressTA.setText(registerDto.getCustomer().getAddress());
		custPhoneTF.setText(registerDto.getCustomer().getPhoneNumber());
		custFaxTF.setText(registerDto.getCustomer().getFaxNumber());
		custEmailTF.setText(registerDto.getCustomer().getEmail());		
		/*custCtPersonMobileTF.setText(registerDto.getCustomer().getContactPersonMobile());
		custCtPersonEmailTF.setText(registerDto.getCustomer().getContactPersonEmail());*/
		dueDateDC.setEnabled(false);
		try{
			dueDateDC.setDate(Util.convertStringToDate(registerDto.getDueDate(), Constants.DATE_PATTERN));
		}catch(Exception e){
			log.debug(e.getMessage(),e);
		}
		dispatchMethTF.setEnabled(false);
		dispatchMethTF.setText(registerDto.getDispatchMethod());
		specialInstrTA.setEditable(false);
		specialInstrTA.setText(registerDto.getSpecialInstrs());
		samplePackingTA.setEditable(false);
		samplePackingTA.setText(registerDto.getPacking());
		samplesTable.setEnabled(false);
		DefaultTableModel model=(DefaultTableModel)samplesTable.getModel();
		List<SampleDto> samples=registerDto.getSamplesList();
		for(SampleDto sample:samples){
			Object[] array=new Object[3];
			array[0]=sample.getSampleName();
			array[1]=sample.getSampleTests();
			array[2]=sample.getSampleQty();
			model.addRow(array);
		}
		if(registerDto.getDispatchLock()){
			dispatchDateDC.setEnabled(false);
			try{
				dispatchDateDC.setDate(Util.convertStringToDate(registerDto.getDispatchDate(), Constants.DATE_PATTERN));
			}catch(Exception e){
				log.debug(e.getMessage(),e);
			}			
		}
		
		if(registerDto.getBillingLocked()){
			amountPaidTF.setEditable(false);
			amountPaidTF.setText(registerDto.getAmountPaid());
			paymentMethTF.setEditable(false);
			paymentMethTF.setText(registerDto.getPaymentMeth());
		}
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

	/**
	 * @return the timeLabel
	 */
	public JLabel getTimeLabel() {
		return timeLabel;
	}

	/**
	 * @param timeLabel the timeLabel to set
	 */
	public void setTimeLabel(JLabel timeLabel) {
		this.timeLabel = timeLabel;
	}

	/**
	 * @return the timeDC
	 */
	public JDateChooser getTimeDC() {
		return timeDC;
	}

	/**
	 * @param timeDC the timeDC to set
	 */
	public void setTimeDC(JDateChooser timeDC) {
		this.timeDC = timeDC;
	}

	/**
	 * @return the custLabel
	 */
	public JLabel getCustLabel() {
		return custLabel;
	}

	/**
	 * @param custLabel the custLabel to set
	 */
	public void setCustLabel(JLabel custLabel) {
		this.custLabel = custLabel;
	}

	/**
	 * @return the custTF
	 */
	public JTextField getCustTF() {
		return custTF;
	}

	/**
	 * @param custTF the custTF to set
	 */
	public void setCustTF(JTextField custTF) {
		this.custTF = custTF;
	}

	/**
	 * @return the ctPersonLabel
	 */
	public JLabel getCtPersonLabel() {
		return ctPersonLabel;
	}

	/**
	 * @param ctPersonLabel the ctPersonLabel to set
	 */
	public void setCtPersonLabel(JLabel ctPersonLabel) {
		this.ctPersonLabel = ctPersonLabel;
	}

	/**
	 * @return the ctPersonTF
	 */
	public JTextField getCtPersonTF() {
		return ctPersonTF;
	}

	/**
	 * @param ctPersonTF the ctPersonTF to set
	 */
	public void setCtPersonTF(JTextField ctPersonTF) {
		this.ctPersonTF = ctPersonTF;
	}

}
