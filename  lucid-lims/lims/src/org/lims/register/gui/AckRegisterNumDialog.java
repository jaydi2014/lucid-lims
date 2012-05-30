/**
 * 
 */
package org.lims.register.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.lims.gui.util.GuiUtil;
import org.lims.register.gui.listeners.PrintAckButtonListener;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class AckRegisterNumDialog extends JDialog{
	
	private static final long serialVersionUID = 325952038934320179L;
	
	private ResourceBundle resources=Util.getResources();
	private JLabel regNumLabel;
	private JTextField regNumTF;
	
	public AckRegisterNumDialog(Frame owner, String title, boolean modal) {
		super(owner,title,modal);		
		getContentPane().setLayout(new BorderLayout());
		JPanel centerPanel=new JPanel();
		centerPanel.setLayout(null);
		regNumLabel=new JLabel(resources.getString("register.dialog.label.dispatch.RegNum"));
		regNumLabel.setBounds(50, 30, 150, 30);
		regNumLabel.setForeground(Color.BLACK);
		centerPanel.add(regNumLabel);	
		regNumTF=new JTextField();
        regNumTF.setBounds(210, 30, 150,30);
        centerPanel.add(regNumTF);
        JButton addButton=new JButton(resources.getString("register.dialog.button.printAck"));
        addButton.addActionListener(new PrintAckButtonListener(this));        
        addButton.setBounds(110,80, 180, 30);
        centerPanel.add(addButton);
        add(centerPanel,BorderLayout.CENTER);
		GuiUtil.CompCenterCords cords=GuiUtil.getCompCenterCords(480, 250);
		setBounds(cords.getX(), cords.getY(), 480, 250);		
		setVisible(true);
	}

	/**
	 * @return the regNumLabel
	 */
	public JLabel getRegNumLabel() {
		return regNumLabel;
	}

	/**
	 * @param regNumLabel the regNumLabel to set
	 */
	public void setRegNumLabel(JLabel regNumLabel) {
		this.regNumLabel = regNumLabel;
	}

	/**
	 * @return the regNumTF
	 */
	public JTextField getRegNumTF() {
		return regNumTF;
	}

	/**
	 * @param regNumTF the regNumTF to set
	 */
	public void setRegNumTF(JTextField regNumTF) {
		this.regNumTF = regNumTF;
	}

}
