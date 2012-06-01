/**
 * 
 */
package org.lims.employee.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

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
		GuiUtil.CompCenterCords cords=GuiUtil.getCompCenterCords(800, 550);
		setBounds(cords.getX(), cords.getY(), 800, 550);		
		setVisible(true);
	}
	
	private JPanel westPanel(){
		JPanel panel=new JPanel();
		panel.setLayout(null);		
		
		JPanel empIdPanel=new JPanel();
		empIdPanel.setLayout(null);
		empIdLabel=new JLabel(resources.getString("dialog.admin.label.view.empId"));
		empIdLabel.setBounds(50, 50, 150, 30);
		empIdPanel.add(empIdLabel);
		empIdTF=new JTextField();
		empIdTF.setBounds(200, 50, 150, 30);		
		empIdPanel.add(empIdTF);
		fetchButton=new JButton(resources.getString("admin.dialog.button.fetchEmp"));
		fetchButton.addActionListener(new FetchEmpButtonListener(this));
		fetchButton.setBounds(100, 100,150, 30);
		empIdPanel.add(fetchButton);
		
		Border titledBorder=BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
                resources.getString("dialog.admin.border.view.byId"), 
                TitledBorder.LEFT, TitledBorder.TOP);
		
		empIdPanel.setBorder(titledBorder);
		empIdPanel.setBounds(0, 0, 360, 150);		
		panel.add(empIdPanel);
		
		
		orLabel=new JLabel(resources.getString("dialog.admin.label.view.or"));	
		orLabel.setBounds(120, 160, 100, 30);
		panel.add(orLabel);
		
		JPanel empNamePanel=new JPanel();
		empNamePanel.setLayout(null);
		empNameLabel=new JLabel(resources.getString("dialog.admin.label.view.empName"));
		empNameLabel.setBounds(50, 50, 100, 30);
		empNamePanel.add(empNameLabel);
		empNameTF=new JTextField();
		empNameTF.addKeyListener(new EmpNameTFKeyListener(this));
		empNameTF.setBounds(150, 50, 150, 30);
		empNameTF.setPreferredSize(new Dimension(150,30));
		empNamePanel.add(empNameTF);
		empList=new JList(new DefaultListModel());
		empList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		empList.addMouseListener(new EmployeeJListMouseListener(this));
		JScrollPane scrolls=new JScrollPane(empList);
		scrolls.setBounds(150, 100, 150, 200);
		empNamePanel.add(scrolls);
		
		Border nameTitledBorder=BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
                resources.getString("dialog.admin.border.view.byName"), 
                TitledBorder.LEFT, TitledBorder.TOP);
		empNamePanel.setBorder(nameTitledBorder);
		empNamePanel.setBounds(0,200, 360, 310);
		panel.add(empNamePanel);
		panel.setPreferredSize(new Dimension(360,150));
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
