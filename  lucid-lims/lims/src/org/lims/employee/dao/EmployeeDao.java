/**
 * 
 */
package org.lims.employee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.lims.employee.dto.EmployeeDto;
import org.lims.util.Util;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class EmployeeDao implements EmployeeDaoInter{

	/* (non-Javadoc)
	 * @see org.lims.employee.dao.EmployeeDaoInter#getEmployee(java.lang.Integer)
	 */
	@Override
	public EmployeeDto getEmployee(String empId) throws Exception {
		EmployeeDto employee=new EmployeeDto();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select emp_name,emp_designation,department_id,role_id," +
				"emp_display_name,emp_phone,emp_mobile_num from employee " +
				"where employee_id=?";
		try{			
			 conn =Util.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, empId);
			 rs=pstmt.executeQuery();
			 if(rs.next()){
				employee.setEmpId(empId);
				employee.setEmpName(rs.getString("emp_name"));
				employee.setEmpDesignation(rs.getString("emp_designation"));
				employee.setEmpDepartmentId(rs.getInt("department_id"));
				employee.setEmpRoleId(rs.getInt("role_id"));
				employee.setEmpDisplayName(rs.getString("emp_display_name"));
				employee.setPhoneNo(rs.getString("emp_phone"));
				employee.setMobileNo(rs.getString("emp_mobile_num"));
			 }
			 
		}catch(Exception e){
			throw e;
		} finally {
			  rs.close() ;
	          pstmt.close();
	          conn.close();		      
		  }
		return employee;
	}

	/* (non-Javadoc)
	 * @see org.lims.employee.dao.EmployeeDaoInter#updateEmployee(org.lims.employee.dto.EmployeeDto)
	 */
	@Override
	public void updateEmployee(EmployeeDto employee) throws Exception {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="update employee set emp_phone=?,emp_mobile_num=? where employee_id=?";
		try{
			
			conn =Util.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 
			 pstmt.setString(1, employee.getPhoneNo());
			 pstmt.setString(2, employee.getMobileNo());
			 pstmt.setString(3, employee.getEmpId());
			 pstmt.executeUpdate();
			 
		}catch(Exception e){
			throw e;
		} finally {		      
		          pstmt.close();
		          conn.close();		      
		  }
		
	}

	
}
