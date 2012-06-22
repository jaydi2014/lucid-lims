/**
 * 
 */
package org.lims.register.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.lims.employee.gui.SelectEmpDialog;
import org.lims.main.Lims;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class EmpNamePanel extends JPanel {	
	
	private static final long serialVersionUID = 934032676749762710L;
	private ResourceBundle resources=Util.getResources();
	
	private JTextField empNameTF;
	private JButton browseB;
	public static String departmrnt;
	
	public EmpNamePanel(){
		super(null);
		empNameTF=new JTextField();
		empNameTF.setEditable(false);
		empNameTF.setBounds(0, 0, 150, 30);
		add(empNameTF);
		browseB=new JButton("Browse");
		browseB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				SelectEmpDialog sed=new SelectEmpDialog(Lims.getFrame(),
						resources.getString("register.dialog.selectemp.title"),
						true);
				
			}
		});
		browseB.setBounds(150,0, 100, 30);
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
	
	
}