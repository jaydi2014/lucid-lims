/**
 * 
 */
package org.lims.main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.apache.log4j.Logger;
import org.lims.employee.dto.EmployeeDto;
import org.lims.employee.service.EmployeeService;
import org.lims.employee.service.EmployeeServiceInter;
import org.lims.gui.LimsJFrame;
import org.lims.gui.util.ErrorsDisplayJPanel;
import org.lims.gui.util.GuiUtil;
import org.lims.util.Constants;
import org.lims.util.Util;
import org.lims.util.resources.Resources;

/**
 * @author Muralidhar Yaragalla
 *
 * This is the main class for this software.
 */
public class Lims {
	
	private static LimsJFrame frame; 
	private final static Map<String,String> sessionMap=new HashMap<String,String>();
	private static Logger log=Logger.getLogger(Lims.class);
	private static ResourceBundle resources=Util.getResources();
	private static EmployeeServiceInter empService=new EmployeeService();	
	private static ErrorsDisplayJPanel errorMsgPanel ; 
	private static JDialog userDialog;

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
		/*cleanup();
		userDialog=new JDialog();
		userDialog.setTitle(resources.getString("login.title"));
		userDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		userDialog.setLayout(new BorderLayout());
		JPanel centerPanel=new JPanel();
		centerPanel.setLayout(null);
		JLabel userLabel=new JLabel(resources.getString("login.label.userName"));
		userLabel.setBounds(50, 50, 150, 30);
		centerPanel.add(userLabel);
		final JTextField userTF=new JTextField();
		userTF.setBounds(200,50, 150, 30);
		centerPanel.add(userTF);
		
		JLabel passwordLabel=new JLabel(resources.getString("login.label.password"));
		passwordLabel.setBounds(50, 100, 150, 30);
		centerPanel.add(passwordLabel);
		final JPasswordField passwordTF=new JPasswordField();
		passwordTF.setBounds(200,100, 150, 30);
		centerPanel.add(passwordTF);
		userDialog.add(centerPanel,BorderLayout.CENTER);
		
		JButton submitButton=new JButton(resources.getString("login.button.submit"));
		submitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				try{
					EmployeeDto employee=empService.checkUserNamePass(userTF.getText(), new String(passwordTF.getPassword()));
					sessionMap.put(Constants.EMP_ID,employee.getEmpId());
			        sessionMap.put(Constants.EMP_NAME,employee.getEmpName());
			        sessionMap.put(Constants.EMP_DESG,employee.getEmpDesignation());
			        sessionMap.put(Constants.EMP_ROLE,employee.getEmpRole());
					userDialog.dispose();
					frame= new LimsJFrame(Util.getResources().getString("frmae.title"));
				}catch(Exception e){
					errorMsgPanel=new ErrorsDisplayJPanel(1);
					errorMsgPanel.addErrMsg(e.getMessage());
					userDialog.add(errorMsgPanel,BorderLayout.NORTH);
					userDialog.validate();
					userDialog.repaint();
				}
			}
		});
		submitButton.setBounds(120,140, 150, 30);
		centerPanel.add(submitButton);
		GuiUtil.CompCenterCords cords=GuiUtil.getCompCenterCords(380, 250);
		userDialog.setBounds(cords.getX(), cords.getY(), 380, 250);
		ImageIcon icon=new ImageIcon(Resources.class.getResource(resources.getString("frame.icon")));
		userDialog.setIconImage(icon.getImage());
		userDialog.setVisible(true);	*/
		
		sessionMap.put(Constants.EMP_ID,"lims-1234");
        sessionMap.put(Constants.EMP_NAME,"Muralidhar");
        sessionMap.put(Constants.EMP_DESG,"Manager");
        sessionMap.put(Constants.EMP_ROLE,"Admin");
		
		frame= new LimsJFrame(Util.getResources().getString("frmae.title"));
        	
	}
	
	/**
	 * It does some cleanup process.
	 */
	private static void cleanup(){
		if(errorMsgPanel!=null){
			userDialog.remove(errorMsgPanel);
			userDialog.validate();
			userDialog.repaint();
		}
	}

	/**
	 * This returns the lims main frame.
	 * @return JFrame.
	 */
	public static LimsJFrame getFrame() {
		return frame;
	}

	/**
	 * @return the sessionmap
	 */
	public static Map<String, String> getSessionmap() {
		return sessionMap;
	}

}
