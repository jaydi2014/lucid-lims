/**
 * 
 */
package org.lims.register.gui.model;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class DeptComboBoxEditor extends DefaultCellEditor{	
	
	private static final long serialVersionUID = 4392603250502174636L;

		public DeptComboBoxEditor(String[] items) {
	        super(new JComboBox(items));
	    }
	

}
