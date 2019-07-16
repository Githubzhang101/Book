package com.mingrisoft;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
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
		StringBuilder sb = new StringBuilder();		// 使用StringBuilder保存数据
		// 获得位置服务
		LocationManager manager = (LocationManager) getSystemService(LOCATION_SERVICE); 
		manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,10000,2,new LocationListener(){
		    @Override
		    public void onStatusChanged(String provider, int status, Bundle extras) {
		    }
		    @Override
		    public void onProviderEnabled(String provider) {
		    }
		    @Override
		    public void onProviderDisabled(String provider) {
		    }
		    @Override
		    public void onLocationChanged(Location location) {
		    }
		});
		Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if (location != null) {
		    sb.append("纬度：" + location.getLatitude() + "\n");
		    sb.append("经度：" + location.getLongitude());
		} else {
		    sb.append("location is null~");
		}
		TextView text = (TextView) findViewById(R.id.location);	// 获得文本框控件
		text.setText(sb.toString());							// 显示位置源列表

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
