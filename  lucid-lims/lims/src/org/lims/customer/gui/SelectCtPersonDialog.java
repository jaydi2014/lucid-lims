/**
 * 
 */
package org.lims.customer.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import org.lims.customer.dto.ContactPersonDto;
import org.lims.customer.dto.CustomerDto;
import org.lims.customer.service.CustomerService;
import org.lims.customer.service.CustomerServiceInter;
import org.lims.gui.util.GuiUtil;
import org.lims.register.gui.RegisterSamplesDialog;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class SelectCtPersonDialog extends JDialog{

	private static final long serialVersionUID = -8230712410015799178L;
	private ResourceBundle resources=Util.getResources();	
	private CustomerServiceInter service=new CustomerService();
	private Logger log=Logger.getLogger(SelectCtPersonDialog.class);
	private RegisterSamplesDialog rsDialog;
	private SelectCtPersonDialog sctpd;
	private List<ContactPersonDto> contactPersons;
	private JLabel custNameLabel;
	private JTextField custNameTF;	
	private JList custList;
	
	
	public SelectCtPersonDialog(RegisterSamplesDialog owner, String title, boolean modal) {
		super(owner,title,modal);
		rsDialog=owner;
		sctpd=this;
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
		
		JPanel ctPersonPanel=new JPanel();
		ctPersonPanel.setLayout(null);	
		String custName=rsDialog.getCustTF().getText();
		DefaultListModel custListModel=new DefaultListModel();
		
		try{
			CustomerDto customer=service.getCustomer(custName, true);
			contactPersons=customer.getContactPersons();			
			for(ContactPersonDto contactPerson:contactPersons){
				custListModel.addElement(contactPerson.getCtPersonName());
			}
		}catch(Exception e){
			log.debug(e.getMessage(), e);
		}
		custList=new JList(custListModel);
		custList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		custList.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent event){
		        if(event.getClickCount()==2){ 
		        	sctpd.dispose();
		        	JList ctPersonsList=(JList)event.getSource();
		        	String ctPersonName=(String)custList.getSelectedValue();
		        	Integer index=ctPersonsList.getSelectedIndex();
		        	
		        	try{
		        		ContactPersonDto contactPerson=contactPersons.get(index);
		        		setContactPerson(contactPerson);
		        	}catch(Exception e){
		        		log.debug(e.getMessage(), e);
		        	}
		        }
			}
		});
		JScrollPane scrolls=new JScrollPane(custList);
		scrolls.setBounds(50, 50, 310, 400);
		ctPersonPanel.add(scrolls);
		
		Border nameTitledBorder=BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
                resources.getString("register.dialog.border.selectCtPerson.title"), 
                TitledBorder.LEFT, TitledBorder.TOP);
		ctPersonPanel.setBorder(nameTitledBorder);
		ctPersonPanel.setBounds(0,0, 370, 460);
		panel.add(ctPersonPanel);
		panel.setPreferredSize(new Dimension(360,150));
		return panel;
	}
	
	private void setContactPerson(ContactPersonDto contactPerson){
		rsDialog.getCtPersonTF().setText(contactPerson.getCtPersonName());
		rsDialog.getCustCtPersonMobileTF().setText(contactPerson.getCtPersonMobile());
		rsDialog.getCustCtPersonEmailTF().setText(contactPerson.getCtPersonEmail());
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
