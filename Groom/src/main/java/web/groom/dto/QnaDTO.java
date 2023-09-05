package web.groom.dto;

import java.sql.Timestamp;

public class QnaDTO {
	
	private int qnanum; //글번호
	private String id;
	private String title;
	private String content; //사용자가 입력한 값
	private Timestamp date;
	private String category;
	private String qnaimgurl; //이미지
	private int qreref; //답글글번호
	private int qrelev; //답글깊이
	private int qreseq; //답글갯수
	private int qreans; //답변여부
	private String recontent; //답글내용
	private Timestamp redate; //답글작성일
	
	public int getQnanum() {
		return qnanum;
	}
	public void setQnanum(int qnanum) {
		this.qnanum = qnanum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getQnaimgurl() {
		return qnaimgurl;
	}
	public void setQnaimgurl(String qnaimgurl) {
		this.qnaimgurl = qnaimgurl;
	}
	public int getQreref() {
		return qreref;
	}
	public void setQreref(int qreref) {
		this.qreref = qreref;
	}
	public int getQrelev() {
		return qrelev;
	}
	public void setQrelev(int qrelev) {
		this.qrelev = qrelev;
	}
	public int getQreseq() {
		return qreseq;
	}
	public void setQreseq(int qreseq) {
		this.qreseq = qreseq;
	}
	public int getQreans() {
		return qreans;
	}
	public void setQreans(int qreans) {
		this.qreans = qreans;
	}

	public String getRecontent() {
		return recontent;
	}
	public void setRecontent(String recontent) {
		this.recontent = recontent;
	}
	public Timestamp getRedate() {
		return redate;
	}
	public void setRedate(Timestamp redate) {
		this.redate = redate;
	}
	@Override
	public String toString() {
		return "QnaDTO [qnanum=" + qnanum + ", id=" + id + ", title=" + title + ", content=" + content + ", date="
				+ date + ", category=" + category + ", qnaimgurl=" + qnaimgurl + ", qreref=" + qreref + ", qrelev="
				+ qrelev + ", qreseq=" + qreseq + ", qreans=" + qreans + ", recontent=" + recontent + ", redate="
				+ redate + "]";
	}

}
