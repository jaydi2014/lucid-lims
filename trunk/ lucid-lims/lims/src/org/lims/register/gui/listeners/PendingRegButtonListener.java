/**
 * 
 */
package org.lims.register.gui.listeners;

import jasper.Jasper;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JRViewer;

import org.apache.log4j.Logger;
import org.lims.register.dto.PRegDto;
import org.lims.register.gui.PendingRegDialog;
import org.lims.register.service.RegisterService;
import org.lims.register.service.RegisterServiceInter;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class PendingRegButtonListener implements ActionListener{

	private ResourceBundle resources=Util.getResources();
	private PendingRegDialog pendingRegDialog;	
	private Logger log=Logger.getLogger(PendingRegButtonListener.class);
	private RegisterServiceInter service=new RegisterService();
	private JRViewer jrviewer;
	
	public PendingRegButtonListener(PendingRegDialog pendingRegDialog){
		this.pendingRegDialog=pendingRegDialog;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		//JTextPane textPane=pendingRegDialog.getPendingRegTP();
		String dept=(String)pendingRegDialog.getDeptCB().getSelectedItem();
		try{
			List<PRegDto> pendingRegs=null;
			if(dept.equals("ALL"))
					dept="";
			
			pendingRegs=service.getPendingRegistrations(dept);
			buildPendingRegString(pendingRegs);
		}catch(Exception e){
			log.debug(e.getMessage(), e);
		}		
	}
	
	/*private String buildPendingRegString(List<PRegDto> pendingRegs){
		StringBuffer sb=new StringBuffer();
		String dept=null;
		sb.append("<body style='font-size:10pt;'>" );
		sb.append("<center><h3><u>Pending Registrations</u></h3></center>");
		for(int i=0;i<pendingRegs.size();i++){
			PRegDto pendingReg=pendingRegs.get(i);
			if(!pendingReg.getDeptName().equals(dept)){
				dept=pendingReg.getDeptName();
				sb.append("<b><u>"+dept+"</u></b><br>");
				sb.append("<table cellspacing='2'>" +
				          "<tr><td><b>SNo</b></td><td width='150'><b>Reg No</b></td><td width='200'><b>Customer Name</b></td><td width='150'><b>Sample Name</b></td><td width='200'><b>Tests</b></td><td width='100'><b>Recieved Date</b></td><td width='100'><b>Due Date</b></td><td><b>ODD</b></td></tr>");
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
	}*/
	
	
	private void buildPendingRegString(List<PRegDto> pendingRegs){
		
			
			JRDataSource dataSource = new JRBeanCollectionDataSource( pendingRegs);		
			Map<String,Object> parameters = new HashMap<String,Object>();
			
			try{
				URL url=Jasper.class.getResource("pendingRegs.jasper");
				JasperReport report = (JasperReport) JRLoader.loadObject(new File(url.toURI()));
				JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, dataSource);
			if(jrviewer !=null)
					pendingRegDialog.remove(jrviewer);
				jrviewer=new JRViewer(jasperPrint);
				
				pendingRegDialog.getContentPane().add(jrviewer,BorderLayout.CENTER);
				pendingRegDialog.validate();
				
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
}
