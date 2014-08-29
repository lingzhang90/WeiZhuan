package com.lixunkj.frame.untils;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.ant.liao.GifView;
import com.lixunkj.biedou.views.DialogCustomView;
import com.lixunkj.frame.R;

public class DialogUtils {

	public interface DialogReturnListener {
		public void dialogReturn(String string, int position);
	}

	public static void showAlertDialog(Context context, final String[] arrays,
			String title, final DialogReturnListener dialogReturnListener) {
		Builder builder = new Builder(context);
		if (!TextUtils.isEmpty(title)) {
			builder.setTitle(title);
		}
		builder.setItems(arrays, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int position) {
				if (dialogReturnListener != null) {
					dialogReturnListener.dialogReturn(arrays[position],
							position);
				}
			}
		}).create().show();
	}

	public static void showSimpleTextDialog(Context context, String title,
			final DialogInterface.OnClickListener onClickListener) {
		Builder builder = new Builder(context);
		builder.setIcon(R.drawable.logo);
		builder.setTitle(title);
		builder.setPositiveButton("确定", onClickListener)
				.setNegativeButton("取消", null).create().show();
	}

	public static void showSimpleItemDialog(Context context, String title,
			String[] items,
			final DialogInterface.OnClickListener onClickListener) {
		Builder builder = new Builder(context);
		if (!TextUtils.isEmpty(title)) {
			builder.setTitle(title);
		}
		builder.setItems(items, onClickListener).create().show();
	}

	public static void showAlertDialog(Context context, int arrayRes,
			String title, final DialogReturnListener dialogReturnListener) {
		final String[] arrays = context.getResources().getStringArray(arrayRes);
		showAlertDialog(context, arrays, title, dialogReturnListener);
	}

	private static Dialog dialog;

	public static void show(Activity activity) {
		show(activity, null);
	}

	public static void show(Activity activity, int stringRes) {
		show(activity, activity.getResources().getString(stringRes));
	}

	public static void show(Activity activity, String message) {

		dismiss();
		dialog = new Dialog(activity, R.style.style_dialog_loading);
		// dialog.setCancelable(false);
		dialog.setContentView(createViewForDialog(activity));
		dialog.show();
	}

	// 类似百度团购的Dialog
	// private View createViewForDialog(Activity activity, String message) {
	// View dialog_view = LayoutInflater.from(activity).inflate(
	// R.layout.layout_dialog, null);
	// ImageView img_rotate = (ImageView) dialog_view
	// .findViewById(R.id.dialog_rotate);
	// img_rotate.startAnimation(AnimationUtils.loadAnimation(activity,
	// R.anim.rotate));
	//
	// if (!TextUtils.isEmpty(message)) {
	// TextView tv = (TextView) dialog_view.findViewById(R.id.dialog_tv);
	// tv.setVisibility(View.VISIBLE);
	// tv.setText(message);
	// }
	// return dialog_view;
	// }

	private static View createViewForDialog(Activity activity) {
		View view = LayoutInflater.from(activity).inflate(
				R.layout.layout_loading, null);
		GifView gifView = (GifView) view.findViewById(R.id.loading_gif);
		gifView.setGifImage(R.drawable.loading);
		return view;
	}

	private static Dialog dialogCustom;

	// 自定义三种Dialog
	public static void showDialogCustom(Context context,
			DialogCustomView dialogCustomView) {
		dismiss();
		dialogCustomView.setLayoutParams(new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		dialogCustom = new Dialog(context, R.style.style_dialog_custom);
		dialogCustom.setCancelable(false);
		dialogCustom.setContentView(dialogCustomView);
		dialogCustom.show();
	}

	public static void dismiss() {
		if (dialog != null && dialog.isShowing()) {
			dialog.cancel();
		}
	}

	public static void dismissCustom() {
		if (dialogCustom != null && dialogCustom.isShowing()) {
			dialogCustom.cancel();
		}
	}

	public static void setDialogNull() {
		dialog = null;
	}

	public interface OnPopupCallbackListener {
		public void itemClick(int position, String text);

		public void dismiss();
	}

	public interface CategoryDialogCallBack {
		public void callback(String text, String cid, String xid);
	}

	public static void hideInputMethod(Context context, EditText edittext) {
		InputMethodManager inputMethodManager = (InputMethodManager) context
				.getApplicationContext().getSystemService(
						Context.INPUT_METHOD_SERVICE);
		inputMethodManager
				.hideSoftInputFromWindow(edittext.getWindowToken(), 0); // 隐藏
	}

}
