package com.mingrisoft;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView tv = (TextView) findViewById(R.id.message); // 获取布局文件中添加的文本框组件
		File root = Environment.getExternalStorageDirectory();// 获得SD卡根路径
		if (root.exists() && root.canWrite()) {
			File file = new File(root, "DemoFile.png");
			try {
				if (file.createNewFile()) {
					tv.setText(file.getName() + "创建成功！");
				} else {
					tv.setText(file.getName() + "创建失败！");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			tv.setText("SD卡不存在或者不可写！");
		}
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
