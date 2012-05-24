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

import org.lims.admin.gui.AddRoleDialog;
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
public class AddRoleButtonListener implements ActionListener{

	private AddRoleDialog addRoleDialog;
	
	private AdminServiceInter service=new AdminService();
	
	private ErrorsDisplayJPanel errorMsgPanel;
	
	private JPanel successPanel;
	
	public AddRoleButtonListener(AddRoleDialog addRoleDialog){
		this.addRoleDialog=addRoleDialog;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		cleanup();
		String roleName=addRoleDialog.getRoleNameTF().getText();
		String roleDesc=addRoleDialog.getRoleDescTA().getText();
		try{
			service.addRole(roleName, roleDesc);
			
			successPanel=GuiUtil.getSuccessMsgPanel(Util.getResources().getString("roleAddedSuccessfully"));
			addRoleDialog.add(successPanel,BorderLayout.NORTH);
			addRoleDialog.validate();
			addRoleDialog.repaint();
			addRoleDialog.getRoleNameTF().setText("");
			addRoleDialog.getRoleDescTA().setText("");
		}catch(Exception e){
			HashMap<String,String> exceptions=AdminServiceInter.exceptions;
			
			if(e instanceof ValidationErrorsException){
				errorMsgPanel = new ErrorsDisplayJPanel(exceptions.size());
				if(exceptions.containsKey("ROLE")){
					addRoleDialog.getRoleNameLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("ROLE");
					errorMsgPanel.addErrMsg(errMsg);
				}
				
				if(exceptions.containsKey("ROLE_DESC")){
					addRoleDialog.getRoleDescLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("ROLE_DESC");
					errorMsgPanel.addErrMsg(errMsg);
				}
			}else{				
				e.printStackTrace();
			}
			
			addRoleDialog.add(errorMsgPanel,BorderLayout.NORTH);
			addRoleDialog.validate();
			addRoleDialog.repaint();
			
		}
		
	}
	
	/**
	 * This performs the cleanup of error decorations.
	 */
	private void cleanup(){
		if(errorMsgPanel!=null){
			addRoleDialog.getRoleNameLabel().setForeground(Color.BLACK);
			addRoleDialog.getRoleDescLabel().setForeground(Color.BLACK);
			addRoleDialog.remove(errorMsgPanel);
			addRoleDialog.validate();
			addRoleDialog.repaint();
			
		}
		
		if(successPanel!=null){
			addRoleDialog.remove(successPanel);
			addRoleDialog.validate();
			addRoleDialog.repaint();
		}
		
	}

	
}
