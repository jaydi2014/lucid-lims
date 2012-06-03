/**
 * 
 */
package org.lims.register.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTextPane;

import org.apache.log4j.Logger;
import org.lims.register.dto.PRegDto;
import org.lims.register.dto.SampleDto;
import org.lims.register.gui.PendingRegDialog;
import org.lims.register.service.RegisterService;
import org.lims.register.service.RegisterServiceInter;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class PendingRegButtonListener implements ActionListener{

	private PendingRegDialog pendingRegDialog;	
	private Logger log=Logger.getLogger(PendingRegButtonListener.class);
	private RegisterServiceInter service=new RegisterService();
	
	public PendingRegButtonListener(PendingRegDialog pendingRegDialog){
		this.pendingRegDialog=pendingRegDialog;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		JTextPane textPane=pendingRegDialog.getPendingRegTP();
		String dept=(String)pendingRegDialog.getDeptCB().getSelectedItem();
		try{
			List<PRegDto> pendingRegs=null;
			if(dept.equals("ALL"))
					pendingRegs=service.getPendingRegistrations();
			else
				pendingRegs=service.getPendingRegistrations(dept);
			textPane.setText(buildPendingRegString(pendingRegs));
		}catch(Exception e){
			log.debug(e.getMessage(), e);
		}		
	}
	
	private String buildPendingRegString(List<PRegDto> pendingRegs){
		StringBuffer sb=new StringBuffer();
		String dept=null;
		sb.append("<body style='font-size:10px;'>" );
		for(int i=0;i<pendingRegs.size();i++){
			PRegDto pendingReg=pendingRegs.get(i);
			if(!pendingReg.getDeptName().equals(dept)){
				dept=pendingReg.getDeptName();
				sb.append("<b><u>"+dept+"</u></b><br>");
				sb.append("<table cellspacing='2'>" +
				          "<tr><td><b>SNo</b></td><td><b>Reg No</b></td><td width='100'><b>Customer Name</b></td><td width='150'><b>Sample Name</b></td><td width='200'><b>Tests</b></td><td><b>Recieved Date</b></td><td><b>Due Date</b></td><td><b>Over Due Days</b></td></tr>");
			}
			boolean flag=false;
			for(SampleDto sample:pendingReg.getSamples()){
				if(pendingReg.getOverDueDays().equals("0"))
					sb.append("<tr style='color: black'>");
				else
					sb.append("<tr style='color: red'>");
				if(!flag){
					sb.append("<td>"+(i+1)+"</td><td>"+pendingReg.getRegNum()+"</td><td>"+pendingReg.getCustName()+"</td><td>"+sample.getSampleName()+"</td><td>"+sample.getSampleTests()+"</td><td>"+pendingReg.getRecievedDate()+"</td><td>"+pendingReg.getDueDate()+"</td><td align='center'>"+pendingReg.getOverDueDays()+"</td></tr>");
					flag=true;
				}else{
					sb.append("<td></td><td></td><td></td><td>"+sample.getSampleName()+"</td><td>"+sample.getSampleTests()+"</td><td></td><td></td><td align='center'></td></tr>");
				}
			}
			
			if(i==(pendingRegs.size()-1)||!pendingRegs.get(i+1).getDeptName().equals(dept)){
				sb.append("</table><br>");
			}
		}
		sb.append("</body>");
		return sb.toString();
	}
	
	
}
