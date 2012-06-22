/**
 * 
 */
package org.lims.register.gui.model;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import org.lims.register.gui.EmpNamePanel;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class EmpNamePanelEditor extends AbstractCellEditor implements TableCellEditor{

	private static final long serialVersionUID = -5575645761840609401L;
	private EmpNamePanel enp;
	
	public EmpNamePanelEditor(){
		enp=new EmpNamePanel();
	}

	/* (non-Javadoc)
	 * @see javax.swing.CellEditor#getCellEditorValue()
	 */
	@Override
	public Object getCellEditorValue() {
		String value=enp.getEmpNameTF().getText();
		return value;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableCellEditor#getTableCellEditorComponent(javax.swing.JTable, java.lang.Object, boolean, int, int)
	 */
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		
		DefaultTableModel dtm=(DefaultTableModel)table.getModel();
		String dept=(String)dtm.getValueAt(row, 0);
		EmpNamePanel.departmrnt=dept;
		return enp;
	}
	
	

}
