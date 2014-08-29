package com.lixunkj.frame.db;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.lixunkj.frame.App;
import com.lixunkj.frame.InitVolley;
import com.lixunkj.frame.entities.User;
import com.lixunkj.frame.utils.network.NetWorkUtils;

@SuppressLint("CommitPrefEdits")
public class SPHelper {
	private static SPHelper preferenceHelper;
	private static SharedPreferences mSharedPreferences;
	private static SharedPreferences.Editor editor;

	public static SPHelper getInstance() {
		if (preferenceHelper == null) {
			preferenceHelper = new SPHelper();
			mSharedPreferences = App.getInstance().getSharedPreferences(
					"biedou", Context.MODE_PRIVATE);
			editor = mSharedPreferences.edit();
		}
		return preferenceHelper;
	}

	private String SHARED_KEY_USER = "shared_key_user";
	private String SHARED_KEY_COOKIE = "shared_key_cookie";
	private String SHARED_KEY_JOKE_TEXTSIZE = "shared_key_joke_textsize";
	private String SHARED_KEY_JOKE_PICLOAD = "shared_key_joke_picload";
	private String SHARED_KEY_MODE_SELECTED = "shared_key_mode_selected";
	private String SHARED_KEY_PUSH_CONFIG = "shared_key_push_config";
	private String SHARED_KEY_FIRST_ENTER = "SHARED_KEY_first_enter";

	public User getUserInfo() {
		String string = mSharedPreferences.getString(SHARED_KEY_USER, "");
		return InitVolley.getInstance().getGson().fromJson(string, User.class);
	}

	public void setUserInfo(User user) {
		String json = InitVolley.getInstance().getGson().toJson(user);
		editor.putString(SHARED_KEY_USER, json).commit();
	}

	public void setFirstEnter() {
		editor.putBoolean(SHARED_KEY_FIRST_ENTER, false).commit();
	}

	public boolean isFirstEnter() {
		return mSharedPreferences.getBoolean(SHARED_KEY_FIRST_ENTER, true);
	}

	public String getCookieString() {
		return mSharedPreferences.getString(SHARED_KEY_COOKIE, "");
	}

	public void setCookieString(String cookie) {
		editor.putString(SHARED_KEY_COOKIE, cookie).commit();
	}

	public static final int TYPE_NORMAL = 0;
	public static final int TYPE_LARGE = 1;

	public void setJokeTextSize(int type) {
		editor.putInt(SHARED_KEY_JOKE_TEXTSIZE, type).commit();
	}

	public int getJokeTextSize() {
		return mSharedPreferences.getInt(SHARED_KEY_JOKE_TEXTSIZE, 0);
	}

	public static final int MODE_SELECTED_RIJIAN = 0;
	public static final int MODE_SELECTED_YEJIAN = 1;

	public void setModeSelected(int type) {
		editor.putInt(SHARED_KEY_MODE_SELECTED, type).commit();
	}

	public int getModeSelected() {
		return mSharedPreferences.getInt(SHARED_KEY_MODE_SELECTED, 0);
	}

	public static final int PIC_LOAD_NORMAL = 0;
	public static final int PIC_LOAD_WIFI = 1;

	public void setPicLoad(int load_statuc) {
		editor.putInt(SHARED_KEY_JOKE_PICLOAD, load_statuc).commit();
	}

	public int getPicLoad() {
		return mSharedPreferences.getInt(SHARED_KEY_JOKE_PICLOAD, 0);
	}

//	public boolean autoLoad() {
////		return getPicLoad() == PIC_LOAD_NORMAL
////				|| (getPicLoad() == PIC_LOAD_WIFI && !NetWorkUtils
////						.isNetTypeMobile(App.getInstance()));
//	}

	public void setPushConfig(boolean pushable) {
		editor.putBoolean(SHARED_KEY_PUSH_CONFIG, pushable).commit();
	}

	public boolean getPushConfig() {
		return mSharedPreferences.getBoolean(SHARED_KEY_PUSH_CONFIG, true);
	}

	public final static int THEME_SUN = 1;
	public final static int THEME_NIGHT = 2;

	public static void setDayNightMode(Context context, int mode) {
		editor.putInt("SUN_NIGHT_MODE", mode).commit();
	}

	public static int getDayNightMode(Context context) {
		return mSharedPreferences.getInt("SUN_NIGHT_MODE", THEME_SUN);
	}
}
