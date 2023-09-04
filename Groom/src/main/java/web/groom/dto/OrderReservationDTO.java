package web.groom.dto;

public class OrderReservationDTO {
	
	private int res_num; //
	private int u_num;
	private int pro_id1;
	private int pro_id2;
	private int s_num;
	private int emp_num;
	private int res_price;
	private int res_point;
	private int used_point;
	private int res_status; //

	private String res_u_req;
	private String res_method;
	private String res_time;
	private String res_day;
	
	private String pro_name; //
	private String pet_size; //
	private String pet_weight; //
	private String s_location; //
	private String emp_grade; //
	private String emp_name; //
	private String u_name; //
	private String u_phone; //
	
	
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	public String getPet_size() {
		return pet_size;
	}
	public void setPet_size(String pet_size) {
		this.pet_size = pet_size;
	}
	public String getPet_weight() {
		return pet_weight;
	}
	public void setPet_weight(String pet_weight) {
		this.pet_weight = pet_weight;
	}
	public String getS_location() {
		return s_location;
	}
	public void setS_location(String s_location) {
		this.s_location = s_location;
	}
	public String getEmp_grade() {
		return emp_grade;
	}
	public void setEmp_grade(String emp_grade) {
		this.emp_grade = emp_grade;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public String getU_phone() {
		return u_phone;
	}
	public void setU_phone(String u_phone) {
		this.u_phone = u_phone;
	}
	public int getRes_num() {
		return res_num;
	}
	public void setRes_num(int res_num) {
		this.res_num = res_num;
	}
	public int getRes_status() {
		return res_status;
	}
	public void setRes_status(int res_status) {
		this.res_status = res_status;
	}
	public int getU_num() {
		return u_num;
	}
	public int getPro_id1() {
		return pro_id1;
	}
	public int getPro_id2() {
		return pro_id2;
	}
	public int getS_num() {
		return s_num;
	}
	public int getEmp_num() {
		return emp_num;
	}
	public int getRes_price() {
		return res_price;
	}
	public int getRes_point() {
		return res_point;
	}
	public int getUsed_point() {
		return used_point;
	}
	
	public String getRes_u_req() {
		return res_u_req;
	}
	public String getRes_method() {
		return res_method;
	}
	public String getRes_time() {
		return res_time;
	}
	public String getRes_day() {
		return res_day;
	}
	
	public void setU_num(int u_num) {
		this.u_num = u_num;
	}
	public void setPro_id1(int pro_id1) {
		this.pro_id1 = pro_id1;
	}
	public void setPro_id2(int pro_id2) {
		this.pro_id2 = pro_id2;
	}
	public void setS_num(int s_num) {
		this.s_num = s_num;
	}
	public void setEmp_num(int emp_num) {
		this.emp_num = emp_num;
	}
	public void setRes_price(int res_price) {
		this.res_price = res_price;
	}
	public void setRes_point(int res_point) {
		this.res_point = res_point;
	}
	public void setUsed_point(int used_point) {
		this.used_point = used_point;
	}
	public void setRes_u_req(String res_u_req) {
		this.res_u_req = res_u_req;
	}
	public void setRes_method(String res_method) {
		this.res_method = res_method;
	}
	public void setRes_time(String res_time) {
		this.res_time = res_time;
	}
	public void setRes_day(String res_day) {
		this.res_day = res_day;
	}
	
	@Override
	public String toString() {
		return "OrderReservationDTO [u_num=" + u_num + ", pro_id1=" + pro_id1 + ", pro_id2=" + pro_id2 + ", s_num="
				+ s_num + ", emp_num=" + emp_num + ", res_u_req=" + res_u_req + ", res_method=" + res_method
				+ ", res_time=" + res_time + ", res_day=" + res_day + "]";
	}

}
