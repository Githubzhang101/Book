package com.mingrisoft;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RemoteViews;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setResult(RESULT_CANCELED);		//�����û�δ���Widget���õ����
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);		//����ʹ�õĲ����ļ�
		Button btn_ok=(Button)findViewById(R.id.ok);	//��ȡActivity����ӵġ�ȷ������ť
		final RadioGroup textGroup=(RadioGroup)findViewById(R.id.textGroup);
		btn_ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String text="";
				/*******************��ȡ��ѡ��ť���ֵ********************/
				for(int i=0;i<textGroup.getChildCount();i++){
					RadioButton r=(RadioButton)textGroup.getChildAt(i);
					if(r.isChecked()){
						text=(String)r.getText();
						break;
					}
				}
				/********************************************************/
				Intent intent=getIntent();	//��ȡIntent����
				Bundle extras=intent.getExtras();		//��ȡBundle����
				int mAppWidgetId=0;		//���屣��Widget ID�ı���
				if(extras!=null){
					mAppWidgetId=extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
							AppWidgetManager.INVALID_APPWIDGET_ID);
					
					
					/**************����Widget***************************/
					AppWidgetManager appWidgetManager=AppWidgetManager.getInstance(MainActivity.this);
					RemoteViews views=new RemoteViews(MainActivity.this.getPackageName(),
							R.layout.widget_layout);
					views.setTextViewText(R.id.msg, text);
					appWidgetManager.updateAppWidget(mAppWidgetId, views);
					/**************���÷�����Ϣ���ر�Activity************/
					Intent resultValue=new Intent();
					resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
					setResult(RESULT_OK,resultValue);
					finish();
				}
				if(mAppWidgetId==AppWidgetManager.INVALID_APPWIDGET_ID){
					finish();
				}
			}
		});
	}

}
