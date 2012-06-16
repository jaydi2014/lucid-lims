/**
 * 
 */
package org.lims.register.gui.listeners;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;
import org.lims.common.exceptions.ValidationErrorsException;
import org.lims.gui.util.ErrorsDisplayJPanel;
import org.lims.register.dto.PDRegDto;
import org.lims.register.dto.TestRegisterDto;
import org.lims.register.gui.ViewRegDialog;
import org.lims.register.service.RegisterService;
import org.lims.register.service.RegisterServiceInter;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class ViewRegsFetchButtonListener implements ActionListener{

	private ViewRegDialog viewRegDialog;
	private Logger log=Logger.getLogger(ViewRegsFetchButtonListener.class);
	private RegisterServiceInter service=new RegisterService();
	private ErrorsDisplayJPanel errorMsgPanel;
	
	public ViewRegsFetchButtonListener(ViewRegDialog viewRegDialog){
		this.viewRegDialog=viewRegDialog;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		cleanup();
		PDRegDto pdregdto=setPDRegDto();
		try{
			pdregdto=service.getRegistrations(pdregdto);
			DefaultTableModel model=(DefaultTableModel)viewRegDialog.getRegsTable().getModel();
			int rowcount=model.getRowCount();
			for(int i=(rowcount-1);i>=0;i--){
				model.removeRow(i);
			}
			for(TestRegisterDto register:pdregdto.getRegs()){
				Object[] row={register.getRegNumber(),register.getCustomer().getCustName(),
						register.getDate(),register.getDueDate(),register.getDispatchDate()};
				model.addRow(row);
			}
			Integer nextOffset=(pdregdto.getPageNumber())*pdregdto.getLimit();
			if(nextOffset<pdregdto.getTotalResults())
				viewRegDialog.getNextB().setEnabled(true);
		}catch(Exception e){			
			if(e instanceof ValidationErrorsException){
				HashMap<String,String> exceptions=RegisterServiceInter.exceptions;
				errorMsgPanel = new ErrorsDisplayJPanel(exceptions.size());
				if(exceptions.containsKey("REG_PAGE_SIZE")){
					viewRegDialog.getPageSizeLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("REG_PAGE_SIZE");
					errorMsgPanel.addErrMsg(errMsg);
				}
				
				
				viewRegDialog.add(errorMsgPanel,BorderLayout.NORTH);
				viewRegDialog.validate();
				viewRegDialog.repaint();
			}else{								
				log.debug(e.getMessage(),e);
			}
			
			
		}//end of catch.
	}
	
	/**
	 * performs pre service cleanup.
	 */
	private void cleanup(){
		if(errorMsgPanel!=null){			
			viewRegDialog.getPageSizeLabel().setForeground(Color.BLACK);
			viewRegDialog.remove(errorMsgPanel);
			viewRegDialog.validate();
			viewRegDialog.repaint();
			
		}
	}
	
	/**
	 * It sets the PDregDto.
	 * @return PDRegDto
	 */
	private PDRegDto setPDRegDto(){
		PDRegDto pdregdto=new PDRegDto();
		pdregdto.setPageNumber(viewRegDialog.getPageNumber());
		pdregdto.setStartDate(viewRegDialog.getFromDateDC().getDate());
		pdregdto.setEndDate(viewRegDialog.getToDateDC().getDate());
		pdregdto.setPageSize(viewRegDialog.getPageSizeTF().getText());
		
		return pdregdto;
	}
	
	
}
