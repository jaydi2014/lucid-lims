/**
 * 
 */
package org.lims.main;

import javax.swing.UIManager;

import org.apache.log4j.Logger;
import org.lims.gui.LimsJFrame;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 * This is the main class for this software.
 */
public class Lims {
	
	private static LimsJFrame frame; 
	private static Logger log=Logger.getLogger(Lims.class);

	/**
	 * This is the Main method for this software.
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
		    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());		    
		} catch (Exception exc) {
		    log.debug("Error loading L&F: ", exc);
		}

		frame= new LimsJFrame(Util.getResources().getString("frmae.title"));
	}

	/**
	 * This returns the lims main frame.
	 * @return JFrame.
	 */
	public static LimsJFrame getFrame() {
		return frame;
	}

}
