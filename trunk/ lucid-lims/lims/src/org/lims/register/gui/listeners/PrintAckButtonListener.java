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
				JTextPane ackTP=getAckTP(registerDto);
				print(ackTP);
				//testSlipTP.print();
			}
		}catch(Exception e){
			log.debug(e.getMessage(), e);
		}
		
	}
	
	private JTextPane getAckTP(TestRegisterDto registerDto){
		/*JDialog dialog=new JDialog();
		dialog.setTitle(resources.getString("register.dialog.testSlip.title"));
		dialog.getContentPane().setLayout(new BorderLayout());*/
		JTextPane ackTP=new JTextPane();		
		ackTP.setContentType("text/html");
		ackTP.setText(buildAck(registerDto));
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
	private String buildAck(TestRegisterDto registerDto){
		
		String imgPath=Resources.class.getResource("lucidfull.jpg").toString();
		StringBuffer sb=new StringBuffer();
		String testSlip="<body style='font: 12pt/10pt'>" +
				        "<table>" +
							"<tr><td width='700' align='right'><img src='"+imgPath+"' width='177' height='87'/></td></tr>" +
						"</table><br>" +
				"<center><h3><u>Acknowledgement</u></h3></center><br>"+
				"<table border='0'><tr><td width=350><b>Registration Number : </b>"+registerDto.getRegNumber()+"</td><td width='350' align='right'><b>Registration Date : </b>"+registerDto.getDate()+"</td></tr></table><br>"+
				"<table border='1' cellpadding='0' cellspacing='0'>" +
					"<tr><td width='175'><b>Customer Name</b></td><td width='175'><b>Address</b></td><td width='175'><b>Contact Person Name</b></td><td width='175'><b>Contact person Mobile</b></td></tr>" +
					"<tr><td>"+registerDto.getCustomer().getCustName()+"</td><td>"+registerDto.getCustomer().getAddress()+"</td><td>"+registerDto.getCustomer().getContactPersonName()+"</td><td>"+registerDto.getCustomer().getContactPersonMobile()+"</td></tr>"+
			    "</table>"+				
				"<br><center><table border='1' cellpadding='0' cellspacing='0'>" +
								"<tr><td width='200'><b>Sample Name</b></td><td width='400' align='center'><b>Tests</b></td><td width='100'><b>Quantity</b></td></tr>";
		sb.append(testSlip);
		for(SampleDto sample:registerDto.getSamplesList()){
			sb.append("<tr><td>"+sample.getSampleName()+"</td><td>"+sample.getSampleTests()+"</td><td>"+sample.getSampleQty()+"</td></tr>");
		}
		sb.append("</table></center><br>");
		sb.append("<b>Due Date : </b>"+registerDto.getDueDate()+"<br>" );
		sb.append("<b>Dispatch Method : </b>"+registerDto.getDispatchMethod()+"<br>" );
		sb.append("<table>" +
				     "<tr><td width='400'><b><u>Special Instructions:-</u></b></td></tr>" +
				     "<tr><td>"+registerDto.getSpecialInstrs()+"</td></tr>"+
	     		  "</table>");
		sb.append("<table>" +
						"<tr><td><b><u>Payment Particulars:-</u></b></td></tr>" +
						"<tr><td>Total Testing Charges :"+registerDto.getTotalTestingChrgs()+"</td></tr>" +
						"<tr><td>Advance : "+registerDto.getAmountPaid()+"</td></tr>"+
						"<tr><td>Payment Method :"+registerDto.getPaymentMeth()+"</td></tr>" +
						"<tr><td>Balance :"+registerDto.getBalance()+"</td></tr>"+
					"</table>");
		sb.append("<table>" +
						"<tr><td width='700' align='right'>Signature Of The Lucid Repesentative,</td></tr>" +
				        "<tr><td align='right'>"+Lims.getSessionmap().get(Constants.EMP_NAME)+"("+Lims.getSessionmap().get(Constants.EMP_DESG)+")</td></tr>"+
					"</table>");
		sb.append("</body>");
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
