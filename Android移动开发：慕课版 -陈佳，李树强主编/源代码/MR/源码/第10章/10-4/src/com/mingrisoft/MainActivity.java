package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;

public class MainActivity extends Activity {
    // 百度地图组件  
    private MapView mMapView = null;  
    // 百度地图对象  
    private BaiduMap mBaiduMap;  

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);  //设置不显示标题栏
	    SDKInitializer.initialize(getApplicationContext());  //初始化地图SDK
	    setContentView(R.layout.activity_main);  
	    init();	//调用init()方法

	}
    /** 
     * 初始化方法 
     */  
    private void init() {  
        mMapView = (MapView) findViewById(R.id.bmapview); //获取地图组件
        mBaiduMap=mMapView.getMap();	//获取百度地图对象
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);		//普通地图
//        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);	//卫星地图
        //开启交通图
//        mBaiduMap.setTrafficEnabled(true);
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
