/**
 * 
 */
package org.lims.admin.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.lims.admin.gui.AddDepartmentDialog;
import org.lims.main.Lims;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class AddDepartmentListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		AddDepartmentDialog addDepartment=new AddDepartmentDialog(Lims.getFrame(),
				                         Util.getResources().getString("dialog.admin.dept.title"),
				                         true);
		
	}
	
}