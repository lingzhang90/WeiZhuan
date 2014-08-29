package com.lixunkj.frame;

import android.app.Application;

public class App extends Application {

	private static App mApp;

	@Override
	public void onCreate() {
		super.onCreate();
		mApp = this;
		InitVolley.getInstance().init(this);
		InitUser.getInstance().init(this);
		// SPUtils.getInstance().init(this);
	}

	public static App getInstance() {
		return mApp;
	}

}
