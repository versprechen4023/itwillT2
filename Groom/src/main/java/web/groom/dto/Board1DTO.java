package web.groom.dto;

import java.sql.Timestamp;

public class Board1DTO {
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
    
    
    
//faq==================================
    
    private int faq_num;
    private String faq_title;
    private String faq_content;
    private Timestamp faq_date;
    private String faq_img_url;


	public int getFaq_num() {
		return faq_num;
	}
	public void setFaq_num(int faq_num) {
		this.faq_num = faq_num;
	}
	public String getFaq_title() {
		return faq_title;
	}
	public void setFaq_title(String faq_title) {
		this.faq_title = faq_title;
	}
	public String getFaq_content() {
		return faq_content;
	}
	public void setFaq_content(String faq_content) {
		this.faq_content = faq_content;
	}
	public Timestamp getFaq_date() {
		return faq_date;
	}
	public void setFaq_date(Timestamp faq_date) {
		this.faq_date = faq_date;
	}
	public String getFaq_img_url() {
		return faq_img_url;
	}
	public void setFaq_img_url(String faq_img_url) {
		this.faq_img_url = faq_img_url;
	}
    
    
    
}
