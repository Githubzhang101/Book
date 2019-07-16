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
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
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
        /***************定位**************************/
      //设定中心点坐标 
        LatLng cenpt = new LatLng(43.835351,125.337083); 
      //定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder().target(cenpt).zoom(15).build();
        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        mBaiduMap.setMapStatus(mMapStatusUpdate);        
        /*******************************************/
      //定义Maker坐标点  
        LatLng point = new LatLng(43.834351,125.337083);
        //构建Marker图标  
        BitmapDescriptor bitmap = BitmapDescriptorFactory  
            .fromResource(R.drawable.ic_launcher);  
        //构建MarkerOption，用于在地图上添加Marker  
        OverlayOptions option = new MarkerOptions()  
            .position(point)  
            .icon(bitmap);  
        //在地图上添加Marker，并显示  
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
