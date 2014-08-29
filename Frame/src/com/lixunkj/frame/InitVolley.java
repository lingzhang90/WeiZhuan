package com.lixunkj.frame;

import java.util.Map;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.content.Context;

import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.OnNeedSetCookieListener;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.ImageRequest.OnCookieGetListener;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.lixunkj.frame.utils.network.BitmapCache;

public class InitVolley {

	private static InitVolley initLocalInfo;
	private static Gson gson;

	public static InitVolley getInstance() {
		if (initLocalInfo == null) {
			initLocalInfo = new InitVolley();
			gson = new Gson();
		}
		return initLocalInfo;
	}

	private ImageLoader mImageLoader;
	private RequestQueue mHttpQueue;
	private RequestQueue mImageHttpQueue;
	public DefaultHttpClient mHttpClient;

	public void init(Context context) {
		mHttpClient = createHttpClient();
		HttpClientStack httpClientStack = new HttpClientStack(mHttpClient);
		mHttpQueue = Volley.newRequestQueue(context, httpClientStack);
		mImageHttpQueue = Volley.newRequestQueue(context, httpClientStack);
		mImageLoader = new ImageLoader(mImageHttpQueue, new BitmapCache());

		ImageRequest.setOnCookieGetListener(new OnCookieGetListener() {

			@Override
			public void getCookie(NetworkResponse response) {
				checkSessionCookie(response.headers);
			}

		});

		mImageLoader.setOnNeedSetCookieListener(new OnNeedSetCookieListener() {

			@Override
			public void setcookie() {
				setCookie();
			}
		});
	}

	private DefaultHttpClient createHttpClient() {
		HttpParams httpParams = new BasicHttpParams();
		SchemeRegistry schReg = new SchemeRegistry();
		schReg.register(new Scheme("http", PlainSocketFactory
				.getSocketFactory(), 80));
		schReg.register(new Scheme("https",
				SSLSocketFactory.getSocketFactory(), 443));
		ClientConnectionManager manager = new ThreadSafeClientConnManager(
				httpParams, schReg);
		DefaultHttpClient mHttpClient = new DefaultHttpClient(manager,
				httpParams);
		HttpParams params = mHttpClient.getParams();
		HttpConnectionParams.setConnectionTimeout(params, 5000);
		HttpConnectionParams.setSoTimeout(params, 10000);
		return mHttpClient;
	}

	public ImageLoader getImageLoader() {
		return mImageLoader;
	}

	public RequestQueue getHttpQueue() {
		return mHttpQueue;
	}

	public void clearCache() {
		mHttpQueue.getCache().clear();
		mImageHttpQueue.getCache().clear();
	}

	public Gson getGson() {
		return gson;
	}

	public static final String SET_COOKIE_KEY = "Set-Cookie";
	public static final String COOKIE_KEY = "Cookie";
	public static final String SESSION_COOKIE = "sessionid";

	/**
	 * Checks the response headers for session cookie and saves it if it finds
	 * it.
	 * 
	 * @param headers
	 *            Response Headers.
	 */
	public final void checkSessionCookie(Map<String, String> headers) {

		// if (headers.containsKey(SET_COOKIE_KEY)) {
		// String cookie = headers.get(SET_COOKIE_KEY);
		// if (cookie.length() > 0) {
		// SPHelper.getInstance().setCookieString(cookie);
		// }
		// }
	}

	public void setCookie() {
		// String cookieString = SPHelper.getInstance().getCookieString();
		// mHttpClient.getParams().setParameter(CoreProtocolPNames.USER_AGENT,
		// System.getProperty("http.agent"));
		// CookieStore cs = mHttpClient.getCookieStore();
		// // create a cookie
		// cs.addCookie(new BasicClientCookie2(COOKIE_KEY, cookieString));
	}

}
