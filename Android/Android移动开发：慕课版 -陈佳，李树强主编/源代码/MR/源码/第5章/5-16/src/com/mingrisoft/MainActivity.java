package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		MenuInflater inflater=new MenuInflater(this);	//实例化一个MenuInflater对象
		inflater.inflate(R.menu.main, menu);		//解析菜单文件
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
			//弹出消息提示框显示选择的菜单项的标题
			Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();	
		return true;
	}
}
