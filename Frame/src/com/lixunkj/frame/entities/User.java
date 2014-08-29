package com.lixunkj.frame.entities;

import android.text.TextUtils;

public class User extends Base {

	private static final long serialVersionUID = -7747026563326375001L;

	public String username;
	public String password;

	public String uid;
	public String nickname;
	public String figureurl;
	public String jifen;

	public User data;
	public String id;
	public String email;
	public String regtime;
	public String collect_num;
	public String content_num;
	public String comment_num;

	// 第三方登录
	public String open_type;
	public String openId;

	public static final String TYPE_QQ = "qc";
	public static final String TYPE_TENCENTWEIBO = "tqq";
	public static final String TYPE_RENREN = "renren";
	public static final String TYPE_SINAWEIBO = "weibo";

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password
				+ ", uid=" + uid + ", nickname=" + nickname + ", figureurl="
				+ figureurl + ", jifen=" + jifen + ", email=" + email
				+ ", regtime=" + regtime + ", collect_num=" + collect_num
				+ ", content_num=" + content_num + ", comment_num="
				+ comment_num + "]";
	}

	public void setUserNameAndPsd(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public void setUserOpenInfo(String type, String openId) {
		this.open_type = type;
		this.openId = openId;
		initUserNameByOpen();
	}

	public void initUserNameByOpen() {
		if (!TextUtils.isEmpty(open_type)) {
			if (TYPE_QQ.equals(open_type)) {
				username = "QQ登录用户";
			} else if (TYPE_TENCENTWEIBO.equals(open_type)) {
				username = "腾讯微博登录用户";
			} else if (TYPE_RENREN.equals(open_type)) {
				username = "人人登录用户";
			} else if (TYPE_SINAWEIBO.equals(open_type)) {
				username = "新浪微博登录用户";
			}
		}
	}

	// public String getAvatarPath() {
	// return AppConfig.getImageUrl("files/avatar/" + uid + ".png");
	// }
	//
	// public static String getAvatarPath(String uid) {
	// return AppConfig.getImageUrl("files/avatar/" + uid + ".png");
	// }

}
