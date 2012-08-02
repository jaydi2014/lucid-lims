/**
 * 
 */
package org.lims.customer.gui.listeners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListModel;

import org.apache.log4j.Logger;
import org.lims.customer.gui.SelectCustDialog;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class SelectCustNameTFKeyListener extends KeyAdapter{
	
	private SelectCustDialog selectCustDialog;	
	private Logger log=Logger.getLogger(SelectCustNameTFKeyListener.class);
	
	public SelectCustNameTFKeyListener(SelectCustDialog selectCustDialog){
		this.selectCustDialog=selectCustDialog;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyAdapter#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent event) {
		
		String prefix=selectCustDialog.getCustNameTF().getText().toLowerCase();		
		if(!prefix.isEmpty()){
			char firstChar=prefix.charAt(0);			
			String firstCharStr=new String(new char[]{firstChar});
			List<String> custNamesList=selectCustDialog.getCustNamesMap().get(firstCharStr);
			
			if(custNamesList==null){
				try{	
					List<String> filteredCust=new ArrayList<String>();
					List<String> all=selectCustDialog.getCustNamesMap().get("ALL");
					Iterator<String> iter=all.iterator();
					while(iter.hasNext()){
						String cust=iter.next();
						String sLower=cust.toLowerCase();
						if(sLower.startsWith(prefix))
							filteredCust.add(cust);
					}
					custNamesList=filteredCust;
					selectCustDialog.getCustNamesMap().put(firstCharStr, custNamesList);
				}catch(Exception e){
					log.debug(e.getMessage(), e);
				}
			}
			JList custList=selectCustDialog.getCustList();
			ListModel model=custList.getModel();			
			DefaultListModel defModel=(DefaultListModel)model;
			if(prefix.length()==1){
				defModel.clear();
				for(String element:custNamesList){
					defModel.addElement(element);
				}
			}else{
				defModel.clear();
				Iterator<String> iter=custNamesList.iterator();
				while(iter.hasNext()){
					String s=iter.next();
					String sLower=s.toLowerCase();
					if(sLower.startsWith(prefix))
						defModel.addElement(s);
				}
			}
		}else{
			JList custList=selectCustDialog.getCustList();
			ListModel model=custList.getModel();			
			DefaultListModel defModel=(DefaultListModel)model;
			defModel.clear();
			List<String> all=selectCustDialog.getCustNamesMap().get("ALL");
			for(String element:all){
				defModel.addElement(element);
			}
		}
	}

}
