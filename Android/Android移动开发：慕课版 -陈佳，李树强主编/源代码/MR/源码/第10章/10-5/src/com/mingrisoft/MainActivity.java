package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;

public class MainActivity extends Activity {
	// 百度地图组件
	private MapView mMapView = null;
	// 百度地图对象
	private BaiduMap mBaiduMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置不显示标题栏
		SDKInitializer.initialize(getApplicationContext()); // 初始化地图SDK
		setContentView(R.layout.activity_main);
		init(); // 调用init()方法
	}

	/**
	 * 初始化方法
	 */
	private void init() {
		mMapView = (MapView) findViewById(R.id.bmapview); // 获取地图组件
		mBaiduMap = mMapView.getMap(); // 获取百度地图对象
		mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL); // 普通地图

		// 定义Maker坐标点
		LatLng point = new LatLng(39.944005, 116.361044);
		// 构建Marker图标
		BitmapDescriptor bitmap = BitmapDescriptorFactory
				.fromResource(R.drawable.ico);
		// 构建MarkerOption，用于在地图上添加Marker
		OverlayOptions option = new MarkerOptions().position(point)
				.icon(bitmap);
		// 在地图上添加Marker，并显示
		mBaiduMap.addOverlay(option);
	}

	@Override
	protected void onResume() {
		super.onResume();
		mMapView.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		mMapView.onPause();
	}

	@Override
	protected void onDestroy() {
		mMapView.onDestroy();
		mMapView = null;
		super.onDestroy();
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
