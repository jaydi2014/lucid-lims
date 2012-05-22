/**
 * 
 */
package org.lims.admin.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.lims.admin.service.AdminService;
import org.lims.admin.service.AdminServiceInter;
import org.lims.gui.util.GuiUtil;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class AddEmployeeDialog extends JDialog{

	
	private static final long serialVersionUID = -8814476378295126789L;
	
	private ResourceBundle resources=Util.getResources();
	private AdminServiceInter adminService=new AdminService();
	
	private JLabel empIdLabel;
	private JTextField empIdTF;
	private JLabel empNameLabel;
	private JTextField empNameTF;
	private JLabel empDesgLabel;
	private JTextField empDesgTF;
	private JLabel empDeptLabel;
	private JComboBox empDeptCB;
	private JLabel empRoleLabel;
	private JComboBox empRoleCB;
	private JLabel empPwdLabel;
	private JPasswordField empPwdPF;
	private JLabel empRePwdLabel;
	private JPasswordField empRePwdPF;

	public AddEmployeeDialog(Frame owner, String title, boolean modal) {
		super(owner,title,modal);		
		getContentPane().setLayout(new BorderLayout());
		JPanel centerPanel=new JPanel();
		centerPanel.setLayout(null);
		empIdLabel=new JLabel(resources.getString("admin.dialog.label.empId"));
		empIdLabel.setBounds(10, 30, 150, 30);
		empIdLabel.setForeground(Color.BLACK);
		centerPanel.add(empIdLabel);	
		empIdTF=new JTextField();
		empIdTF.setBounds(170, 30, 150,30);
        centerPanel.add(empIdTF);
        
        empNameLabel=new JLabel(resources.getString("admin.dialog.label.empName"));
		empNameLabel.setBounds(10,70, 150, 30);
		empNameLabel.setForeground(Color.BLACK);
		centerPanel.add(empNameLabel);	
		empNameTF=new JTextField();
		empNameTF.setBounds(170, 70, 150,30);
        centerPanel.add(empNameTF);
        
        empDesgLabel=new JLabel(resources.getString("admin.dialog.label.empDesignation"));
		empDesgLabel.setBounds(10,110, 150, 30);
		empDesgLabel.setForeground(Color.BLACK);
		centerPanel.add(empDesgLabel);	
		empDesgTF=new JTextField();
		empDesgTF.setBounds(170, 110, 150,30);
        centerPanel.add(empDesgTF);
        
        empDeptLabel=new JLabel(resources.getString("admin.dialog.label.empDept"));
		empDeptLabel.setBounds(10,150, 150, 30);
		empDeptLabel.setForeground(Color.BLACK);
		centerPanel.add(empDeptLabel);
		Object[] depts=null;
		try{
			List<String> deptList=adminService.getDepartments();
			depts=deptList.toArray();
		}catch(Exception e){
			e.printStackTrace();
		}
		empDeptCB=new JComboBox(depts);
		empDeptCB.setBounds(170, 150, 150,30);
        centerPanel.add(empDeptCB);
        
        empRoleLabel=new JLabel(resources.getString("admin.dialog.label.empRole"));
		empRoleLabel.setBounds(10,190, 150, 30);
		empRoleLabel.setForeground(Color.BLACK);
		centerPanel.add(empRoleLabel);
		Object[] roles=null;
		try{
			List<String> roleList=adminService.getRoles();
			roles=roleList.toArray();
		}catch(Exception e){
			e.printStackTrace();
		}
		empRoleCB=new JComboBox(roles);
		empRoleCB.setBounds(170, 190, 150,30);
        centerPanel.add(empRoleCB);
        
        empPwdLabel=new JLabel(resources.getString("admin.dialog.label.empPwd"));
		empPwdLabel.setBounds(10,240, 150, 30);
		empPwdLabel.setForeground(Color.BLACK);
		centerPanel.add(empPwdLabel);	
		empPwdPF=new JPasswordField();
		empPwdPF.setBounds(170, 240, 150,30);
        centerPanel.add(empPwdPF);
        
        empRePwdLabel=new JLabel(resources.getString("admin.dialog.label.empPwd"));
		empRePwdLabel.setBounds(10,280, 150, 30);
		empRePwdLabel.setForeground(Color.BLACK);
		centerPanel.add(empRePwdLabel);	
		empRePwdPF=new JPasswordField();
		empRePwdPF.setBounds(170, 280, 150,30);
        centerPanel.add(empRePwdPF);
        
        JButton addButton=new JButton(resources.getString("admin.dialog.button.emp.add"));
       // addButton.addActionListener(new AddRoleButtonListener(this));        
        addButton.setBounds(170,320, 70, 30);
        centerPanel.add(addButton);
        add(centerPanel,BorderLayout.CENTER);
		GuiUtil.CompCenterCords cords=GuiUtil.getCompCenterCords(480, 550);
		setBounds(cords.getX(), cords.getY(), 480, 550);		
		setVisible(true);
	}
}
