package com.mingrisoft;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private Button button1; 					// 声明一个“访问页面”按钮对象
	private Button button2; 					// 声明一个“用户登录”按钮对象
	private Handler handler; 					// 声明一个Handler对象
	private String result = ""; 				// 声明一个代表显示内容的字符串
	private TextView resultTV; 				// 声明一个显示结果的文本框对象
	public static HttpClient httpclient; 		// 声明一个静态的全局的HttpClient对象

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		httpclient = new DefaultHttpClient(); 				// 创建HttpClient对象
		resultTV = (TextView) findViewById(R.id.result); 	// 获取显示结果的TextView组件
		button1 = (Button) findViewById(R.id.button1); 		// 获取“访问页面”按钮组件
		// 为按钮添加单击事件监听器
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 创建一个新线程，用于向服务器发送一个GET请求
				new Thread(new Runnable() {
					public void run() {
						access();
						Message m = handler.obtainMessage(); 		// 获取一个Message
						handler.sendMessage(m); 					// 发送消息
					}
				}).start(); 										// 开启线程
			}
		});
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (result != null) {
					resultTV.setText(result); 						// 显示获得的结果
				}
				super.handleMessage(msg);
			}
		};
		button2 = (Button) findViewById(R.id.button2);				//获取“用户登录”按钮
		button2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						LoginActivity.class); 					// 创建Intent对象
				startActivityForResult(intent, 0x11); 				// 启动新的Activity
			}
		});

	}
	public void access() {
		String target = "http://192.168.1.66:8080/login/index.jsp"; 	// 要提交的目标地址
		HttpGet httpRequest = new HttpGet(target); 					// 创建HttpGet对象
		HttpResponse httpResponse;
		try {
			httpResponse = httpclient.execute(httpRequest); 	// 执行HttpClient请求
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				result=EntityUtils.toString(httpResponse.getEntity());//获取返回的字符串
			} else {
				result = "请求失败！";
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace(); 								// 输出异常信息
		} catch (IOException e) {
			e.printStackTrace();
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
