package web.groom.dto;

public class OrderServiceDTO {
	private int s_num;
	private int l_num;
	private String s_name;
	private String s_weight;
	private int s_price;
	private String emp_name;
	private String emp_grade;
	private int emp_num;
	
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
	public String getS_weight() {
		return s_weight;
	}
	public void setS_weight(String s_wight) {
		this.s_weight = s_wight;
	}
	
	public int getS_num() {
		return s_num;
	}
	public int getL_num() {
		return l_num;
	}
	public String getS_name() {
		return s_name;
	}
	public int getS_price() {
		return s_price;
	}
	public void setS_num(int s_num) {
		this.s_num = s_num;
	}
	public void setL_num(int l_num) {
		this.l_num = l_num;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public void setS_price(int s_price) {
		this.s_price = s_price;
	}
	
}
