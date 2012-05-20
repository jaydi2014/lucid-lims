/**
 * 
 */
package org.lims.admin.gui;

import java.awt.Frame;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.lims.gui.util.GuiUtil;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class AddDepartmentDialog extends JDialog{

	private static final long serialVersionUID = -6253271550309142168L;
	private ResourceBundle resources=Util.getResources();
	
	/**
	 * Constructs add employee dialog.
	 * @param owner
	 * @param title
	 * @param modal
	 */
	public AddDepartmentDialog(Frame owner, String title, boolean modal) {
		super(owner,title,modal);		
		getContentPane().setLayout(null);
		JLabel dptNameLabel=new JLabel(resources.getString("dialog.label.admin.deptName"));
		dptNameLabel.setBounds(10, 30, 150, 30);
        add(dptNameLabel);	
        JTextField deptNameTF=new JTextField();
        deptNameTF.setBounds(170, 30, 150,30);
        add(deptNameTF);
        JLabel dptDescLabel=new JLabel(Util.getResources().getString("dialog.label.admin.deptDesc"));
        dptDescLabel.setBounds(10,70, 150, 30);
        add(dptDescLabel);	
        JTextArea deptDescTA=new JTextArea();
        deptDescTA.setBounds(170, 70, 200,80);
        add(deptDescTA);
        JButton addButton=new JButton(resources.getString("dialog.button.admin.add"));
        //addButton.addActionListener(arg0);        
        addButton.setBounds(170,160, 70, 30);
        add(addButton);
		GuiUtil.CompCenterCords cords=GuiUtil.getCompCenterCords(480, 250);
		setBounds(cords.getX(), cords.getY(), 480, 250);
		setResizable(false);
		setVisible(true);
	}
}
