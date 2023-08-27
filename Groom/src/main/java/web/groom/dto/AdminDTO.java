package web.groom.dto;

public class AdminDTO {
	
	private int number; // 순서 표시용
	
	private int s_num;
	private String s_location;
	
	private int emp_num;
	private String emp_name;
	private String emp_grade;
	
	
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
