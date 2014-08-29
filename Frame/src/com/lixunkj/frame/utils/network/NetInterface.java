package com.lixunkj.frame.utils.network;

import java.util.HashMap;

import android.text.TextUtils;

import com.android.volley.Request.Method;
import com.lixunkj.frame.db.AppConfig;
import com.lixunkj.frame.entities.RestEntity;

public class NetInterface {
	private static NetInterface netService;

	public static NetInterface getInstance() {
		if (netService == null) {
			netService = new NetInterface();
		}
		return netService;
	}

	public String getURL(String subPath) {
		// if (isSecret) {
		// User loginData = InitUser.getInstance().getUserData();
		// String uid = "";
		// long currentTimeMillis = System.currentTimeMillis() / 1000;
		// if (loginData != null) {
		// uid = loginData.uid;
		// }
		// return AppConfig.HOST_URL
		// + subPath
		// + RsaUtils.createSecretKey(currentTimeMillis, subPath
		// .substring(subPath.lastIndexOf("/") + 1,
		// subPath.lastIndexOf(".r")), uid);
		// } else {
		return AppConfig.HOST_URL + subPath;
		// }
	}

	// 登录
	public RestEntity login(String userid, String pwd) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("userid", userid);
		hashMap.put("pwd", pwd);
		return new RestEntity(Method.POST, getURL("&c=Login&a=run&mobile=1"),
				hashMap);
	}

	// 登录
	public RestEntity loginOAuth(String type, String openid, String nickname,
			String figureurl) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("type", type);
		hashMap.put("openid", openid);
		hashMap.put("nickname", nickname);
		hashMap.put("figureurl", figureurl);
		return new RestEntity(Method.POST, getURL("&c=Login&a=snsLogin"),
				hashMap);
	}

	// 登录注册
	public RestEntity loginout() {
		return new RestEntity(Method.POST, getURL("&c=Login&a=logout"));
	}

	// 注册
	public RestEntity register(String email, String uname, String userpwd,
			String userpwdok) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("email", email);
		hashMap.put("uname", uname);
		hashMap.put("userpwd", userpwd);
		hashMap.put("userpwdok", userpwdok);
		return new RestEntity(Method.POST, getURL("&c=Lixun&a=addUser"),
				hashMap);
	}

	// 找回密码
	public RestEntity findPassword(String email) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("email", email);
		return new RestEntity(Method.POST, getURL("&c=Lixun&a=findpwd"),
				hashMap);
	}

	// 段子接口
	public RestEntity getJokeList(String p, String hOrder, String h_cat) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("p", p);
		hashMap.put("hOrder", hOrder);
		hashMap.put("h_cat", h_cat);
		return new RestEntity(Method.POST, getURL("&c=Lixun&a=index") + "&ran="
				+ hOrder + h_cat, hashMap);
	}

	public RestEntity jokeZan(String post_id) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("post_id", post_id);
		return new RestEntity(Method.POST,
				getURL("&c=Index&a=updataShareNum&type=zan"), hashMap);
	}

	public RestEntity jokeCai(String post_id) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("post_id", post_id);
		return new RestEntity(Method.POST,
				getURL("&c=Index&a=updataShareNum&type=cai"), hashMap);
	}

	public RestEntity jokeFav(String post_id) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("post_id", post_id);
		return new RestEntity(Method.POST, getURL("&c=Lixun&a=collect"),
				hashMap);
	}

	public RestEntity getJokeDetailCommentList(String p, String cid) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("p", p);
		hashMap.put("cid", cid);
		return new RestEntity(Method.POST, getURL("&c=Lixun&a=comments"),
				hashMap);
	}

	public RestEntity sendJokeComment(String cid, String comment) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("cid", cid);
		hashMap.put("comment", comment);
		return new RestEntity(Method.POST, getURL("&c=Comments&a=addComment"),
				hashMap);
	}

	public RestEntity jokeCommentZan(String commentId) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("commentId", commentId);
		return new RestEntity(Method.POST, getURL("&c=Comments&a=clickLiang"),
				hashMap);
	}

	public RestEntity publishJoke(String tgContent, String tgPic) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		boolean isOnlyContent = TextUtils.isEmpty(tgPic);
		hashMap.put("tgCat", isOnlyContent ? AppConfig.H_CAT_WENZI
				: AppConfig.H_CAT_TUPIAN);
		hashMap.put("tgContent", tgContent);
		if (!isOnlyContent) {
			hashMap.put("tgPic", tgPic);
		}
		return new RestEntity(Method.POST, getURL("&c=TouGao&a=runTouGao"),
				hashMap);
	}

	public String getPublishJokeUrl() {
		return getURL("&c=Lixun&a=uploadImg");
	}

	public RestEntity reportJoke(String cid, String content) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("cid", cid);
		hashMap.put("content", content);
		return new RestEntity(Method.POST, getURL("&c=Lixun&a=juBao"), hashMap);
	}

	public RestEntity getJokeExamineList(String p) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("p", p);
		return new RestEntity(Method.POST, getURL("&c=Lixun&a=showExamine"),
				hashMap);
	}

	public RestEntity examineJoke(String type, String cid) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("type", type);
		hashMap.put("cid", cid);
		return new RestEntity(Method.POST, getURL("&c=TouGao&a=runExamine"),
				hashMap);
	}

	public String getJokeShareString(String cid) {
		return getURL("&c=SingleContent&a=showContent&cid=" + cid);
	}

	public RestEntity getMineJokeFavList(int p) {
		return new RestEntity(Method.POST, getURL("/user/joke_fav.r") + "&p="
				+ p);
	}

	public RestEntity getMineJokeSendList(int p) {
		return new RestEntity(Method.POST, getURL("/user/joke_my.r") + "&p="
				+ p);
	}

	public RestEntity feedback(String name, String content) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("name", name);
		hashMap.put("content", content);
		return new RestEntity(Method.POST, getURL("&c=Lixun&a=feedback"),
				hashMap);
	}

	public String getUserEditUrl() {
		return getURL("&c=Lixun&a=updateUserinfo");
	}

	// 个人中心
	public RestEntity getUserCenterPublished() {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		return new RestEntity(Method.POST, getURL("&c=Lixun&a=myarticle"),
				hashMap);
	}

	public RestEntity getUserCenterCollected() {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		return new RestEntity(Method.POST,
				getURL("&c=Lixun&a=share&type=collect"), hashMap);
	}

	public RestEntity getUserCenterCommented() {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		return new RestEntity(Method.POST, getURL("&c=Lixun&a=mycomments"),
				hashMap);
	}

	public RestEntity getUserCenterOther(String uid) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("uid", uid);
		return new RestEntity(Method.POST, getURL("&c=Lixun&a=userpage"),
				hashMap);
	}

	public RestEntity getUserCenterInfo(String uid) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("uid", uid);
		return new RestEntity(Method.POST, getURL("&c=Lixun&a=userinfo"),
				hashMap);
	}
}
