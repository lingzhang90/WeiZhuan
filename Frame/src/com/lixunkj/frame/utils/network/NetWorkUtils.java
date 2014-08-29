package com.lixunkj.frame.utils.network;

import java.io.File;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.lixunkj.frame.InitUser;
import com.lixunkj.frame.InitVolley;
import com.lixunkj.frame.db.AppConfig;
import com.lixunkj.frame.entities.Base;
import com.lixunkj.frame.entities.RestEntity;
import com.lixunkj.frame.untils.AppLogger;

public class NetWorkUtils {

	private static NetWorkUtils netWorkUtils;
	private static RequestQueue queue;

	public static NetWorkUtils getInstance() {
		if (netWorkUtils == null) {
			netWorkUtils = new NetWorkUtils();
			queue = InitVolley.getInstance().getHttpQueue();
		}
		return netWorkUtils;
	}

	public <T extends Base> void workNoCheck(RestEntity restEntity,
			final NetWorkCallBack<T> callBack) {
		this.work(restEntity, true, callBack);
	}

	public <T extends Base> void work(RestEntity restEntity,
			final NetWorkCallBack<T> callBack) {
		this.work(restEntity, false, callBack);
	}

	// 增加一个接口控制，正在读取接口的时候不再请求
	HashMap<String, Boolean> taskmap = new HashMap<String, Boolean>();

	private <T extends Base> void work(final RestEntity restEntity,
			final boolean donotCheck, final NetWorkCallBack<T> callBack) {

		if (!taskmap.containsKey(restEntity.url)
				|| taskmap.get(restEntity.url) == false) {

			taskmap.put(restEntity.url, true);
			Type genericSuperclass = callBack.getClass().getGenericSuperclass();
			final Type type = ((ParameterizedType) genericSuperclass)
					.getActualTypeArguments()[0];
			AppLogger.i(restEntity.url);
			if (restEntity.requestData != null) {
				AppLogger.i(restEntity.requestData.toString());
			}
			StringRequestForCookie postRequest = new StringRequestForCookie(
					restEntity.method, restEntity.url,
					new Response.Listener<String>() {
						@Override
						public void onResponse(String response) {
							AppLogger.i(response);
							T fromJson = InitVolley.getInstance().getGson()
									.fromJson(response, type);
							// if (donotCheck) {
							// fromJson.setStatus(ConstConfig.NETWORK_OK);
							// }
							if (fromJson.noLogin()) {
								InitUser.getInstance().reLogin();
							}
							callBack.onComplete(fromJson);
							taskmap.remove(restEntity.url);
						}
					}, new Response.ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError error) {
							T fromJson = InitVolley.getInstance().getGson()
									.fromJson(AppConfig.NET_ERROR_JSON, type);
							callBack.onComplete(fromJson);
							taskmap.remove(restEntity.url);
						}
					}) {
				@Override
				protected Map<String, String> getParams() {
					return restEntity.requestData;
				}
			};
			addRequest(postRequest);
		}
	}

	// 上传图片用的
	public <T extends Base> void workImage(String url, String filename,
			File file, HashMap<String, String> params,
			final NetWorkCallBack<T> callBack) {

		Type genericSuperclass = callBack.getClass().getGenericSuperclass();
		final Type type = ((ParameterizedType) genericSuperclass)
				.getActualTypeArguments()[0];

		AppLogger.i(url);
		if (params != null) {
			AppLogger.i(params.toString());
		}

		MultipartRequest multipartRequest = new MultipartRequest(url,
				new Listener<String>() {

					@Override
					public void onResponse(String response) {
						AppLogger.i(response);
						T fromJson = InitVolley.getInstance().getGson()
								.fromJson(response, type);
						// if (fromJson.status() == ConstConfig.NETWORK_UNLOGIN)
						// {
						// InitUser.getInstance().reLogin();
						// }
						callBack.onComplete(fromJson);
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						// T fromJson = InitVolley.getInstance().getGson()
						// .fromJson(ConstConfig.NET_ERROR_JSON, type);
						// callBack.onComplete(fromJson);
					}
				}, filename, file, params);
		addRequest(multipartRequest);
	}

	private void addRequest(Request<String> request) {
		request.setTag(AppConfig.TAG);
		InitVolley.getInstance().setCookie();
		queue.add(request);
	}

	// 检查是否有网络
	public static boolean checkNet(Context context) {

		try {
			ConnectivityManager connectivity = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivity != null) {
				NetworkInfo info = connectivity.getActiveNetworkInfo();
				if (info != null && info.isConnected()) {
					if (info.getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	// 检查是否是流量
	public static boolean isNetTypeMobile(Context context) {
		ConnectivityManager connectMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = connectMgr.getActiveNetworkInfo();
		return info != null
				&& info.getType() == ConnectivityManager.TYPE_MOBILE;
	}
}
