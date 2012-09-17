package org.lims.register.gui;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import org.lims.register.gui.listeners.SendMailActionListener;

/**
 * 
 */

/**
 * @author Muralidhar Yaragalla
 *
 */
public class MailClientDialog extends JDialog{

	private static final long serialVersionUID = -834720977094529530L;
	private JTextField toTF;
	private JTextField subjectTF;
	private JTextPane bodyTP;

	public MailClientDialog(){
		super();
		setSize(1000, 650);
		getContentPane().setLayout(new BorderLayout());
		JPanel centerPanel=createCentralPanel();
		add(centerPanel,BorderLayout.CENTER);
		setVisible(true);
	}
	
	public JPanel createCentralPanel(){
		JPanel panel=new JPanel(null);
		
		JLabel toLabel=new JLabel("To :");
		toLabel.setBounds(10, 10, 30, 30);
		panel.add(toLabel);
		toTF=new JTextField();
		toTF.setBounds(40,10,600,30);
		panel.add(toTF);
		
		JLabel subjectLabel=new JLabel("Subject :");
		subjectLabel.setBounds(10, 50, 50, 30);
		panel.add(subjectLabel);
		subjectTF=new JTextField();
		subjectTF.setBounds(60,50,600,30);
		panel.add(subjectTF);
		
		bodyTP=new JTextPane();
		bodyTP.setContentType("text/html");
		JScrollPane bodyTPSP=new JScrollPane(bodyTP);
		bodyTPSP.setBounds(10, 90, 900, 450);
		panel.add(bodyTPSP);
		
		JButton sendB=new JButton("Send");
		sendB.addActionListener(new SendMailActionListener(this));
		sendB.setBounds(10, 550, 100, 30);
		panel.add(sendB);
		
		return panel;
	}

	/**
	 * @return the toTF
	 */
	public JTextField getToTF() {
		return toTF;
	}

	/**
	 * @param toTF the toTF to set
	 */
	public void setToTF(JTextField toTF) {
		this.toTF = toTF;
	}

	/**
	 * @return the subjectTF
	 */
	public JTextField getSubjectTF() {
		return subjectTF;
	}

	/**
	 * @param subjectTF the subjectTF to set
	 */
	public void setSubjectTF(JTextField subjectTF) {
		this.subjectTF = subjectTF;
	}

	/**
	 * @return the bodyTP
	 */
	public JTextPane getBodyTP() {
		return bodyTP;
	}

	/**
	 * @param bodyTP the bodyTP to set
	 */
	public void setBodyTP(JTextPane bodyTP) {
		this.bodyTP = bodyTP;
	}

}
