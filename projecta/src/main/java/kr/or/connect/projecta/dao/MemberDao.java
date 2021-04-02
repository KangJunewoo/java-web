package kr.or.connect.projecta.dao;

public class MemberDao {
	private static String dbUrl = "jdbc:mysql://localhost:3306/connectdb?useSSL=false";
	private static String dbUser = "connectuser";
	private static String dbPassword = "connect123!";
	
	
	public int createMember(Member member) {
		// jdbc 사용해 멤버 인자로 받은 member DB에 삽
		return 1;
	}
	
	public List<Member> findMembers(String keyword){
		List <Member> memberList = new ArrayList<>();
		// jdbc 사용해 keyword로 검색된 멤버들 리스트에 삽
		return memberList;
	}
}
