/**
 * 
 */
package org.lims.register.gui.model;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import org.lims.register.gui.EmpNamePanel;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class EmpNamePanelRenderer extends EmpNamePanel implements TableCellRenderer{
	
	private static final long serialVersionUID = 5637984468556275113L;
	

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
		
		getEmpNameTF().setText((String)value);
		return this;
	}
}
