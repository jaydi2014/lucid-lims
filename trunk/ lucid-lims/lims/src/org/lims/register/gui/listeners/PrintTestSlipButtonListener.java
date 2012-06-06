/**
 * 
 */
package org.lims.register.gui.listeners;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JTextPane;

import org.apache.log4j.Logger;
import org.lims.gui.util.ErrorsDisplayJPanel;
import org.lims.register.dto.SampleDto;
import org.lims.register.dto.TestRegisterDto;
import org.lims.register.gui.TestSlipRegisterNumDialog;
import org.lims.register.service.RegisterService;
import org.lims.register.service.RegisterServiceInter;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class PrintTestSlipButtonListener implements ActionListener{
	
	private TestSlipRegisterNumDialog regNumDialog;	
	private ResourceBundle resources=Util.getResources();	
	private Logger log=Logger.getLogger(PrintTestSlipButtonListener.class);	
	private RegisterServiceInter service=new RegisterService();	
	private ErrorsDisplayJPanel errorMsgPanel;	
	
	public PrintTestSlipButtonListener(TestSlipRegisterNumDialog regNumDialog){
		this.regNumDialog=regNumDialog;
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String regNum=regNumDialog.getRegNumTF().getText().trim();
		try{
			Boolean exist=service.checkRegNumExist(regNum);
			if(!exist){
				errorMsgPanel = new ErrorsDisplayJPanel(1);
				errorMsgPanel.addErrMsg(resources.getString("regNumInvalid"));
				regNumDialog.add(errorMsgPanel,BorderLayout.NORTH);
				regNumDialog.validate();
				regNumDialog.repaint();
			}else{
				regNumDialog.dispose();				
				TestRegisterDto registerDto=service.getRegisterEntry(regNum);
				JTextPane testSlipTP=getTestSlipTP(registerDto);
				testSlipTP.print();
			}
		}catch(Exception e){
			log.debug(e.getMessage(), e);
		}
		
	}
	
	private JTextPane getTestSlipTP(TestRegisterDto registerDto){
		/*JDialog dialog=new JDialog();
		dialog.setTitle(resources.getString("register.dialog.testSlip.title"));
		dialog.getContentPane().setLayout(new BorderLayout());*/
		JTextPane testSlipTP=new JTextPane();
		testSlipTP.setContentType("text/html");
		testSlipTP.setText(buildTestSlip(registerDto));
		/*testSlipTP.setEditable(false);
		dialog.add(testSlipTP,BorderLayout.CENTER);
		GuiUtil.CompCenterCords cords=GuiUtil.getCompCenterCords(1000, 600);
		dialog.setBounds(cords.getX(), cords.getY(), 1000, 600);		
		dialog.setVisible(true);
		dialog.dispose();*/
		return testSlipTP;
	}
	
	/**
	 * Builds the content that has to be set in the text pane.
	 * @param registerDto
	 * @return text pane string.
	 */
	private String buildTestSlip(TestRegisterDto registerDto){
		StringBuffer sb=new StringBuffer();
		String testSlip="<html>" +
				"<head>" +
				    "<style type='text/css'>" +				
				        "td, th {background-color: white}" +
				     "</style>" +
				"</head>" +
				"<body style='font-size:10pt;'>" +
				"<center><h3><u>Test Slip</u></h3></center><br>" +
				"<b>Registration Number : </b>"+registerDto.getRegNumber()+"<br>" +
				"<b>Registration Date : </b>"+registerDto.getDate()+"<br>" +
				"<b>Due Date : </b>"+registerDto.getDueDate()+"<br>" +
				"<br><center><div style='background-color: black'><table border='0' cellpadding='2' cellspacing='1'>" +
								"<tr><td width='200'><b>Sample Name</b></td><td width='400' align='center'><b>Tests</b></td><td width='100'><b>Quantity</b></td></tr>";
		sb.append(testSlip);
		for(SampleDto sample:registerDto.getSamplesList()){
			sb.append("<tr><td>"+sample.getSampleName()+"</td><td>"+sample.getSampleTests()+"</td><td>"+sample.getSampleQty()+"</td></tr>");
		}
		
		sb.append("</table></center>");
		sb.append("</div>");
		sb.append("<br>");
		sb.append("<table>" +
				     "<tr><td width='400'><b>Special Instructions</b></td></tr>" +
				     "<tr><td>"+registerDto.getSpecialInstrs()+"</td></tr>"+
	     		  "</table>");
		
		sb.append("</body>");
		return sb.toString();
	}

}
