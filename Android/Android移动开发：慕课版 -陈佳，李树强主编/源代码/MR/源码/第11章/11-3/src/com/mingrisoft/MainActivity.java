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
	private Button button; 					// ����һ������ť����
	private Handler handler; 					// ����һ��Handler����
	private String result = ""; 				// ����һ��������ʾ������ַ���
	private TextView resultTV; 				// ����һ����ʾ������ı������

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		resultTV = (TextView) findViewById(R.id.result); 	// ��ȡ��ʾ�����TextView���
		button = (Button) findViewById(R.id.button); 		// ��ȡ��������ť���
		// Ϊ��ť��ӵ����¼�������
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				// ����һ�����̣߳����ڷ��Ͳ���ȡGET����
				new Thread(new Runnable() {
					public void run() {
						send();
						Message m = handler.obtainMessage(); 	// ��ȡһ��Message
						handler.sendMessage(m); 				// ������Ϣ
					}
				}).start(); 									// �����߳�
			}
		});
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (result != null) {
					resultTV.setText(result); 					// ��ʾ��õĽ��
				}
				super.handleMessage(msg);
			}
		};

	}
	public void send() {
		//Ҫ�ύ��Ŀ���ַ
		String target = "http://192.168.1.66:8080/blog/deal_httpclient.jsp?param=get";
		HttpClient httpclient = new DefaultHttpClient();		//����HttpClient����
		HttpGet httpRequest = new HttpGet(target);				//����HttpGet���Ӷ���
		HttpResponse httpResponse;
		try {
			httpResponse = httpclient.execute(httpRequest);		//ִ��HttpClient����
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				result=EntityUtils.toString(httpResponse.getEntity());//��ȡ���ص��ַ���
			}else{
				result="����ʧ�ܣ�";
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();								//����쳣��Ϣ
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
