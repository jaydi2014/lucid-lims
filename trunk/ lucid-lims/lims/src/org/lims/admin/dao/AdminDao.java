/**
 * 
 */
package org.lims.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class AdminDao {

	public void addDepartment(String name,String desc)throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="INSERT INTO departments (department_name,department_desc) VALUES (?,?)";
		try{
			
			conn =Util.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 
			 pstmt.setString(1, name);
			 pstmt.setString(2, desc);
			 
			 
			 pstmt.executeUpdate();
			 
		}catch(Exception e){
			throw e;
		} finally {		      
		          pstmt.close();
		          conn.close();		      
		  }
	}
}
