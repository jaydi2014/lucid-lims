/**
 * 
 */
package org.lims.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.lims.admin.gui.listeners.AddDepartmentListener;
import org.lims.admin.gui.listeners.AddEmpListener;
import org.lims.admin.gui.listeners.AddRoleListener;
import org.lims.util.Util;
import org.lims.util.resources.Resources;

/**
 * @author Muralidhar Yaragalla
 * This is the main frame.
 */
public class LimsJFrame extends JFrame{

	private static final long serialVersionUID = 314538076292679644L;
	
	private ResourceBundle resources=Util.getResources();
	public LimsJFrame(String title){
		super(title);
		
		JMenuBar menubar=new JMenuBar();
		
		JMenu menu=new JMenu(resources.getString("menubar.admin"));
		JMenuItem empRegMI=new JMenuItem(resources.getString("menubar.admin.addEmp"));
		empRegMI.addActionListener(new AddEmpListener());
		menu.add(empRegMI);
		
		JMenuItem addDptMI=new JMenuItem(resources.getString("menubar.admin.addDepartment"));
		addDptMI.addActionListener(new AddDepartmentListener());
		menu.add(addDptMI);
		
		JMenuItem addRolesMI=new JMenuItem(resources.getString("menubar.admin.addRoles"));
		addRolesMI.addActionListener(new AddRoleListener());
		menu.add(addRolesMI);
		
		menu.addSeparator() ;
		
		JMenuItem addressBookMI=new JMenuItem(resources.getString("menubar.admin.addressBook"));
		//addressBookMI.addActionListener(new EmployeeRegListener());
		menu.add(addressBookMI);
		
		menu.addSeparator() ;
		
		JMenu reportsMenu=new JMenu(resources.getString("menubar.admin.reports"));
		
		JMenuItem pendingRegMI=new JMenuItem(resources.getString("menubar.admin.reports.pendingRegistrations"));
		//pendingRegMI.addActionListener(new EmployeeRegListener());
		reportsMenu.add(pendingRegMI);
		
		menu.add(reportsMenu);
		
		menubar.add(menu);
		
		setJMenuBar(menubar);
				
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0,0,(screenSize.width-50),(screenSize.height-50));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JDesktopPane desktop = new JDesktopPane();
		setContentPane(desktop);
		ImageIcon icon=new ImageIcon(Resources.class.getResource(resources.getString("frame.icon")));
		setIconImage(icon.getImage());
		setVisible(true);
	}
	
	
}
