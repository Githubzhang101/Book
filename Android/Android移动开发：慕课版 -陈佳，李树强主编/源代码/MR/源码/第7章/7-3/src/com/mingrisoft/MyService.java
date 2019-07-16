package com.mingrisoft;

import java.util.Random;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {
	private final IBinder binder = new MyBinder();	//����һ��IBinder����
	private final Random generator = new Random();	//������ʵ����һ��Randrom����
	//��д һ���̳���Binder���ڲ���
	public class MyBinder extends Binder {
		MyService getService() {
			return MyService.this;
		}

	}

	@Override
	public IBinder onBind(Intent arg0) {
		return binder;	//����IBinder����
	}
	//��ȡ������ķ���
    public int getRandomNumber() {
        return generator.nextInt(100);	//�������ɵ������
    }

}
