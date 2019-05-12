package com.mingrisoft;

import android.app.Activity;
import android.location.Criteria;
import android.location.LocationManager;
import android.location.LocationProvider;
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
        // ���GPSλ��Դ
        LocationProvider provider = manager.getProvider(LocationManager.GPS_PROVIDER); 
        sb.append("���ȣ�");
        switch (provider.getAccuracy()) {							// ��þ�����Ϣ
        case Criteria.ACCURACY_HIGH:
            sb.append("ACCURACY_HIGH");
            break;
        case Criteria.ACCURACY_MEDIUM:
            sb.append("ACCURACY_MEDIUM");
            break;
        case Criteria.ACCURACY_LOW:
            sb.append("ACCURACY_LOW");
            break;
        }
        sb.append("\n�ĵ�����");
        switch (provider.getPowerRequirement()) {					// ��úĵ���Ϣ
        case Criteria.POWER_HIGH:
            sb.append("POWER_HIGH");
            break;
        case Criteria.POWER_MEDIUM:
            sb.append("POWER_MEDIUM");
            break;
        case Criteria.POWER_LOW:
            sb.append("POWER_LOW");
            break;
        }
        TextView text = (TextView) findViewById(R.id.location);	// ����ı���ؼ�
        text.setText(sb.toString());								// ��ʾλ��Դ�б�
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
