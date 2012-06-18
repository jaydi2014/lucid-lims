/**
 * 
 */
package org.lims.customer.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import org.apache.log4j.Logger;
import org.lims.customer.gui.listeners.SelectCustNameTFKeyListener;
import org.lims.customer.gui.listeners.SelectCustomerJLMouseListener;
import org.lims.customer.service.CustomerService;
import org.lims.customer.service.CustomerServiceInter;
import org.lims.gui.util.GuiUtil;
import org.lims.register.gui.RegisterSamplesDialog;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class SelectCustDialog extends JDialog{

	private static final long serialVersionUID = -8230712410015799178L;
	private ResourceBundle resources=Util.getResources();
	private Map<String,List<String>>custNamesMap=new HashMap<String,List<String>>();
	private CustomerServiceInter service=new CustomerService();
	private Logger log=Logger.getLogger(SelectCustDialog.class);
	private RegisterSamplesDialog rsDialog;
	
	private JLabel custNameLabel;
	private JTextField custNameTF;	
	private JList custList;
	
	
	public SelectCustDialog(RegisterSamplesDialog owner, String title, boolean modal) {
		super(owner,title,modal);
		rsDialog=owner;
		getContentPane().setLayout(new BorderLayout());
		JPanel centerPanel=centerPanel();
		add(centerPanel,BorderLayout.CENTER);
		GuiUtil.CompCenterCords cords=GuiUtil.getCompCenterCords(400, 500);
		setBounds(cords.getX(), cords.getY(), 400, 500);		
		setVisible(true);
	}
	
	private JPanel centerPanel(){
		JPanel panel=new JPanel();
		panel.setLayout(null);			
		
		JPanel custNamePanel=new JPanel();
		custNamePanel.setLayout(null);
		custNameLabel=new JLabel(resources.getString("dialog.admin.label.viewCust.custName"));
		custNameLabel.setBounds(10, 50, 150, 30);
		custNamePanel.add(custNameLabel);
		custNameTF=new JTextField();
		custNameTF.addKeyListener(new SelectCustNameTFKeyListener(this));
		custNameTF.setBounds(160, 50, 150, 30);
		custNameTF.setPreferredSize(new Dimension(150,30));
		custNamePanel.add(custNameTF);
		DefaultListModel custListModel=new DefaultListModel();
		try{
			List<String> custNames=service.getAllCustomerNames();
			custNamesMap.put("ALL",custNames );
			for(String custName:custNames){
				custListModel.addElement(custName);
			}
		}catch(Exception e){
			log.debug(e.getMessage(), e);
		}
		custList=new JList(custListModel);
		custList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		custList.addMouseListener(new SelectCustomerJLMouseListener(this));
		JScrollPane scrolls=new JScrollPane(custList);
		scrolls.setBounds(100, 100, 210, 300);
		custNamePanel.add(scrolls);
		
		Border nameTitledBorder=BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
                resources.getString("dialog.admin.border.viewCust.title"), 
                TitledBorder.LEFT, TitledBorder.TOP);
		custNamePanel.setBorder(nameTitledBorder);
		custNamePanel.setBounds(0,0, 360, 420);
		panel.add(custNamePanel);
		panel.setPreferredSize(new Dimension(360,150));
		return panel;
	}

	/**
	 * @return the custNamesMap
	 */
	public Map<String, List<String>> getCustNamesMap() {
		return custNamesMap;
	}

	/**
	 * @param custNamesMap the custNamesMap to set
	 */
	public void setCustNamesMap(Map<String, List<String>> custNamesMap) {
		this.custNamesMap = custNamesMap;
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
	 * @return the custList
	 */
	public JList getCustList() {
		return custList;
	}

	/**
	 * @param custList the custList to set
	 */
	public void setCustList(JList custList) {
		this.custList = custList;
	}

	/**
	 * @return the rsDialog
	 */
	public RegisterSamplesDialog getRsDialog() {
		return rsDialog;
	}

	/**
	 * @param rsDialog the rsDialog to set
	 */
	public void setRsDialog(RegisterSamplesDialog rsDialog) {
		this.rsDialog = rsDialog;
	}

	

	
}
