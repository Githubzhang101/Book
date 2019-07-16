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
		TextView tv = (TextView) findViewById(R.id.message); // ��ȡ�����ļ�����ӵ��ı������
		File root = Environment.getExternalStorageDirectory();// ���SD����·��
		if (root.exists() && root.canWrite()) {
			File file = new File(root, "DemoFile.png");
			try {
				if (file.createNewFile()) {
					tv.setText(file.getName() + "�����ɹ���");
				} else {
					tv.setText(file.getName() + "����ʧ�ܣ�");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			tv.setText("SD�������ڻ��߲���д��");
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
