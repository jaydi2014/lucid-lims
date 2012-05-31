/**
 * 
 */
package org.lims.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.lims.admin.dto.RoleDto;
import org.lims.employee.dto.EmployeeDto;
import org.lims.register.dto.DepartmentDto;
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
				"role_id,password,emp_display_name,emp_user_name) values(?,?,?,?,?,?,?,?)";
		try{
			
			conn =Util.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 
			 pstmt.setString(1, empDto.getEmpId());
			 pstmt.setString(2, empDto.getEmpName());
			 pstmt.setString(3, empDto.getEmpDesignation());
			 pstmt.setInt(4,departmentId);
			 pstmt.setInt(5, roleId);
			 pstmt.setString(6, empDto.getPassword());
			 pstmt.setString(7, empDto.getEmpDisplayName());
			 pstmt.setString(8, empDto.getUserName());
			 
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
	public Integer getDepartmentId(String deptName)throws Exception{
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

	/* (non-Javadoc)
	 * @see org.lims.admin.dao.AdminDaoInter#checkDeptNameExist(java.lang.String)
	 */
	@Override
	public Boolean checkDeptNameExist(String deptName) throws Exception {
		Boolean exist=null;
		Integer id=getDepartmentId(deptName);
		if(id.intValue()!=0)
			exist=true;
		else  
			exist=false;
		return exist;
		
	}

	/* (non-Javadoc)
	 * @see org.lims.admin.dao.AdminDaoInter#checkRoleNameExist(java.lang.String)
	 */
	@Override
	public Boolean checkRoleNameExist(String roleName) throws Exception {
		Boolean exist=null;
		Integer id=getRoleId(roleName);
		if(id.intValue()!=0)
			exist=true;
		else
			exist=false;
		return exist;
	}
	
	/**
	 * This returns the employee id when provided display name.
	 * @param displayName
	 * @return
	 * @throws Exception
	 */
	private String getEmployeeId(String displayName)throws Exception{
		String id=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select employee_id from employee where emp_display_name=?";
		try{
			
			 conn =Util.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, displayName);
			 rs=pstmt.executeQuery();
			 if(rs.next()){
				id= rs.getString("employee_id");				
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

	/* (non-Javadoc)
	 * @see org.lims.admin.dao.AdminDaoInter#checkEmpDisplayNameExist(java.lang.String)
	 */
	@Override
	public Boolean checkEmpDisplayNameExist(String displayName)
			throws Exception {
		Boolean exist=null;
		String id=getEmployeeId(displayName);
		if(id!=null)
			exist=true;
		else
			exist=false;
		return exist;
	}

	/* (non-Javadoc)
	 * @see org.lims.admin.dao.AdminDaoInter#checkUserNameExist(java.lang.String)
	 */
	@Override
	public Boolean checkUserNameExist(String userName) throws Exception {
		Boolean exist=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select employee_id from employee where emp_user_name=?";
		try{
			
			 conn =Util.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, userName);
			 rs=pstmt.executeQuery();
			 if(rs.next()){
				exist=true;				
			 }else{
				 exist=false;
			 }
			 
		}catch(Exception e){
			throw e;
		} finally {
			  rs.close() ;
	          pstmt.close();
	          conn.close();		      
		  }
		return exist;
	}

	/* (non-Javadoc)
	 * @see org.lims.admin.dao.AdminDaoInter#getDepartment(java.lang.Integer)
	 */
	@Override
	public DepartmentDto getDepartment(Integer id) throws Exception {
		
		DepartmentDto deptdto=new DepartmentDto();
		if(id!=null){
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			String sql="select department_name,department_desc from departments where department_id=?";
			try{
				
				 conn =Util.getConnection();
				 pstmt = conn.prepareStatement(sql);
				 pstmt.setInt(1, id);
				 rs=pstmt.executeQuery();
				 if(rs.next()){
					deptdto.setDepartmentId(id);
					deptdto.setDeptName(rs.getString("department_name"));
					deptdto.setDeptDesc(rs.getString("department_desc"));
				 }
				 
			}catch(Exception e){
				throw e;
			} finally {
				  rs.close() ;
		          pstmt.close();
		          conn.close();		      
			  }
		}
		return deptdto;
	}

	/* (non-Javadoc)
	 * @see org.lims.admin.dao.AdminDaoInter#getRole(java.lang.Integer)
	 */
	@Override
	public RoleDto getRole(Integer id) throws Exception {
		RoleDto role=new RoleDto();
		if(id!=null){
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			String sql="select role_name,role_desc from roles where role_id=?";
			try{			
				 conn =Util.getConnection();
				 pstmt = conn.prepareStatement(sql);
				 pstmt.setInt(1, id);
				 rs=pstmt.executeQuery();
				 if(rs.next()){
					role.setRoleId(id);
					role.setRoleName(rs.getString("role_name"));
					role.setRoleDesc(rs.getString("role_desc"));
				 }
				 
			}catch(Exception e){
				throw e;
			} finally {
				  rs.close() ;
		          pstmt.close();
		          conn.close();		      
			  }
		}
		return role;
	}
	
	
	
	
}
