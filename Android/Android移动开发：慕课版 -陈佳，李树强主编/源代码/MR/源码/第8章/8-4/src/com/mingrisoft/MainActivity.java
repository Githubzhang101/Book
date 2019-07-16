package com.mingrisoft;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.util.EncodingUtils;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Resources resources=getResources();		//获取资源实例
		InputStream is=null;	//声明输入流对象
		is=resources.openRawResource(R.raw.demo_raw);	//获取raw资源
		byte[] buffer;
		try {
			buffer = new byte[is.available()];
			is.read(buffer);
			String result=new String(buffer, "utf-8");	//转换为字符串，采用UTF-8编码
			Log.i("MainActivity", result);		//将读取结果输出到LogCat中
			is.close();	//关闭输入流对象
		} catch (IOException e) {
			e.printStackTrace();	//输出异常信息
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
