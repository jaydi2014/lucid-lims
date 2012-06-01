/**
 * 
 */
package org.lims.employee.gui.listeners;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.lims.common.exceptions.ValidationErrorsException;
import org.lims.employee.gui.ChangePasswordDialog;
import org.lims.employee.service.EmployeeService;
import org.lims.employee.service.EmployeeServiceInter;
import org.lims.gui.util.ErrorsDisplayJPanel;
import org.lims.gui.util.GuiUtil;
import org.lims.main.Lims;
import org.lims.util.Constants;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class UpdatePwdButtonListener implements ActionListener{

	private ChangePasswordDialog changePwdDialog;
	
	private Logger log=Logger.getLogger(UpdatePwdButtonListener.class);
	
	private EmployeeServiceInter service=new EmployeeService();
	
	private ErrorsDisplayJPanel errorMsgPanel;
	
	private JPanel successPanel;
	
	public UpdatePwdButtonListener(ChangePasswordDialog changePwdDialog){
		this.changePwdDialog=changePwdDialog;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		cleanup();
		String empId=Lims.getSessionmap().get(Constants.EMP_ID);
		String password=new String(changePwdDialog.getEmpPasswordTF().getPassword());
		String newPassword=new String(changePwdDialog.getEmpNewPasswordTF().getPassword());
		String retypedPassword=new String(changePwdDialog.getEmpReNewPasswordTF().getPassword());
		
		try{
			service.changePassword(empId, password, newPassword, retypedPassword);
			
			successPanel=GuiUtil.getSuccessMsgPanel(Util.getResources().getString("passwordChangedSuccessfully"));
			changePwdDialog.add(successPanel,BorderLayout.NORTH);
			changePwdDialog.validate();
			changePwdDialog.repaint();
			postServiceCleanup();
		}catch(Exception e){
			HashMap<String,String> exceptions=EmployeeServiceInter.exceptions;
			
			if(e instanceof ValidationErrorsException){
				errorMsgPanel = new ErrorsDisplayJPanel(exceptions.size());
				if(exceptions.containsKey("EMP_PWD")){
					changePwdDialog.getEmpPasswordLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("EMP_PWD");
					errorMsgPanel.addErrMsg(errMsg);
				}
				
				if(exceptions.containsKey("EMP_NEW_PWD")){
					changePwdDialog.getEmpNewPasswordLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("EMP_NEW_PWD");
					errorMsgPanel.addErrMsg(errMsg);
				}
				
				if(exceptions.containsKey("EMP_RETYPED_PWD")){
					changePwdDialog.getEmpReNewPasswordLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("EMP_RETYPED_PWD");
					errorMsgPanel.addErrMsg(errMsg);
				}
				
				if(exceptions.containsKey("EMP_BOTH_PWD")){
					changePwdDialog.getEmpNewPasswordLabel().setForeground(Color.RED);
					changePwdDialog.getEmpReNewPasswordLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("EMP_BOTH_PWD");
					errorMsgPanel.addErrMsg(errMsg);
				}
			}else{				
				log.debug(e.getMessage(), e);
			}
			
			changePwdDialog.add(errorMsgPanel,BorderLayout.NORTH);
			changePwdDialog.validate();
			changePwdDialog.repaint();
			
		}
		
	}
	
	/**
	 * This performs the cleanup of error decorations.
	 */
	private void cleanup(){
		if(errorMsgPanel!=null){
			changePwdDialog.getEmpPasswordLabel().setForeground(Color.BLACK);
			changePwdDialog.getEmpNewPasswordLabel().setForeground(Color.BLACK);
			changePwdDialog.getEmpReNewPasswordLabel().setForeground(Color.BLACK);
			changePwdDialog.remove(errorMsgPanel);
			changePwdDialog.validate();
			changePwdDialog.repaint();
			
		}
		
		if(successPanel!=null){
			changePwdDialog.remove(successPanel);
			changePwdDialog.validate();
			changePwdDialog.repaint();
		}
		
	}

	private void postServiceCleanup(){
		changePwdDialog.getEmpPasswordTF().setText("");
		changePwdDialog.getEmpNewPasswordTF().setText("");
		changePwdDialog.getEmpReNewPasswordTF().setText("");
	}
	

}
