/**
 * 
 */
package org.lims.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.lims.admin.gui.AddDepartmentDialog;
import org.lims.admin.gui.AddEmployeeDialog;
import org.lims.admin.gui.AddRoleDialog;
import org.lims.main.Lims;
import org.lims.register.gui.RegisterSamplesDialog;
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
		JMenu adminMenu=createAdminMenu();		
		menubar.add(adminMenu);	
		JMenu customerMenu=createCustomerMenu();
		menubar.add(customerMenu);
		JMenu registerMenu=createSampleRegisterMenu();
		menubar.add(registerMenu);
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
	
	/**
	 * Creates Admin menu.
	 * @return JMenu.
	 */
	private JMenu createAdminMenu(){
		JMenu menu=new JMenu(resources.getString("menubar.admin"));
		JMenuItem empRegMI=new JMenuItem(resources.getString("menubar.admin.addEmp"));
		empRegMI.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddEmployeeDialog addDepartment=new AddEmployeeDialog(Lims.getFrame(),
                        Util.getResources().getString("dialog.admin.employee.title"),
                        true);
			}
		});
		menu.add(empRegMI);
		
		JMenuItem addDptMI=new JMenuItem(resources.getString("menubar.admin.addDepartment"));
		addDptMI.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddDepartmentDialog addDepartment=new AddDepartmentDialog(Lims.getFrame(),
                        Util.getResources().getString("dialog.admin.dept.title"),
                        true);
			}
		});
		menu.add(addDptMI);
		
		JMenuItem addRolesMI=new JMenuItem(resources.getString("menubar.admin.addRoles"));
		addRolesMI.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				AddRoleDialog roleDialog=new AddRoleDialog(Lims.getFrame(),
						        Util.getResources().getString("dialog.admin.role.title"),
						        true  );
				
			}
		});
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
		
		return menu;
	}
	
	/**
	 * creates register menu.
	 * @return register Menu.
	 */
	private JMenu createSampleRegisterMenu(){
		JMenu menu=new JMenu(resources.getString("register.menu.name"));
		JMenuItem sampleisterMI=new JMenuItem(resources.getString("register.menu.mi.sampleRegister"));
		sampleisterMI.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				
				RegisterSamplesDialog registerSamplesDialog=new RegisterSamplesDialog(Lims.getFrame(),
						Util.getResources().getString("register.dialog.title"),
						true);
			}
		});
		menu.add(sampleisterMI);
		
		return menu;
	}
	
	private JMenu createCustomerMenu(){		
		JMenu menu=new JMenu(resources.getString("customer.dialog.menu.title"));
		JMenuItem addCustomerMI=new JMenuItem(resources.getString("customer.dialog.menuitem.addCust"));
		addCustomerMI.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				//AddRoleDialog roleDialog=new AddRoleDialog(Lims.getFrame(),
						      //  Util.getResources().getString("dialog.admin.role.title"),
						      //  true  );
				
			}
		});
		menu.add(addCustomerMI);
		
		return menu;
		
	}
	
	
}