package web.groom.dto;

import java.sql.Timestamp;

public class NoticeDTO {
	  //notice==================================
    private int n_num;
    private String n_title;
    private String n_content;
    private Timestamp n_date;
    private String n_img_url;
    public int getN_num() {
        return n_num;
    }
    public void setN_num(int n_num) {
        this.n_num = n_num;
    }
    public String getN_title() {
        return n_title;
    }
    public void setN_title(String n_title) {
        this.n_title = n_title;
    }
    public String getN_content() {
        return n_content;
    }
    public void setN_content(String n_content) {
        this.n_content = n_content;
    }
    public Timestamp getN_date() {
        return n_date;
    }
    public void setN_date(Timestamp n_date) {
        this.n_date = n_date;
    }
    public String getN_img_url() {
        return n_img_url;
    }
    public void setN_img_url(String n_img_url) {
        this.n_img_url = n_img_url;
    }

}
