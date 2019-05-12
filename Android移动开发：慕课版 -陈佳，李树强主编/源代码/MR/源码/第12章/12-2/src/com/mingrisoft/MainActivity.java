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
		setResult(RESULT_CANCELED);		//处理用户未完成Widget配置的情况
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);		//设置使用的布局文件
		Button btn_ok=(Button)findViewById(R.id.ok);	//获取Activity上添加的“确定”按钮
		final RadioGroup textGroup=(RadioGroup)findViewById(R.id.textGroup);
		btn_ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String text="";
				/*******************获取单选按钮组的值********************/
				for(int i=0;i<textGroup.getChildCount();i++){
					RadioButton r=(RadioButton)textGroup.getChildAt(i);
					if(r.isChecked()){
						text=(String)r.getText();
						break;
					}
				}
				/********************************************************/
				Intent intent=getIntent();	//获取Intent对象
				Bundle extras=intent.getExtras();		//获取Bundle对象
				int mAppWidgetId=0;		//定义保存Widget ID的变量
				if(extras!=null){
					mAppWidgetId=extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
							AppWidgetManager.INVALID_APPWIDGET_ID);
					
					
					/**************更新Widget***************************/
					AppWidgetManager appWidgetManager=AppWidgetManager.getInstance(MainActivity.this);
					RemoteViews views=new RemoteViews(MainActivity.this.getPackageName(),
							R.layout.widget_layout);
					views.setTextViewText(R.id.msg, text);
					appWidgetManager.updateAppWidget(mAppWidgetId, views);
					/**************设置返回信息并关闭Activity************/
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
