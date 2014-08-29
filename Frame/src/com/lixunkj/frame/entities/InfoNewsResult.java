package com.lixunkj.frame.entities;


public class InfoNewsResult extends InfoBaseListResult<InfoNewsResult> {

	private static final long serialVersionUID = 780037935559797584L;

	public String id;
	public String title;
	public String source;
	public String imgmode;
	public String images;

	public String hits;
	public String content;
	public String img_description;

	public boolean isRead;

	@Override
	public String toString() {
		return "NewsResult [id=" + id + ", title=" + title + ", source="
				+ source + ", imgmode=" + imgmode + ", images=" + images
				+ ", hits=" + hits + ", content=" + content
				+ ", img_description=" + img_description + ", maxsize="
				+ maxsize + ", uptime=" + uptime + ", list=" + list
				+ ", status=" + status + ", msg=" + msg + "]";
	}

	public void setReadState() {
		this.isRead = true;
	}

}
