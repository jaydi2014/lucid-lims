/**
 * 
 */
package org.lims.register.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.lims.main.Lims;
import org.lims.register.gui.RegisterSamplesDialog;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class SampleRegisterListener implements ActionListener{

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		RegisterSamplesDialog registerSamplesDialog=new RegisterSamplesDialog(Lims.getFrame(),
				Util.getResources().getString("register.dialog.title"),
				true);
	}

	
}
