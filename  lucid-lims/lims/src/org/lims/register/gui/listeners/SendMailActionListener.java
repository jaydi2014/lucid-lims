package org.lims.register.gui.listeners;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.mail.MessagingException;

import org.lims.gui.util.ErrorsDisplayJPanel;
import org.lims.register.gui.MailClientDialog;
import org.lims.util.Util;


/**
 * 
 */

/**
 * @author Muralidhar Yaragalla
 *
 */
public class SendMailActionListener implements ActionListener{
	
	
	private ResourceBundle resources=Util.getResources();
	
	private MailClientDialog mailClient;
	
	public SendMailActionListener(MailClientDialog mailClient){
		this.mailClient=mailClient;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		String recipents=mailClient.getToTF().getText();	
		String subject=mailClient.getSubjectTF().getText();
		String content=mailClient.getBodyTP().getText();
		try{
			Util.SendEmail( recipents,subject, content);
			mailClient.dispose();
		}catch(MessagingException me){
			ErrorsDisplayJPanel errorMsgPanel = new ErrorsDisplayJPanel(1);
			errorMsgPanel.addErrMsg(resources.getString("mailNotSent"));
			mailClient.add(errorMsgPanel,BorderLayout.NORTH);
			mailClient.validate();
		}
	}
	
	

}
