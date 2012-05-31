/**
 * 
 */
package org.lims.employee.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import org.lims.customer.gui.listeners.EmpNameTFKeyListener;
import org.lims.employee.gui.listeners.EmployeeJListMouseListener;
import org.lims.employee.gui.listeners.FetchEmpButtonListener;
import org.lims.gui.util.GuiUtil;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class ViewEmpDialog extends JDialog{

	private static final long serialVersionUID = -8230712410015799178L;
	private ResourceBundle resources=Util.getResources();
	private Map<String,List<String>> empNamesMap=new HashMap<String,List<String>>();
	
	private JLabel empIdLabel;
	private JTextField empIdTF;
	private JLabel orLabel;
	private JLabel empNameLabel;
	private JTextField empNameTF;
	private JLabel selectEmpLabel; 
	private JList empList;
	private JButton fetchButton;
	
	public ViewEmpDialog(Frame owner, String title, boolean modal) {
		super(owner,title,modal);		
		getContentPane().setLayout(new BorderLayout());
		JPanel westPanel=westPanel();
		add(westPanel,BorderLayout.WEST);
		GuiUtil.CompCenterCords cords=GuiUtil.getCompCenterCords(700, 550);
		setBounds(cords.getX(), cords.getY(), 700, 550);		
		setVisible(true);
	}
	
	private JPanel westPanel(){
		JPanel panel=new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		empIdLabel=new JLabel(resources.getString("dialog.admin.label.view.empId"));
		panel.add(empIdLabel,c);
		empIdTF=new JTextField();
		c.gridwidth = GridBagConstraints.REMAINDER;
		empIdTF.setPreferredSize(new Dimension(150,30));		
		panel.add(empIdTF,c);
		fetchButton=new JButton(resources.getString("admin.dialog.button.fetchEmp"));
		fetchButton.addActionListener(new FetchEmpButtonListener(this));
		panel.add(fetchButton,c);
		orLabel=new JLabel(resources.getString("dialog.admin.label.view.or"));		 
		panel.add(orLabel,c);
		empNameLabel=new JLabel(resources.getString("dialog.admin.label.view.empName"));
		c.gridwidth = GridBagConstraints.RELATIVE;
		panel.add(empNameLabel,c);
		empNameTF=new JTextField();
		empNameTF.addKeyListener(new EmpNameTFKeyListener(this));
		c.gridwidth = GridBagConstraints.REMAINDER;
		empNameTF.setPreferredSize(new Dimension(150,30));
		panel.add(empNameTF,c);
		selectEmpLabel=new JLabel(resources.getString("dialog.admin.label.view.selectEmp"));
		c.gridwidth = GridBagConstraints.RELATIVE;
		panel.add(selectEmpLabel,c);
		empList=new JList(new DefaultListModel());
		empList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		empList.addMouseListener(new EmployeeJListMouseListener(this));
		c.gridwidth = GridBagConstraints.REMAINDER;
		JScrollPane scrolls=new JScrollPane(empList);
		scrolls.setPreferredSize(new Dimension(150,300));
		panel.add(scrolls,c);
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

	
}
