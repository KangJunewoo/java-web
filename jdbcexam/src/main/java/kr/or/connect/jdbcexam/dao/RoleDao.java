package kr.or.connect.jdbcexam.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.or.connect.jdbcexam.dto.Role;

public class RoleDao {
	// 실제로 요렇게 코딩하면 큰일날듯..
	private static String dburl = "jdbc:mysql://localhost:3306/connectdb";
	private static String dbUser = "connectuser";
	private static String dbpasswd = "connect123!";
	public Role getRole(Integer roleId) {
		Role role = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			String sql = "SELECT role_id, description FROM role WHERE role_id = ?";
			ps = conn.prepareStatement(sql); // ps가쿼리 반복될때 일일이 다 입력 안해줘도 되어서 좋음.
			ps.setInt(1, roleId);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				String description = rs.getString(1);
				int id = rs.getInt("role_id"); // 칼럼값을 꺼내주는 역할
				role = new Role(id, description);
			}
		} catch( Exception e) {
			e.printStackTrace();
		} finally { // finally로 try, catch 코딩하기 전에 연결 꼭 닫아주기!
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps != null) {
				try {
					ps.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return role;
	}
}
