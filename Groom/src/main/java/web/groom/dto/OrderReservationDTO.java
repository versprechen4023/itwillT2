package web.groom.dto;

public class OrderReservationDTO {
	
	private int u_num;
	private int pro_id1;
	private int pro_id2;
	private int s_num;
	private int emp_num;
	
	private String res_u_req;
	private String res_method;
	private String res_time;
	private String res_day;
	
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
