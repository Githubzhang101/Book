package com.mingrisoft;
import java.util.LinkedList;
import java.util.Queue;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class TimerWidget extends AppWidgetProvider {
	private static Queue<Integer> widgetIds=new LinkedList<Integer>();	//����һ����������Widgetʵ����IDֵ�Ķ���
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		super.onDeleted(context, appWidgetIds);
		//��Widget��ID��WidgetIds�������Ƴ�
		for(int i=0;i<appWidgetIds.length;i++){
			if(widgetIds.contains(appWidgetIds[i])){	//����ڶ�����
				widgetIds.remove(appWidgetIds[i]);	//�Ӷ������Ƴ�
				
			}
		}
	}

	@Override
	public void onDisabled(Context context) {
		super.onDisabled(context);
		context.stopService(new Intent(context,TimerService.class));	//ֹͣ����
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
			widgetIds.add(appWidgetIds[i]);	//��Widget ID��ӵ�������
		}
		context.startService(new Intent(context,TimerService.class));	//��������
	}

	public static void updateAppWidget(Context context, String time) {
		AppWidgetManager appWidgetManager=AppWidgetManager.getInstance(context);	//��ȡAppWidgetManager����
		RemoteViews views=new RemoteViews(context.getPackageName(),R.layout.widget_layout);	//��ȡWidget��ͼ����
		views.setTextViewText(R.id.msg,time);	//����Widget��msg�ı������ʾ�ı�Ϊ��ȡ��ϵͳʱ��
		final int N=widgetIds.size();	//��ȡ������Ԫ�صĸ���
		for(int i=0;i<N;i++){
			int appWidgetId=widgetIds.poll();	//�Ƴ������ض���ͷ����Ԫ��
			appWidgetManager.updateAppWidget(appWidgetId, views);	//����Widget
			widgetIds.add(appWidgetId);	//��Widget ID��ӵ�������
		}
	}

}