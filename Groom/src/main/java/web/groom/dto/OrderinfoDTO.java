package web.groom.dto;

public class OrderinfoDTO {
	
private String s_location;
private String p_dog;
private String s_name;
private String s_weight;
private String emp_name;

private int s_num;
private int p_num;
private int serveiceNum;
private int emp_num;

private String u_name;
private String u_phone;
private String res_day;
private String res_time;
private String res_price;
private String res_u_req;
private String res_point;


public String getU_name() {
	return u_name;
}
public String getU_phone() {
	return u_phone;
}
public String getRes_day() {
	return res_day;
}
public String getRes_time() {
	return res_time;
}
public String getRes_price() {
	return res_price;
}
public String getRes_u_req() {
	return res_u_req;
}
public String getRes_point() {
	return res_point;
}
public void setU_name(String u_name) {
	this.u_name = u_name;
}
public void setU_phone(String u_phone) {
	this.u_phone = u_phone;
}
public void setRes_day(String res_day) {
	this.res_day = res_day;
}
public void setRes_time(String res_time) {
	this.res_time = res_time;
}
public void setRes_price(String res_price) {
	this.res_price = res_price;
}
public void setRes_u_req(String res_u_req) {
	this.res_u_req = res_u_req;
}
public void setRes_point(String res_point) {
	this.res_point = res_point;
}
public int getS_num() {
	return s_num;
}
public int getP_num() {
	return p_num;
}
public int getServeiceNum() {
	return serveiceNum;
}
public int getEmp_num() {
	return emp_num;
}
public void setS_num(int s_num) {
	this.s_num = s_num;
}
public void setP_num(int p_num) {
	this.p_num = p_num;
}
public void setServeiceNum(int serveiceNum) {
	this.serveiceNum = serveiceNum;
}
public void setEmp_num(int emp_num) {
	this.emp_num = emp_num;
}
public String getS_location() {
	return s_location;
}
public String getP_dog() {
	return p_dog;
}
public String getS_name() {
	return s_name;
}
public String getS_weight() {
	return s_weight;
}
public String getEmp_name() {
	return emp_name;
}
public void setS_location(String s_location) {
	this.s_location = s_location;
}
public void setP_dog(String p_dog) {
	this.p_dog = p_dog;
}
public void setS_name(String s_name) {
	this.s_name = s_name;
}
public void setS_weight(String s_weight) {
	this.s_weight = s_weight;
}
public void setEmp_name(String emp_name) {
	this.emp_name = emp_name;
}

@Override
public String toString() {
	return "OrderinfoDTO [s_location=" + s_location + ", p_dog=" + p_dog + ", s_name=" + s_name + ", s_weight="
			+ s_weight + ", emp_name=" + emp_name + ", s_num=" + s_num + ", p_num=" + p_num + ", serveiceNum="
			+ serveiceNum + ", emp_num=" + emp_num + ", u_name=" + u_name + ", u_phone=" + u_phone + ", res_day="
			+ res_day + ", res_time=" + res_time + ", res_price=" + res_price + ", res_u_req=" + res_u_req
			+ ", res_point=" + res_point + "]";
}

}