package org.lims.register.gui.listeners;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		Util.SendEmail( recipents,subject, content);
	}
	
	

}
