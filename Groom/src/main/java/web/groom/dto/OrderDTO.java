package web.groom.dto;

import java.sql.Time;
import java.util.Date;

public class OrderDTO {
	
	private int id;
	private Date date;
	private Time time;
	
	public int getId() {
		return id;
	}
	public Date getDate() {
		return date;
	}
	public Time getTime() {
		return time;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	

}
