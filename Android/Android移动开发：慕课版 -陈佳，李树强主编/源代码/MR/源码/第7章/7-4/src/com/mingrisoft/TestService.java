package com.mingrisoft;

import java.util.Random;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class TestService extends Service {
	//字义一个字符串数组，用于从该数组中获取随机字符串
	String[] tests =new String[]{
		"坚持",
		"奋斗",
		"淡泊"
	};
	private String test;	//随机字符串属性
	//创建ITextService.Stub的实例，并实现ITestService中的方法
	private final ITestService.Stub mBinder= new ITestService.Stub() {
		
		@Override
		public String getTest() throws RemoteException {
			return test;
		}
	};
	//重写远程绑定方法
	@Override
	public IBinder onBind(Intent arg0) {
		Log.i("TestService","远程绑定：TestService");	//输出日志信息
		return mBinder;		//返回IBinder对象
	}
	@Override
	public void onCreate() {
		super.onCreate();
		int index =new Random().nextInt(3);	//生一个0-2的随机整数
		test=tests[index];	//从数字中取出对应的字符串
		Log.i("TestService输出：",test);	//将获取的随机字符串输出到LogCat中
	}
	//重写解除绑定方法
	@Override
	public boolean onUnbind(Intent intent) {
		Log.i("TestService","取消远程绑定：TestService");	//输出日志信息
		return false;
	}
	

}
