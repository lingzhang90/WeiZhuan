package com.lixunkj.frame.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class InfoBaseListResult<T> extends InfoBaseResult implements Serializable {

	private static final long serialVersionUID = -6289557389233939462L;
	public int maxsize;
	public String uptime;
	
	public ArrayList<T> list;

	@Override
	public String toString() {
		return "BaseListResult [maxsize=" + maxsize + ", uptime=" + uptime
				+ ", status=" + status + ", msg=" + msg + "]";
	}

}
