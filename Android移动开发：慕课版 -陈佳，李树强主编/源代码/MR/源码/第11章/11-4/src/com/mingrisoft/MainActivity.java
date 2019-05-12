package com.mingrisoft;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText nickname;					//声明一个输入昵称的编辑框对象
	private EditText content;						//声明一个输入文本内容的编辑框对象
	private Button button;						//声明一个发表按钮对象
	private Handler handler; 						//声明一个Handler对象
	private String result = "";					//声明一个代表显示内容的字符串
	private TextView resultTV;					//声明一个显示结果的文本框对象

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		content = (EditText) findViewById(R.id.content);	//获取输入文本内容的EditText组件
		resultTV = (TextView) findViewById(R.id.result);	//获取显示结果的TextView组件
		nickname=(EditText)findViewById(R.id.nickname);		//获取输入昵称的EditText组件
		button = (Button) findViewById(R.id.button);		//获取“发表”按钮组件
		//为按钮添加单击事件监听器
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if ("".equals(content.getText().toString())) {
					Toast.makeText(MainActivity.this, "请输入要发表的内容！",Toast.LENGTH_SHORT).show();
					return;
				}
				// 创建一个新线程，用于发送并读取微博信息
				new Thread(new Runnable() {
					public void run() {
						send();
						Message m = handler.obtainMessage(); 	// 获取一个Message
						handler.sendMessage(m); 				// 发送消息
					}
				}).start(); 									// 开启线程
			}
		});
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (result != null) {
					resultTV.setText(result); 					// 显示获得的结果
					content.setText("");						// 清空内容编辑框
					nickname.setText("");						// 清空昵称编辑框
				}
				super.handleMessage(msg);
			}
		};

	}
	public void send() {
		//要提交的目标地址
		String target = "http://192.168.1.66:8080/blog/deal_httpclient.jsp";	
		HttpClient httpclient = new DefaultHttpClient();		//创建HttpClient对象
		HttpPost httpRequest = new HttpPost(target);			//创建HttpPost对象
		//将要传递的参数保存到List集合中
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("param", "post"));		//标记参数
	//昵称
		params.add(new BasicNameValuePair("nickname", nickname.getText().toString()));
		//内容
		params.add(new BasicNameValuePair("content", content.getText().toString()));
		try {
			//设置编码方式
			httpRequest.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
			//执行HttpClient请求
			HttpResponse httpResponse = httpclient.execute(httpRequest);	
			//如果请求成功
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				result+=EntityUtils.toString(httpResponse.getEntity());//获取返回的字符串
			}else{
				result = "请求失败！";
			}
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();							//输出异常信息
		} catch (ClientProtocolException e) {
			e.printStackTrace();							//输出异常信息
		} catch (IOException e) {
			e.printStackTrace();							//输出异常信息
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
