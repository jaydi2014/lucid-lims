/**
 * 
 */
package org.lims.register.gui.listeners;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;
import org.lims.common.exceptions.ValidationErrorsException;
import org.lims.customer.dto.ContactPersonDto;
import org.lims.gui.util.ErrorsDisplayJPanel;
import org.lims.gui.util.GuiUtil;
import org.lims.register.dto.ContractReviewDto;
import org.lims.register.dto.RegDeptDto;
import org.lims.register.dto.SampleDto;
import org.lims.register.dto.TestRegisterDto;
import org.lims.register.gui.EmpNamePanel;
import org.lims.register.gui.MailClientDialog;
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
			
			MailClientDialog mailClient=new MailClientDialog();
			mailClient.getToTF().setText(rsDialog.getCustCtPersonEmailTF().getText());
			mailClient.getSubjectTF().setText("Acknowledgement");
			String content=buildMailContent( registerdto);
			mailClient.getBodyTP().setText(content);
			
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
				
				if(exceptions.containsKey("CUST_NAME")){
					rsDialog.getCustLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("CUST_NAME");
					errorMsgPanel.addErrMsg(errMsg);
				}
				
				if(exceptions.containsKey("CONTACT_PERSON_NAME")){
					rsDialog.getCtPersonLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("CONTACT_PERSON_NAME");
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
				
				if(exceptions.containsKey("LAB_DUE_DATE")){
					rsDialog.getLabDueDateLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("LAB_DUE_DATE");
					errorMsgPanel.addErrMsg(errMsg);
					
				}
				
				if(exceptions.containsKey("CUSTOMER_REF_NUMBER")){
					rsDialog.getCrNumberLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("CUSTOMER_REF_NUMBER");
					errorMsgPanel.addErrMsg(errMsg);
					
				}
				
				if(exceptions.containsKey("QUANTITY_ADEQUATE")){
					rsDialog.getAdqLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("QUANTITY_ADEQUATE");
					errorMsgPanel.addErrMsg(errMsg);
					
				}
				
				if(exceptions.containsKey("QUANTITY_INADEQUATE")){
					rsDialog.getInAdeqLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("QUANTITY_INADEQUATE");
					errorMsgPanel.addErrMsg(errMsg);
					
				}
				
				if(exceptions.containsKey("STRG_CONDT_ROOM_TEMP")){
					rsDialog.getRoomTempLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("STRG_CONDT_ROOM_TEMP");
					errorMsgPanel.addErrMsg(errMsg);
					
				}
				
				if(exceptions.containsKey("STRG_CONDT_CUSTOMER_REQUEST")){
					rsDialog.getCrTempLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("STRG_CONDT_CUSTOMER_REQUEST");
					errorMsgPanel.addErrMsg(errMsg);
					
				}
				
				if(exceptions.containsKey("COA_ACCEPTABLE")){
					rsDialog.getAcptlLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("COA_ACCEPTABLE");
					errorMsgPanel.addErrMsg(errMsg);
					
				}
				
				if(exceptions.containsKey("COA_NOT_ACCEPTABLE")){
					rsDialog.getNtacptlLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("COA_NOT_ACCEPTABLE");
					errorMsgPanel.addErrMsg(errMsg);
					
				}
				
				if(exceptions.containsKey("SEAL_INTACT")){
					rsDialog.getIntactLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("SEAL_INTACT");
					errorMsgPanel.addErrMsg(errMsg);
					
				}
				
				if(exceptions.containsKey("SEAL_NOT_INTACT")){
					rsDialog.getNtIntactLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("SEAL_NOT_INTACT");
					errorMsgPanel.addErrMsg(errMsg);
					
				}
				
				if(exceptions.containsKey("TEST_METH_AVAILABLE")){
					rsDialog.getAvlLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("TEST_METH_AVAILABLE");
					errorMsgPanel.addErrMsg(errMsg);
					
				}
				
				if(exceptions.containsKey("TEST_METH_NOT_AVAILABLE")){
					rsDialog.getNtAvlLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("TEST_METH_NOT_AVAILABLE");
					errorMsgPanel.addErrMsg(errMsg);
					
				}
				
				if(exceptions.containsKey("CHEQUE_NUMBER")){
					rsDialog.getChequeNumLabel().setForeground(Color.RED);
					String errMsg=exceptions.remove("CHEQUE_NUMBER");
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
			rsDialog.getCustLabel().setForeground(Color.BLACK);
			rsDialog.getCtPersonLabel().setForeground(Color.BLACK);
			rsDialog.getDueDateLabel().setForeground(Color.BLACK);
			rsDialog.getDispatchMethLabel().setForeground(Color.BLACK);
			rsDialog.getTotalTestingChrgsLabel().setForeground(Color.BLACK);
			rsDialog.getAmountPaidLabel().setForeground(Color.BLACK);
			rsDialog.getBalanceLabel().setForeground(Color.BLACK);
			rsDialog.getPaymentMethLabel().setForeground(Color.BLACK);
			rsDialog.getSpecialInstrLabel().setForeground(Color.BLACK);
			rsDialog.getSamplePackingLabel().setForeground(Color.BLACK);
			rsDialog.getLabDueDateLabel().setForeground(Color.BLACK);
			rsDialog.getCrNumberLabel().setForeground(Color.BLACK);
			rsDialog.getAdqLabel().setForeground(Color.BLACK);
			rsDialog.getInAdeqLabel().setForeground(Color.BLACK);
			rsDialog.getRoomTempLabel().setForeground(Color.BLACK);
			rsDialog.getCrTempLabel().setForeground(Color.BLACK);
			rsDialog.getAcptlLabel().setForeground(Color.BLACK);
			rsDialog.getNtacptlLabel().setForeground(Color.BLACK);
			rsDialog.getIntactLabel().setForeground(Color.BLACK);
			rsDialog.getNtIntactLabel().setForeground(Color.BLACK);
			rsDialog.getAvlLabel().setForeground(Color.BLACK);
			rsDialog.getNtAvlLabel().setForeground(Color.BLACK);
			rsDialog.getChequeNumLabel().setForeground(Color.BLACK);
			
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
		rsDialog.getTimeDC().setDate(new Date());
		rsDialog.getCustTF().setText("");
		rsDialog.getCtPersonTF().setText("");
		rsDialog.getLabDueDateDC().setDate(null);
		rsDialog.getCrNumberTF().setText("");
		rsDialog.getCrDC().setDate(null);
		rsDialog.getCrFileTF().setText("");
		rsDialog.getAdqTF().setText("");
		rsDialog.getInAdeqTF().setText("");
		rsDialog.getRoomTempTF().setText("");
		rsDialog.getCrTempTF().setText("");
		rsDialog.getAcptlTF().setText("");
		rsDialog.getNtAcptlTF().setText("");
		rsDialog.getIntactTF().setText("");
		rsDialog.getNtIntactTF().setText("");
		rsDialog.getTmAvailableTF().setText("");
		rsDialog.getNtTmAvailableTF().setText("");
		rsDialog.getChequeNumTF().setText("");
		rsDialog.getChequeDateDC().setDate(null);
		rsDialog.getCustAddressTA().setText("");
		rsDialog.getCustPhoneTF().setText("");
		rsDialog.getCustFaxTF().setText("");
		rsDialog.getCustEmailTF().setText("");
		rsDialog.getCustCtPersonMobileTF().setText("");
		rsDialog.getCustCtPersonEmailTF().setText("");
		
		
		JTable samplesTable=rsDialog.getSamplesTable();
		DefaultTableModel model=(DefaultTableModel)samplesTable.getModel();
		int rowCount=model.getRowCount();
		for(int k=rowCount-1;k>=0;k--){
			model.removeRow(k);
		}
		
		JPanel deptPanel=rsDialog.getDeptPanel();
		int deptPanelRowCount=rsDialog.getDeptPanelList().size();
		for(int k=(deptPanelRowCount-1);k>=0;k--){
			EmpNamePanel empNamePanel=rsDialog.getDeptPanelList().remove(k);
			deptPanel.remove(empNamePanel);
		}
		deptPanel.revalidate();
		deptPanel.repaint();
		
	}
	
	/**
	 * sets the TestRegisterDto.
	 * @return TestRegisterDto
	 */
	private TestRegisterDto setTestRegisterDto(){
		TestRegisterDto registerDto=new TestRegisterDto();
		registerDto.setRegNumber(rsDialog.getRegNoTF().getText().trim());		
		registerDto.getCustomer().setCustName((String)rsDialog.getCustTF().getText());
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
		registerDto.setRegTime(rsDialog.getTimeDC().getDate());
		ContactPersonDto ctPerson=new ContactPersonDto();
		ctPerson.setCtPersonName(rsDialog.getCtPersonTF().getText());
		registerDto.setCtPerson(ctPerson);
		registerDto.setSampleCollectionMethod((String)rsDialog.getScMethodsCB().getSelectedItem());
		registerDto.setLabDueDate(rsDialog.getLabDueDateDC().getDate());
		registerDto.setCrNumber(rsDialog.getCrNumberTF().getText());
		registerDto.setCrDate(rsDialog.getCrDC().getDate());
		if(rsDialog.getCrFileTF().getText() !=null && !rsDialog.getCrFileTF().getText().isEmpty()){
			File crFile=new File(rsDialog.getCrFileTF().getText());
			FileInputStream fis=null;
			try{
				fis=new FileInputStream(crFile);
			}catch(FileNotFoundException fe){
				log.debug(fe.getMessage(), fe);
			}
			registerDto.setCrFile(fis);
		
			String fileName=crFile.getName();
			int ind=fileName.lastIndexOf(".");
			String extension=fileName.substring(ind+1);
			registerDto.setCrFileExt(extension);
		}
		List<RegDeptDto> depts=new ArrayList<RegDeptDto>();
		for(EmpNamePanel emp:rsDialog.getDeptPanelList()){
			RegDeptDto dept=new RegDeptDto();
			dept.setDeptName((String)emp.getDeptCB().getSelectedItem());
			dept.setEmpDisplayName(emp.getEmpNameTF().getText());
			depts.add(dept);
		}
		registerDto.setDepts(depts);
		ContractReviewDto contractRev=new ContractReviewDto();
		contractRev.setAdequateQty(rsDialog.getAdqTF().getText());
		contractRev.setInadequateQty(rsDialog.getInAdeqTF().getText());
		contractRev.setStrgCdtRoomTemp(rsDialog.getRoomTempTF().getText());
		contractRev.setStrgCdtCustomerReq(rsDialog.getCrTempTF().getText());
		contractRev.setCoaAcceptable(rsDialog.getAcptlTF().getText());
		contractRev.setCoaNotAcceptable(rsDialog.getNtAcptlTF().getText());
		contractRev.setSealIntact(rsDialog.getIntactTF().getText());
		contractRev.setSealNotIntact(rsDialog.getNtIntactTF().getText());
		contractRev.setTestMethodAvailable(rsDialog.getTmAvailableTF().getText());
		contractRev.setTestmethodNotAvailable(rsDialog.getNtTmAvailableTF().getText());
		registerDto.setContractReview(contractRev);
		registerDto.setChequeNumber(rsDialog.getChequeNumTF().getText());
		registerDto.setChequeDate(rsDialog.getChequeDateDC().getDate());
		List<SampleDto> samples=new ArrayList<SampleDto>();
		JTable samplesTable=rsDialog.getSamplesTable();
		int rowCount=samplesTable.getRowCount();
		for(int i=0;i<rowCount;i++){
			SampleDto sample=new SampleDto();
			for(int b=0;b<7;b++){
				String value=(String)samplesTable.getValueAt(i, b);
				if(b==1 && (value==null || value.isEmpty()))
					break;
				if(b==0)
					sample.setSerialNo(value);
				if(b==1)
					sample.setSampleName(value);
				if(b==2)
					sample.setSampleQty(value);
				if(b==3)
					sample.setBatchMfgDetails(value);
				if(b==4)
					sample.setSampleTests(value);
				if(b==5)
					sample.setSpecification(value);
				if(b==6)
					sample.setTestMethod(value);
				
			}
			if(sample.getSampleName()!=null && !sample.getSampleName().isEmpty())
			samples.add(sample);
		}
		registerDto.setSamplesList(samples);
		return registerDto;
	}
	
	private String buildMailContent(TestRegisterDto registerdto){
		StringBuffer mailBuffer=new StringBuffer();
		mailBuffer.append("<html>" +
				"<head>" +
				"<style type=\"text/css\">"						
					+ "td, th {background-color: white}"
				+ "</style>"+
			"</head>" +
			"<body style='font: 12pt/12pt'>" );
		mailBuffer.append("<Center><b>Acknowledgement</b></center><br>");
		mailBuffer.append("Dear Madam/Sir,<br><br>");
		mailBuffer.append("Greetings From Lucid!!!!!!<br><br>");
		mailBuffer.append("With reference to above subject we are in receipt of your samples <br>" +
				          "and registered  at Lucid Laboratories Pvt Ltd having <b>Registration number :"+registerdto.getRegNumber()+"</b>. <br><br>");
		mailBuffer.append("<b>Test Details:<b><br><br>");
		mailBuffer.append("<table style='border: 1px black solid; background-color: black' width='100%' cellspacing='1' cellpadding='2'>" +
				          "<tr><td><b>Sample Name</b></td><td width='300'><b>Tests</b></td><td><b>Batch/MFG Details</b></td></tr>");
		for(SampleDto sample:registerdto.getSamplesList()){
			mailBuffer.append("<tr><td>"+sample.getSampleName()+"</td>" +
					               "<td>"+sample.getSampleTests()+"</td>" +
					               	"<td>"+sample.getBatchMfgDetails()+"</td>" +
					           "</tr>");
		}
		mailBuffer.append("</table>");
		mailBuffer.append("<br><br>The Report will be available on : <b>"+registerdto.getDueDate()+"</b> after 4.00PM.<br><br>");
		mailBuffer.append("Thanks & Regards <br><br>");
		mailBuffer.append("Lucid Laboratories Pvt Ltd,<br>" +
				          "B-1/A, TIE Phase-II,Balanagar,<br>" +
				          "Hyderabad-500037,India.<br>" +
				          "Phone : 91(40)2372 0678,680,681<br>" +
				          "Fax : 91(40)2372 0406<br>" +
				          "Email:<u>info@lucidlabsindia.com</u><br>" +
				          "Web: <u>www.lucidlabsindia.com</u>");
		mailBuffer.append("</body>");
		return mailBuffer.toString();
	}
}
