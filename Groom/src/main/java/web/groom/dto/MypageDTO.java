package web.groom.dto;

import java.sql.Timestamp;

public class MypageDTO {

	private int num;
	private String id;
	private String role;
	private String name;
	private String phone;
	private String email;
	private Timestamp regDate;
	private int count;
	private int point;
	private int petNum;
	private String petName;
	private String petBreed;
	private String petGender;
	private String petNeuter;
	private String petComment;
	private String pass;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getPetNum() {
		return petNum;
	}
	public void setPetNum(int petNum) {
		this.petNum = petNum;
	}
	public String getPetName() {
		return petName;
	}
	public void setPetName(String petName) {
		this.petName = petName;
	}
	public String getPetBreed() {
		return petBreed;
	}
	public void setPetBreed(String petBreed) {
		this.petBreed = petBreed;
	}
	public String getPetGender() {
		return petGender;
	}
	public void setPetGender(String petGender) {
		this.petGender = petGender;
	}
	public String getPetNeuter() {
		return petNeuter;
	}
	public void setPetNeuter(String petNeuter) {
		this.petNeuter = petNeuter;
	}
	public String getPetComment() {
		return petComment;
	}
	public void setPetComment(String petComment) {
		this.petComment = petComment;
	}
	
	public String getPass() {
		return pass;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	@Override
	public String toString() {
		return "MypageDTO [num=" + num + ", id=" + id + ", role=" + role + ", name=" + name + ", phone=" + phone
				+ ", email=" + email + ", regDate=" + regDate + ", count=" + count + ", point=" + point + ", petNum="
				+ petNum + ", petName=" + petName + ", petBreed=" + petBreed + ", petGender=" + petGender
				+ ", petNeuter=" + petNeuter + ", petComment=" + petComment + ", getNum()=" + getNum() + ", getId()="
				+ getId() + ", getRole()=" + getRole() + ", getName()=" + getName() + ", getPhone()=" + getPhone()
				+ ", getEmail()=" + getEmail() + ", getRegDate()=" + getRegDate() + ", getCount()=" + getCount()
				+ ", getPoint()=" + getPoint() + ", getPetNum()=" + getPetNum() + ", getPetName()=" + getPetName()
				+ ", getPetBreed()=" + getPetBreed() + ", getPetGender()=" + getPetGender() + ", getPetNeuter()="
				+ getPetNeuter() + ", getPetComment()=" + getPetComment() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
}

