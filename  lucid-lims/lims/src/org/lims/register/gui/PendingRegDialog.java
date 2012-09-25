/**
 * 
 */
package org.lims.register.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import org.apache.log4j.Logger;
import org.lims.admin.service.AdminService;
import org.lims.admin.service.AdminServiceInter;
import org.lims.gui.util.GuiUtil;
import org.lims.register.gui.listeners.PendingRegButtonListener;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class PendingRegDialog extends JDialog{
	
	private static final long serialVersionUID = -8725104686917355851L;	
		
	private ResourceBundle resources=Util.getResources();
	private Logger log=Logger.getLogger(PendingRegDialog.class);
	private AdminServiceInter adminService=new AdminService();
	
	private JTextPane pendingRegTP;
	private JComboBox deptCB;
	
	public PendingRegDialog(Frame owner, String title, boolean modal) {
		super(owner,title,modal);		
		getContentPane().setLayout(new BorderLayout());
		JPanel southPanel=southPanel();
		add(southPanel,BorderLayout.SOUTH);
		GuiUtil.CompCenterCords cords=GuiUtil.getCompCenterCords(1000, 650);
		setBounds(cords.getX(), cords.getY(), 1000, 650);		
		setVisible(true);
	}
	
	/**
	 * creates the center panel.
	 * @return JPanel
	 */
	public JPanel southPanel(){
		JPanel panel=new JPanel();
		panel.setLayout(null);
		/*pendingRegTP=new JTextPane();
		pendingRegTP.setContentType("text/html");
		pendingRegTP.setEditable(false);
		JScrollPane scrolls=new JScrollPane(pendingRegTP);
		scrolls.setBounds(10, 10, 970, 550);
		panel.add(scrolls);*/
		JButton fetchRegsButton=new JButton(resources.getString("dialog.reports.button.pendingRegs"));
		fetchRegsButton.addActionListener(new PendingRegButtonListener(this));
		fetchRegsButton.setBounds(10, 10, 200, 30);
		panel.add(fetchRegsButton);
		JLabel deptLabel=new JLabel(resources.getString("dialog.admin.label.penReg.department"));
		deptLabel.setBounds(230, 10,100, 30);
		panel.add(deptLabel);
		List<String> depts=null;
		try{
			depts=adminService.getDepartments();
			depts.add(0, "ALL");
		}catch(Exception e){
			log.debug(e.getMessage(), e);
		}
		deptCB=new JComboBox(depts.toArray());
		deptCB.setBounds(330, 10, 150, 30);
		panel.add(deptCB);
		/*JButton printButton=new JButton(resources.getString("dialog.admin.button.penReg.print"));
		printButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				try{
					pendingRegTP.print();
				}catch(Exception e){
					log.debug(e.getMessage(), e);
				}
			}
		});
		printButton.setBounds(500, 570, 100, 30);
		panel.add(printButton);*/
		panel.setPreferredSize(new Dimension(50,50));
		return panel;
	}

	/**
	 * @return the pendingRegTP
	 */
	public JTextPane getPendingRegTP() {
		return pendingRegTP;
	}

	/**
	 * @param pendingRegTP the pendingRegTP to set
	 */
	public void setPendingRegTP(JTextPane pendingRegTP) {
		this.pendingRegTP = pendingRegTP;
	}

	/**
	 * @return the deptCB
	 */
	public JComboBox getDeptCB() {
		return deptCB;
	}

	/**
	 * @param deptCB the deptCB to set
	 */
	public void setDeptCB(JComboBox deptCB) {
		this.deptCB = deptCB;
	}

}
