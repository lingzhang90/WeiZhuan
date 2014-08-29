package com.lixunkj.frame.entities;

import java.io.Serializable;

public class Base implements Serializable {

	private static final long serialVersionUID = -2806704748193847566L;

	protected String success = "yes";
	protected String text;
	protected String errCode;
	protected String login;
	public String error;

	@Override
	public String toString() {
		return "Base [success=" + success + ", text=" + text + "]";
	}

	public String message() {
		return text;
	}

	public void setMessage(String message) {
		this.text = message;
	}

	public boolean success() {
		return "yes".equals(success);
	}

	public boolean noLogin() {
		return "nologin".equals(errCode);
	}

}
