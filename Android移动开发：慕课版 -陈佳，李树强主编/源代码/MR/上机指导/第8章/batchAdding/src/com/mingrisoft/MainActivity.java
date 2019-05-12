package com.mingrisoft;

import java.io.InputStream;
import java.util.Scanner;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.mingrisoft.util.DBHelper;
import com.mingrisoft.util.DataBean;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button btn=(Button)findViewById(R.id.add);		//��ȡ����ʼ������ݡ���ť
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				/******************�����������******************/
				DBHelper helper = new DBHelper(MainActivity.this);
				InputStream is = getResources().openRawResource(R.raw.data); // ���������
				Scanner scanner = new Scanner(is);
				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();// ���һ������
					String[] data = line.split(" ");// ʹ�ÿո����ݷ���
					DataBean db = new DataBean();
					db.setNumber(Integer.parseInt(data[0]));// ����numberֵ
					db.setSquare(Integer.parseInt(data[1]));// ����squareֵ
					db.setCube(Integer.parseInt(data[2]));// ����cubeֵ
					helper.insert(db);// �����ݿ��в���һ������
				}
				/***********************************************/
				ListView lv = (ListView) findViewById(R.id.list);// ����б���ͼ
				ArrayAdapter<String> fileList = new ArrayAdapter<String>(MainActivity.this, R.layout.list_item, helper.queryAll());
				lv.setAdapter(fileList);// �����б�������
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
