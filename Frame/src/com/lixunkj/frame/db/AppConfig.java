package com.lixunkj.frame.db;

import android.text.TextUtils;

public class AppConfig {

	public static final String HOST_URL = "http://app.nbyang.com:8008/index.php?m=Home";
	// public static final String HOST_URL = "http://app.nbyang.com";
	public static final int NETWORK_START_PAGE = 1;

	// 咨询的数据先不变
	public static final String TAG = "biedou";
	// keys
	// public static final String ShareSDK_APPKEY = "25763f7cfd0a";
	public static final String HOST_WEB = "http://biedou.net/";

	// joke
	public static final String H_CAT_TUPIAN = "tupian";
	public static final String H_CAT_WENZI = "wenzi";
	public static final String IMAGE_SPLIT_KEY = ",";
	public static final String SHAREFILENAME = "share1.png";
	public static final int CONTENT_LENGTH = 500;

	public static final String PHOTO_FILE = "biedou";
	public static final String PHOTO_NAME = "biedou.jpg";
	// joke examine
	public static final String JOKE_EXAMINE_HX = "haoxiao";
	public static final String JOKE_EXAMINE_WL = "bugeili";

	public static final String INTENT_ENTITY = "intent_entity";
	public static final String INTENT_KEY = "intent_key";
	public static final String INTENT_KEY_SECOND = "intent_key_second";
	public static final String INTENT_STRING = "intent_string";

	// for request result code
	public static final int INTENT_REQUESTCODE_INFO_DETAIL = 1001;
	public static final int INTENT_REQUESTCODE_INFO_WRITE = 1002;
	public static final int INTENT_REQUESTCODE_USER_REGISTER = 1003;
	public static final int INTENT_REQUESTCODE_LOGIN = 1014;

	public static boolean checkImage(String imgPath) {
		return imgPath != null && !TextUtils.isEmpty(imgPath)
				&& !"/2000".equals(imgPath);
	}

	public static final String NET_ERROR_STRING = "无法连接到网络，请检查网络配置";
	public static final String NET_ERROR_JSON = "{\"success\":\"no\",\"text\":"
			+ NET_ERROR_STRING + "}";

}
