package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	final String TAG = "***生命周期***";		//定义标记常量
	//完整生命周期开始被调用，初始化Activity
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.i(TAG, "onCreate()");		//输出一条日志信息
		Button close = (Button) findViewById(R.id.close);		//获取布局文件中添加的按钮组件
		close.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();		//结束当前Activity
			}
		});
	}
	//可视生命周期开始时被调用，对用户界面进行必要的更改
	@Override
	protected void onStart() {
		super.onStart();
		Log.i(TAG, "onStart()");
	}

	//活动生命周期开始时被调用，恢复被onPause()停止的用户界面更新的资源
	@Override
	protected void onResume() {
		super.onResume();
		Log.i(TAG, "onResume()");
	}
	//在重新进入可视生命前被调用，载入界面所需的更改信息
	@Override
	protected void onRestart() {
		super.onRestart();
		Log.i(TAG, "onRestart()");
	}
	//在活动生命周期结束时被调用，用来保存持久的数据或释放占用的资源
	@Override
	protected void onPause() {
		super.onPause();
		Log.i(TAG, "onPause()");
	}
	//在可视生命周期结束时被调用，用来释放占用的资源
	@Override
	protected void onStop() {
		super.onStop();
		Log.i(TAG, "onStop()");
	}
	//在完整生命周期结束时被调用，释放资源
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i(TAG, "onDestroy()");
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
