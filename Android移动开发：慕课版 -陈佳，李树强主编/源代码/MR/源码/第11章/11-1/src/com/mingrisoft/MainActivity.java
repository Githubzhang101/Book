package com.mingrisoft;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText content;				//����һ�������ı����ݵı༭�����
	private Button button;				//����һ������ť����
	private Handler handler; 				// ����һ��Handler����
	private String result = "";			//����һ��������ʾ���ݵ��ַ���
	private TextView resultTV;				//����һ����ʾ������ı������

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		content = (EditText) findViewById(R.id.content);	//��ȡ�����ı����ݵ�EditText���
		resultTV = (TextView) findViewById(R.id.result);	//��ȡ��ʾ�����TextView���
		button = (Button) findViewById(R.id.button);		//��ȡ��������ť���
		//Ϊ��ť��ӵ����¼�������
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if ("".equals(content.getText().toString())) {
					Toast.makeText(MainActivity.this, "������Ҫ��������ݣ�",
							Toast.LENGTH_SHORT).show();	//��ʾ��Ϣ��ʾ
					return;
				}
				// ����һ�����̣߳����ڷ��Ͳ���ȡ΢����Ϣ
				new Thread(new Runnable() {
					public void run() {
						send();							//�����ı����ݵ�Web������������ȡ
						Message m = handler.obtainMessage(); 	// ��ȡһ��Message
						handler.sendMessage(m); 				// ������Ϣ
					}
				}).start();									// �����߳�
			}
		});
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (result != null) {
					resultTV.setText(result); 					// ��ʾ��õĽ��
					content.setText("");						// ��ձ༭��
				}
				super.handleMessage(msg);
			}
		};

	}
	public void send() {
		String target="";
		target = "http://192.168.1.66:8080/blog/index.jsp?content="
					+base64(content.getText().toString().trim());	//Ҫ���ʵ�URL��ַ
		URL url;
		try {
			url = new URL(target);							// ����URL����
			HttpURLConnection urlConn = (HttpURLConnection) url
					.openConnection();						//����һ��HTTP����
			InputStreamReader in = new InputStreamReader(
					urlConn.getInputStream()); 				// ��ö�ȡ������
			BufferedReader buffer = new BufferedReader(in); 	// ��ȡ����������
			String inputLine = null;
			//ͨ��ѭ�����ж�ȡ�������е�����
			while ((inputLine = buffer.readLine()) != null) {
				result += inputLine + "\n";
			}
			in.close();										//�ر��ַ�����������
			urlConn.disconnect();								//�Ͽ�����
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String base64(String content){
		try {
			//���ַ�������Base64����
			content=Base64.encodeToString(content.getBytes("utf-8"), Base64.DEFAULT);
			content=URLEncoder.encode(content);			//���ַ�������URL����
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();							//����쳣��Ϣ
		}
		return content;
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
