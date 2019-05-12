package com.mingrisoft;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.format.Time;
import android.util.Log;

public class MyService extends Service {
	//必须实现的方法
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	//Service被创建时回调
	@Override
	public void onCreate() {
		Log.i("Service","onCreate()方法被调用");
		super.onCreate();
	}
	//Service被关闭之前回调，通常用于清理资源
	@Override
	public void onDestroy() {
		Log.i("Service","onDestroy()方法被调用");
		super.onDestroy();
	}
	//Service被启动时回调
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i("Service","onStartCommand()方法被调用");
		Time time = new Time();								// 创建Time对象
        time.setToNow();										// 设置时间为当前时间
        String currentTime = time.format("%Y-%m-%d %H:%M:%S");	// 设置时间格式
        Log.i("Service", currentTime);					// 记录当前时间
        MyService.this.stopSelf();		//停止Service
		
		return super.onStartCommand(intent, flags, startId);
	}

}
