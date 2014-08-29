package com.lixunkj.biedou.views;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lixunkj.frame.R;

public class ActionTitlebar extends RelativeLayout implements OnClickListener {

	private TextView tv_title;
	// for left
	private TextView btn_backbtn;
	private ImageView btn_back_image;
	private FrameLayout layout_left;
	// for right
	private FrameLayout layout_right;
	// , layout_right_amuse, layout_right_joke
	private TextView btn_right_text;
	private ImageView btn_right_image;
	// for right home
	private LinearLayout layout_right_home;
	private ImageView btn_right_home_first;
	private ImageView btn_right_home_second;

	public ActionTitlebar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public ActionTitlebar(Context context) {
		super(context);
		init(context);
	}

	void init(Context context) {
		LayoutInflater.from(context).inflate(R.layout.titlebar, this);

		tv_title = (TextView) findViewById(R.id.titlebar_tv);

		btn_backbtn = (TextView) findViewById(R.id.titlebar_left_back_btn);
		btn_back_image = (ImageView) findViewById(R.id.titlebar_left_img);
		layout_left = (FrameLayout) findViewById(R.id.titlebar_layout_left);
		layout_left.setOnClickListener(this);

		layout_right = (FrameLayout) findViewById(R.id.titlebar_layout_right);
		btn_right_text = (TextView) findViewById(R.id.titlebar_right_text);
		btn_right_image = (ImageView) findViewById(R.id.titlebar_right_image);

		layout_right_home = (LinearLayout) findViewById(R.id.titlebar_layout_right_home);
		btn_right_home_first = (ImageView) findViewById(R.id.titlebar_right_image_first);
		btn_right_home_second = (ImageView) findViewById(R.id.titlebar_right_image_second);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.titlebar_layout_left:
			((Activity) getContext()).finish();
			break;
		default:
			break;
		}
	}

	public void setTitle(String title) {
		tv_title.setText(title);
	}

	public void setTitle(int titleRes) {
		setTitle(getContext().getString(titleRes));
	}

	// 用于显示在“返回”位置
	public void setTitleLeft(String title, OnClickListener clickListener) {
		btn_back_image.setVisibility(View.GONE);
		btn_backbtn.setVisibility(View.VISIBLE);
		btn_backbtn.setText(title);
		if (clickListener != null) {
			layout_left.setOnClickListener(clickListener);
		} else {
			layout_left.setOnClickListener(this);
		}
	}

	public void setTitleLeft(int titleRes, OnClickListener clickListener) {
		setTitleLeft(getContext().getString(titleRes), clickListener);
	}

	// for left image
	public void setLeftImage(OnClickListener clickListener) {
		btn_backbtn.setVisibility(View.GONE);
		btn_back_image.setVisibility(View.VISIBLE);
		if (clickListener != null) {
			layout_left.setOnClickListener(clickListener);
		}
	}

	public void hideBackBtn() {
		btn_backbtn.setVisibility(View.GONE);
		layout_left.setVisibility(View.GONE);
	}

	public void showBackBtn() {
		btn_backbtn.setVisibility(View.VISIBLE);
		layout_left.setVisibility(View.VISIBLE);
	}

	public void hideRightLayout() {
		layout_right.setVisibility(View.GONE);
	}

	public View getLeftView() {
		if (btn_backbtn.getVisibility() == View.VISIBLE) {
			return btn_backbtn;
		}
		if (btn_back_image.getVisibility() == View.VISIBLE) {
			return btn_back_image;
		}
		return null;
	}

	public View getLeftLayout() {
		return layout_left;
	}

	public View getRightLayout() {
		return layout_right;
	}

	// right
	public void setRight(String text, OnClickListener clickListener) {
		layout_right.setVisibility(View.VISIBLE);
		btn_right_text.setVisibility(View.VISIBLE);
		btn_right_text.setText(text);
		layout_right.setOnClickListener(clickListener);
	}

	public void setRight(OnClickListener clickListener) {
		layout_right.setVisibility(View.VISIBLE);
		btn_right_image.setVisibility(View.VISIBLE);
		// btn_right_image.setImageResource(imageResource);
		layout_right.setOnClickListener(clickListener);
	}

	public void setRight(int imageResource, OnClickListener clickListener) {
		layout_right.setVisibility(View.VISIBLE);
		btn_right_image.setVisibility(View.VISIBLE);
		btn_right_image.setImageResource(imageResource);
		layout_right.setOnClickListener(clickListener);
	}

	public View getRightView() {
		if (btn_right_text.getVisibility() == View.VISIBLE) {
			return btn_right_text;
		}
		if (btn_right_image.getVisibility() == View.VISIBLE) {
			return btn_right_image;
		}
		return null;
	}

	public void setRightHomeLayout(int firstImageResource,
			OnClickListener onFirstClickListener, int secondImageResource,
			OnClickListener onSecondClickListener) {
		layout_right.setVisibility(View.GONE);
		layout_right_home.setVisibility(View.VISIBLE);
		if (onFirstClickListener != null) {
			btn_right_home_first.setImageResource(firstImageResource);
			btn_right_home_first.setOnClickListener(onFirstClickListener);
		}
		if (onSecondClickListener != null) {
			btn_right_home_second.setVisibility(View.VISIBLE);
			btn_right_home_second.setImageResource(secondImageResource);
			btn_right_home_second.setOnClickListener(onSecondClickListener);
		}
	}

	public void setRightHomeFirstImage(int firstImageResource) {
		btn_right_home_first.setImageResource(firstImageResource);
	}

	public void setRightHomeSecondImage(int secondImageResource) {
		btn_right_home_second.setImageResource(secondImageResource);
	}

}
