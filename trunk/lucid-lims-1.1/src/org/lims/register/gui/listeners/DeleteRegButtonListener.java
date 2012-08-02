/**
 * 
 */
package org.lims.register.gui.listeners;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JPanel;
import javax.swing.JTextPane;

import org.apache.log4j.Logger;
import org.lims.gui.util.ErrorsDisplayJPanel;
import org.lims.gui.util.GuiUtil;
import org.lims.register.dto.SampleDto;
import org.lims.register.dto.TestRegisterDto;
import org.lims.register.gui.DeleteRegisterNumDialog;
import org.lims.register.service.RegisterService;
import org.lims.register.service.RegisterServiceInter;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class DeleteRegButtonListener implements ActionListener{
	
	private DeleteRegisterNumDialog regNumDialog;	
	private ResourceBundle resources=Util.getResources();	
	private Logger log=Logger.getLogger(DeleteRegButtonListener.class);	
	private RegisterServiceInter service=new RegisterService();	
	private ErrorsDisplayJPanel errorMsgPanel;	
	private JPanel successPanel;
	
	public DeleteRegButtonListener(DeleteRegisterNumDialog regNumDialog){
		this.regNumDialog=regNumDialog;
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String regNum=regNumDialog.getRegNumTF().getText().trim();
		cleanup();
		try{
			Boolean exist=service.checkRegNumExist(regNum);
			if(!exist){
				errorMsgPanel = new ErrorsDisplayJPanel(1);
				errorMsgPanel.addErrMsg(resources.getString("regNumInvalid"));
				regNumDialog.add(errorMsgPanel,BorderLayout.NORTH);
				regNumDialog.validate();
				regNumDialog.repaint();
			}else{								
				service.deleteRegistration(regNum);
				successPanel=GuiUtil.getSuccessMsgPanel(Util.getResources().getString("registrationDeletedSuccessfully"));
				regNumDialog.add(successPanel,BorderLayout.NORTH);
				regNumDialog.validate();
				regNumDialog.repaint();
			}
		}catch(Exception e){
			log.debug(e.getMessage(), e);
		}
		
	}
	
	private void cleanup(){
		if(errorMsgPanel!=null){			
			regNumDialog.remove(errorMsgPanel);
			regNumDialog.validate();
			regNumDialog.repaint();
			
		}
		
		if(successPanel!=null){
			regNumDialog.remove(successPanel);
			regNumDialog.validate();
			regNumDialog.repaint();
		}
	}

}
