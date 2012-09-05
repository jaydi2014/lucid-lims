/**
 * 
 */
package org.lims.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.lims.gui.util.GuiUtil;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class ReportViewerDialog extends JDialog{
	
	private Logger log=Logger.getLogger(ReportViewerDialog.class);
	

	public ReportViewerDialog(Frame owner, String title, boolean modal) {
		super(owner,title,modal);		
		getContentPane().setLayout(new BorderLayout());		
		GuiUtil.CompCenterCords cords=GuiUtil.getCompCenterCords(1000, 650);
		setBounds(cords.getX(), cords.getY(), 1000, 650);		
		
	}
	
	
}
