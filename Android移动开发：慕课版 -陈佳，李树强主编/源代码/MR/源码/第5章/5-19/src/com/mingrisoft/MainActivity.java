package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity implements OnTouchListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		RelativeLayout layout = (RelativeLayout) findViewById(R.id.layout); // ��ȡ��Բ��ֹ�����
		layout.setOnTouchListener(this); // ���ô����¼�������

	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int x = (int) event.getX();		//��ȡx������
		int y = (int) event.getY();		//��ȡy������
		Toast.makeText(this, "������Ļλ��Ϊ����" + x + "," + y + "��", Toast.LENGTH_LONG)
				.show();
		return true;
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
