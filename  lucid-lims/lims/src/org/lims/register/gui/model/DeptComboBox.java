/**
 * 
 */
package org.lims.register.gui.model;

import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class DeptComboBox extends JComboBox implements TableCellRenderer {	
	
	private static final long serialVersionUID = -5145465944626755563L;

	public DeptComboBox(Object[] values){
		super(values);
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		if (isSelected) {
            setForeground(table.getSelectionForeground());
            super.setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(table.getBackground());
        }
		
		setSelectedItem(value);
		return this;
	}

}
