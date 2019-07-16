package com.mingrisoft;
import java.util.LinkedList;
import java.util.Queue;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class TimerWidget extends AppWidgetProvider {
	private static Queue<Integer> widgetIds=new LinkedList<Integer>();	//声明一个保存所有Widget实例的ID值的队列
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		super.onDeleted(context, appWidgetIds);
		//将Widget的ID从WidgetIds队列中移除
		for(int i=0;i<appWidgetIds.length;i++){
			if(widgetIds.contains(appWidgetIds[i])){	//如果在队列中
				widgetIds.remove(appWidgetIds[i]);	//从队列中移除
				
			}
		}
	}

	@Override
	public void onDisabled(Context context) {
		super.onDisabled(context);
		context.stopService(new Intent(context,TimerService.class));	//停止服务
	}

	@Override
	public void onEnabled(Context context) {
		super.onEnabled(context);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		for(int i=0;i<appWidgetIds.length;i++){
			widgetIds.add(appWidgetIds[i]);	//将Widget ID添加到队列中
		}
		context.startService(new Intent(context,TimerService.class));	//开启服务
	}

	public static void updateAppWidget(Context context, String time) {
		AppWidgetManager appWidgetManager=AppWidgetManager.getInstance(context);	//获取AppWidgetManager对象
		RemoteViews views=new RemoteViews(context.getPackageName(),R.layout.widget_layout);	//获取Widget视图对象
		views.setTextViewText(R.id.msg,time);	//更改Widget的msg文本框的显示文本为获取的系统时间
		final int N=widgetIds.size();	//获取队列中元素的个数
		for(int i=0;i<N;i++){
			int appWidgetId=widgetIds.poll();	//移除并返回队列头部的元素
			appWidgetManager.updateAppWidget(appWidgetId, views);	//更新Widget
			widgetIds.add(appWidgetId);	//将Widget ID添加到队列中
		}
	}

}