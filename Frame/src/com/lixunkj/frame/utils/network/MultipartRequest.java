package com.lixunkj.frame.utils.network;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Set;

import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.lixunkj.frame.InitVolley;

public class MultipartRequest extends Request<String> {

	private MultipartEntity entity = new MultipartEntity();

	private final Response.Listener<String> mListener;
	private String mFilename;
	private final File mFile;
	private HashMap<String, String> mParams;

	public MultipartRequest(String url, Response.Listener<String> listener,
			Response.ErrorListener errorListener, String filename, File file,
			HashMap<String, String> params) {
		super(Method.POST, url, errorListener);

		mListener = listener;
		this.mFilename = filename;
		mFile = file;
		mParams = params;
		buildMultipartEntity();
	}

	private void buildMultipartEntity() {
		if (mFile != null) {
			entity.addPart(mFilename, new FileBody(mFile));
		}

		if (mParams != null) {
			Set<String> set = mParams.keySet();
			try {
				for (String string : set) {
					entity.addPart(string, new StringBody(mParams.get(string),
							Charset.forName("UTF-8")));
				}
			} catch (UnsupportedEncodingException e) {
				VolleyLog.e("UnsupportedEncodingException");
			}
		}
	}

	@Override
	public String getBodyContentType() {
		return entity.getContentType().getValue();
	}

	@Override
	public byte[] getBody() throws AuthFailureError {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			entity.writeTo(bos);
		} catch (IOException e) {
			VolleyLog.e("IOException writing to ByteArrayOutputStream");
		}
		return bos.toByteArray();
	}

	@Override
	protected Response<String> parseNetworkResponse(NetworkResponse response) {
		String parsed;
		try {
			parsed = new String(response.data,
					HttpHeaderParser.parseCharset(response.headers));
		} catch (UnsupportedEncodingException e) {
			parsed = new String(response.data);
		}
		InitVolley.getInstance().checkSessionCookie(response.headers);
		return Response.success(parsed, getCacheEntry());
	}

	@Override
	protected void deliverResponse(String response) {
		mListener.onResponse(response);
	}
}