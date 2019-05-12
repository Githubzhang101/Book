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
	private EditText nickname;					//����һ�������ǳƵı༭�����
	private EditText content;						//����һ�������ı����ݵı༭�����
	private Button button;						//����һ������ť����
	private Handler handler; 						//����һ��Handler����
	private String result = "";					//����һ��������ʾ���ݵ��ַ���
	private TextView resultTV;					//����һ����ʾ������ı������

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		content = (EditText) findViewById(R.id.content);	//��ȡ�����ı����ݵ�EditText���
		resultTV = (TextView) findViewById(R.id.result);	//��ȡ��ʾ�����TextView���
		nickname=(EditText)findViewById(R.id.nickname);		//��ȡ�����ǳƵ�EditText���
		button = (Button) findViewById(R.id.button);		//��ȡ��������ť���
		//Ϊ��ť��ӵ����¼�������
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if ("".equals(content.getText().toString())) {
					Toast.makeText(MainActivity.this, "������Ҫ��������ݣ�",Toast.LENGTH_SHORT).show();
					return;
				}
				// ����һ�����̣߳����ڷ��Ͳ���ȡ΢����Ϣ
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
					content.setText("");						// ������ݱ༭��
					nickname.setText("");						// ����ǳƱ༭��
				}
				super.handleMessage(msg);
			}
		};

	}
	public void send() {
		//Ҫ�ύ��Ŀ���ַ
		String target = "http://192.168.1.66:8080/blog/deal_httpclient.jsp";	
		HttpClient httpclient = new DefaultHttpClient();		//����HttpClient����
		HttpPost httpRequest = new HttpPost(target);			//����HttpPost����
		//��Ҫ���ݵĲ������浽List������
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("param", "post"));		//��ǲ���
	//�ǳ�
		params.add(new BasicNameValuePair("nickname", nickname.getText().toString()));
		//����
		params.add(new BasicNameValuePair("content", content.getText().toString()));
		try {
			//���ñ��뷽ʽ
			httpRequest.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
			//ִ��HttpClient����
			HttpResponse httpResponse = httpclient.execute(httpRequest);	
			//�������ɹ�
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				result+=EntityUtils.toString(httpResponse.getEntity());//��ȡ���ص��ַ���
			}else{
				result = "����ʧ�ܣ�";
			}
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();							//����쳣��Ϣ
		} catch (ClientProtocolException e) {
			e.printStackTrace();							//����쳣��Ϣ
		} catch (IOException e) {
			e.printStackTrace();							//����쳣��Ϣ
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
