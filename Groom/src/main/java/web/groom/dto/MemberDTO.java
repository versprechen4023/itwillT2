package web.groom.dto;

import java.sql.Timestamp;

public class MemberDTO {
	private int num;
	private String id;
	private String pass;
	private String salt;
	private String role;
	private String name;
	private String phone;
	private String email;
	private Timestamp regDate;
	private int count;
	private int point;
	
	public int getNum() {
		return num;
	}
	public String getId() {
		return id;
	}
	public String getPass() {
		return pass;
	}
	public String getSalt() {
		return salt;
	}
	public String getRole() {
		return role;
	}
	public String getName() {
		return name;
	}
	public String getPhone() {
		return phone;
	}
	public String getEmail() {
		return email;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	public int getCount() {
		return count;
	}
	public int getPoint() {
		return point;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	
}
