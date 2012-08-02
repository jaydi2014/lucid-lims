/**
 * 
 */
package org.lims.register.gui.listeners;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;
import org.lims.common.exceptions.ValidationErrorsException;
import org.lims.gui.util.ErrorsDisplayJPanel;
import org.lims.register.dto.PDRegDto;
import org.lims.register.dto.TestRegisterDto;
import org.lims.register.gui.ViewRegDialog;
import org.lims.register.service.RegisterService;
import org.lims.register.service.RegisterServiceInter;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class ViewRegPreviousButtonListener implements ActionListener{

	private ViewRegDialog viewRegDialog;
	private Logger log=Logger.getLogger(ViewRegsFetchButtonListener.class);
	private ResourceBundle resources=Util.getResources();
	private RegisterServiceInter service=new RegisterService();
	private ErrorsDisplayJPanel errorMsgPanel;
	
	public ViewRegPreviousButtonListener(ViewRegDialog viewRegDialog){
		this.viewRegDialog=viewRegDialog;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		cleanup();
		viewRegDialog.setPageNumber((viewRegDialog.getPageNumber()-1));
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
			
			if(pdregdto.getOffset()==0)
				viewRegDialog.getPreviousB().setEnabled(false);
			if(pdregdto.getPageNumber()>=1)
				viewRegDialog.getNextB().setEnabled(true);
			
			String pattern=resources.getString("pageLabelMsg");
			String pageMsg=MessageFormat.format(pattern, pdregdto.getPageNumber(),(pdregdto.getOffset()+1),(pdregdto.getOffset()+pdregdto.getLimit()), pdregdto.getTotalResults());
			viewRegDialog.getDmessageLabel().setText(pageMsg);
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
