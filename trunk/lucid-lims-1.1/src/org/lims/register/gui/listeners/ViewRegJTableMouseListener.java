/**
 * 
 */
package org.lims.register.gui.listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

import org.lims.main.Lims;
import org.lims.register.gui.RegisterSamplesDialog;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class ViewRegJTableMouseListener extends MouseAdapter{

	public void mouseClicked(MouseEvent e){
        if(e.getClickCount()==2){ 
          JTable table=(JTable)e.getSource();
          int row=table.getSelectedRow();
          String regNum=(String)table.getValueAt(row, 0);
          RegisterSamplesDialog.dispatchRegNum=regNum;
		  new RegisterSamplesDialog(Lims.getFrame(),
					Util.getResources().getString("register.dialog.title"),
					true,"DISPATCH");
          
        }
	}
}
