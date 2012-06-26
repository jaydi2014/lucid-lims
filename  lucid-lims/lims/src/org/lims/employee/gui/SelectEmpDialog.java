/**
 * 
 */
package org.lims.employee.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import org.apache.log4j.Logger;
import org.lims.employee.gui.listeners.SelectEmpTFKeyListener;
import org.lims.employee.service.EmployeeService;
import org.lims.employee.service.EmployeeServiceInter;
import org.lims.gui.util.GuiUtil;
import org.lims.register.gui.EmpNamePanel;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class SelectEmpDialog extends JDialog{

	private static final long serialVersionUID = -8230712410015799178L;
	private Logger log=Logger.getLogger(SelectEmpDialog.class);
	private ResourceBundle resources=Util.getResources();
	private EmployeeServiceInter service=new EmployeeService();
	private Map<String,List<String>> empNamesMap=new HashMap<String,List<String>>();
	
	private JLabel empIdLabel;
	private JTextField empIdTF;
	private JLabel orLabel;
	private JLabel empNameLabel;
	private JTextField empNameTF;
	private JLabel selectEmpLabel; 
	private JList empList;
	private EmpNamePanel enp;
	private SelectEmpDialog sed;
	
	public SelectEmpDialog(Frame owner, String title, boolean modal, EmpNamePanel empNamePanel) {
		super(owner,title,modal);
		sed=this;
		enp=empNamePanel;
		getContentPane().setLayout(new BorderLayout());
		JPanel centerPanel=centerPanel();
		add(centerPanel,BorderLayout.CENTER);
		GuiUtil.CompCenterCords cords=GuiUtil.getCompCenterCords(390, 560);
		setBounds(cords.getX(), cords.getY(), 390,560);		
		setVisible(true);
	}
	
	private JPanel centerPanel(){
		JPanel panel=new JPanel();
		panel.setLayout(null);	
		
		JPanel empNamePanel=new JPanel();
		empNamePanel.setLayout(null);
		empNameLabel=new JLabel(resources.getString("dialog.admin.label.view.empName"));
		empNameLabel.setBounds(10, 50, 150, 30);
		empNamePanel.add(empNameLabel);
		empNameTF=new JTextField();
		empNameTF.addKeyListener(new SelectEmpTFKeyListener(this));
		empNameTF.setBounds(160, 50, 150, 30);
		empNameTF.setPreferredSize(new Dimension(150,30));
		empNamePanel.add(empNameTF);
		DefaultListModel empListModel=new DefaultListModel();
		try{
			String origdept=EmpNamePanel.department;
			if(origdept !=null && !origdept.isEmpty()&& !origdept.equals("Select Department")){
				String dept=origdept.toLowerCase();
				List<String> empNames=service.getEmpDisplayNames(dept);
				empNamesMap.put(dept,empNames );
				for(String empName:empNames){
					empListModel.addElement(empName);
				}
			}
		}catch(Exception e){
			log.debug(e.getMessage(), e);
		}
		empList=new JList(empListModel);
		empList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		empList.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent event){
		        if(event.getClickCount()==2){ 
		        	sed.dispose();
		        	JList custList=(JList)event.getSource();
		        	String empName=(String)custList.getSelectedValue();
		        	enp.getEmpNameTF().setText(empName);
		        }
			}
		});
		JScrollPane scrolls=new JScrollPane(empList);
		scrolls.setBounds(150, 100, 150, 400);
		empNamePanel.add(scrolls);
		
		Border nameTitledBorder=BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
                resources.getString("dialog.admin.border.view.byName"), 
                TitledBorder.LEFT, TitledBorder.TOP);
		empNamePanel.setBorder(nameTitledBorder);
		empNamePanel.setBounds(0,0, 360, 510);
		panel.add(empNamePanel);		
		return panel;
	}

	/**
	 * @return the empIdLabel
	 */
	public JLabel getEmpIdLabel() {
		return empIdLabel;
	}

	/**
	 * @param empIdLabel the empIdLabel to set
	 */
	public void setEmpIdLabel(JLabel empIdLabel) {
		this.empIdLabel = empIdLabel;
	}

	/**
	 * @return the empIdTF
	 */
	public JTextField getEmpIdTF() {
		return empIdTF;
	}

	/**
	 * @param empIdTF the empIdTF to set
	 */
	public void setEmpIdTF(JTextField empIdTF) {
		this.empIdTF = empIdTF;
	}

	/**
	 * @return the orLabel
	 */
	public JLabel getOrLabel() {
		return orLabel;
	}

	/**
	 * @param orLabel the orLabel to set
	 */
	public void setOrLabel(JLabel orLabel) {
		this.orLabel = orLabel;
	}

	/**
	 * @return the empNameLabel
	 */
	public JLabel getEmpNameLabel() {
		return empNameLabel;
	}

	/**
	 * @param empNameLabel the empNameLabel to set
	 */
	public void setEmpNameLabel(JLabel empNameLabel) {
		this.empNameLabel = empNameLabel;
	}

	/**
	 * @return the empNameTF
	 */
	public JTextField getEmpNameTF() {
		return empNameTF;
	}

	/**
	 * @param empNameTF the empNameTF to set
	 */
	public void setEmpNameTF(JTextField empNameTF) {
		this.empNameTF = empNameTF;
	}

	/**
	 * @return the selectEmpLabel
	 */
	public JLabel getSelectEmpLabel() {
		return selectEmpLabel;
	}

	/**
	 * @param selectEmpLabel the selectEmpLabel to set
	 */
	public void setSelectEmpLabel(JLabel selectEmpLabel) {
		this.selectEmpLabel = selectEmpLabel;
	}

	/**
	 * @return the empList
	 */
	public JList getEmpList() {
		return empList;
	}

	/**
	 * @param empList the empList to set
	 */
	public void setEmpList(JList empList) {
		this.empList = empList;
	}

	/**
	 * @return the empNamesMap
	 */
	public Map<String, List<String>> getEmpNamesMap() {
		return empNamesMap;
	}

	/**
	 * @param empNamesMap the empNamesMap to set
	 */
	public void setEmpNamesMap(Map<String, List<String>> empNamesMap) {
		this.empNamesMap = empNamesMap;
	}

	/**
	 * @return the enp
	 */
	public EmpNamePanel getEnp() {
		return enp;
	}

	

	
}
