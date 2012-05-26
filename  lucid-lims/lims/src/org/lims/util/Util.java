/**
 * 
 */
package org.lims.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author Muralidhar Yaragalla
 *
 *This is an utility class.
 */
public class Util {

	private static ResourceBundle resources;
	
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
			conn =DriverManager.getConnection("jdbc:mysql://localhost/lims?" +
				                                   "user=root&password=yaragalla");
		}catch(Exception e){
			e.printStackTrace();
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
		DateFormat format=new SimpleDateFormat(pattern);
		java.util.Date date=format.parse(strDate);
		java.sql.Date sqlDate=new java.sql.Date(date.getTime());
		return sqlDate;
	}
	
	
	/**
	 * This method returns the resource bundle object.
	 * @return ResourceBundle
	 */
	public static ResourceBundle getResources() {
		return resources;
	}

}
