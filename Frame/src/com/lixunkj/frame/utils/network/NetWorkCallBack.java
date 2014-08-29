package com.lixunkj.frame.utils.network;

import com.lixunkj.frame.entities.Base;

public abstract class NetWorkCallBack<T extends Base> {

	public abstract void onComplete(T response);

}
