package web.groom.dto;

import java.sql.Timestamp;

public class AdminDTO {
	
	private int number; // 순서 표시용
	
	private int s_num;
	private String s_location;
	
	private int emp_num;
	private String emp_name;
	private String emp_grade;
	private int emp_extrafee;
	private String emp_phone;
	private String emp_email;
	private Timestamp emp_date;
	
	private int total_user;
	private int total_res;
	private int today_res;
	private int res_a;
	private int res_b;
	private int res_c;
	private int today_res_a;
	private int today_res_b;
	private int today_res_c;
	
	
	public int getTotal_user() {
		return total_user;
	}
	public void setTotal_user(int total_user) {
		this.total_user = total_user;
	}
	public int getTotal_res() {
		return total_res;
	}
	public void setTotal_res(int total_res) {
		this.total_res = total_res;
	}
	public int getToday_res() {
		return today_res;
	}
	public void setToday_res(int today_res) {
		this.today_res = today_res;
	}
	public int getRes_a() {
		return res_a;
	}
	public void setRes_a(int res_a) {
		this.res_a = res_a;
	}
	public int getRes_b() {
		return res_b;
	}
	public void setRes_b(int res_b) {
		this.res_b = res_b;
	}
	public int getRes_c() {
		return res_c;
	}
	public void setRes_c(int res_c) {
		this.res_c = res_c;
	}
	public int getToday_res_a() {
		return today_res_a;
	}
	public void setToday_res_a(int today_res_a) {
		this.today_res_a = today_res_a;
	}
	public int getToday_res_b() {
		return today_res_b;
	}
	public void setToday_res_b(int today_res_b) {
		this.today_res_b = today_res_b;
	}
	public int getToday_res_c() {
		return today_res_c;
	}
	public void setToday_res_c(int today_res_c) {
		this.today_res_c = today_res_c;
	}
	public int getEmp_extrafee() {
		return emp_extrafee;
	}
	public void setEmp_extrafee(int emp_extrafee) {
		this.emp_extrafee = emp_extrafee;
	}
	public String getEmp_phone() {
		return emp_phone;
	}
	public void setEmp_phone(String emp_phone) {
		this.emp_phone = emp_phone;
	}
	public String getEmp_email() {
		return emp_email;
	}
	public void setEmp_email(String emp_email) {
		this.emp_email = emp_email;
	}
	public Timestamp getEmp_date() {
		return emp_date;
	}
	public void setEmp_date(Timestamp emp_date) {
		this.emp_date = emp_date;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getS_num() {
		return s_num;
	}
	public void setS_num(int s_num) {
		this.s_num = s_num;
	}
	public String getS_location() {
		return s_location;
	}
	public void setS_location(String s_location) {
		this.s_location = s_location;
	}
	public int getEmp_num() {
		return emp_num;
	}
	public void setEmp_num(int emp_num) {
		this.emp_num = emp_num;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getEmp_grade() {
		return emp_grade;
	}
	public void setEmp_grade(String emp_grade) {
		this.emp_grade = emp_grade;
	}
	
	
	
}
