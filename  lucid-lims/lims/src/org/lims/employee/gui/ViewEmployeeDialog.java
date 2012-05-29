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
	        JButton addButton=new JButton(resources.getString("profie.dialog.button.updateEmp"));
	       // addButton.addActionListener(new AddEmpButtonListener(this));        
	        addButton.setBounds(170,370, 70, 30);
	        mainPanel.add(addButton);
        }
        
        if(actionCmd.equals("VIEW")){
        	setFieldsForView(employee);
        }
        
        return mainPanel;
	}
	
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
}
