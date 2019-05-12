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
	 private TextView tv_random ; // 声明一个显示随机数的TextView组件
	private Handler handler; // 声明一个Handler对象
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv_random = (TextView) findViewById(R.id.random); // 获取显示随机数据的文本框
		Thread t = new Thread(this); // 创建新线程
		t.start(); // 开启线程
		// 实例化一个Handler对象
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// 更新UI
				if (msg.what == 0x101) {
					// 显示随机数
					tv_random.setText(msg.obj.toString());
				}
				super.handleMessage(msg);
			}
		};
	}

	@Override
	public void run() {
		String value = "";		//生成的随机数
		int max=9999999;		//随机数的最大值
		int min=1000000;		//随机数的最小值
		while (!Thread.currentThread().isInterrupted()) {
			value=String.valueOf(new Random().nextInt(max)%(max-min+1)+min);	//生成指定范围的随机数
			Message m = handler.obtainMessage(); // 获取一个Message
			m.obj = value; // 保存生成的随机数
			m.what = 0x101; // 设置消息标识
			handler.sendMessage(m); // 发送消息

			try {
				Thread.sleep(1000); // 线程休眠1秒钟
			} catch (InterruptedException e) {
				e.printStackTrace(); // 输出异常信息
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
