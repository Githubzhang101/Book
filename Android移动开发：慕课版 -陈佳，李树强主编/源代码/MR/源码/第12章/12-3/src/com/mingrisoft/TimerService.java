package com.mingrisoft;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class TimerService extends Service implements Runnable {
	boolean threadRunning=false;		//����߳��Ƿ���
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void run() {
		while(!Thread.interrupted()){	//���߳�û���ж�
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");	//�������ڸ�ʽ
			String time=df.format(new Date());	//��ȡ��ǰϵͳʱ��
			TimerWidget.updateAppWidget(this,time);	//����Widget����
			
			try {
				Thread.sleep(60000);	//���߳�����1����
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		if(!threadRunning){
			threadRunning=true;
			new Thread(this).start();	//�����������߳�
		}
	}
	

}
