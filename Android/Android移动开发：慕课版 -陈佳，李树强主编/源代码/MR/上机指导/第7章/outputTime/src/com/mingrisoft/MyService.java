package com.mingrisoft;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.format.Time;
import android.util.Log;

public class MyService extends Service {
	//����ʵ�ֵķ���
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	//Service������ʱ�ص�
	@Override
	public void onCreate() {
		Log.i("Service","onCreate()����������");
		super.onCreate();
	}
	//Service���ر�֮ǰ�ص���ͨ������������Դ
	@Override
	public void onDestroy() {
		Log.i("Service","onDestroy()����������");
		super.onDestroy();
	}
	//Service������ʱ�ص�
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i("Service","onStartCommand()����������");
		Time time = new Time();								// ����Time����
        time.setToNow();										// ����ʱ��Ϊ��ǰʱ��
        String currentTime = time.format("%Y-%m-%d %H:%M:%S");	// ����ʱ���ʽ
        Log.i("Service", currentTime);					// ��¼��ǰʱ��
        MyService.this.stopSelf();		//ֹͣService
		
		return super.onStartCommand(intent, flags, startId);
	}

}
