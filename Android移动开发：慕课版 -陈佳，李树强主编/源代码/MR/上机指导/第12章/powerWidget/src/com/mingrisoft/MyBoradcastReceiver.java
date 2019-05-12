package com.mingrisoft;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class MyBoradcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		int level=intent.getIntExtra("level", 0);
		int scale=intent.getIntExtra("scale", 100);
		SharedPreferences pres=context.getSharedPreferences("mySharedPreferences", context.MODE_PRIVATE);
		if(pres!=null){
			SharedPreferences.Editor ed=pres.edit();
			ed.putInt("power",(level*100/scale));
			System.out.println("当前电量："+level+"最大值："+scale);
			ed.commit();
		}
	}

}
