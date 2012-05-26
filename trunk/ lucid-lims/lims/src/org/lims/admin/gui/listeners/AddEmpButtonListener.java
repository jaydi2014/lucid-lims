/**
 * 
 */
package org.lims.admin.gui.listeners;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JPanel;

import org.lims.admin.dto.EmployeeDto;
import org.lims.admin.gui.AddEmployeeDialog;
import org.lims.admin.service.AdminService;
import org.lims.admin.service.AdminServiceInter;
import org.lims.common.exceptions.ValidationErrorsException;
import org.lims.gui.util.ErrorsDisplayJPanel;
import org.lims.gui.util.GuiUtil;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class AddEmpButtonListener implements ActionListener{
	
	private AddEmployeeDialog empDialog;
	
	private AdminServiceInter service=new AdminService();
	
	private ErrorsDisplayJPanel errorMsgPanel;
	
	private JPanel successPanel;
	
	public AddEmpButtonListener(AddEmployeeDialog empDialog){
		this.empDialog=empDialog;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		cleanup();
		EmployeeDto empdto=setEmployeeDto(empDialog);
		
		try{
			service.addEmployee(empdto);
			
			successPanel=GuiUtil.getSuccessMsgPanel(Util.getResources().getString("empAddedSuccessfully"));
			empDialog.add(successPanel,BorderLayout.NORTH);
			empDialog.validate();
			empDialog.repaint();
			postServiceCleanup();
		}catch(Exception e){			
			if(e instanceof ValidationErrorsException){
				HashMap<String,String> exceptions=AdminServiceInter.exceptions;
				errorMsgPanel = new ErrorsDisplayJPanel(exceptions.size());
				if(exceptions.containsKey("EMP_ID")){
					empDialog.getEmpIdLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("EMP_ID");
					errorMsgPanel.addErrMsg(errMsg);
				}
				
				if(exceptions.containsKey("EMP_NAME")){
					empDialog.getEmpNameLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("EMP_NAME");
					errorMsgPanel.addErrMsg(errMsg);
				}
				
				if(exceptions.containsKey("EMP_DESG")){
					empDialog.getEmpDesgLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("EMP_DESG");
					errorMsgPanel.addErrMsg(errMsg);
				}
				
				if(exceptions.containsKey("EMP_PWD")){
					empDialog.getEmpPwdLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("EMP_PWD");
					errorMsgPanel.addErrMsg(errMsg);
				}
				
				if(exceptions.containsKey("EMP_REPWD")){
					empDialog.getEmpRePwdLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("EMP_REPWD");
					errorMsgPanel.addErrMsg(errMsg);
				}
				
				if(exceptions.containsKey("EMP_PWD_EQL")){
					empDialog.getEmpPwdLabel().setForeground(Color.RED);
					empDialog.getEmpRePwdLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("EMP_PWD_EQL");
					errorMsgPanel.addErrMsg(errMsg);
					
				}
				
				if(exceptions.containsKey("EMP_DISPLAY_NAME")){
					empDialog.getEmpDisplayNameLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("EMP_DISPLAY_NAME");
					errorMsgPanel.addErrMsg(errMsg);
					
				}
				
				if(exceptions.containsKey("EMP_USER_NAME")){
					empDialog.getEmpUserNameLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("EMP_USER_NAME");
					errorMsgPanel.addErrMsg(errMsg);
					
				}
				empDialog.add(errorMsgPanel,BorderLayout.NORTH);
				empDialog.validate();
				empDialog.repaint();
			}else{								
				e.printStackTrace();
			}
			
			
		}
	}
	
	/**
	 * This performs the cleanup of error decorations.
	 */
	private void cleanup(){
		if(errorMsgPanel!=null){
			empDialog.getEmpIdLabel().setForeground(Color.BLACK);
			empDialog.getEmpNameLabel().setForeground(Color.BLACK);
			empDialog.getEmpDesgLabel().setForeground(Color.BLACK);
			empDialog.getEmpPwdLabel().setForeground(Color.BLACK);
			empDialog.getEmpRePwdLabel().setForeground(Color.BLACK);
			empDialog.getEmpDisplayNameLabel().setForeground(Color.BLACK);
			empDialog.getEmpUserNameLabel().setForeground(Color.BLACK);
			empDialog.remove(errorMsgPanel);
			empDialog.validate();
			empDialog.repaint();
			
		}
		
		if(successPanel!=null){
			empDialog.remove(successPanel);
			empDialog.validate();
			empDialog.repaint();
		}
		
	}
	
	/**
	 * This performs the post service cleanup of GUI.
	 */
	private void postServiceCleanup(){		
		empDialog.getEmpIdTF().setText("");
		empDialog.getEmpNameTF().setText("");
		empDialog.getEmpDesgTF().setText("");
		empDialog.getEmpPwdPF().setText("");
		empDialog.getEmpRePwdPF().setText("");	
		empDialog.getEmpDisplayNameTF().setText("");
		empDialog.getEmpUserNameTF().setText("");
	}

	/**
	 * This sets the EmployeeDto.
	 * @param empDialog
	 * @return EmployeeDto.
	 */
	private EmployeeDto setEmployeeDto(AddEmployeeDialog empDialog){
		EmployeeDto empdto=new EmployeeDto();
		empdto.setEmpId(empDialog.getEmpIdTF().getText());
		empdto.setEmpName(empDialog.getEmpNameTF().getText());
		empdto.setEmpDesignation(empDialog.getEmpDesgTF().getText());
		empdto.setEmpDepartment((String)empDialog.getEmpDeptCB().getModel().getSelectedItem());
		empdto.setEmpRole((String)empDialog.getEmpRoleCB().getModel().getSelectedItem());
		String password=new String(empDialog.getEmpPwdPF().getPassword());
		empdto.setPassword(password);
		String retypedPassword=new String(empDialog.getEmpRePwdPF().getPassword());
		empdto.setRetypedPwd(retypedPassword);
		empdto.setEmpDisplayName(empDialog.getEmpDisplayNameTF().getText());
		empdto.setUserName(empDialog.getEmpUserNameTF().getText());
		return empdto;
	}
}
