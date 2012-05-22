/**
 * 
 */
package org.lims.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class AdminDao implements AdminDaoInter{

	@Override
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

	@Override
	public void addRole(String name, String desc) throws Exception {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="INSERT INTO roles (role_name,role_desc) VALUES (?,?)";
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

	@Override
	public List<String> getRoles()throws Exception {
		List<String> list=new ArrayList<String>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select role_name from roles order by role_name";
		try{
			
			conn =Util.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 
			 rs=pstmt.executeQuery();
			 while(rs.next()){
				String name= rs.getString("role_name");
				list.add(name);
			 }
			 
		}catch(Exception e){
			throw e;
		} finally {
			  rs.close() ;
	          pstmt.close();
	          conn.close();		      
		  }
		return list;
	}

	@Override
	public List<String> getDepartments() throws Exception {
		List<String> list=new ArrayList<String>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select department_name from departments order by department_name";
		try{
			
			 conn =Util.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 
			 rs=pstmt.executeQuery();
			 while(rs.next()){
				String name= rs.getString("department_name");
				list.add(name);
			 }
			 
		}catch(Exception e){
			throw e;
		} finally {
			  rs.close() ;
	          pstmt.close();
	          conn.close();		      
		  }
		return list;
	}
	
	
}
