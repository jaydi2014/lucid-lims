/**
 * 
 */
package org.lims.register.gui.listeners;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ResourceBundle;

import javax.swing.JTextPane;

import org.apache.log4j.Logger;
import org.lims.admin.dto.OrgDto;
import org.lims.admin.service.AdminService;
import org.lims.admin.service.AdminServiceInter;
import org.lims.gui.util.ErrorsDisplayJPanel;
import org.lims.gui.util.MyPrintable;
import org.lims.main.Lims;
import org.lims.register.dto.SampleDto;
import org.lims.register.dto.TestRegisterDto;
import org.lims.register.gui.AckRegisterNumDialog;
import org.lims.register.service.RegisterService;
import org.lims.register.service.RegisterServiceInter;
import org.lims.util.Constants;
import org.lims.util.Util;
import org.lims.util.resources.Resources;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class PrintAckButtonListener implements ActionListener{
	
	private AckRegisterNumDialog regNumDialog;	
	private ResourceBundle resources=Util.getResources();	
	private Logger log=Logger.getLogger(PrintAckButtonListener.class);	
	private RegisterServiceInter service=new RegisterService();	
	private AdminServiceInter adminService=new AdminService();
	private ErrorsDisplayJPanel errorMsgPanel;	
	
	public PrintAckButtonListener(AckRegisterNumDialog regNumDialog){
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
				OrgDto orgdto=adminService.getOrg();
				JTextPane ackTP=getAckTP(registerDto,orgdto);
				print(ackTP);
				//testSlipTP.print();
			}
		}catch(Exception e){
			log.debug(e.getMessage(), e);
		}
		
	}
	
	private JTextPane getAckTP(TestRegisterDto registerDto,OrgDto orgdto){
		/*JDialog dialog=new JDialog();
		dialog.setTitle(resources.getString("register.dialog.testSlip.title"));
		dialog.getContentPane().setLayout(new BorderLayout());*/
		JTextPane ackTP=new JTextPane();		
		ackTP.setContentType("text/html");
		ackTP.setText(buildAck(registerDto,orgdto));
		ackTP.setBounds(0, 0, 550, 700);
		ackTP.setEditable(false);
		/*dialog.add(ackTP,BorderLayout.CENTER);
		GuiUtil.CompCenterCords cords=GuiUtil.getCompCenterCords(600, 600);
		dialog.setBounds(cords.getX(), cords.getY(), 600, 600);		
		dialog.setVisible(true);		*/
		return ackTP;
	}
	
	/**
	 * Builds the content that has to be set in the text pane.
	 * @param registerDto
	 * @return text pane string.
	 */
	private String buildAck(TestRegisterDto registerDto,OrgDto orgdto){
		
		String imgPath=Resources.class.getResource("lucidfull.jpg").toString();
		StringBuffer sb=new StringBuffer();
		String testSlip="<html>" +
				"<head>" +
					"<style type=\"text/css\">"						
						+ "td, th {background-color: white}"
					+ "</style>"+
				"</head>" +
				"<body style='font: 10pt/10pt'>" +
				        "<table>" +
							"<tr><td width='600'><b>"+orgdto.getOrgName()+",<br>"+orgdto.getOrgAddress()+"<br>Phone : "+orgdto.getOrgPhone()+"&nbsp Fax : "+orgdto.getOrgFax()+"&nbsp <br>Email : "+orgdto.getOrgEmail()+"&nbsp web : "+orgdto.getOrgWebsite()+"</b></td>" +
								"<td width='100' align='right'><img src='"+imgPath+"' width='80' height='40'/></td>" +
							"</tr>" +
						"</table><br>" +
				"<center><h3><u>Acknowledgement</u></h3></center><br>"+
				"<table border='0'><tr><td width=350><b>Registration Number : </b>"+registerDto.getRegNumber()+"</td><td width='350' align='right'><b>Registration Date : </b>"+registerDto.getDate()+"</td></tr></table><br>"+
				"<table border='0' cellpadding='0' cellspacing='0'>" +
					"<tr><td width='350'>" +
							"<table>" +
								"<tr><td><b>Customer : </b></td><td>" +registerDto.getCustomer().getCustName()+"</td></tr>" +
								"<tr><td><b>Address : </b></td><td>"+registerDto.getCustomer().getAddress()+"</td></tr>" +
							"</table>"+
						"</td>" +
					    "<td width='350' align='right'>" +
					    	"<table>" +
					    		"<tr><td><b>Contact Person : </b></td><td>" +registerDto.getCustomer().getContactPersonName()+"</td></tr>" +
			    				"<tr><td><b>Mobile : </b></td><td>"+registerDto.getCustomer().getContactPersonMobile()+"</td></tr>" +
    						"</table>"+
					    "</td>" +
				    "</tr>" +					
			    "</table>"+				
				"<br><center><table style='border: 1px black solid; background-color: black' width='100%' cellspacing='1' cellpadding='2'>" +
								"<tr><td width='200'><b>Sample Name</b></td><td width='400' align='center'><b>Tests</b></td><td width='100'><b>Quantity</b></td></tr>";
		sb.append(testSlip);
		for(SampleDto sample:registerDto.getSamplesList()){
			sb.append("<tr><td>"+sample.getSampleName()+"</td><td>"+sample.getSampleTests()+"</td><td>"+sample.getSampleQty()+"</td></tr>");
		}
		sb.append("</table></center><br>");
		sb.append("<table>" +				
				"<tr><td width='230'><b>Total Charges (Rs) : </b>"+registerDto.getTotalTestingChrgs()+"</td>" +
					"<td width='230'><b>Advance (Rs) : </b>"+registerDto.getAmountPaid()+"</td>" +
					"<td width='230'><b>Balance (Rs) : </b>"+registerDto.getBalance()+"</td>" +
				"</tr>" +				
				"<tr><td colspan='3'><b>Payment Method : </b>"+registerDto.getPaymentMeth()+"</td>" +					
				"</tr>" +				
			"</table>");		
		sb.append("<table>" +
						"<tr>" +
							"<td width='180'><b>Due Date : </b>"+registerDto.getDueDate()+"</td>" +
							"<td width='230' align='left'><b>Dispatch Method : </b>"+registerDto.getDispatchMethod()+"</td>" +
						"</tr>" +
					"</table>");
		
		sb.append("<table>" +
				     "<tr><td width='400'><b><u>Customer Instructions:-</u></b></td></tr>" +
				     "<tr><td>"+registerDto.getSpecialInstrs()+"</td></tr>"+
	     		  "</table><br>");
		
		sb.append("<table>" +
						"<tr><td width='700' align='right'>Signature Of The Lucid Repesentative,</td></tr>" +
				        "<tr><td align='right'>"+Lims.getSessionmap().get(Constants.EMP_NAME)+"("+Lims.getSessionmap().get(Constants.EMP_DESG)+")</td></tr>"+
					"</table>");
		sb.append("</body></html>");
		return sb.toString();
	}
	
	/**
	 * prints JTextPane.
	 * @param textPane
	 */
	private void print(JTextPane textPane){
		PrinterJob pj = PrinterJob.getPrinterJob();
        
        PageFormat pf = pj.defaultPage();
        Paper paper = new Paper();
        double margin = 0; 
        paper.setImageableArea(margin, margin, paper.getWidth(), paper.getHeight());
        pf.setPaper(paper);
        
        pj.setPrintable(new MyPrintable(textPane), pf);
        if (pj.printDialog()) {
          try {
            pj.print();
          } catch (PrinterException pp) {
            System.out.println(pp);
          }
        }
	}

}
