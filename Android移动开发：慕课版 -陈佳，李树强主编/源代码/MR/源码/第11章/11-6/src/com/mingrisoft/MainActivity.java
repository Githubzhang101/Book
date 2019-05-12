package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		WebView webview=(WebView)findViewById(R.id.webView1);//��ȡ���ֹ���������ӵ�WebView���
		//����һ���ַ�������������Ҫ��ʾ��HTML���ݷ����ڸù�������
		StringBuilder sb=new StringBuilder();
		sb.append("<div>���������ͨ��ʹ�ð�����</div>");
		sb.append("<ul>");
		sb.append("<li>�޸����룺ѡ��ϵͳ���á�ģ������޸ĵ�¼���룬��Ŀ����ʱ��Ĭ��û�����롣</li>");
		sb.append("<li>֧������ѡ������֧����ģ��������֧����Ϣ��ѡ���ҵ�֧����ģ����Բ鿴���޸Ļ�ɾ��֧����Ϣ��</li>");
		sb.append("<li>�������ѡ���������롱ģ��������������Ϣ��ѡ���ҵ����롱ģ����Բ鿴���޸Ļ�ɾ��������Ϣ��</li>");
		sb.append("<li>��ǩ����ѡ����֧��ǩ��ģ�������ӱ�ǩ��Ϣ��ѡ�����ݹ���ģ���еġ���ǩ��Ϣ����ť���Բ鿴���޸Ļ�ɾ����ǩ��Ϣ��</li>");
		sb.append("<li>�˳�ϵͳ��ѡ���˳���ģ������˳����������ͨ����Ŀ��</li>");
		sb.append("</ul>");
		webview.loadDataWithBaseURL(null, sb.toString(),"text/html","utf-8",null);//��������

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
