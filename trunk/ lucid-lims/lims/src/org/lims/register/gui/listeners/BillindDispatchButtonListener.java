/**
 * 
 */
package org.lims.register.gui.listeners;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;
import org.lims.common.exceptions.ValidationErrorsException;
import org.lims.gui.util.ErrorsDisplayJPanel;
import org.lims.gui.util.GuiUtil;
import org.lims.register.dto.TestRegisterDto;
import org.lims.register.gui.RegisterSamplesDialog;
import org.lims.register.service.RegisterService;
import org.lims.register.service.RegisterServiceInter;
import org.lims.util.Constants;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class BillindDispatchButtonListener implements ActionListener{
	
	private RegisterSamplesDialog rsDialog;
	private Logger log=Logger.getLogger(RegisterSamplesButtonListener.class);
	private RegisterServiceInter service=new RegisterService();
	
	private ErrorsDisplayJPanel errorMsgPanel;
	
	private JPanel successPanel;
	
	public BillindDispatchButtonListener(RegisterSamplesDialog rsDialog){
		this.rsDialog=rsDialog;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		cleanup();
		TestRegisterDto registerdto=setTestRegisterDto();
		
		try{
			service.updateBillingandDispatch(registerdto);
			
			successPanel=GuiUtil.getSuccessMsgPanel(Util.getResources().getString("disBillingUpdated"));
			rsDialog.dispose();
			
		}catch(Exception e){			
			if(e instanceof ValidationErrorsException){
				HashMap<String,String> exceptions=RegisterServiceInter.exceptions;
				errorMsgPanel = new ErrorsDisplayJPanel(exceptions.size());
				if(exceptions.containsKey("REG_AMT_PAID")){
					rsDialog.getAmountPaidLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("REG_AMT_PAID");
					errorMsgPanel.addErrMsg(errMsg);
				}
				
				if(exceptions.containsKey("REG_PAY_METH")){
					rsDialog.getPaymentMethLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("REG_PAY_METH");
					errorMsgPanel.addErrMsg(errMsg);
				}
				
				if(exceptions.containsKey("DISPATCH_DATE")){
					rsDialog.getDateLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("DISPATCH_DATE");
					errorMsgPanel.addErrMsg(errMsg);
				}
				
				if(exceptions.containsKey("DISPATCH_METH")){
					rsDialog.getDispatchMethLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("DISPATCH_METH");
					errorMsgPanel.addErrMsg(errMsg);
				}				
				
				rsDialog.add(errorMsgPanel,BorderLayout.NORTH);
				rsDialog.validate();
				rsDialog.repaint();
			}else{								
				log.debug(e.getMessage(),e);
			}
			
			
		}
		
	}
	
	/**
	 * This performs the pre service cleanup.
	 */
	private void cleanup(){
		if(errorMsgPanel!=null){
			rsDialog.getDispatchDateLabel().setForeground(Color.BLACK);
			rsDialog.getDispatchMethLabel().setForeground(Color.BLACK);
			rsDialog.getAmountPaidLabel().setForeground(Color.BLACK);
			rsDialog.getPaymentMethLabel().setForeground(Color.BLACK);
			rsDialog.remove(errorMsgPanel);
			rsDialog.validate();
			rsDialog.repaint();
			
		}
		
		if(successPanel!=null){
			rsDialog.remove(successPanel);
			rsDialog.validate();
			rsDialog.repaint();
		}
	}
	
		
	/**
	 * sets the TestRegisterDto.
	 * @return TestRegisterDto
	 */
	private TestRegisterDto setTestRegisterDto(){
		TestRegisterDto registerDto=new TestRegisterDto();
		registerDto.setRegNumber(rsDialog.getRegNoTF().getText());
		String dispatchDate=Util.convertDateToString(rsDialog.getDispatchDateDC().getDate(), Constants.DATE_PATTERN);
		registerDto.setDispatchDate(dispatchDate);
		registerDto.setDispatchMethod(rsDialog.getDispatchMethTF().getText());
		registerDto.setAmountPaid(rsDialog.getAmountPaidTF().getText());
		registerDto.setPaymentMeth(rsDialog.getPaymentMethTF().getText());
		registerDto.setBalance(rsDialog.getBalanceTF().getText());
		return registerDto;
	}
		

}
