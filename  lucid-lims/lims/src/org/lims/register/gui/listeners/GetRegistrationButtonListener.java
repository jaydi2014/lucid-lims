/**
 * 
 */
package org.lims.register.gui.listeners;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.lims.gui.util.ErrorsDisplayJPanel;
import org.lims.main.Lims;
import org.lims.register.gui.RegisterNumDialog;
import org.lims.register.gui.RegisterSamplesDialog;
import org.lims.register.service.RegisterService;
import org.lims.register.service.RegisterServiceInter;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class GetRegistrationButtonListener implements ActionListener{
	
	private RegisterNumDialog regNumDialog;	
	private ResourceBundle resources=Util.getResources();	
	private Logger log=Logger.getLogger(GetRegistrationButtonListener.class);	
	private RegisterServiceInter service=new RegisterService();	
	private ErrorsDisplayJPanel errorMsgPanel;	
	private String actionCmd;
	
	
	public GetRegistrationButtonListener(RegisterNumDialog regNumDialog,String actionCmd){
		this.regNumDialog=regNumDialog;
		this.actionCmd=actionCmd;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		String regNum=regNumDialog.getRegNumTF().getText().trim();
		try{
			Boolean exist=service.checkRegNumExist(regNum);
			if(!exist){
				errorMsgPanel = new ErrorsDisplayJPanel(1);
				errorMsgPanel.addErrMsg(resources.getString("regNumInvalid"));
				regNumDialog.add(errorMsgPanel,BorderLayout.NORTH);
				regNumDialog.validate();
				regNumDialog.repaint();
			}else{
				regNumDialog.dispose();				
				RegisterSamplesDialog.dispatchRegNum=regNum;
				RegisterSamplesDialog registerSamplesDialog=new RegisterSamplesDialog(Lims.getFrame(),
						Util.getResources().getString("register.dialog.title"),
						true,actionCmd);
			}
		}catch(Exception e){
			log.debug(e.getMessage(), e);
		}
		
	}

}
