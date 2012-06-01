/**
 * 
 */
package org.lims.register.gui.listeners;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;
import org.lims.common.exceptions.ValidationErrorsException;
import org.lims.gui.util.ErrorsDisplayJPanel;
import org.lims.gui.util.GuiUtil;
import org.lims.register.dto.SampleDto;
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
public class RegisterSamplesButtonListener implements ActionListener {

	private RegisterSamplesDialog rsDialog;
	private Logger log=Logger.getLogger(RegisterSamplesButtonListener.class);
	private RegisterServiceInter service=new RegisterService();
	
	private ErrorsDisplayJPanel errorMsgPanel;
	
	private JPanel successPanel;
	
	public RegisterSamplesButtonListener(RegisterSamplesDialog rsDialog){
		this.rsDialog=rsDialog;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		cleanup();
		TestRegisterDto registerdto=setTestRegisterDto();
		
		try{
			service.createRegisterEntry(registerdto);
			
			successPanel=GuiUtil.getSuccessMsgPanel(Util.getResources().getString("regEntryCreatedSuccessfully"));
			rsDialog.add(successPanel,BorderLayout.NORTH);
			rsDialog.validate();
			rsDialog.repaint();
			postServiceCleanup();
		}catch(Exception e){			
			if(e instanceof ValidationErrorsException){
				HashMap<String,String> exceptions=RegisterServiceInter.exceptions;
				errorMsgPanel = new ErrorsDisplayJPanel(exceptions.size());
				if(exceptions.containsKey("REG_NUM")){
					rsDialog.getRegNoLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("REG_NUM");
					errorMsgPanel.addErrMsg(errMsg);
				}
				
				if(exceptions.containsKey("REG_DATE")){
					rsDialog.getDateLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("REG_DATE");
					errorMsgPanel.addErrMsg(errMsg);
				}
				
				if(exceptions.containsKey("REG_DUE_DATE")){
					rsDialog.getDueDateLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("REG_DUE_DATE");
					errorMsgPanel.addErrMsg(errMsg);
				}
				
				if(exceptions.containsKey("REG_TTC")){
					rsDialog.getTotalTestingChrgsLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("REG_TTC");
					errorMsgPanel.addErrMsg(errMsg);
				}
				
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
				
				if(exceptions.containsKey("REG_SPCL_INSTR")){
					rsDialog.getSpecialInstrLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("REG_SPCL_INSTR");
					errorMsgPanel.addErrMsg(errMsg);
					
				}
				
				if(exceptions.containsKey("REG_PACK")){
					rsDialog.getSamplePackingLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("REG_PACK");
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
			rsDialog.getRegNoLabel().setForeground(Color.BLACK);
			rsDialog.getDateLabel().setForeground(Color.BLACK);
			rsDialog.getDueDateLabel().setForeground(Color.BLACK);
			rsDialog.getDispatchMethLabel().setForeground(Color.BLACK);
			rsDialog.getTotalTestingChrgsLabel().setForeground(Color.BLACK);
			rsDialog.getAmountPaidLabel().setForeground(Color.BLACK);
			rsDialog.getBalanceLabel().setForeground(Color.BLACK);
			rsDialog.getPaymentMethLabel().setForeground(Color.BLACK);
			rsDialog.getSpecialInstrLabel().setForeground(Color.BLACK);
			rsDialog.getSamplePackingLabel().setForeground(Color.BLACK);
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
	 * This performs the post service cleanup of GUI.
	 */
	private void postServiceCleanup(){		
		rsDialog.getRegNoTF().setText("");
		rsDialog.getDateDC().setDate(new Date());
		rsDialog.getDueDateDC().setDate(new Date());
		rsDialog.getTotalTestingChrgsTF().setText("");
		rsDialog.getAmountPaidTF().setText("");	
		rsDialog.getBalanceTF().setText("");
		rsDialog.getPaymentMethTF().setText("");
		rsDialog.getSpecialInstrTA().setText("");
		rsDialog.getSamplePackingTA().setText("");
		rsDialog.getDispatchMethTF().setText("");
		JTable samplesTable=rsDialog.getSamplesTable();
		DefaultTableModel model=(DefaultTableModel)samplesTable.getModel();
		int rowCount=model.getRowCount();
		for(int k=rowCount-1;k>=0;k--){
			model.removeRow(k);
		}		
	}
	
	/**
	 * sets the TestRegisterDto.
	 * @return TestRegisterDto
	 */
	private TestRegisterDto setTestRegisterDto(){
		TestRegisterDto registerDto=new TestRegisterDto();
		registerDto.setRegNumber(rsDialog.getRegNoTF().getText());
		registerDto.getDepartment().setDeptName((String)rsDialog.getDeptCB().getSelectedItem());
		registerDto.getCustomer().setCustName((String)rsDialog.getCustNameCB().getSelectedItem());
		String regDate=Util.convertDateToString(rsDialog.getDateDC().getDate(), Constants.DATE_PATTERN);
		registerDto.setDate(regDate);
		String dueDate=Util.convertDateToString(rsDialog.getDueDateDC().getDate(), Constants.DATE_PATTERN);
		registerDto.setDueDate(dueDate);
		registerDto.setTotalTestingChrgs(rsDialog.getTotalTestingChrgsTF().getText());
		registerDto.setAmountPaid(rsDialog.getAmountPaidTF().getText());
		registerDto.setBalance(rsDialog.getBalanceTF().getText());
		registerDto.setPaymentMeth(rsDialog.getPaymentMethTF().getText());
		registerDto.setSpecialInstrs(rsDialog.getSpecialInstrTA().getText());
		registerDto.setPacking(rsDialog.getSamplePackingTA().getText());
		registerDto.setDispatchMethod(rsDialog.getDispatchMethTF().getText());
		List<SampleDto> samples=new ArrayList<SampleDto>();
		JTable samplesTable=rsDialog.getSamplesTable();
		int rowCount=samplesTable.getRowCount();
		for(int i=0;i<rowCount;i++){
			SampleDto sample=new SampleDto();
			for(int b=0;b<3;b++){
				String value=(String)samplesTable.getValueAt(i, b);
				if(b==0 && (value==null || value.isEmpty()))
					break;
				if(b==0)
					sample.setSampleName(value);
				if(b==1)
					sample.setSampleTests(value);
				if(b==2)
					sample.setSampleQty(value);
				
			}
			if(sample.getSampleName()!=null && !sample.getSampleName().isEmpty())
			samples.add(sample);
		}
		registerDto.setSamplesList(samples);
		return registerDto;
	}
}
