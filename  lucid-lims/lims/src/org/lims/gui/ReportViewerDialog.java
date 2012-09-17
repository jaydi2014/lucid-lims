/**
 * 
 */
package org.lims.gui;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JDialog;

import org.lims.gui.util.GuiUtil;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class ReportViewerDialog extends JDialog{
	
	
	private static final long serialVersionUID = -6741203353054504811L;
	
	//private Logger log=Logger.getLogger(ReportViewerDialog.class);
	

	public ReportViewerDialog(Frame owner, String title, boolean modal) {
		super(owner,title,modal);		
		getContentPane().setLayout(new BorderLayout());		
		GuiUtil.CompCenterCords cords=GuiUtil.getCompCenterCords(1000, 650);
		setBounds(cords.getX(), cords.getY(), 1000, 650);		
		
	}
	
	
}
