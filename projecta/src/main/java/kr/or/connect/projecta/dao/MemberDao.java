package kr.or.connect.projecta.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.or.connect.projecta.dto.Member;

public class MemberDao {
	private static String dbUrl = "jdbc:mysql://localhost:3306/connectdb?useSSL=false";
	private static String dbUser = "connectuser";
	private static String dbPassword = "connect123!";
	
	
	
	public int createMember(Member member) {
		int insertCount = 0;
		String sql = "INSERT INTO member (name, phone, company) VALUES (?, ?, ?);";
		
		try(Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, member.getName());
			ps.setString(2, member.getPhone());
			ps.setString(3, member.getCompany());
			insertCount = ps.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return insertCount;
	}
	
	public List<Member> searchMembers(String keyword){
		List <Member> memberList = new ArrayList<>();
		String sql = "SELECT name, phone, company FROM member WHERE name LIKE ?";
		
		try(Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, "%"+keyword+"%");
			try(ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					String name = rs.getString("name");
					String phone = rs.getString("phone");
					String company = rs.getString("company");
					Member member = new Member(name, phone, company);
					memberList.add(member);
				}
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return memberList;
	} 
}
