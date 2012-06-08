/**
 * 
 */
package org.lims.employee.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.log4j.Logger;
import org.lims.employee.dto.EmployeeDto;
import org.lims.employee.gui.ViewEmployeeDialog;
import org.lims.employee.service.EmployeeService;
import org.lims.employee.service.EmployeeServiceInter;
import org.lims.main.Lims;
import org.lims.util.Constants;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class UpdateEmpButtonListener implements ActionListener{
	
	private ViewEmployeeDialog viewEmpDialog;	
	private Logger log=Logger.getLogger(UpdateEmpButtonListener.class);
	
	private EmployeeServiceInter service=new EmployeeService();
	
	
	public UpdateEmpButtonListener(ViewEmployeeDialog viewEmpDialog){
		this.viewEmpDialog=viewEmpDialog;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		EmployeeDto emp=new EmployeeDto();
		emp.setEmpId(Lims.getSessionmap().get(Constants.EMP_ID));
		emp.setPhoneNo(viewEmpDialog.getEmpPhoneNumTF().getText());
		emp.setMobileNo(viewEmpDialog.getEmpMobileTF().getText());
		try{
			service.updateEmployee(emp);
			viewEmpDialog.dispose();
		}catch(Exception e){
			log.debug(e.getMessage(),e);
		}
		
	}


}
