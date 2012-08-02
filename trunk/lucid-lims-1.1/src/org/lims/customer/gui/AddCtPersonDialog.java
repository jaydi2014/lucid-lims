/**
 * 
 */
package org.lims.customer.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.lims.customer.gui.listeners.AddCtPersonButtonListener;
import org.lims.gui.util.GuiUtil;
import org.lims.register.gui.RegisterSamplesDialog;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class AddCtPersonDialog extends JDialog{
	
	private static final long serialVersionUID = -326205744565094550L;
	private ResourceBundle resources=Util.getResources();
	private JLabel ctPersonLabel;
	private JTextField ctPersonTF;
	private JLabel ctPersonMobileLabel;
	private JTextField ctPersonMobileTF;
	private JLabel ctPersonEmailLabel;
	private JTextField ctPersonEmialTF;
	private RegisterSamplesDialog rsd;
	
	public  AddCtPersonDialog(RegisterSamplesDialog owner, String title, boolean modal) {
		super(owner,title,modal);	
		rsd=owner;
		getContentPane().setLayout(new BorderLayout());
		JPanel centerPanel=createCtPersonPanel();
		add(centerPanel,BorderLayout.CENTER);
		GuiUtil.CompCenterCords cords=GuiUtil.getCompCenterCords(510, 300);
		setBounds(cords.getX(), cords.getY(), 510, 300);	
		setVisible(true);		
	}
	
	
	
	private JPanel createCtPersonPanel(){
		JPanel mainPanel=new JPanel();
		mainPanel.setLayout(null);
		
		JPanel panel=new JPanel(new GridLayout(3,2));
		ctPersonLabel=new JLabel(resources.getString("register.dialog.label.ctPersonName"));
		panel.add(ctPersonLabel);
		ctPersonTF=new JTextField();
		panel.add(ctPersonTF);
		ctPersonMobileLabel=new JLabel(resources.getString("register.dialog.label.ctPersonMobile"));
		panel.add(ctPersonMobileLabel);
		ctPersonMobileTF=new JTextField();
		panel.add(ctPersonMobileTF);
		ctPersonEmailLabel=new JLabel(resources.getString("register.dialog.label.ctPersonEmail"));
		panel.add(ctPersonEmailLabel);
		ctPersonEmialTF=new JTextField();
		panel.add(ctPersonEmialTF);
		panel.setBounds(50,50,300,100);
		JButton addCtPersonB=new JButton(resources.getString("register.dialog.button.addCtp"));
		addCtPersonB.addActionListener(new AddCtPersonButtonListener(this));
		addCtPersonB.setBounds(180, 160,100, 30);
		mainPanel.add(addCtPersonB);
		mainPanel.add(panel);
		return mainPanel;
	}



	/**
	 * @return the ctPersonLabel
	 */
	public JLabel getCtPersonLabel() {
		return ctPersonLabel;
	}



	/**
	 * @return the ctPersonTF
	 */
	public JTextField getCtPersonTF() {
		return ctPersonTF;
	}



	/**
	 * @return the ctPersonMobileLabel
	 */
	public JLabel getCtPersonMobileLabel() {
		return ctPersonMobileLabel;
	}



	/**
	 * @return the ctPersonMobileTF
	 */
	public JTextField getCtPersonMobileTF() {
		return ctPersonMobileTF;
	}



	/**
	 * @return the ctPersonEmailLabel
	 */
	public JLabel getCtPersonEmailLabel() {
		return ctPersonEmailLabel;
	}



	/**
	 * @return the ctPersonEmialTF
	 */
	public JTextField getCtPersonEmialTF() {
		return ctPersonEmialTF;
	}



	/**
	 * @return the rsd
	 */
	public RegisterSamplesDialog getRsd() {
		return rsd;
	}

}
