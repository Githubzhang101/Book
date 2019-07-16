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
	private MyBoradcastReceiver myBoradcastReceiver;	//声明自定义的广播接收器对象
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	@Override
	public void onDestroy() {
		unregisterReceiver(myBoradcastReceiver);	//注销广播接收器
		super.onDestroy();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		IntentFilter intentFilter=new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		myBoradcastReceiver=new MyBoradcastReceiver();	//实例化用于获取电量的广播
		registerReceiver(myBoradcastReceiver, intentFilter);	//注册广播接收器
		RemoteViews updateViews=keepUpdate(this);	//调用自定义方法获取更新的视图
		ComponentName thisWidget=new ComponentName(this, PowerWidget.class);
		AppWidgetManager appWidgetManager=AppWidgetManager.getInstance(this);	//获取AppWidgetManager对象
		appWidgetManager.updateAppWidget(thisWidget, updateViews);	//更新Widget
	}

	private RemoteViews keepUpdate(Context context) {
		RemoteViews views=new RemoteViews(context.getPackageName(),R.layout.widget_layout);
		int power=0;
		SharedPreferences pres=context.getSharedPreferences("mySharedPreferences",
				Context.MODE_PRIVATE);	//获取SharedPreferences对象
		if(pres!=null){
			power=pres.getInt("power", 0);	//获取共享文件中保存的电量
		}
		views.setTextViewText(R.id.value, power+"%");	//更新电量百分比
		/***************************更新电量的显示进度***************************/
		if(power!=0){
			Bitmap bmp=BitmapFactory.decodeResource(getResources(), R.drawable.power);	//获取位图对象
			float x=47.5f/100*power;	//计算x轴的缩放比例
			Matrix matrix=new Matrix();
			matrix.postScale(x, 1.0f);	//进行模式缩放
			Bitmap resizeBmp=Bitmap.createBitmap(bmp,0,0,1,45,matrix,true);	//创建Bitmap对象
			views.setBitmap(R.id.ico, "setImageBitmap", resizeBmp);	//更新电池的进度
		}
		/***********************************************************************/
		return views;
	}
	

}
