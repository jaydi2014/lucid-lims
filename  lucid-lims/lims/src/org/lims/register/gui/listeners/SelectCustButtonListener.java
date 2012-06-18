/**
 * 
 */
package org.lims.register.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import org.lims.customer.gui.SelectCustDialog;
import org.lims.register.gui.RegisterSamplesDialog;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class SelectCustButtonListener implements ActionListener{

	private RegisterSamplesDialog rsDialog;
	private ResourceBundle resources=Util.getResources();
	
	
	public SelectCustButtonListener(RegisterSamplesDialog rsDialog){
		this.rsDialog=rsDialog;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		SelectCustDialog scd=new SelectCustDialog(rsDialog,
				resources.getString("register.dialog.selectCust.title"),
				true);
		
	}
	
	
}
