/**
 * 
 */
package org.lims.admin.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.lims.admin.gui.AddEmployeeDialog;
import org.lims.main.Lims;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class AddEmpListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		AddEmployeeDialog empDialog=new AddEmployeeDialog(Lims.getFrame(),
				Util.getResources().getString("dialog.admin.employee.title"),
				true);
		
	}

	
}
