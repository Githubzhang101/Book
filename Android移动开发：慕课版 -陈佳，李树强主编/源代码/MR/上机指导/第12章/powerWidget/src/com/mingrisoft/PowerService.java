package com.mingrisoft;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.IBinder;
import android.widget.RemoteViews;

public class PowerService extends Service{
	private MyBoradcastReceiver myBoradcastReceiver;	//�����Զ���Ĺ㲥����������
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	@Override
	public void onDestroy() {
		unregisterReceiver(myBoradcastReceiver);	//ע���㲥������
		super.onDestroy();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		IntentFilter intentFilter=new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		myBoradcastReceiver=new MyBoradcastReceiver();	//ʵ�������ڻ�ȡ�����Ĺ㲥
		registerReceiver(myBoradcastReceiver, intentFilter);	//ע��㲥������
		RemoteViews updateViews=keepUpdate(this);	//�����Զ��巽����ȡ���µ���ͼ
		ComponentName thisWidget=new ComponentName(this, PowerWidget.class);
		AppWidgetManager appWidgetManager=AppWidgetManager.getInstance(this);	//��ȡAppWidgetManager����
		appWidgetManager.updateAppWidget(thisWidget, updateViews);	//����Widget
	}

	private RemoteViews keepUpdate(Context context) {
		RemoteViews views=new RemoteViews(context.getPackageName(),R.layout.widget_layout);
		int power=0;
		SharedPreferences pres=context.getSharedPreferences("mySharedPreferences",
				Context.MODE_PRIVATE);	//��ȡSharedPreferences����
		if(pres!=null){
			power=pres.getInt("power", 0);	//��ȡ�����ļ��б���ĵ���
		}
		views.setTextViewText(R.id.value, power+"%");	//���µ����ٷֱ�
		/***************************���µ�������ʾ����***************************/
		if(power!=0){
			Bitmap bmp=BitmapFactory.decodeResource(getResources(), R.drawable.power);	//��ȡλͼ����
			float x=47.5f/100*power;	//����x������ű���
			Matrix matrix=new Matrix();
			matrix.postScale(x, 1.0f);	//����ģʽ����
			Bitmap resizeBmp=Bitmap.createBitmap(bmp,0,0,1,45,matrix,true);	//����Bitmap����
			views.setBitmap(R.id.ico, "setImageBitmap", resizeBmp);	//���µ�صĽ���
		}
		/***********************************************************************/
		return views;
	}
	

}
