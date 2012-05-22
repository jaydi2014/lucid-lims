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

import org.lims.admin.gui.AddDepartmentDialog;
import org.lims.admin.service.AdminService;
import org.lims.admin.service.AdminServiceInter;
import org.lims.common.exceptions.ValidationErrorsException;
import org.lims.gui.util.GuiUtil;
import org.lims.util.ErrorsDisplayJPanel;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class AddDeptButtonListener implements ActionListener{
	
	private AddDepartmentDialog addDeptDialog;
	
	private AdminServiceInter service=new AdminService();
	
	private ErrorsDisplayJPanel errorMsgPanel;
	
	private JPanel successPanel;
	
	public AddDeptButtonListener(AddDepartmentDialog addDeptDialog){
		this.addDeptDialog=addDeptDialog;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		cleanup();
		String deptName=addDeptDialog.getDeptNameTF().getText();
		String deptDesc=addDeptDialog.getDeptDescTA().getText();
		try{
			service.addDepartment(deptName, deptDesc);
			successPanel=GuiUtil.getSuccessMsgPanel(Util.getResources().getString("departmentAddedSuccessfully"));
			addDeptDialog.add(successPanel,BorderLayout.NORTH);
			addDeptDialog.validate();
			addDeptDialog.repaint();
			addDeptDialog.getDeptNameTF().setText("");
			addDeptDialog.getDeptDescTA().setText("");
		}catch(Exception e){
			HashMap<String,String> exceptions=AdminServiceInter.exceptions;
			errorMsgPanel = new ErrorsDisplayJPanel(exceptions.size());
			if(e instanceof ValidationErrorsException){
				if(exceptions.containsKey("DEPT")){
					addDeptDialog.getDptNameLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("DEPT");
					errorMsgPanel.addErrMsg(errMsg);
				}
				
				if(exceptions.containsKey("DEPT_DESC")){
					addDeptDialog.getDptDescLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("DEPT_DESC");
					errorMsgPanel.addErrMsg(errMsg);
				}
			}else{
				String errMsg=exceptions.remove("OTHER");
				errorMsgPanel.addErrMsg(errMsg);
			}
			
			addDeptDialog.add(errorMsgPanel,BorderLayout.NORTH);
			addDeptDialog.validate();
			addDeptDialog.repaint();
			
		}
		
	}
	
	/**
	 * This performs the cleanup of error decorations.
	 */
	private void cleanup(){
		if(errorMsgPanel!=null){
			addDeptDialog.getDptNameLabel().setForeground(Color.BLACK);
			addDeptDialog.getDptDescLabel().setForeground(Color.BLACK);
			addDeptDialog.remove(errorMsgPanel);
			addDeptDialog.validate();
			addDeptDialog.repaint();
			
		}
		
		if(successPanel!=null){
			addDeptDialog.remove(successPanel);
			addDeptDialog.validate();
			addDeptDialog.repaint();
		}
		
	}

	
}
