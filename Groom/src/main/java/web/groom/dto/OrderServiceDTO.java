package web.groom.dto;

public class OrderServiceDTO {
	private int s_num;
	private int l_num;
	private String s_name;
	private int s_price;
	
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
