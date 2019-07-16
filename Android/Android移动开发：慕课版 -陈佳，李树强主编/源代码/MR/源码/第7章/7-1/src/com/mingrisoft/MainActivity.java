package com.mingrisoft;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity implements Runnable {
	 private TextView tv_random ; // ����һ����ʾ�������TextView���
	private Handler handler; // ����һ��Handler����
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv_random = (TextView) findViewById(R.id.random); // ��ȡ��ʾ������ݵ��ı���
		Thread t = new Thread(this); // �������߳�
		t.start(); // �����߳�
		// ʵ����һ��Handler����
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// ����UI
				if (msg.what == 0x101) {
					// ��ʾ�����
					tv_random.setText(msg.obj.toString());
				}
				super.handleMessage(msg);
			}
		};
	}

	@Override
	public void run() {
		String value = "";		//���ɵ������
		int max=9999999;		//����������ֵ
		int min=1000000;		//���������Сֵ
		while (!Thread.currentThread().isInterrupted()) {
			value=String.valueOf(new Random().nextInt(max)%(max-min+1)+min);	//����ָ����Χ�������
			Message m = handler.obtainMessage(); // ��ȡһ��Message
			m.obj = value; // �������ɵ������
			m.what = 0x101; // ������Ϣ��ʶ
			handler.sendMessage(m); // ������Ϣ

			try {
				Thread.sleep(1000); // �߳�����1����
			} catch (InterruptedException e) {
				e.printStackTrace(); // ����쳣��Ϣ
			}

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
