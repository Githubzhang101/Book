package com.mingrisoft;

import java.util.List;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private ITestService testService;	//����ITestService����
	private TextView tv;				//������ʾ����ַ������ı����������
	//����ServiceConnection����
	private ServiceConnection conn = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			testService=null;
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			testService =ITestService.Stub.asInterface(service);
			
		}
	};

	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.unbindService(conn);   //�����
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Intent intent = new Intent();
		intent.setAction("com.mingrisoft.AIDL_SERVICE");		//ΪIntent����Action
//		bindService(intent, conn, Service.BIND_AUTO_CREATE);	//Android 5.0��ǰ��Զ�̷���Ĵ���
		bindService(new Intent(getExplicitIntent(this,intent)), conn, Service.BIND_AUTO_CREATE);		//��Զ�̷���
		Button btn=(Button)findViewById(R.id.button1);		//��ȡ����ȡ����ַ�������ť
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				try {
					tv=(TextView)findViewById(R.id.test);	//��ȡ��ʾ���ݵ��ı������
					tv.setText("��Զ�̷����ȡ������ַ�����"+testService.getTest());	//��Զ�̷����л�ȡ���ɵ�����ַ�����ʾ���ı�����
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
	}
	//���Android 5.0�汾���ֵ�Service Intent must be explitict�������д�ķ���
	//��Ҫ�����ڽ���ʽIntentת��Ϊ��ʽIntent
	public static Intent getExplicitIntent(Context context,Intent implicitIntent){
		PackageManager pm=context.getPackageManager();
		List<ResolveInfo> resolveInfo =pm.queryIntentServices(implicitIntent, 0);
		if(resolveInfo==null||resolveInfo.size()!=1){
			return null;
		}
		ResolveInfo serviceInfo= resolveInfo.get(0);
		String packageName=serviceInfo.serviceInfo.packageName;
		String className=serviceInfo.serviceInfo.name;
		ComponentName component = new ComponentName(packageName, className);
		Intent explicitIntent = new Intent(implicitIntent);
		explicitIntent.setComponent(component);
		return explicitIntent;
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
