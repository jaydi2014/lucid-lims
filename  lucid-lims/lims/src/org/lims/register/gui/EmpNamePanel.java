/**
 * 
 */
package org.lims.register.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.lims.admin.service.AdminService;
import org.lims.admin.service.AdminServiceInter;
import org.lims.employee.gui.SelectEmpDialog;
import org.lims.main.Lims;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class EmpNamePanel extends JPanel {	
	
	private static final long serialVersionUID = 934032676749762710L;
	private Logger log=Logger.getLogger(EmpNamePanel.class);
	private AdminServiceInter adminService=new AdminService();
	private ResourceBundle resources=Util.getResources();
	
	private JTextField empNameTF;
	private JButton browseB;
	public static String department;
	private EmpNamePanel enp;
	private JComboBox deptCB;
	
	public EmpNamePanel(){
		super(null);
		enp=this;
		String[] depts=null;
		try{
			List<String> deptList=adminService.getDepartments();
			depts=new String[deptList.size()+1];			
			for(int i=0;i<deptList.size();i++){
				depts[i+1]=deptList.get(i);
			}
		}catch(Exception e){
			log.debug(e.getMessage(), e);
		}
		depts[0]="Select Department";
		deptCB=new JComboBox(depts);
		deptCB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				JComboBox deptCB=(JComboBox)event.getSource();
				department=(String)deptCB.getSelectedItem();
			}
		});
		deptCB.setBounds(0,0,150,30);
		add(deptCB);
		empNameTF=new JTextField();
		empNameTF.setEditable(false);
		empNameTF.setBounds(160, 0, 150, 30);
		add(empNameTF);
		browseB=new JButton("Browse");
		browseB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				SelectEmpDialog sed=new SelectEmpDialog(Lims.getFrame(),
						resources.getString("register.dialog.selectemp.title"),
						true,enp);
				
			}
		});
		browseB.setBounds(320,0, 100, 30);
		add(browseB);
	}

	
	/**
	 * @return the empNameTF
	 */
	public JTextField getEmpNameTF() {
		return empNameTF;
	}

	/**
	 * @return the browseB
	 */
	public JButton getBrowseB() {
		return browseB;
	}


	/**
	 * @return the deptCB
	 */
	public JComboBox getDeptCB() {
		return deptCB;
	}
	
	
}
