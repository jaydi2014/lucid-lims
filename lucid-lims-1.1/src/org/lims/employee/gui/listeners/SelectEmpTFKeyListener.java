/**
 * 
 */
package org.lims.employee.gui.listeners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListModel;

import org.lims.employee.gui.SelectEmpDialog;
import org.lims.register.gui.EmpNamePanel;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class SelectEmpTFKeyListener extends KeyAdapter{
	
	private SelectEmpDialog selectEmpDialog;
	
	
	public SelectEmpTFKeyListener(SelectEmpDialog selectEmpDialog){
		this.selectEmpDialog=selectEmpDialog;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyAdapter#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent event) {
		
		String dept=selectEmpDialog.getEnp().getDepartment().toLowerCase();	
		String prefix=selectEmpDialog.getEmpNameTF().getText().toLowerCase();
		if(!dept.isEmpty()){			
			List<String> empNamesList=selectEmpDialog.getEmpNamesMap().get(dept);
			JList empList=selectEmpDialog.getEmpList();
			ListModel model=empList.getModel();			
			DefaultListModel defModel=(DefaultListModel)model;			
			defModel.clear();
			Iterator<String> iter=empNamesList.iterator();
			while(iter.hasNext()){
				String s=iter.next();
				String sLower=s.toLowerCase();
				if(sLower.startsWith(prefix))
					defModel.addElement(s);
			}
			
		}
	}

}
