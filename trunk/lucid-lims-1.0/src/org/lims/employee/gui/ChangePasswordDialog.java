/**
 * 
 */
package org.lims.employee.gui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import org.lims.employee.gui.listeners.UpdatePwdButtonListener;
import org.lims.gui.util.GuiUtil;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class ChangePasswordDialog extends JDialog{
	
	private static final long serialVersionUID = -721817343467660237L;
	private ResourceBundle resources=Util.getResources();
	
	private JLabel empPasswordLabel;
	private JPasswordField empPasswordTF;
	private JLabel empNewPasswordLabel;
	private JPasswordField empNewPasswordTF;
	private JLabel empReNewPasswordLabel;
	private JPasswordField empReNewPasswordTF;
	
	public ChangePasswordDialog(Frame owner, String title, boolean modal) {
		super(owner,title,modal);		
		getContentPane().setLayout(new BorderLayout());
		JPanel centerPanel=new JPanel();
		centerPanel.setLayout(null);
		JPanel passwordPanel=new JPanel();
		passwordPanel.setLayout(new GridLayout(3,2));
		
		empPasswordLabel=GuiUtil.displayLabel(resources.getString("profile.dialog.label.password"));
		passwordPanel.add(empPasswordLabel);	
		empPasswordTF=new JPasswordField();
		passwordPanel.add(empPasswordTF);
		
		empNewPasswordLabel=GuiUtil.displayLabel(resources.getString("profile.dialog.label.newPassword"));
		passwordPanel.add(empNewPasswordLabel);	
		empNewPasswordTF=new JPasswordField();
		passwordPanel.add(empNewPasswordTF);
		
		empReNewPasswordLabel=GuiUtil.displayLabel(resources.getString("profile.dialog.label.reNewPassword"));
		passwordPanel.add(empReNewPasswordLabel);	
		empReNewPasswordTF=new JPasswordField();
		passwordPanel.add(empReNewPasswordTF);
       
		passwordPanel.setBounds(50,50,300,150);
		centerPanel.add(passwordPanel);
		
        JButton addButton=new JButton(resources.getString("profile.dialog.button.update"));
        addButton.addActionListener(new UpdatePwdButtonListener(this));        
        addButton.setBounds(170,220, 150, 30);
        centerPanel.add(addButton);
        add(centerPanel,BorderLayout.CENTER);
		GuiUtil.CompCenterCords cords=GuiUtil.getCompCenterCords(480, 350);
		setBounds(cords.getX(), cords.getY(), 480, 350);		
		setVisible(true);
	}

	/**
	 * @return the empPasswordLabel
	 */
	public JLabel getEmpPasswordLabel() {
		return empPasswordLabel;
	}

	/**
	 * @param empPasswordLabel the empPasswordLabel to set
	 */
	public void setEmpPasswordLabel(JLabel empPasswordLabel) {
		this.empPasswordLabel = empPasswordLabel;
	}

	/**
	 * @return the empPasswordTF
	 */
	public JPasswordField getEmpPasswordTF() {
		return empPasswordTF;
	}

	/**
	 * @param empPasswordTF the empPasswordTF to set
	 */
	public void setEmpPasswordTF(JPasswordField empPasswordTF) {
		this.empPasswordTF = empPasswordTF;
	}

	/**
	 * @return the empNewPasswordLabel
	 */
	public JLabel getEmpNewPasswordLabel() {
		return empNewPasswordLabel;
	}

	/**
	 * @param empNewPasswordLabel the empNewPasswordLabel to set
	 */
	public void setEmpNewPasswordLabel(JLabel empNewPasswordLabel) {
		this.empNewPasswordLabel = empNewPasswordLabel;
	}

	/**
	 * @return the empNewPasswordTF
	 */
	public JPasswordField getEmpNewPasswordTF() {
		return empNewPasswordTF;
	}

	/**
	 * @param empNewPasswordTF the empNewPasswordTF to set
	 */
	public void setEmpNewPasswordTF(JPasswordField empNewPasswordTF) {
		this.empNewPasswordTF = empNewPasswordTF;
	}

	/**
	 * @return the empReNewPasswordLabel
	 */
	public JLabel getEmpReNewPasswordLabel() {
		return empReNewPasswordLabel;
	}

	/**
	 * @param empReNewPasswordLabel the empReNewPasswordLabel to set
	 */
	public void setEmpReNewPasswordLabel(JLabel empReNewPasswordLabel) {
		this.empReNewPasswordLabel = empReNewPasswordLabel;
	}

	/**
	 * @return the empReNewPasswordTF
	 */
	public JPasswordField getEmpReNewPasswordTF() {
		return empReNewPasswordTF;
	}

	/**
	 * @param empReNewPasswordTF the empReNewPasswordTF to set
	 */
	public void setEmpReNewPasswordTF(JPasswordField empReNewPasswordTF) {
		this.empReNewPasswordTF = empReNewPasswordTF;
	}

	

}
