/**
 * 
 */
package org.lims.admin.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.lims.admin.gui.AddRoleDialog;
import org.lims.main.Lims;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class AddRoleListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		AddRoleDialog roleDialog=new AddRoleDialog(Lims.getFrame(),
				        Util.getResources().getString("dialog.admin.role.title"),
				        true  );
		
	}

}
