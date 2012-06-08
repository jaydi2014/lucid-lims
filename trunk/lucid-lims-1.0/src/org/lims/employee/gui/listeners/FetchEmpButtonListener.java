/**
 * 
 */
package org.lims.employee.gui.listeners;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.lims.employee.dto.EmployeeDto;
import org.lims.employee.gui.ViewEmpDialog;
import org.lims.employee.service.EmployeeService;
import org.lims.employee.service.EmployeeServiceInter;
import org.lims.gui.util.ErrorsDisplayJPanel;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class FetchEmpButtonListener implements ActionListener{
	
	private ViewEmpDialog viewEmpDialog;
	
	private Logger log=Logger.getLogger(FetchEmpButtonListener.class);
	private static ResourceBundle resources=Util.getResources();
	
	private EmployeeServiceInter empService=new EmployeeService();
	
	private ErrorsDisplayJPanel errorMsgPanel;	
	
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
	private JPanel employeePanel;
	
	
	public FetchEmpButtonListener(ViewEmpDialog viewEmpDialog){
		this.viewEmpDialog=viewEmpDialog;
	}


	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		cleanup();
		String empId=viewEmpDialog.getEmpIdTF().getText();
		try{
			if(employeePanel!=null){
        		viewEmpDialog.remove(employeePanel);
        		viewEmpDialog.validate();
        		viewEmpDialog.repaint();
        	}
			EmployeeDto employee=empService.getEmployee(empId);
			if(employee.getEmpId()==null){
				errorMsgPanel = new ErrorsDisplayJPanel(1);
				errorMsgPanel.addErrMsg(resources.getString("employeeIdNotExist"));
				viewEmpDialog.add(errorMsgPanel,BorderLayout.NORTH);
				viewEmpDialog.validate();
				viewEmpDialog.repaint();
			}else{
				employeePanel=createCentralPanel(employee);
        		viewEmpDialog.add(employeePanel,BorderLayout.CENTER);
        		viewEmpDialog.validate();
        		viewEmpDialog.repaint();
			}
				
		}catch(Exception e){
			log.debug(e.getMessage(), e);
		}
	}
	
	/**
	 * This performs the cleanup of error decorations.
	 */
	private void cleanup(){
		if(errorMsgPanel!=null){			
			viewEmpDialog.remove(errorMsgPanel);
			viewEmpDialog.validate();
			viewEmpDialog.repaint();			
		}			
	}
	
	/**
	 * This creates the central panel.
	 * @return JPanel.
	 */
	private JPanel createCentralPanel(EmployeeDto employee){
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
        setFieldsForView(employee);
        
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

}
