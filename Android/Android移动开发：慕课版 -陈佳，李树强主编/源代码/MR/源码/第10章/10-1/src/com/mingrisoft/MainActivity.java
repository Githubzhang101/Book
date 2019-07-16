package com.mingrisoft;

import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 StringBuilder sb = new StringBuilder();		// 使用StringBuilder保存数据
	        // 获得位置服务
	        LocationManager manager = (LocationManager) getSystemService(LOCATION_SERVICE); 
	        List<String> providers = manager.getAllProviders();		// 获得全部位置源
	        for (Iterator<String> it = providers.iterator(); it.hasNext();) {// 遍历列表
	            sb.append(it.next() + "\n");
	        }
	        TextView text = (TextView) findViewById(R.id.location);	// 获得文本框控件
	        text.setText(sb.toString());							// 显示位置源列表

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
