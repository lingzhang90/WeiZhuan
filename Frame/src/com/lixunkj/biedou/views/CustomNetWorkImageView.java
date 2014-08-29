package com.lixunkj.biedou.views;

import android.content.Context;
import android.util.AttributeSet;

import com.android.volley.toolbox.NetworkImageView;
import com.lixunkj.frame.R;

public class CustomNetWorkImageView extends NetworkImageView {

	public CustomNetWorkImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initImage();
	}

	public CustomNetWorkImageView(Context context) {
		super(context);
		initImage();
	}

	private void initImage() {
		setDefaultImageResId(R.drawable.default_background_big);
		setErrorImageResId(R.drawable.default_background_big);
	}

	public void setDefaultImage(int res) {
		setDefaultImageResId(res);
		setErrorImageResId(res);
	}

	public void setColorDefault() {
		// setDefaultImageResId(R.color.border_color);
		// setErrorImageResId(R.color.border_color);
	}

}
