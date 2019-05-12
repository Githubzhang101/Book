package com.mingrisoft;

import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 StringBuilder sb = new StringBuilder();		// ʹ��StringBuilder��������
	        // ���λ�÷���
	        LocationManager manager = (LocationManager) getSystemService(LOCATION_SERVICE); 
	        List<String> providers = manager.getAllProviders();		// ���ȫ��λ��Դ
	        for (Iterator<String> it = providers.iterator(); it.hasNext();) {// �����б�
	            sb.append(it.next() + "\n");
	        }
	        TextView text = (TextView) findViewById(R.id.location);	// ����ı���ؼ�
	        text.setText(sb.toString());							// ��ʾλ��Դ�б�

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
