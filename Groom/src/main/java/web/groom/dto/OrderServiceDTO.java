package web.groom.dto;

public class OrderServiceDTO {
	private int s_num;
	private int l_num;
	private String pro_name;
	private String pet_weight;
	private int pro_price;
	private String emp_name;
	private String emp_grade;
	private int emp_num;
	private int emp_extrafee;
	
	public int getEmp_extrafee() {
		return emp_extrafee;
	}
	public void setEmp_extrafee(int emp_extrafee) {
		this.emp_extrafee = emp_extrafee;
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
	public String getPet_weight() {
		return pet_weight;
	}
	public void setPet_weight(String pet_wight) {
		this.pet_weight = pet_wight;
	}
	
	public int getS_num() {
		return s_num;
	}
	public int getL_num() {
		return l_num;
	}
	public String getPro_name() {
		return pro_name;
	}
	public int getPro_price() {
		return pro_price;
	}
	public void setS_num(int s_num) {
		this.s_num = s_num;
	}
	public void setL_num(int l_num) {
		this.l_num = l_num;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	public void setPro_price(int pro_price) {
		this.pro_price = pro_price;
	}
	
}
