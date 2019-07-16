package com.mingrisoft;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class TimerService extends Service implements Runnable {
	boolean threadRunning=false;		//标记线程是否开启
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void run() {
		while(!Thread.interrupted()){	//当线程没有中断
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");	//设置日期格式
			String time=df.format(new Date());	//获取当前系统时间
			TimerWidget.updateAppWidget(this,time);	//更新Widget界面
			
			try {
				Thread.sleep(60000);	//让线程休眠1分钟
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
			new Thread(this).start();	//创建并开启线程
		}
	}
	

}
