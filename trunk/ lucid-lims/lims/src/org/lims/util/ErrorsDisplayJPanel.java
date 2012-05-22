/**
 * 
 */
package org.lims.util;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class ErrorsDisplayJPanel extends JPanel{

	private static final long serialVersionUID = 8698965649281059194L;	
	
	/**
	 * This sets Grid Layout to the panel.
	 * @param exceptions
	 */
	public ErrorsDisplayJPanel(int rows){
		setLayout(new GridLayout(rows, 0));
	}
	
	/**
	 * This adds a error message label to the panel.
	 * @param errMsg
	 */
	public void addErrMsg(String errMsg){
		JLabel msgLabel=new JLabel(errMsg);
		msgLabel.setForeground(Color.RED);
		add(msgLabel);
	}
	
	
	
}
