/**
 * 
 */
package org.lims.register.gui;

import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class EmpNamePanelDisplay extends JPanel {	
	
	private static final long serialVersionUID = 934032676749762710L;
	
	private ResourceBundle resources=Util.getResources();
	
	private JTextField empNameTF;
	private JButton browseB;
	
	
	private JComboBox deptCB;
	
	public EmpNamePanelDisplay(){
		super(null);				
		deptCB=new JComboBox();		
		deptCB.setBounds(0,0,150,30);
		add(deptCB);
		empNameTF=new JTextField();
		empNameTF.setEditable(false);
		empNameTF.setBounds(160, 0, 150, 30);
		add(empNameTF);
		browseB=new JButton(resources.getString("register.dialog.button.empNamePanel.browse"));		
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
