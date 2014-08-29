package com.lixunkj.frame.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class BaseListResult<T> extends Base implements Serializable {

	private static final long serialVersionUID = -6289557389233939462L;

	public ArrayList<T> data;
	public String total_found;

	@Override
	public String toString() {
		return "BaseListResult [data=" + data + ", total_found=" + total_found
				+ "]";
	}

}
