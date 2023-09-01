package web.groom.dto;

import java.sql.Timestamp;

public class MemberDTO {
	
	private int u_num;
	private String u_id;
	private String u_pass;
	private String u_salt;
	private String u_role;
	private String u_name;
	private String u_phone;
	private String u_email;
	private Timestamp u_regDate;
	private int u_count;
	private int u_point;
	private int u_disabled;
	
	public int getU_disabled() {
		return u_disabled;
	}
	public void setU_disabled(int u_disabled) {
		this.u_disabled = u_disabled;
	}
	public int getU_Num() {
		return u_num;
	}
	public String getU_Id() {
		return u_id;
	}
	public String getU_Pass() {
		return u_pass;
	}
	public String getU_Salt() {
		return u_salt;
	}
	public String getU_Role() {
		return u_role;
	}
	public String getU_Name() {
		return u_name;
	}
	public String getU_Phone() {
		return u_phone;
	}
	public String getU_Email() {
		return u_email;
	}
	public Timestamp getU_RegDate() {
		return u_regDate;
	}
	public int getU_Count() {
		return u_count;
	}
	public int getU_Point() {
		return u_point;
	}
	public void setU_Num(int num) {
		this.u_num = num;
	}
	public void setU_Id(String id) {
		this.u_id = id;
	}
	public void setU_Pass(String pass) {
		this.u_pass = pass;
	}
	public void setU_Salt(String salt) {
		this.u_salt = salt;
	}
	public void setU_Role(String role) {
		this.u_role = role;
	}
	public void setU_Name(String name) {
		this.u_name = name;
	}
	public void setU_Phone(String phone) {
		this.u_phone = phone;
	}
	public void setU_Email(String email) {
		this.u_email = email;
	}
	public void setU_RegDate(Timestamp regDate) {
		this.u_regDate = regDate;
	}
	public void setU_Count(int count) {
		this.u_count = count;
	}
	public void setU_Point(int point) {
		this.u_point = point;
	}
	
	@Override
	public String toString() {
		return "MemberDTO [num=" + u_num + ", id=" + u_id + ", pass=" + u_pass + ", salt=" + u_salt + ", role=" + u_role
				+ ", name=" + u_name + ", phone=" + u_phone + ", email=" + u_email + ", regDate=" + u_regDate + ", count="
				+ u_count + ", point=" + u_point + "]";
	}
	
}
