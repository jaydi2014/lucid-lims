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
import org.lims.register.dto.RegDeptDto;
import org.lims.register.dto.SampleCollectionMethodDto;
import org.lims.register.dto.SampleDto;
import org.lims.register.dto.TestRegisterDto;
import org.lims.register.gui.listeners.BillindDispatchButtonListener;
import org.lims.register.gui.listeners.RegisterSamplesButtonListener;
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
	private ArrayList<EmpNamePanel> deptPanelList=new ArrayList<EmpNamePanel>();
	public static String dispatchRegNum;
	
	private JLabel regNoLabel;
	private JTextField regNoTF;
	private JLabel dateLabel;
	private JDateChooser dateDC;
	private JLabel deptLabel;		
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
	private JButton selectCustB;
	private JButton addCustB;
	private JLabel ctPersonLabel;
	private JTextField ctPersonTF;
	private JButton selectCTPB;
	private JButton addCustCtpB;
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
	private JTextField adqTF;
	private JTextField inAdeqTF;
	private JTextField roomTempTF;
	private JTextField crTempTF;
	private JTextField acptlTF;
	private JTextField ntAcptlTF;
	private JTextField intactTF;
	private JTextField ntIntactTF;
	private JTextField tmAvailableTF;
	private JTextField ntTmAvailableTF;
	private JTextField chequeNumTF;
	private JDateChooser chequeDateDC;
	private JLabel inAdeqLabel;
	private JLabel adqLabel;
	private JLabel roomTempLabel;
	private JLabel crTempLabel;
	private JLabel acptlLabel;
	private JLabel ntacptlLabel;
	private JLabel intactLabel;
	private JLabel ntIntactLabel;
	private JLabel avlLabel;
	private JLabel ntAvlLabel;
	private JLabel chequeNumLabel;
	private JLabel chequeDateLabel;
	private JPanel deptPanel;
	
	
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
		centerPanel.setPreferredSize(new Dimension(1000,2000));
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
		
		JSeparator fifthSep=new JSeparator();
		fifthSep.setBounds(0, 1040, 1000, 2);
		panel.add(fifthSep);
		
		JPanel deptPanel=createDeptPanel();
		deptPanel.setBounds(200, 1050, 530,270);
		panel.add(deptPanel);
		
		JSeparator sixthSep=new JSeparator();
		sixthSep.setBounds(0, 1330, 1000, 2);
		panel.add(sixthSep);
		
		JPanel sampleAdeqPanel=createSampleAdequacyPanel();
		sampleAdeqPanel.setBounds(100, 1340, 800, 200);
		panel.add(sampleAdeqPanel);
		
		JSeparator seventhSep=new JSeparator();
		seventhSep.setBounds(0, 1550, 1000, 2);
		panel.add(seventhSep);
				
		specialInstrLabel=GuiUtil.displayLabel(resources.getString("register.dialog.label.specialInstructions"));
		specialInstrLabel.setBounds(10, 1560, 150, 30);
		panel.add(specialInstrLabel);
		specialInstrTA=new JTextArea();
		JScrollPane scrollPane=new JScrollPane(specialInstrTA);
		scrollPane.setBounds(160, 1560, 200, 100);
		panel.add(scrollPane);
		
		samplePackingLabel=GuiUtil.displayLabel(resources.getString("register.dialog.label.samplePacking"));
		samplePackingLabel.setBounds(380, 1560, 200, 30);
		panel.add(samplePackingLabel);
		samplePackingTA=new JTextArea();
		JScrollPane packingScrollPane=new JScrollPane(samplePackingTA);
		packingScrollPane.setBounds(580, 1560, 200,100);
		panel.add(packingScrollPane);
		
		JSeparator eightSep=new JSeparator();
		eightSep.setBounds(0, 1670, 1000, 2);
		panel.add(eightSep);
		
		JPanel billingPanel=createBillingDetailsPanel();
		billingPanel.setBounds(250, 1680, 300, 210);
		panel.add(billingPanel);
		
		JSeparator ninethSep=new JSeparator();
		ninethSep.setBounds(0, 1900, 1000, 2);
		panel.add(ninethSep);
		
		if(actionCommand.equals("REG")){
			setDiabledFieldsRegistration();
			JButton registerButton=new JButton(resources.getString("register.dialog.button.register"));
			registerButton.addActionListener(new RegisterSamplesButtonListener(this));
			registerButton.setBounds(400, 1910, 200, 40);
			panel.add(registerButton);
		}
		
		if(actionCommand.equals("DISPATCH")){
			try{
				TestRegisterDto registerDto=regService.getRegisterEntry(dispatchRegNum);
				setFieldsDispatch(registerDto);
				JButton updateDisBill=new JButton(resources.getString("register.dialog.button.dispatch.updateDisBill"));
				updateDisBill.addActionListener(new BillindDispatchButtonListener(this));
				updateDisBill.setBounds(300, 1910, 300, 40);
				panel.add(updateDisBill);
				if(registerDto.getDispatchLock() && registerDto.getBillingLocked())
					updateDisBill.setEnabled(false);
			}catch(Exception e){
				log.debug(e.getMessage(), e);
			}			
		}
		
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
		custTF.setEditable(false);
		custTF.setPreferredSize(new Dimension(150,30));
		custPanel.add(custTF,c);		
		selectCustB=new JButton(resources.getString("register.dialog.label.custName"));
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
		addCustB=new JButton(resources.getString("register.dialog.button.addcust"));
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
		ctPersonTF.setEditable(false);
		ctPersonTF.setPreferredSize(new Dimension(150,30));
	    custPanel.add(ctPersonTF,c);
	   
	    selectCTPB=new JButton(resources.getString("register.dialog.button.selectCustCtPerson"));
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
		addCustCtpB=new JButton(resources.getString("register.dialog.button.addcustCtp"));
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
		displayB.setEnabled(false);
		displayB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					regService.displayCustRefFile(regNoTF.getText());
				}catch(Exception exp){
					log.debug(exp.getMessage(), exp);
				}
			}
		});
		displayB.setBounds(700,20, 100, 30);
	    custRefPanel.add(displayB);
		
		Border titledBorder=BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
                resources.getString("register.dialog.border.customerRef.title"), 
                TitledBorder.LEFT, TitledBorder.TOP);
		custRefPanel.setBorder(titledBorder);
		return custRefPanel;
	}
	
	
	
	private JPanel createDeptPanel(){
		JPanel panel=new JPanel(null);
		deptPanel=new JPanel(new GridLayout(20,1));
		deptPanel.setPreferredSize(new Dimension(480,600));
		JScrollPane scrolls=new JScrollPane(deptPanel);
		scrolls.setBounds(20, 20, 500, 200);
		scrolls.setPreferredSize(new Dimension(500,200));
		panel.add(scrolls);
		JButton addRowButton=new JButton(resources.getString("register.dialog.button.depts.addRow"));
		addRowButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				EmpNamePanel enp=new EmpNamePanel();
				deptPanelList.add(enp);
				deptPanel.add(enp);
				deptPanel.validate();							
			}
		});
		addRowButton.setBounds(210, 230, 100, 30);
		panel.add(addRowButton);
		
		Border titledBorder=BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
                resources.getString("register.dialog.border.dept.title"), 
                TitledBorder.LEFT, TitledBorder.TOP);
		panel.setBorder(titledBorder);
		return panel;
		
	}
	
	/**
	 * creates sample adequacy panel.
	 * @return JPanel
	 */
	
	private JPanel createSampleAdequacyPanel(){
		JPanel panel=new JPanel(null);
		
		//This is quantity panel
		JPanel qtyPanel=new JPanel(null);
		JLabel qtyLabel=new JLabel(resources.getString("register.dialog.label.qty"));
		qtyLabel.setBounds(5, 5, 80, 30);
		qtyPanel.add(qtyLabel);
		
		JPanel adqPanel=new JPanel(new GridLayout(2,2));
		adqLabel=new JLabel(resources.getString("register.dialog.label.adequate"));
		adqPanel.add(adqLabel);
		adqTF=new JTextField();
		adqPanel.add(adqTF);
		inAdeqLabel=new JLabel(resources.getString("register.dialog.label.inAdequate"));
		adqPanel.add(inAdeqLabel);
		inAdeqTF=new JTextField();
		adqPanel.add(inAdeqTF);
		Border adeqBorder=BorderFactory.createLineBorder(Color.BLACK);
		adqPanel.setBorder(adeqBorder);
		adqPanel.setBounds(0, 35, 150, 135);
		qtyPanel.add(adqPanel);
		
		qtyPanel.setBounds(10, 20, 150, 170);
		Border qtyBorder=BorderFactory.createLineBorder(Color.BLACK);
		qtyPanel.setBorder(qtyBorder);
		panel.add(qtyPanel);
		
		// The following is storage conditions.
		JPanel scPanel=new JPanel(null);
		JLabel scLabel=new JLabel(resources.getString("register.dialog.label.storageconditions"));
		scLabel.setBounds(5, 5, 130, 30);
		scPanel.add(scLabel);
		
		JPanel tempPanel=new JPanel(new GridLayout(2,2));
		roomTempLabel=new JLabel(resources.getString("register.dialog.label.roomTemp"));
		tempPanel.add(roomTempLabel);
		roomTempTF=new JTextField();
		tempPanel.add(roomTempTF);
		crTempLabel=new JLabel(resources.getString("register.dialog.label.crTemp"));
		crTempLabel.setToolTipText(resources.getString("register.dialog.tooltip.crTemp"));
		tempPanel.add(crTempLabel);
		crTempTF=new JTextField();
		tempPanel.add(crTempTF);
		tempPanel.setBounds(0, 35, 150, 135);
		Border tempBorder=BorderFactory.createLineBorder(Color.BLACK);
		tempPanel.setBorder(tempBorder);
		scPanel.add(tempPanel);
		
		scPanel.setBounds(160,20,150,170);
		Border scBorder=BorderFactory.createLineBorder(Color.BLACK);
		scPanel.setBorder(scBorder);
		panel.add(scPanel);
		
		//The following is conditions on arrival
		JPanel coaPanel=new JPanel(null);
		JLabel coaLabel=new JLabel(resources.getString("register.dialog.label.coa"));
        coaLabel.setBounds(5, 5, 150, 30);
		coaPanel.add(coaLabel);
		
		JPanel acceptablePanel=new JPanel(new GridLayout(2,2));
		acptlLabel=new JLabel(resources.getString("register.dialog.label.acceptable"));
		acceptablePanel.add(acptlLabel);
		acptlTF=new JTextField();
		acceptablePanel.add(acptlTF);
		ntacptlLabel=new JLabel(resources.getString("register.dialog.label.notAcceptable"));
		ntacptlLabel.setToolTipText(resources.getString("register.dialog.tooltip.notAcceptable"));
		acceptablePanel.add(ntacptlLabel);
		ntAcptlTF=new JTextField();
		acceptablePanel.add(ntAcptlTF);
		Border acptlBorder=BorderFactory.createLineBorder(Color.BLACK);
		acceptablePanel.setBorder(acptlBorder);
		acceptablePanel.setBounds(0, 35, 150, 135);
		coaPanel.add(acceptablePanel);
		
		coaPanel.setBounds(310, 20, 150, 170);		
		Border coaBorder=BorderFactory.createLineBorder(Color.BLACK);
		coaPanel.setBorder(coaBorder);
		panel.add(coaPanel);
		
		// The following is customer seal
		JPanel sealPanel=new JPanel(null);
		JLabel sealLabel=new JLabel(resources.getString("register.dialog.label.seal"));
		sealLabel.setBounds(5, 5, 150, 30);
		sealPanel.add(sealLabel);
		
		JPanel intactPanel=new JPanel(new GridLayout(2,2));
		intactLabel=new JLabel(resources.getString("register.dialog.label.intact"));
		intactPanel.add(intactLabel);
		intactTF=new JTextField();
		intactPanel.add(intactTF);
		ntIntactLabel=new JLabel(resources.getString("register.dialog.label.notIntact"));
		intactPanel.add(ntIntactLabel);
		ntIntactTF=new JTextField();
		intactPanel.add(ntIntactTF);
		Border intactBorder=BorderFactory.createLineBorder(Color.BLACK);
		intactPanel.setBorder(intactBorder);
		intactPanel.setBounds(0, 35, 150, 135);
		sealPanel.add(intactPanel);
		
		sealPanel.setBounds(460,20,150,170);
		Border sealBorder=BorderFactory.createLineBorder(Color.BLACK);
		sealPanel.setBorder(sealBorder);
		panel.add(sealPanel);
		
		// the following is the availability of test Method
		JPanel tmPanel=new JPanel(null);
		JLabel tmLabel=new JLabel(resources.getString("register.dialog.label.tm"));
		tmLabel.setBounds(5, 5, 150, 30);
		tmPanel.add(tmLabel);
		
		JPanel avlPanel=new JPanel(new GridLayout(2,2));
		avlLabel=new JLabel(resources.getString("register.dialog.label.available"));
		avlPanel.add(avlLabel);
		tmAvailableTF=new JTextField();
		avlPanel.add(tmAvailableTF);
		ntAvlLabel=new JLabel(resources.getString("register.dialog.label.notAvailable"));
		avlPanel.add(ntAvlLabel);
		ntTmAvailableTF=new JTextField();
		avlPanel.add(ntTmAvailableTF);
		avlPanel.setBounds(0, 35, 150, 135);
		Border avlBorder=BorderFactory.createLineBorder(Color.BLACK);
		avlPanel.setBorder(avlBorder);
		tmPanel.add(avlPanel);
		
		tmPanel.setBounds(610, 20, 150, 170);
		Border tmBorder=BorderFactory.createLineBorder(Color.BLACK);
		tmPanel.setBorder(tmBorder);
		panel.add(tmPanel);
		
		Border titledBorder=BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
                resources.getString("register.dialog.border.sampleAdeq.title"), 
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
		panel.setLayout(new GridLayout(6,2));
		
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
		
		chequeNumLabel=GuiUtil.displayLabel(resources.getString("register.dialog.label.chequeNum"));
		panel.add(chequeNumLabel);
		chequeNumTF=new JTextField();
		panel.add(chequeNumTF);
		
		chequeDateLabel=GuiUtil.displayLabel(resources.getString("register.dialog.label.chequeDate"));
		panel.add(chequeDateLabel);
		chequeDateDC=new JDateChooser(null,Constants.DATE_PATTERN);
		panel.add(chequeDateDC);
		
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
		paymentMethTF.setEditable(false);
		paymentMethTF.setText(registerDto.getPaymentMeth());
		balanceTF.setText(registerDto.getBalance());
		totalTestingChrgsTF.setText(registerDto.getTotalTestingChrgs());
		dateDC.setEnabled(false);
		try{
			dateDC.setDate(Util.convertStringToDate(registerDto.getDate(), Constants.DATE_PATTERN));
		}catch(Exception e){
			log.debug(e.getMessage(),e);
		}
		timeDC.setDate(registerDto.getRegTime());
		custTF.setText(registerDto.getCustomer().getCustName())	;
		selectCustB.setEnabled(false);
		addCustB.setEnabled(false);
		custAddressTA.setText(registerDto.getCustomer().getAddress());
		custPhoneTF.setText(registerDto.getCustomer().getPhoneNumber());
		custFaxTF.setText(registerDto.getCustomer().getFaxNumber());
		custEmailTF.setText(registerDto.getCustomer().getEmail());
		ctPersonTF.setText(registerDto.getCtPerson().getCtPersonName());
		selectCTPB.setEnabled(false);
		addCustCtpB.setEnabled(false);
		custCtPersonMobileTF.setText(registerDto.getCtPerson().getCtPersonMobile());
		custCtPersonEmailTF.setText(registerDto.getCtPerson().getCtPersonEmail());
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
		scMethodsCB.setEnabled(false);
		scMethodsCB.setSelectedItem(registerDto.getSampleCollectionMethod());		
		labDueDateDC.setEnabled(false);
		labDueDateDC.setDate(registerDto.getLabDueDate());
		crNumberTF.setEditable(false);
		crNumberTF.setText(registerDto.getCrNumber());
		crDC.setEnabled(false);
		crDC.setDate(registerDto.getCrDate());
		browseB.setEnabled(false);
		displayB.setEnabled(true);
		adqTF.setEditable(false);
		adqTF.setText(registerDto.getContractReview().getAdequateQty());
		inAdeqTF.setEditable(false);
		inAdeqTF.setText(registerDto.getContractReview().getInadequateQty());
		roomTempTF.setEditable(false);
		roomTempTF.setText(registerDto.getContractReview().getStrgCdtRoomTemp());
		crTempTF.setEditable(false);
		crTempTF.setText(registerDto.getContractReview().getStrgCdtCustomerReq());
		acptlTF.setEditable(false);
		acptlTF.setText(registerDto.getContractReview().getCoaAcceptable());
		ntAcptlTF.setEditable(false);
		ntAcptlTF.setText(registerDto.getContractReview().getCoaNotAcceptable());
		intactTF.setEditable(false);
		intactTF.setText(registerDto.getContractReview().getSealIntact());
		ntIntactTF.setEditable(false);
		ntIntactTF.setText(registerDto.getContractReview().getSealNotIntact());
		tmAvailableTF.setEditable(false);
		tmAvailableTF.setText(registerDto.getContractReview().getTestMethodAvailable());
		ntTmAvailableTF.setEditable(false);
		ntTmAvailableTF.setText(registerDto.getContractReview().getTestmethodNotAvailable());
		chequeNumTF.setEditable(false);
		chequeNumTF.setText(registerDto.getChequeNumber());
		chequeDateDC.setEnabled(false);
		chequeDateDC.setDate(registerDto.getChequeDate());

		samplesTable.setEnabled(false);
		DefaultTableModel model=(DefaultTableModel)samplesTable.getModel();
		List<SampleDto> samples=registerDto.getSamplesList();
		for(SampleDto sample:samples){
			Object[] array=new Object[7];
			array[0]=sample.getSerialNo();
			array[1]=sample.getSampleName();
			array[2]=sample.getSampleQty();
			array[3]=sample.getBatchMfgDetails();
			array[4]=sample.getSampleTests();
			array[5]=sample.getSpecification();
			array[6]=sample.getTestMethod();
			model.addRow(array);
		}
		List<RegDeptDto> depts=registerDto.getDepts();
		for(RegDeptDto dept:depts){
			EmpNamePanelDisplay enpd=new EmpNamePanelDisplay();
			enpd.getDeptCB().setEnabled(false);
			enpd.getDeptCB().addItem(dept.getDeptName());
			enpd.getEmpNameTF().setText(dept.getEmpDisplayName());
			enpd.getBrowseB().setEnabled(false);
			deptPanel.add(enpd);
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

	/**
	 * @return the deptPanelList
	 */
	public ArrayList<EmpNamePanel> getDeptPanelList() {
		return deptPanelList;
	}

	/**
	 * @return the dispatchRegNum
	 */
	public static String getDispatchRegNum() {
		return dispatchRegNum;
	}

	/**
	 * @return the scMethodsCB
	 */
	public JComboBox getScMethodsCB() {
		return scMethodsCB;
	}

	/**
	 * @return the labDueDateLabel
	 */
	public JLabel getLabDueDateLabel() {
		return labDueDateLabel;
	}

	/**
	 * @return the labDueDateDC
	 */
	public JDateChooser getLabDueDateDC() {
		return labDueDateDC;
	}

	/**
	 * @return the crNumberLabel
	 */
	public JLabel getCrNumberLabel() {
		return crNumberLabel;
	}

	/**
	 * @return the crNumberTF
	 */
	public JTextField getCrNumberTF() {
		return crNumberTF;
	}

	/**
	 * @return the crDateLabel
	 */
	public JLabel getCrDateLabel() {
		return crDateLabel;
	}

	/**
	 * @return the crDC
	 */
	public JDateChooser getCrDC() {
		return crDC;
	}

	/**
	 * @return the crFileTF
	 */
	public JTextField getCrFileTF() {
		return crFileTF;
	}

	/**
	 * @return the browseB
	 */
	public JButton getBrowseB() {
		return browseB;
	}

	/**
	 * @return the displayB
	 */
	public JButton getDisplayB() {
		return displayB;
	}

	/**
	 * @return the adqTF
	 */
	public JTextField getAdqTF() {
		return adqTF;
	}

	/**
	 * @return the inAdeqTF
	 */
	public JTextField getInAdeqTF() {
		return inAdeqTF;
	}

	/**
	 * @return the roomTempTF
	 */
	public JTextField getRoomTempTF() {
		return roomTempTF;
	}

	/**
	 * @return the crTempTF
	 */
	public JTextField getCrTempTF() {
		return crTempTF;
	}

	/**
	 * @return the acptlTF
	 */
	public JTextField getAcptlTF() {
		return acptlTF;
	}

	/**
	 * @return the ntAcptlTF
	 */
	public JTextField getNtAcptlTF() {
		return ntAcptlTF;
	}

	/**
	 * @return the intactTF
	 */
	public JTextField getIntactTF() {
		return intactTF;
	}

	/**
	 * @return the ntIntactTF
	 */
	public JTextField getNtIntactTF() {
		return ntIntactTF;
	}

	/**
	 * @return the tmAvailableTF
	 */
	public JTextField getTmAvailableTF() {
		return tmAvailableTF;
	}

	/**
	 * @return the ntTmAvailableTF
	 */
	public JTextField getNtTmAvailableTF() {
		return ntTmAvailableTF;
	}

	/**
	 * @return the chequeNumTF
	 */
	public JTextField getChequeNumTF() {
		return chequeNumTF;
	}

	/**
	 * @return the chequeDateDC
	 */
	public JDateChooser getChequeDateDC() {
		return chequeDateDC;
	}

	/**
	 * @return the inAdeqLabel
	 */
	public JLabel getInAdeqLabel() {
		return inAdeqLabel;
	}

	/**
	 * @return the adqLabel
	 */
	public JLabel getAdqLabel() {
		return adqLabel;
	}

	/**
	 * @return the roomTempLabel
	 */
	public JLabel getRoomTempLabel() {
		return roomTempLabel;
	}

	/**
	 * @return the crTempLabel
	 */
	public JLabel getCrTempLabel() {
		return crTempLabel;
	}

	/**
	 * @return the acptlLabel
	 */
	public JLabel getAcptlLabel() {
		return acptlLabel;
	}

	/**
	 * @return the ntacptlLabel
	 */
	public JLabel getNtacptlLabel() {
		return ntacptlLabel;
	}

	/**
	 * @return the intactLabel
	 */
	public JLabel getIntactLabel() {
		return intactLabel;
	}

	/**
	 * @return the ntIntactLabel
	 */
	public JLabel getNtIntactLabel() {
		return ntIntactLabel;
	}

	/**
	 * @return the avlLabel
	 */
	public JLabel getAvlLabel() {
		return avlLabel;
	}

	/**
	 * @return the ntAvlLabel
	 */
	public JLabel getNtAvlLabel() {
		return ntAvlLabel;
	}

	/**
	 * @return the chequeNumLabel
	 */
	public JLabel getChequeNumLabel() {
		return chequeNumLabel;
	}

	/**
	 * @return the chequeDateLabel
	 */
	public JLabel getChequeDateLabel() {
		return chequeDateLabel;
	}

	/**
	 * @return the deptPanel
	 */
	public JPanel getDeptPanel() {
		return deptPanel;
	}

}
