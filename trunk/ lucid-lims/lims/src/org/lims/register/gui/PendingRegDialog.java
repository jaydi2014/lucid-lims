/**
 * 
 */
package org.lims.register.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import org.apache.log4j.Logger;
import org.lims.admin.service.AdminService;
import org.lims.admin.service.AdminServiceInter;
import org.lims.gui.util.GuiUtil;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class PendingRegDialog extends JDialog{
	
	private static final long serialVersionUID = -8725104686917355851L;
	
	private Logger log=Logger.getLogger(PendingRegDialog.class);	
	private ResourceBundle resources=Util.getResources();
	private AdminServiceInter adminService=new AdminService();
	
	private JTextPane pendingRegTP;
	
	public PendingRegDialog(Frame owner, String title, boolean modal) {
		super(owner,title,modal);		
		getContentPane().setLayout(new BorderLayout());
		JPanel westPanel=centerPanel();
		add(westPanel,BorderLayout.CENTER);
		GuiUtil.CompCenterCords cords=GuiUtil.getCompCenterCords(1000, 650);
		setBounds(cords.getX(), cords.getY(), 1000, 650);		
		setVisible(true);
	}
	
	public JPanel centerPanel(){
		JPanel panel=new JPanel();
		panel.setLayout(null);
		pendingRegTP=new JTextPane();
		JScrollPane scrolls=new JScrollPane(pendingRegTP);
		scrolls.setBounds(10, 10, 970, 550);
		panel.add(scrolls);
		JButton fetchRegsButton=new JButton(resources.getString("dialog.admin.button.penReg.fetchPenRegs"));
		//fetchRegsButton.addActionListener(arg0);
		fetchRegsButton.setBounds(10, 570, 200, 30);
		panel.add(fetchRegsButton);
		panel.setPreferredSize(new Dimension(300,300));
		return panel;
	}

}
