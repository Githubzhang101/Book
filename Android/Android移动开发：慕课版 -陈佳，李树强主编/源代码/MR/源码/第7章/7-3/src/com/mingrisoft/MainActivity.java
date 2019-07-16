package com.mingrisoft;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mingrisoft.MyService.MyBinder;

public class MainActivity extends Activity {
	boolean bound = false;		//����Ƿ�󶨵ı���
	MyService myService;		//����һ��MyService����
	private ServiceConnection connection = new ServiceConnection() {
	        public void onServiceConnected(ComponentName className, IBinder service) {
	            MyBinder binder = (MyBinder) service;
	            myService = binder.getService();	//��ȡServiceʵ��
	            bound = true;		//����Ѿ���
	        }
	        public void onServiceDisconnected(ComponentName arg0) {
	            bound = false;		//���δ��
	        }
	    };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onStart() {
		super.onStart();
		Intent intent = new Intent(this, MyService.class);
		bindService(intent, connection, Context.BIND_AUTO_CREATE);	//��Service

	}

	@Override
	protected void onStop() {
		super.onStop();
		if (bound) {
			unbindService(connection);		//ȡ���󶨵�Service
			bound = false;
		}
	}
	//��дһ����ť�ĵ����¼����õķ���
    public void onButtonClick(View v) {
        if (bound) {
            int num = myService.getRandomNumber();
            Log.i("MainActivity", "����������" + num);	//��LogCat�������ȡ�������
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
