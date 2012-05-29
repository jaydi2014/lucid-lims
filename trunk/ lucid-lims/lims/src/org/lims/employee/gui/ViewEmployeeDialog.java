/**
 * 
 */
package org.lims.employee.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.lims.employee.dto.EmployeeDto;
import org.lims.employee.gui.listeners.UpdateEmpButtonListener;
import org.lims.employee.service.EmployeeService;
import org.lims.employee.service.EmployeeServiceInter;
import org.lims.gui.util.GuiUtil;
import org.lims.main.Lims;
import org.lims.util.Constants;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class ViewEmployeeDialog extends JDialog{

	private static final long serialVersionUID = -8814476378295126789L;
	private Logger log=Logger.getLogger(ViewEmployeeDialog.class);
	
	private ResourceBundle resources=Util.getResources();
	private EmployeeServiceInter service=new EmployeeService();
	
	private JLabel empIdLabel;
	private JTextField empIdTF;
	private JLabel empNameLabel;
	private JTextField empNameTF;
	private JLabel empDisplayNameLabel;
	private JTextField empDisplayNameTF;
	private JLabel empDesgLabel;
	private JTextField empDesgTF;
	private JLabel empDeptLabel;
	private JTextField empDeptTF;
	private JLabel empRoleLabel;
	private JTextField empRoleTF;
	private JLabel empPhoneNumLabel;
	private JTextField empPhoneNumTF;
	private JLabel empMobileLabel;
	private JTextField empMobileTF;
	
	

	public ViewEmployeeDialog(Frame owner, String title, boolean modal,String actionCmd) {
		super(owner,title,modal);		
		getContentPane().setLayout(new BorderLayout());
		EmployeeDto employee=null;
		try{
			employee=service.getEmployee(Lims.getSessionmap().get(Constants.EMP_ID));
		}catch(Exception e){
			log.debug(e.getMessage(), e);
		}
		JPanel centerPanel=createCentralPanel(employee,actionCmd);
        add(centerPanel,BorderLayout.CENTER);
		GuiUtil.CompCenterCords cords=GuiUtil.getCompCenterCords(480, 550);
		setBounds(cords.getX(), cords.getY(), 480, 550);		
		setVisible(true);
	}
	
	/**
	 * This creates the central panel.
	 * @return JPanel.
	 */
	private JPanel createCentralPanel(EmployeeDto employee,String actionCmd){
		JPanel mainPanel=new JPanel();
		mainPanel.setLayout(null);
		
		JPanel centerPanel=new JPanel();		
		centerPanel.setLayout(new GridLayout(9,2));
		empIdLabel=new JLabel(resources.getString("profile.dialog.label.empId"));
		empIdLabel.setForeground(Color.BLACK);
		centerPanel.add(empIdLabel);	
		empIdTF=new JTextField();
		centerPanel.add(empIdTF);
        
        empNameLabel=new JLabel(resources.getString("profile.dialog.label.empName"));
		empNameLabel.setForeground(Color.BLACK);
		centerPanel.add(empNameLabel);	
		empNameTF=new JTextField();
		centerPanel.add(empNameTF);
        
        empDisplayNameLabel=new JLabel(resources.getString("profile.dialog.label.empDisplayName"));
        empDisplayNameLabel.setForeground(Color.BLACK);
		centerPanel.add(empDisplayNameLabel);	
		empDisplayNameTF=new JTextField();
		centerPanel.add(empDisplayNameTF);
        
        empDesgLabel=new JLabel(resources.getString("profile.dialog.label.empDesignation"));
		empDesgLabel.setForeground(Color.BLACK);
		centerPanel.add(empDesgLabel);	
		empDesgTF=new JTextField();
		 centerPanel.add(empDesgTF);
        
        empDeptLabel=new JLabel(resources.getString("profile.dialog.label.empDept"));
		empDeptLabel.setForeground(Color.BLACK);
		centerPanel.add(empDeptLabel);
		empDeptTF=new JTextField();
		centerPanel.add(empDeptTF);
        
        empRoleLabel=new JLabel(resources.getString("profile.dialog.label.empRole"));
		empRoleLabel.setForeground(Color.BLACK);
		centerPanel.add(empRoleLabel);
		empRoleTF=new JTextField();
		centerPanel.add(empRoleTF);
        
		empPhoneNumLabel=new JLabel(resources.getString("profile.dialog.label.phoneNum"));
		empPhoneNumLabel.setForeground(Color.BLACK);
		centerPanel.add(empPhoneNumLabel);	
		empPhoneNumTF=new JTextField();
		centerPanel.add(empPhoneNumTF);
        
		empMobileLabel=new JLabel(resources.getString("profile.dialog.label.mobile"));
		empMobileLabel.setForeground(Color.BLACK);
		centerPanel.add(empMobileLabel);	
		empMobileTF=new JTextField();
		centerPanel.add(empMobileTF);
        
        
        centerPanel.setBounds(50,50, 300, 300);
        mainPanel.add(centerPanel);
        if(actionCmd.equals("UPDATE")){
        	setFieldsForUpdate(employee);
	        JButton addButton=new JButton(resources.getString("profie.dialog.button.updateEmp"));
	        addButton.addActionListener(new UpdateEmpButtonListener(this));        
	        addButton.setBounds(170,370, 150, 30);
	        mainPanel.add(addButton);
        }
        
        if(actionCmd.equals("VIEW")){
        	setFieldsForView(employee);
        }
        
        return mainPanel;
	}
	
	/**
	 * sets the fields for view.
	 * @param employee
	 */
	private void setFieldsForView(EmployeeDto employee){
		empIdTF.setEditable(false);
		empIdTF.setText(employee.getEmpId());
		empNameTF.setEditable(false);
		empNameTF.setText(employee.getEmpName());
		empDisplayNameTF.setEditable(false);
		empDisplayNameTF.setText(employee.getEmpDisplayName());
		empDesgTF.setEditable(false);
		empDesgTF.setText(employee.getEmpDesignation());
		empDeptTF.setEditable(false);
		empDeptTF.setText(employee.getEmpDepartment());
		empRoleTF.setEditable(false);
		empRoleTF.setText(employee.getEmpRole());
		empPhoneNumTF.setEditable(false);
		empPhoneNumTF.setText(employee.getPhoneNo());
		empMobileTF.setEditable(false);
		empMobileTF.setText(employee.getMobileNo());
	}
	
	/**
	 * sets the fields for update operation.
	 * @param employee
	 */
	private void setFieldsForUpdate(EmployeeDto employee){
		empIdTF.setEditable(false);
		empIdTF.setText(employee.getEmpId());
		empNameTF.setEditable(false);
		empNameTF.setText(employee.getEmpName());
		empDisplayNameTF.setEditable(false);
		empDisplayNameTF.setText(employee.getEmpDisplayName());
		empDesgTF.setEditable(false);
		empDesgTF.setText(employee.getEmpDesignation());
		empDeptTF.setEditable(false);
		empDeptTF.setText(employee.getEmpDepartment());
		empRoleTF.setEditable(false);
		empRoleTF.setText(employee.getEmpRole());		
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
	 * @return the empDisplayNameLabel
	 */
	public JLabel getEmpDisplayNameLabel() {
		return empDisplayNameLabel;
	}

	/**
	 * @param empDisplayNameLabel the empDisplayNameLabel to set
	 */
	public void setEmpDisplayNameLabel(JLabel empDisplayNameLabel) {
		this.empDisplayNameLabel = empDisplayNameLabel;
	}

	/**
	 * @return the empDisplayNameTF
	 */
	public JTextField getEmpDisplayNameTF() {
		return empDisplayNameTF;
	}

	/**
	 * @param empDisplayNameTF the empDisplayNameTF to set
	 */
	public void setEmpDisplayNameTF(JTextField empDisplayNameTF) {
		this.empDisplayNameTF = empDisplayNameTF;
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
	 * @return the empDeptTF
	 */
	public JTextField getEmpDeptTF() {
		return empDeptTF;
	}

	/**
	 * @param empDeptTF the empDeptTF to set
	 */
	public void setEmpDeptTF(JTextField empDeptTF) {
		this.empDeptTF = empDeptTF;
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
	 * @return the empRoleTF
	 */
	public JTextField getEmpRoleTF() {
		return empRoleTF;
	}

	/**
	 * @param empRoleTF the empRoleTF to set
	 */
	public void setEmpRoleTF(JTextField empRoleTF) {
		this.empRoleTF = empRoleTF;
	}

	/**
	 * @return the empPhoneNumLabel
	 */
	public JLabel getEmpPhoneNumLabel() {
		return empPhoneNumLabel;
	}

	/**
	 * @param empPhoneNumLabel the empPhoneNumLabel to set
	 */
	public void setEmpPhoneNumLabel(JLabel empPhoneNumLabel) {
		this.empPhoneNumLabel = empPhoneNumLabel;
	}

	/**
	 * @return the empPhoneNumTF
	 */
	public JTextField getEmpPhoneNumTF() {
		return empPhoneNumTF;
	}

	/**
	 * @param empPhoneNumTF the empPhoneNumTF to set
	 */
	public void setEmpPhoneNumTF(JTextField empPhoneNumTF) {
		this.empPhoneNumTF = empPhoneNumTF;
	}

	/**
	 * @return the empMobileLabel
	 */
	public JLabel getEmpMobileLabel() {
		return empMobileLabel;
	}

	/**
	 * @param empMobileLabel the empMobileLabel to set
	 */
	public void setEmpMobileLabel(JLabel empMobileLabel) {
		this.empMobileLabel = empMobileLabel;
	}

	/**
	 * @return the empMobileTF
	 */
	public JTextField getEmpMobileTF() {
		return empMobileTF;
	}

	/**
	 * @param empMobileTF the empMobileTF to set
	 */
	public void setEmpMobileTF(JTextField empMobileTF) {
		this.empMobileTF = empMobileTF;
	}
}
