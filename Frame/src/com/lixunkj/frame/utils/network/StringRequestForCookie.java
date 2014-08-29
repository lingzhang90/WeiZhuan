package com.lixunkj.frame.utils.network;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.RetryPolicy;
import com.lixunkj.frame.InitVolley;

public class StringRequestForCookie extends
		com.android.volley.toolbox.StringRequest {

	public StringRequestForCookie(int method, String url,
			Listener<String> listener, ErrorListener errorListener) {
		super(method, url, listener, errorListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.android.volley.toolbox.StringRequest#parseNetworkResponse(com.android
	 * .volley.NetworkResponse)
	 */
	@Override
	protected Response<String> parseNetworkResponse(NetworkResponse response) {
		// since we don't know which of the two underlying network vehicles
		// will Volley use, we have to handle and store session cookies manually
		InitVolley.getInstance().checkSessionCookie(response.headers);
		return super.parseNetworkResponse(response);
	}

	// /*
	// * (non-Javadoc)
	// *
	// * @see com.android.volley.Request#getHeaders()
	// */
	// @Override
	// public Map<String, String> getHeaders() throws AuthFailureError {
	// return InitVolley.getInstance().addSessionCookie(super.getHeaders());
	// }

	@Override
	public RetryPolicy getRetryPolicy() {
		RetryPolicy retryPolicy = new DefaultRetryPolicy(5000,
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
		return retryPolicy;
	}
}