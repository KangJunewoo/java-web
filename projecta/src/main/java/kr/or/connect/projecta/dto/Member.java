package kr.or.connect.projecta.dto;

public class Member {
	private Integer memberIdx;
	private String name;
	private String phone;
	private String company;

	public Member(String name, String phone, String company) {
		super();
		this.name = name;
		this.phone = phone;
		this.company = company;
	}

	public Integer getMemberIdx() {
		return memberIdx;
	}

	public void setMemberIdx(Integer memberIdx) {
		this.memberIdx = memberIdx;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "Member [memberIdx=" + memberIdx + ", name=" + name + ", phone=" + phone + ", company=" + company + "]";
	}
}
