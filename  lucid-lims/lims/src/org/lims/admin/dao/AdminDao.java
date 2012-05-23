/**
 * 
 */
package org.lims.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.lims.admin.dto.EmployeeDto;
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

	/* (non-Javadoc)
	 * @see org.lims.admin.dao.AdminDaoInter#addEmployee(org.lims.admin.dto.EmployeeDto)
	 */
	@Override
	public void addEmployee(EmployeeDto empDto) throws Exception {
		int departmentId=getDepartmentId(empDto.getEmpDepartment());
		int roleId=getRoleId(empDto.getEmpRole());
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="insert into employee(employee_id,emp_name,emp_designation,department_id," +
				"role_id,password) values(?,?,?,?,?,?)";
		try{
			
			conn =Util.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 
			 pstmt.setString(1, empDto.getEmpId());
			 pstmt.setString(2, empDto.getEmpName());
			 pstmt.setString(3, empDto.getEmpDesignation());
			 pstmt.setInt(4,departmentId);
			 pstmt.setInt(5, roleId);
			 pstmt.setString(6, empDto.getPassword());			 
			 
			 pstmt.executeUpdate();
			 
		}catch(Exception e){
			throw e;
		} finally {		      
		          pstmt.close();
		          conn.close();		      
		  }
		
	}
	
	/**
	 * This returns the Department Id when provided Department Name.
	 * @param deptName
	 * @return Department Id.
	 * @throws Exception
	 */
	private Integer getDepartmentId(String deptName)throws Exception{
		int id=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select department_id from departments where department_name=?";
		try{
			
			 conn =Util.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, deptName);
			 rs=pstmt.executeQuery();
			 if(rs.next()){
				id= rs.getInt("department_id");				
			 }
			 
		}catch(Exception e){
			throw e;
		} finally {
			  rs.close() ;
	          pstmt.close();
	          conn.close();		      
		  }
		return id;
	}
	
	/**
	 * This returns Role Id when given Role Name.
	 * @param roleName
	 * @return role id.
	 * @throws Exception
	 */
	private Integer getRoleId(String roleName)throws Exception{
		int id=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select role_id from roles where role_name=?";
		try{
			
			 conn =Util.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, roleName);
			 rs=pstmt.executeQuery();
			 if(rs.next()){
				id= rs.getInt("role_id");				
			 }
			 
		}catch(Exception e){
			throw e;
		} finally {
			  rs.close() ;
	          pstmt.close();
	          conn.close();		      
		  }
		return id;
	}
	
	
}
