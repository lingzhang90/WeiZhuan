package com.lixunkj.biedou.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.lixunkj.frame.R;
import com.lixunkj.frame.db.SPHelper;

public class ClickableLayout extends RelativeLayout {

	public ClickableLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void setOnClickListener(final OnClickListener l) {
		listener = l;
		post(new Runnable() {

			@Override
			public void run() {
				img_mask = new ImageView(getContext());
				img_mask.setLayoutParams(new LayoutParams(getWidth(),
						getHeight()));
				clickable(true);
				addView(img_mask, new LayoutParams(LayoutParams.MATCH_PARENT,
						getHeight()));
			}
		});
	}

	OnClickListener listener;
	boolean clickable = true;
	ImageView img_mask;

	@SuppressWarnings("static-access")
	public void clickable(boolean bool) {
		this.clickable = bool;
		this.setClickable(bool);
		if (img_mask != null) {
			if (bool) {
//				img_mask.setImageResource(SPHelper.getInstance()
//						.getDayNightMode(getContext()) == NightModeUtils.THEME_SUN ? R.drawable.selector_clickableimageview_mask
//						: R.drawable.titlebar_selector_back);
//				img_mask.setClickable(true);
//				img_mask.setOnClickListener(listener);
			} else {
				img_mask.setImageResource(getResources().getColor(
						R.color.transparent));
				img_mask.setClickable(false);
				img_mask.setOnClickListener(null);
			}
		}

	}
}
