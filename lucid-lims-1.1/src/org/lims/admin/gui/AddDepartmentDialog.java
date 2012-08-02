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

import org.lims.admin.gui.listeners.AddDeptButtonListener;
import org.lims.gui.util.GuiUtil;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class AddDepartmentDialog extends JDialog{

	private static final long serialVersionUID = -6253271550309142168L;
	private ResourceBundle resources=Util.getResources();
	private JLabel dptNameLabel;
	private JTextField deptNameTF;
	private JLabel dptDescLabel;
	private JTextArea deptDescTA;
	
	/**
	 * Constructs add employee dialog.
	 * @param owner
	 * @param title
	 * @param modal
	 */
	public AddDepartmentDialog(Frame owner, String title, boolean modal) {
		super(owner,title,modal);		
		getContentPane().setLayout(new BorderLayout());
		JPanel centerPanel=new JPanel();
		centerPanel.setLayout(null);
		dptNameLabel=new JLabel(resources.getString("dialog.label.admin.deptName"));
		dptNameLabel.setBounds(10, 30, 150, 30);
		dptNameLabel.setForeground(Color.BLACK);
		centerPanel.add(dptNameLabel);	
        deptNameTF=new JTextField();
        deptNameTF.setBounds(170, 30, 150,30);
        centerPanel.add(deptNameTF);
        dptDescLabel=new JLabel(Util.getResources().getString("dialog.label.admin.deptDesc"));
        dptDescLabel.setBounds(10,70, 150, 30);
        dptDescLabel.setForeground(Color.BLACK);
        centerPanel.add(dptDescLabel);	
        deptDescTA=new JTextArea();
        
        JScrollPane deptDescTAscroll=new JScrollPane(deptDescTA);
        deptDescTAscroll.setBounds(170, 70, 200,80);
        centerPanel.add(deptDescTAscroll);
        JButton addButton=new JButton(resources.getString("dialog.button.admin.add"));
        addButton.addActionListener(new AddDeptButtonListener(this));        
        addButton.setBounds(170,160, 70, 30);
        centerPanel.add(addButton);
        add(centerPanel,BorderLayout.CENTER);
		GuiUtil.CompCenterCords cords=GuiUtil.getCompCenterCords(480, 250);
		setBounds(cords.getX(), cords.getY(), 480, 250);		
		setVisible(true);
	}

	public JLabel getDptNameLabel() {
		return dptNameLabel;
	}

	public void setDptNameLabel(JLabel dptNameLabel) {
		this.dptNameLabel = dptNameLabel;
	}

	public JTextField getDeptNameTF() {
		return deptNameTF;
	}

	public void setDeptNameTF(JTextField deptNameTF) {
		this.deptNameTF = deptNameTF;
	}

	public JLabel getDptDescLabel() {
		return dptDescLabel;
	}

	public void setDptDescLabel(JLabel dptDescLabel) {
		this.dptDescLabel = dptDescLabel;
	}

	public JTextArea getDeptDescTA() {
		return deptDescTA;
	}

	public void setDeptDescTA(JTextArea deptDescTA) {
		this.deptDescTA = deptDescTA;
	}
}
