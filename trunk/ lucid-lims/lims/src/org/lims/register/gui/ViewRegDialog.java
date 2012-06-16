/**
 * 
 */
package org.lims.register.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.lims.gui.util.GuiUtil;
import org.lims.register.gui.listeners.ViewRegNextButtonListener;
import org.lims.register.gui.listeners.ViewRegPreviousButtonListener;
import org.lims.register.gui.listeners.ViewRegsFetchButtonListener;
import org.lims.util.Constants;
import org.lims.util.Util;

import com.toedter.calendar.JDateChooser;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class ViewRegDialog extends JDialog{	
	
	private static final long serialVersionUID = 7587871450546226456L;
	private ResourceBundle resources=Util.getResources();
	//private Logger log=Logger.getLogger(ViewRegDialog.class);
	private JDateChooser fromDateDC;
	private JDateChooser toDateDC;
	private JTable regsTable;
	private JLabel pageSizeLabel;
	private JTextField pageSizeTF;
	private JButton previousB;
	private JButton nextB;
	private JLabel dmessageLabel;
	private Integer pageNumber=1;
	
	public ViewRegDialog(Frame owner, String title, boolean modal) {
		super(owner,title,modal);		
		getContentPane().setLayout(new BorderLayout());
		JPanel westPanel=westPanel();
		add(westPanel,BorderLayout.WEST);
		JPanel centralPanel=centralPanel();
		add(centralPanel,BorderLayout.CENTER);
		GuiUtil.CompCenterCords cords=GuiUtil.getCompCenterCords(1000, 700);
		setBounds(cords.getX(), cords.getY(), 1000, 700);		
		setVisible(true);
	}
	
	private JPanel westPanel(){
		JPanel panel=new JPanel();
		panel.setLayout(null);
		
		JPanel fromdatePanel=new JPanel();
		fromdatePanel.setLayout(new GridLayout(1,1));
		fromDateDC=new JDateChooser(null,Constants.DATE_PATTERN);
		fromdatePanel.add(fromDateDC);
		Border titledBorder=BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
                resources.getString("register.dialog.border.fromdate.title"), 
                TitledBorder.LEFT, TitledBorder.TOP);
		fromdatePanel.setBorder(titledBorder);
		fromdatePanel.setBounds(20, 20, 120, 50);
		panel.add(fromdatePanel);
		
		JPanel todatePanel=new JPanel();
		todatePanel.setLayout(new GridLayout(1,1));
		toDateDC=new JDateChooser(null,Constants.DATE_PATTERN);
		todatePanel.add(toDateDC);
		Border totitledBorder=BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
                resources.getString("register.dialog.border.todate.title"), 
                TitledBorder.LEFT, TitledBorder.TOP);
		todatePanel.setBorder(totitledBorder);
		todatePanel.setBounds(20, 80, 120, 50);
		panel.add(todatePanel);
		
		JButton fetchB=new JButton(resources.getString("register.dialog.button.fetchRegs"));
		fetchB.addActionListener(new ViewRegsFetchButtonListener(this));
		fetchB.setBounds(20, 140, 120, 30);
		panel.add(fetchB);
		
		panel.setPreferredSize(new Dimension(150,100));
		return panel;		
		
	}
	
	private JPanel centralPanel(){
		JPanel panel=new JPanel();	
		panel.setLayout(null);
		Object columns[]={"Reg Number","Customer Name","Reg Date","Due Date","Dispatch Date"};	
		DefaultTableModel regsTableModel=new DefaultTableModel(columns,0){
			 public boolean isCellEditable(int row, int column){
			      return false;
			    }
		};
		regsTable=new JTable(regsTableModel);
		regsTable.setColumnSelectionAllowed(true);
		JScrollPane scrolls=new JScrollPane(regsTable);
		scrolls.setBounds(0, 0, 850, 600);
		panel.add(scrolls);
		previousB=new JButton(resources.getString("register.dialog.button.previous"));
		previousB.addActionListener(new ViewRegPreviousButtonListener(this));
		previousB.setEnabled(false);
		previousB.setBounds(20, 610,120, 30);
		panel.add(previousB);
		nextB=new JButton(resources.getString("register.dialog.button.next"));
		nextB.addActionListener(new ViewRegNextButtonListener(this));
		nextB.setEnabled(false);
		nextB.setBounds(150, 610, 80, 30);
		panel.add(nextB);
		pageSizeLabel=new JLabel(resources.getString("register.dialog.label.view.pageSize"));
		pageSizeLabel.setBounds(240, 610, 60, 30);
		panel.add(pageSizeLabel);
		pageSizeTF=new JTextField("50");
		pageSizeTF.setBounds(300, 610, 80, 30);
		panel.add(pageSizeTF);
		dmessageLabel=new JLabel();
		dmessageLabel.setBounds(390, 610, 200, 30);
		panel.add(dmessageLabel);
		return panel;
	}

	/**
	 * @return the fromDateDC
	 */
	public JDateChooser getFromDateDC() {
		return fromDateDC;
	}

	/**
	 * @param fromDateDC the fromDateDC to set
	 */
	public void setFromDateDC(JDateChooser fromDateDC) {
		this.fromDateDC = fromDateDC;
	}

	/**
	 * @return the toDateDC
	 */
	public JDateChooser getToDateDC() {
		return toDateDC;
	}

	/**
	 * @param toDateDC the toDateDC to set
	 */
	public void setToDateDC(JDateChooser toDateDC) {
		this.toDateDC = toDateDC;
	}

	/**
	 * @return the regsTable
	 */
	public JTable getRegsTable() {
		return regsTable;
	}

	/**
	 * @param regsTable the regsTable to set
	 */
	public void setRegsTable(JTable regsTable) {
		this.regsTable = regsTable;
	}

	/**
	 * @return the pageSizeLabel
	 */
	public JLabel getPageSizeLabel() {
		return pageSizeLabel;
	}

	/**
	 * @param pageSizeLabel the pageSizeLabel to set
	 */
	public void setPageSizeLabel(JLabel pageSizeLabel) {
		this.pageSizeLabel = pageSizeLabel;
	}

	/**
	 * @return the pageSizeTF
	 */
	public JTextField getPageSizeTF() {
		return pageSizeTF;
	}

	/**
	 * @param pageSizeTF the pageSizeTF to set
	 */
	public void setPageSizeTF(JTextField pageSizeTF) {
		this.pageSizeTF = pageSizeTF;
	}

	/**
	 * @return the previousB
	 */
	public JButton getPreviousB() {
		return previousB;
	}

	/**
	 * @param previousB the previousB to set
	 */
	public void setPreviousB(JButton previousB) {
		this.previousB = previousB;
	}

	/**
	 * @return the nextB
	 */
	public JButton getNextB() {
		return nextB;
	}

	/**
	 * @param nextB the nextB to set
	 */
	public void setNextB(JButton nextB) {
		this.nextB = nextB;
	}

	/**
	 * @return the dmessageLabel
	 */
	public JLabel getDmessageLabel() {
		return dmessageLabel;
	}

	/**
	 * @param dmessageLabel the dmessageLabel to set
	 */
	public void setDmessageLabel(JLabel dmessageLabel) {
		this.dmessageLabel = dmessageLabel;
	}

	/**
	 * @return the pageNumber
	 */
	public Integer getPageNumber() {
		return pageNumber;
	}

	/**
	 * @param pageNumber the pageNumber to set
	 */
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

}
