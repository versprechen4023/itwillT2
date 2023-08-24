package web.groom.dto;

public class ReviewDTO {
	
	private int rev_num;
	private String rev_img_url;
	private String pro_name;
	private String emp_grade;
	private String emp_name;
	private String s_location;
	private String rev_rating;
	private String u_name;
	private String rev_date;
	private int rev_count;
	private String u_count;
	private String rev_content;

	public int getRev_num() {
		return rev_num;
	}
	public void setRev_num(int rev_num) {
		this.rev_num = rev_num;
	}
	public String getRev_img_url() {
		return rev_img_url;
	}
	public void setRev_img_url(String rev_img_url) {
		this.rev_img_url = rev_img_url;
	}
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
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
	public String getS_location() {
		return s_location;
	}
	public void setS_location(String s_location) {
		this.s_location = s_location;
	}
	public String getRev_rating() {
		return rev_rating;
	}
	public void setRev_rating(String rev_rating) {
		this.rev_rating = rev_rating;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public String getRev_date() {
		return rev_date;
	}
	public void setRev_date(String rev_date) {
		this.rev_date = rev_date;
	}
	public String getU_count() {
		return u_count;
	}
	public int getRev_count() {
		return rev_count;
	}
	public void setRev_count(int rev_count) {
		this.rev_count = rev_count;
	}
	public void setU_count(String u_count) {
		this.u_count = u_count;
	}
	public String getRev_content() {
		return rev_content;
	}
	public void setRev_content(String rev_content) {
		this.rev_content = rev_content;
	}
}
