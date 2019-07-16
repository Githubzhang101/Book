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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        Button btn=(Button)findViewById(R.id.output);
        btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.v("MainActivity", "verbose日志信息");	// 输出Verbose日志信息
				Log.d("MainActivity", "Debug日志信息");	 	// 输出Debug日志信息
				Log.i("MainActivity", "Info日志信息"); 		// 输出Info日志信息
				Log.w("MainActivity", "Warn日志信息"); 		// 输出Warn日志信息
				Log.e("MainActivity", "Error日志信息");	 	// 输出Error日志信息				
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
