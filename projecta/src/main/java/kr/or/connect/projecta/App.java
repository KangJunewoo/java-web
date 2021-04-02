package kr.or.connect.projecta;

import java.util.List;
import java.util.Scanner;

import kr.or.connect.projecta.dao.MemberDao;
import kr.or.connect.projecta.dto.Member;

public class App {
	static Scanner scanner = new Scanner(System.in);
	
	private static void modeCreateMember() {
		System.out.print("이름을 입력하세요 : ");
		String name = scanner.next();
		System.out.print("전화번호를 입력하세요 : ");
		String phone = scanner.next();
		System.out.print("회사 이름을 입력하세요 : ");
		String company = scanner.next();
		
		Member member = new Member(name, phone, company);
		MemberDao dao = new MemberDao();
		
		int insertCount = dao.createMember(member);
		
		System.out.println(insertCount);
		
	}

	private static void modeSearchMember() {
		System.out.print("검색할 이름을 입력하세요 : ");
		String name = scanner.next();
		
		MemberDao dao = new MemberDao();
		List<Member> memberList = dao.searchMembers(name);
		for(Member member:memberList) {
			System.out.println(member);
		}
	}

	public static void main(String[] args) {
		while (true) {
			System.out.println("===================");
			System.out.println("1. 명함 입력");
			System.out.println("2. 명함 검색");
			System.out.println("3. 종료");
			System.out.println("===================\n");
			System.out.print("메뉴를 입력하세요 : ");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				modeCreateMember();
				break;
			case 2:
				modeSearchMember();
				break;
			case 3:
				scanner.close();
				return;
			default:
				System.out.println("잘못된 입력입니다.");
				break;
			}
		}

	}
}
