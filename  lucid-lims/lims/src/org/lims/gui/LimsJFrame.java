/**
 * 
 */
package org.lims.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.ButtonModel;
import javax.swing.DefaultButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.lims.admin.gui.AddDepartmentDialog;
import org.lims.admin.gui.AddEmployeeDialog;
import org.lims.admin.gui.AddRoleDialog;
import org.lims.customer.gui.AddCustomerDialog;
import org.lims.customer.gui.ViewCustDialog;
import org.lims.employee.gui.ChangePasswordDialog;
import org.lims.employee.gui.ViewEmpDialog;
import org.lims.employee.gui.ViewEmployeeDialog;
import org.lims.main.Lims;
import org.lims.register.gui.AckRegisterNumDialog;
import org.lims.register.gui.DeleteRegisterNumDialog;
import org.lims.register.gui.PendingRegDialog;
import org.lims.register.gui.RegisterNumDialog;
import org.lims.register.gui.RegisterSamplesDialog;
import org.lims.register.gui.TestSlipRegisterNumDialog;
import org.lims.util.Constants;
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
		if(Lims.getSessionmap().get(Constants.EMP_ROLE).equalsIgnoreCase("Admin")){
			JMenu adminMenu=createAdminMenu();		
			menubar.add(adminMenu);	
		}
		JMenu customerMenu=createCustomerMenu();
		menubar.add(customerMenu);
		JMenu registerMenu=createSampleRegisterMenu();
		menubar.add(registerMenu);
		JMenu myProfileMenu=createEmpProfile();
		menubar.add(myProfileMenu);
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
		
		JMenu empMenu=new JMenu(resources.getString("dialog.admin.employee.employeeManagement"));
		JMenuItem empRegMI=new JMenuItem(resources.getString("menubar.admin.addEmp"));
		empRegMI.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddEmployeeDialog addDepartment=new AddEmployeeDialog(Lims.getFrame(),
                        Util.getResources().getString("dialog.admin.employee.title"),
                        false);
			}
		});
		empMenu.add(empRegMI);
		
		JMenuItem viewEmpMI=new JMenuItem(resources.getString("dialog.admin.employee.viewEmp"));
		viewEmpMI.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ViewEmpDialog addDepartment=new ViewEmpDialog(Lims.getFrame(),
                        Util.getResources().getString("dialog.admin.view.title"),
                        false);
			}
		});
		empMenu.add(viewEmpMI);		
		menu.add(empMenu);
		
		JMenuItem addDptMI=new JMenuItem(resources.getString("menubar.admin.addDepartment"));
		addDptMI.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddDepartmentDialog addDepartment=new AddDepartmentDialog(Lims.getFrame(),
                        Util.getResources().getString("dialog.admin.dept.title"),
                        false);
			}
		});
		menu.add(addDptMI);
		
		JMenuItem addRolesMI=new JMenuItem(resources.getString("menubar.admin.addRoles"));
		addRolesMI.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				AddRoleDialog roleDialog=new AddRoleDialog(Lims.getFrame(),
						        Util.getResources().getString("dialog.admin.role.title"),
						        false  );
				
			}
		});
		menu.add(addRolesMI);
		
		menu.addSeparator() ;
		
		JMenuItem addressBookMI=new JMenuItem(resources.getString("menubar.admin.addressBook"));
		addressBookMI.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				ViewCustDialog viewCustDialog=new ViewCustDialog(Lims.getFrame(),
						        Util.getResources().getString("dialog.admin.viewCust.title"),
						        false  );
			}
		});
		menu.add(addressBookMI);		
		menu.addSeparator() ;
		
		JMenu reportsMenu=new JMenu(resources.getString("menubar.admin.reports"));
		
		JMenuItem pendingRegMI=new JMenuItem(resources.getString("menubar.admin.reports.pendingRegistrations"));
		pendingRegMI.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				PendingRegDialog pendingRegDialog=new PendingRegDialog(Lims.getFrame(),
						        Util.getResources().getString("dialog.admin.penReg.title"),
						        false  );
			}
		});
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
		
		JMenuItem sampleRisterMI=new JMenuItem(resources.getString("register.menu.mi.sampleRegister"));
		ButtonModel model=new DefaultButtonModel();
		model.setActionCommand("REG");
		sampleRisterMI.setModel(model);
		sampleRisterMI.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				
				RegisterSamplesDialog registerSamplesDialog=new RegisterSamplesDialog(Lims.getFrame(),
						Util.getResources().getString("register.dialog.title"),
						false,event.getActionCommand());
			}
		});
		menu.add(sampleRisterMI);
		
		JMenuItem addDispatchBillingMI=new JMenuItem(resources.getString("register.dialog.menuItem.addDispatchBilling"));
		ButtonModel dispatchModel=new DefaultButtonModel();
		dispatchModel.setActionCommand("DISPATCH");
		addDispatchBillingMI.setModel(dispatchModel);
		addDispatchBillingMI.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				
				RegisterNumDialog registerNumDialog=new RegisterNumDialog(Lims.getFrame(),
						"",	false,event.getActionCommand());
			}
		});
		menu.add(addDispatchBillingMI);	
		
		JMenuItem deleteRegMI=new JMenuItem(resources.getString("register.dialog.menuitem.deleteReg"));		
		deleteRegMI.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				
				DeleteRegisterNumDialog registerNumDialog=new DeleteRegisterNumDialog(Lims.getFrame(),
						"",	false);
			}
		});
		menu.add(deleteRegMI);	
		
		JMenuItem printTestSlipMI=new JMenuItem(resources.getString("register.dialog.menuItem.printTestSlip"));
		printTestSlipMI.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				
				TestSlipRegisterNumDialog registerNumDialog=new TestSlipRegisterNumDialog(Lims.getFrame(),
						"",	false);
			}
		});
		menu.add(printTestSlipMI);	
		
		JMenuItem printAckMI=new JMenuItem(resources.getString("register.dialog.menuItem.printAck"));
		printAckMI.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				
				AckRegisterNumDialog registerNumDialog=new AckRegisterNumDialog(Lims.getFrame(),
						                                    "",	false);
			}
		});
		menu.add(printAckMI);
		
		return menu;
	}
	
	/**
	 * Creates customer menu.
	 * @return
	 */
	private JMenu createCustomerMenu(){		
		JMenu menu=new JMenu(resources.getString("customer.dialog.menu.title"));
		JMenuItem addCustomerMI=new JMenuItem(resources.getString("customer.dialog.menuitem.addCust"));
		addCustomerMI.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				AddCustomerDialog customerDialog=new AddCustomerDialog(Lims.getFrame(),
						       Util.getResources().getString("customer.dialog.menuitem.addCust"),
						       false  );
				
			}
		});
		menu.add(addCustomerMI);
		
		return menu;
		
	}
	
	/**
	 * Creates Employee Profile Menu.
	 * @return JMenu.
	 */
	private JMenu createEmpProfile(){
		JMenu menu=new JMenu(resources.getString("profile.dialog.menu.title"));
		
		JMenuItem viewProfileMI=new JMenuItem(resources.getString("profile.dialog.menuItem.viewProfile"));
		ButtonModel model=new DefaultButtonModel();
		model.setActionCommand("VIEW");
		viewProfileMI.setModel(model);
		viewProfileMI.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				
				ViewEmployeeDialog viewEmpDialog=new ViewEmployeeDialog(Lims.getFrame(),
						Util.getResources().getString("profile.dialog.viewEmp.title"),
						false,event.getActionCommand());
			}
		});
		menu.add(viewProfileMI);
		
		JMenuItem updateProfileMI=new JMenuItem(resources.getString("profile.dialog.menuItem.updateProfile"));
		ButtonModel upModel=new DefaultButtonModel();
		upModel.setActionCommand("UPDATE");
		updateProfileMI.setModel(upModel);
		updateProfileMI.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				
				ViewEmployeeDialog updateEmpDialog=new ViewEmployeeDialog(Lims.getFrame(),
						Util.getResources().getString("profile.dialog.update.title"),
						false,event.getActionCommand());
			}
		});
		menu.add(updateProfileMI);	
		
		JMenuItem changePasswordMI=new JMenuItem(resources.getString("profile.dialog.menuItem.changePwd"));
		changePasswordMI.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				
				ChangePasswordDialog registerNumDialog=new ChangePasswordDialog(Lims.getFrame(),
						Util.getResources().getString("profile.dialog.changePassword.title"),
						true);
			}
		});
		menu.add(changePasswordMI);
		return menu;
	}
	
	
}
