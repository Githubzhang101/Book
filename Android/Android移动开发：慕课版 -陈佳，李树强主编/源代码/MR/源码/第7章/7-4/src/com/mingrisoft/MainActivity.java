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
	private ITestService testService;	//声明ITestService对象
	private TextView tv;				//声明显示随机字符串的文本框组件对象
	//创建ServiceConnection对象
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
		this.unbindService(conn);   //解除绑定
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Intent intent = new Intent();
		intent.setAction("com.mingrisoft.AIDL_SERVICE");		//为Intent设置Action
//		bindService(intent, conn, Service.BIND_AUTO_CREATE);	//Android 5.0以前绑定远程服务的代码
		bindService(new Intent(getExplicitIntent(this,intent)), conn, Service.BIND_AUTO_CREATE);		//绑定远程服务
		Button btn=(Button)findViewById(R.id.button1);		//获取“获取随机字符串”按钮
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				try {
					tv=(TextView)findViewById(R.id.test);	//获取显示内容的文本框组件
					tv.setText("从远程服务获取的随机字符串："+testService.getTest());	//从远程服务中获取生成的随机字符串显示到文本框中
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
	}
	//解决Android 5.0版本出现的Service Intent must be explitict错误而编写的方法
	//主要是用于将隐式Intent转换为显式Intent
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
