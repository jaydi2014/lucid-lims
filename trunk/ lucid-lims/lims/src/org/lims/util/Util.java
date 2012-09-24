/**
 * 
 */
package org.lims.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.TimeZone;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

/**
 * @author Muralidhar Yaragalla
 *
 *This is an utility class.
 */
public class Util {

	private static ResourceBundle resources;	
	private static Logger log=Logger.getLogger(Util.class);
	
	static {
        try {
            resources = ResourceBundle.getBundle("org.lims.util.resources.Lims" );
        } catch (MissingResourceException mre) {
            System.err.println("resources/Lims.properties not found"+mre);            
            System.exit(1);
        }
    }

	/**
	 * This method instances the driver and gives the database connection
	 * @return
	 */
	public static Connection getConnection(){
		Connection conn=null;
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			/*conn =DriverManager.getConnection("jdbc:mysql://192.168.1.2/lims?" +
				                                   "user=root&password=lucid@18");*/
			conn =DriverManager.getConnection("jdbc:mysql://localhost/lims?" +
                    "user=root&password=yaragalla");
		}catch(Exception e){
			log.debug(e.getMessage(), e);
		}
		
		return conn;
	}
	
	/**
	 * Returns java.sql.date from the given date string and pattern
	 * @param strDate
	 * @param pattern
	 * @return java.sql.Date
	 * @throws Exception
	 */
	public static java.sql.Date convertStringToSqlDate(String strDate,String pattern)throws Exception{
		java.sql.Date sqlDate=null;
		DateFormat format=new SimpleDateFormat(pattern);
		if(strDate!=null){
			java.util.Date date=format.parse(strDate);
			sqlDate=new java.sql.Date(date.getTime());
		}
		return sqlDate;
	}
	
	/**
	 * converts the date object to string in the given pattern.
	 * @param date
	 * @param pattern
	 * @return date string.
	 */
	public static String convertDateToString(Date date,String pattern){
		String strDate=null;
		if(date!=null){
			DateFormat format=new SimpleDateFormat(pattern);
			strDate=format.format(date);
		}
		return strDate;
	}
	
	/**
	 * Converts String to date in supplied date pattern.
	 * @param strDate
	 * @param pattern
	 * @return Date
	 * @throws Exception
	 */
	public static java.util.Date convertStringToDate(String strDate,String pattern)throws Exception{
		DateFormat format=new SimpleDateFormat(pattern);
		java.util.Date date=format.parse(strDate);		
		return date;
	}
	
	/**
	 * Converts java.sql.date into a String in the supplied pattern.
	 * @param date
	 * @param pattern
	 * @return date in string.
	 */
	public static String convertSqlDateToString(java.sql.Date date,String pattern){
		String strDate=null;
		DateFormat format=new SimpleDateFormat(pattern);
		if(date!=null){
			java.util.Date utilDate=new java.util.Date(date.getTime());
			strDate=format.format(utilDate);
		}
		return strDate;
	}
	
	/**
	 * Calculate the days difference between two days.
	 * @param startDate
	 * @param endDate
	 * @return difference in days.
	 */
	public static long daysBetween(Calendar startDate, Calendar endDate) {  
		  Calendar date = (Calendar) startDate.clone();  
		  long daysBetween = 0;  
		  while (date.before(endDate)) {  
		    date.add(Calendar.DAY_OF_MONTH, 1);  
		    daysBetween++;  
		  }  
		  return daysBetween;  
		} 
	
	/**
	 * Converts java.util.Date to java.sql.Date
	 * @param date
	 * @return sql date.
	 */
	public static java.sql.Date convertDateToSqlDate(java.util.Date date){
		java.sql.Date sqlDate=null;
		if(date !=null){
			sqlDate=new java.sql.Date(date.getTime());			
		}
		return sqlDate;
	}
	
	/**
	 * This method returns the resource bundle object.
	 * @return ResourceBundle
	 */
	public static ResourceBundle getResources() {
		return resources;
	}
	
	/**
	 * It returns the default time zone id.
	 * @return timezone Id.
	 */
	public static String getDefaultTZId(){
		TimeZone defaultTZ=TimeZone.getDefault();
		String id=defaultTZ.getID();
		return id;
	}
	
	/**
	 * Converts java.sql.Date to java.util.Date
	 * @param date
	 * @return java.util.Date.
	 */
	public static java.util.Date sqldateToDate(java.sql.Date date){
		java.util.Date finaldate=null;
		if(date !=null){
			finaldate=new java.util.Date(date.getTime());
		}
		return finaldate;
	}
	
	/**
	 * sends email to the given recipients.
	 * @param recipents
	 * @param subject
	 * @param content  should be in htmls
	 */
	public static void SendEmail(String recipents,String subject,String content)throws MessagingException{
		final String username =Constants.USER_NAME;
		final String password =Constants.PASSWORD;
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", Constants.SMTP_AUTH);
		props.put("mail.smtp.starttls.enable", Constants.SMTP_STARTTLS);
		props.put("mail.smtp.host", Constants.SMTP_HOST);
		props.put("mail.smtp.port", Constants.SMTP_PORT);
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(Constants.FROM_ADD));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(recipents));
			message.setSubject(subject);
			message.setContent(content,"text/html");
 
			Transport.send(message); 
		
	}

}
