package com.lixunkj.frame.entities;

public class PublishJokeImage extends Base {

	private static final long serialVersionUID = 6971617884018089216L;

	public String pic_url;

	@Override
	public String toString() {
		return "PublishJokeImage [pic_url=" + pic_url + ", success=" + success
				+ ", text=" + text + ", login=" + login + ", error=" + error
				+ "]";
	}

}
