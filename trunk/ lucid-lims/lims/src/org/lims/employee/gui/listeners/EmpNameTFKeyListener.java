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

import org.apache.log4j.Logger;
import org.lims.employee.gui.ViewEmpDialog;
import org.lims.employee.service.EmployeeService;
import org.lims.employee.service.EmployeeServiceInter;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class EmpNameTFKeyListener extends KeyAdapter{
	
	private ViewEmpDialog viewEmpDialog;
	private EmployeeServiceInter service=new EmployeeService();
	private Logger log=Logger.getLogger(EmpNameTFKeyListener.class);
	
	public EmpNameTFKeyListener(ViewEmpDialog viewEmpDialog){
		this.viewEmpDialog=viewEmpDialog;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyAdapter#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent event) {
		
		String prefix=viewEmpDialog.getEmpNameTF().getText().toLowerCase();		
		if(!prefix.isEmpty()){
			char firstChar=prefix.charAt(0);			
			String firstCharStr=new String(new char[]{firstChar});
			List<String> empNamesList=viewEmpDialog.getEmpNamesMap().get(firstCharStr);
			
			if(empNamesList==null){
				try{					
					empNamesList=service.getEmployeeDisplayNames(prefix);
					viewEmpDialog.getEmpNamesMap().put(firstCharStr, empNamesList);
				}catch(Exception e){
					log.debug(e.getMessage(), e);
				}
			}
			JList empList=viewEmpDialog.getEmpList();
			ListModel model=empList.getModel();			
			DefaultListModel defModel=(DefaultListModel)model;
			if(prefix.length()==1){
				defModel.clear();
				for(String element:empNamesList){
					defModel.addElement(element);
				}
			}else{
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

}
