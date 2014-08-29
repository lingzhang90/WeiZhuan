package com.lixunkj.frame;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.lixunkj.frame.db.SPHelper;
import com.lixunkj.frame.entities.User;

public class InitUser {

	private static InitUser initUser;
	private Context context;

	public void init(Context context) {
		this.context = context;
	}

	public static InitUser getInstance() {
		if (initUser == null) {
			initUser = new InitUser();
		}
		return initUser;
	}

	public void reLogin() {
//		Intent intent = new Intent(context, UserLoginActivity.class);
//		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		context.startActivity(intent);
	}

	// add
	public void saveUserInfo(User result) {
		SPHelper.getInstance().setUserInfo(result);
		userChange(result);
	}

	// delete
	public void clearUserInfo() {
		SPHelper.getInstance().setUserInfo(null);
		userChange(null);
	}

	// 清除除了用户名密码之外的用户信息
	public void clearUserData() {
		User userInfo = SPHelper.getInstance().getUserInfo();
		User user = new User();
		user.username = userInfo.username;
		user.password = userInfo.password;
		SPHelper.getInstance().setUserInfo(user);
	}

	private void userChange(User user) {
		if (listUserInfoChangedListener != null) {
			int size = listUserInfoChangedListener.size();
			for (int i = 0; i < size; i++) {
				UserInfoChangedListener userInfoChangedListener = listUserInfoChangedListener
						.get(i);
				if (userInfoChangedListener != null) {
					userInfoChangedListener.changed(user);
				}
			}
		}
	}

	// get
	public User getUserInfo() {
		return SPHelper.getInstance().getUserInfo();
	}

	// user change callback
	public interface UserInfoChangedListener {
		public void changed(User user);
	}

	public void addUserInfoChangedListener(
			UserInfoChangedListener userInfoChangedListener) {
		if (listUserInfoChangedListener == null) {
			listUserInfoChangedListener = new ArrayList<InitUser.UserInfoChangedListener>();
		}
		listUserInfoChangedListener.add(userInfoChangedListener);
	}

	private ArrayList<UserInfoChangedListener> listUserInfoChangedListener;

	public boolean canLogin() {
		return canLoginByUserName() || canLoginByOpenId();
	}

	public boolean canLoginByUserName() {
		User userInfo = getInstance().getUserInfo();
		return userInfo != null && !TextUtils.isEmpty(userInfo.username)
				&& !TextUtils.isEmpty(userInfo.password);
	}

	public boolean canLoginByOpenId() {
		User userInfo = getInstance().getUserInfo();
		return userInfo != null && !TextUtils.isEmpty(userInfo.open_type)
				&& !TextUtils.isEmpty(userInfo.openId);
	}

	public static boolean canUse() {
		User userInfo = getInstance().getUserInfo();
		return userInfo != null && !TextUtils.isEmpty(userInfo.uid);
	}

	// 更新用户信息编辑之后的User信息
	public void updateUserByEditResult(User user) {
		User userInfo = getUserInfo();
		userInfo.figureurl = user.figureurl;
		userInfo.uid = user.uid;
		userInfo.nickname = user.nickname;
		saveUserInfo(userInfo);
	}

	//判断是否登录
	public static boolean checkUserLoginStatus(Activity activity) {
		if (canUse()) {
			return true;
		} else {
//			Toast.makeText(activity, R.string.toast_need_login,
//					Toast.LENGTH_SHORT).show();
//			IntentUtils.intent2Login(activity);
			return false;
		}

	}
}
