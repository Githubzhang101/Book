package com.mingrisoft;

import java.util.Random;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class TestService extends Service {
	//����һ���ַ������飬���ڴӸ������л�ȡ����ַ���
	String[] tests =new String[]{
		"���",
		"�ܶ�",
		"����"
	};
	private String test;	//����ַ�������
	//����ITextService.Stub��ʵ������ʵ��ITestService�еķ���
	private final ITestService.Stub mBinder= new ITestService.Stub() {
		
		@Override
		public String getTest() throws RemoteException {
			return test;
		}
	};
	//��дԶ�̰󶨷���
	@Override
	public IBinder onBind(Intent arg0) {
		Log.i("TestService","Զ�̰󶨣�TestService");	//�����־��Ϣ
		return mBinder;		//����IBinder����
	}
	@Override
	public void onCreate() {
		super.onCreate();
		int index =new Random().nextInt(3);	//��һ��0-2���������
		test=tests[index];	//��������ȡ����Ӧ���ַ���
		Log.i("TestService�����",test);	//����ȡ������ַ��������LogCat��
	}
	//��д����󶨷���
	@Override
	public boolean onUnbind(Intent intent) {
		Log.i("TestService","ȡ��Զ�̰󶨣�TestService");	//�����־��Ϣ
		return false;
	}
	

}
