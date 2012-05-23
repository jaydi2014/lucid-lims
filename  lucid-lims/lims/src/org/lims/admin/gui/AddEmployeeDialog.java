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

import org.lims.admin.gui.listeners.AddEmpButtonListener;
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
		JPanel centerPanel=createCentralPanel();
        add(centerPanel,BorderLayout.CENTER);
		GuiUtil.CompCenterCords cords=GuiUtil.getCompCenterCords(480, 550);
		setBounds(cords.getX(), cords.getY(), 480, 550);		
		setVisible(true);
	}
	
	/**
	 * This creates the central panel.
	 * @return JPanel.
	 */
	private JPanel createCentralPanel(){
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
        
        empRePwdLabel=new JLabel(resources.getString("admin.dialog.label.empRePwd"));
		empRePwdLabel.setBounds(10,280, 150, 30);
		empRePwdLabel.setForeground(Color.BLACK);
		centerPanel.add(empRePwdLabel);	
		empRePwdPF=new JPasswordField();
		empRePwdPF.setBounds(170, 280, 150,30);
        centerPanel.add(empRePwdPF);
        
        JButton addButton=new JButton(resources.getString("admin.dialog.button.emp.add"));
        addButton.addActionListener(new AddEmpButtonListener(this));        
        addButton.setBounds(170,320, 70, 30);
        centerPanel.add(addButton);
        
        return centerPanel;
	}

	/**
	 * @return the empIdLabel
	 */
	public JLabel getEmpIdLabel() {
		return empIdLabel;
	}

	/**
	 * @param empIdLabel the empIdLabel to set
	 */
	public void setEmpIdLabel(JLabel empIdLabel) {
		this.empIdLabel = empIdLabel;
	}

	/**
	 * @return the empIdTF
	 */
	public JTextField getEmpIdTF() {
		return empIdTF;
	}

	/**
	 * @param empIdTF the empIdTF to set
	 */
	public void setEmpIdTF(JTextField empIdTF) {
		this.empIdTF = empIdTF;
	}

	/**
	 * @return the empNameLabel
	 */
	public JLabel getEmpNameLabel() {
		return empNameLabel;
	}

	/**
	 * @param empNameLabel the empNameLabel to set
	 */
	public void setEmpNameLabel(JLabel empNameLabel) {
		this.empNameLabel = empNameLabel;
	}

	/**
	 * @return the empNameTF
	 */
	public JTextField getEmpNameTF() {
		return empNameTF;
	}

	/**
	 * @param empNameTF the empNameTF to set
	 */
	public void setEmpNameTF(JTextField empNameTF) {
		this.empNameTF = empNameTF;
	}

	/**
	 * @return the empDesgLabel
	 */
	public JLabel getEmpDesgLabel() {
		return empDesgLabel;
	}

	/**
	 * @param empDesgLabel the empDesgLabel to set
	 */
	public void setEmpDesgLabel(JLabel empDesgLabel) {
		this.empDesgLabel = empDesgLabel;
	}

	/**
	 * @return the empDesgTF
	 */
	public JTextField getEmpDesgTF() {
		return empDesgTF;
	}

	/**
	 * @param empDesgTF the empDesgTF to set
	 */
	public void setEmpDesgTF(JTextField empDesgTF) {
		this.empDesgTF = empDesgTF;
	}

	/**
	 * @return the empDeptLabel
	 */
	public JLabel getEmpDeptLabel() {
		return empDeptLabel;
	}

	/**
	 * @param empDeptLabel the empDeptLabel to set
	 */
	public void setEmpDeptLabel(JLabel empDeptLabel) {
		this.empDeptLabel = empDeptLabel;
	}

	/**
	 * @return the empDeptCB
	 */
	public JComboBox getEmpDeptCB() {
		return empDeptCB;
	}

	/**
	 * @param empDeptCB the empDeptCB to set
	 */
	public void setEmpDeptCB(JComboBox empDeptCB) {
		this.empDeptCB = empDeptCB;
	}

	/**
	 * @return the empRoleLabel
	 */
	public JLabel getEmpRoleLabel() {
		return empRoleLabel;
	}

	/**
	 * @param empRoleLabel the empRoleLabel to set
	 */
	public void setEmpRoleLabel(JLabel empRoleLabel) {
		this.empRoleLabel = empRoleLabel;
	}

	/**
	 * @return the empRoleCB
	 */
	public JComboBox getEmpRoleCB() {
		return empRoleCB;
	}

	/**
	 * @param empRoleCB the empRoleCB to set
	 */
	public void setEmpRoleCB(JComboBox empRoleCB) {
		this.empRoleCB = empRoleCB;
	}

	/**
	 * @return the empPwdLabel
	 */
	public JLabel getEmpPwdLabel() {
		return empPwdLabel;
	}

	/**
	 * @param empPwdLabel the empPwdLabel to set
	 */
	public void setEmpPwdLabel(JLabel empPwdLabel) {
		this.empPwdLabel = empPwdLabel;
	}

	/**
	 * @return the empPwdPF
	 */
	public JPasswordField getEmpPwdPF() {
		return empPwdPF;
	}

	/**
	 * @param empPwdPF the empPwdPF to set
	 */
	public void setEmpPwdPF(JPasswordField empPwdPF) {
		this.empPwdPF = empPwdPF;
	}

	/**
	 * @return the empRePwdLabel
	 */
	public JLabel getEmpRePwdLabel() {
		return empRePwdLabel;
	}

	/**
	 * @param empRePwdLabel the empRePwdLabel to set
	 */
	public void setEmpRePwdLabel(JLabel empRePwdLabel) {
		this.empRePwdLabel = empRePwdLabel;
	}

	/**
	 * @return the empRePwdPF
	 */
	public JPasswordField getEmpRePwdPF() {
		return empRePwdPF;
	}

	/**
	 * @param empRePwdPF the empRePwdPF to set
	 */
	public void setEmpRePwdPF(JPasswordField empRePwdPF) {
		this.empRePwdPF = empRePwdPF;
	}
}
