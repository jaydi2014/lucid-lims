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
				          "<tr><td><b>Reg No</b></td><td><b>Customer Name</b></td><td><b>Recieved Date</b></td><td><b>Due Date</b></td><td><b>Over Due Days</b></td><td width='50'></td></tr>");
			}
			sb.append("<tr><td>"+pendingReg.getRegNum()+"</td><td>"+pendingReg.getCustName()+"</td><td>"+pendingReg.getRecievedDate()+"</td><td>"+pendingReg.getDueDate()+"</td><td align='center'>"+pendingReg.getOverDueDays()+"</td>");
			if(pendingReg.getOverDueDays().equals("0"))
				sb.append("<td bgcolor='yellow'></td></tr>");
			else
				sb.append("<td bgcolor='red'></td></tr>");
			if(i==(pendingRegs.size()-1)||!pendingRegs.get(i+1).getDeptName().equals(dept)){
				sb.append("</table><br>");
			}
		}
		sb.append("</body>");
		return sb.toString();
	}
	
	
}
