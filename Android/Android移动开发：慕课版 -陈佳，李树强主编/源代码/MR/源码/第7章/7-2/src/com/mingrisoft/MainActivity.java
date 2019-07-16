package com.mingrisoft;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button btn_start=(Button)findViewById(R.id.btn_start);	//获取“启动Service”按钮
		
		final Intent intent=new Intent(MainActivity.this,MyService.class);	//创建Intent对象
		btn_start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				startService(intent);	//启动Service
				
			}
		});
		Button btn_stop=(Button)findViewById(R.id.btn_stop);	//获取“停止Service”按钮
		btn_stop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				stopService(intent);		//停止Service
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
