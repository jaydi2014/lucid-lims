/**
 * 
 */
package org.lims.admin.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.lims.admin.gui.listeners.AddRoleButtonListener;
import org.lims.gui.util.GuiUtil;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class AddRoleDialog extends JDialog{
	
	private static final long serialVersionUID = -6253271550309142168L;
	private ResourceBundle resources=Util.getResources();
	private JLabel roleNameLabel;
	private JTextField roleNameTF;
	private JLabel roleDescLabel;
	private JTextArea roleDescTA;
	
	/**
	 * Constructs add employee dialog.
	 * @param owner
	 * @param title
	 * @param modal
	 */
	public AddRoleDialog(Frame owner, String title, boolean modal) {
		super(owner,title,modal);		
		getContentPane().setLayout(new BorderLayout());
		JPanel centerPanel=new JPanel();
		centerPanel.setLayout(null);
		roleNameLabel=new JLabel(resources.getString("dialog.label.admin.roleName"));
		roleNameLabel.setBounds(10, 30, 150, 30);
		roleNameLabel.setForeground(Color.BLACK);
		centerPanel.add(roleNameLabel);	
		roleNameTF=new JTextField();
		roleNameTF.setBounds(170, 30, 150,30);
        centerPanel.add(roleNameTF);
        roleDescLabel=new JLabel(Util.getResources().getString("dialog.label.admin.roleDesc"));
        roleDescLabel.setBounds(10,70, 150, 30);
        roleDescLabel.setForeground(Color.BLACK);
        centerPanel.add(roleDescLabel);	
        roleDescTA=new JTextArea();        
        JScrollPane roleDescTAscroll=new JScrollPane(roleDescTA);
        roleDescTAscroll.setBounds(170, 70, 200,80);
        centerPanel.add(roleDescTAscroll);
        JButton addButton=new JButton(resources.getString("dialog.button.admin.role.add"));
        addButton.addActionListener(new AddRoleButtonListener(this));        
        addButton.setBounds(170,160, 70, 30);
        centerPanel.add(addButton);
        add(centerPanel,BorderLayout.CENTER);
		GuiUtil.CompCenterCords cords=GuiUtil.getCompCenterCords(480, 250);
		setBounds(cords.getX(), cords.getY(), 480, 250);
		
		//setResizable(false);
		setVisible(true);
	}

	public JLabel getRoleNameLabel() {
		return roleNameLabel;
	}

	public void setRoleNameLabel(JLabel roleNameLabel) {
		this.roleNameLabel = roleNameLabel;
	}

	public JTextField getRoleNameTF() {
		return roleNameTF;
	}

	public void setRoleNameTF(JTextField roleNameTF) {
		this.roleNameTF = roleNameTF;
	}

	public JLabel getRoleDescLabel() {
		return roleDescLabel;
	}

	public void setRoleDescLabel(JLabel roleDescLabel) {
		this.roleDescLabel = roleDescLabel;
	}

	public JTextArea getRoleDescTA() {
		return roleDescTA;
	}

	public void setRoleDescTA(JTextArea roleDescTA) {
		this.roleDescTA = roleDescTA;
	}

}
