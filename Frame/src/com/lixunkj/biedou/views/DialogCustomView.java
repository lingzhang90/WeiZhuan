package com.lixunkj.biedou.views;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lixunkj.frame.R;
import com.lixunkj.frame.untils.DialogUtils;

public class DialogCustomView extends LinearLayout {

	public DialogCustomView(Context context) {
		super(context);
		LayoutInflater.from(context).inflate(R.layout.layout_dialog_custom,
				this);
	}

	ImageView img_main, img_delete;
	TextView tv_title, tv_desc, btn_positive, btn_negative;
	LinearLayout layout_btns;
	LinearLayout layout_img;
	RelativeLayout layout_edit;
	EditText edit;

	public static final int DIALOGTYPE_SUCCESS = 100;
	public static final int DIALOGTYPE_Q = 200;
	public static final int DIALOGTYPE_FAILED = 300;
	public static final int DIALOGTYPE_EDIT = 400;

	public void setData(int type, String title, String desc,
			OnClickListener positiveClickLisenter,
			OnClickListener negativeClickLisenter) {
		this.setData(type, title, desc, null, null, positiveClickLisenter,
				null, negativeClickLisenter);
	}

	// public void setData(int type, String title, String desc,
	// OnClickListener positiveClickLisenter) {
	// this.setData(type, title, desc, null, null, positiveClickLisenter,
	// null, negativeClickLisenter);
	// }

	public void setData(int type, String title, String desc,
			String positiveText, OnClickListener positiveClickLisenter,
			String nagativeText, OnClickListener negativeClickLisenter) {
		this.setData(type, title, desc, null, positiveText,
				positiveClickLisenter, nagativeText, negativeClickLisenter);
	}

	public void setData(int type, String title, String desc,
			OnClickListener deleteClickLisenter) {
		this.setData(type, title, desc, deleteClickLisenter, null, null, null,
				null);
	}

	public void setData(int type, String title, String desc,
			OnClickListener deleteClickLisenter, String positiveText,
			OnClickListener positiveClickLisenter, String nagativeText,
			OnClickListener negativeClickLisenter) {

		findPublicViews();

		tv_title.setText(title);
		tv_desc.setText(desc);

		switch (type) {
		case DIALOGTYPE_SUCCESS:
			initSuccessLayout(deleteClickLisenter);
			break;
		case DIALOGTYPE_FAILED:
			initFailedLayout(deleteClickLisenter);
			break;
		case DIALOGTYPE_Q:
			initQLayout(positiveText, positiveClickLisenter, nagativeText,
					negativeClickLisenter);
			break;
		default:
			break;
		}
	}

	/**
	 * 输入框Dialog
	 */
	public void setEditData(String hintString,
			OnClickListener positiveClickLisenter,
			OnClickListener negativeClickLisenter) {
		findPublicViews();
		layout_img.setVisibility(View.GONE);
		layout_edit.setVisibility(View.VISIBLE);

		btn_positive.setOnClickListener(positiveClickLisenter);
		btn_negative.setOnClickListener(negativeClickLisenter);

		edit.setHint(hintString);
	}

	public boolean isEditNull() {
		return TextUtils.isEmpty(edit.getText().toString());
	}

	public String getEditString() {
		return edit.getText().toString();
	}

	private void findPublicViews() {
		img_main = (ImageView) findViewById(R.id.dialog_custom_img);
		tv_title = (TextView) findViewById(R.id.dialog_custom_tv_title);
		tv_desc = (TextView) findViewById(R.id.dialog_custom_tv_desc);

		img_delete = (ImageView) findViewById(R.id.dialog_custom_btn_delete);
		btn_positive = (TextView) findViewById(R.id.dialog_custom_btn_positive);
		btn_negative = (TextView) findViewById(R.id.dialog_custom_btn_negative);
		layout_btns = (LinearLayout) findViewById(R.id.dialog_custom_btn_layout);

		layout_img = (LinearLayout) findViewById(R.id.dialog_custom_img_layout);
		layout_edit = (RelativeLayout) findViewById(R.id.dialog_custom_edit_layout);
		edit = (EditText) findViewById(R.id.dialog_custom_edit);
	}

	private void initSuccessLayout(OnClickListener deleteClickLisenter) {
		img_delete.setVisibility(View.VISIBLE);
		if (deleteClickLisenter == null) {
			img_delete.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					DialogUtils.dismissCustom();
				}
			});
		} else {
			img_delete.setOnClickListener(deleteClickLisenter);
		}
		img_main.setImageResource(R.drawable.dialog_custom_icon_success);
		layout_btns.setVisibility(View.GONE);
	}

	private void initFailedLayout(OnClickListener deleteClickLisenter) {
		img_delete.setVisibility(View.VISIBLE);
		if (deleteClickLisenter == null) {
			img_delete.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					DialogUtils.dismissCustom();
				}
			});
		} else {
			img_delete.setOnClickListener(deleteClickLisenter);
		}
		img_main.setImageResource(R.drawable.dialog_custom_icon_failed);
		layout_btns.setVisibility(View.GONE);
	}

	private void initQLayout(String positiveText,
			OnClickListener positiveClickLisenter, String nagativeText,
			OnClickListener negativeClickLisenter) {
		layout_btns.setVisibility(View.VISIBLE);
		img_main.setImageResource(R.drawable.dialog_custom_icon_q);
		if (!TextUtils.isEmpty(positiveText)) {
			btn_positive.setText(positiveText);
		}
		if (positiveClickLisenter == null) {
			btn_positive.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					DialogUtils.dismissCustom();
				}
			});
		} else {
			btn_positive.setOnClickListener(positiveClickLisenter);
		}
		if (!TextUtils.isEmpty(nagativeText)) {
			btn_negative.setText(nagativeText);
		}
		if (negativeClickLisenter == null) {
			btn_negative.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					DialogUtils.dismissCustom();
				}
			});
		} else {
			btn_negative.setOnClickListener(negativeClickLisenter);
		}

	}
//测试测试Tortoisegit

}
