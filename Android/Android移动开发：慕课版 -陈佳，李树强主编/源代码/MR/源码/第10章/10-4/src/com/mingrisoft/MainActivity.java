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
    // �ٶȵ�ͼ���  
    private MapView mMapView = null;  
    // �ٶȵ�ͼ����  
    private BaiduMap mBaiduMap;  

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);  //���ò���ʾ������
	    SDKInitializer.initialize(getApplicationContext());  //��ʼ����ͼSDK
	    setContentView(R.layout.activity_main);  
	    init();	//����init()����

	}
    /** 
     * ��ʼ������ 
     */  
    private void init() {  
        mMapView = (MapView) findViewById(R.id.bmapview); //��ȡ��ͼ���
        mBaiduMap=mMapView.getMap();	//��ȡ�ٶȵ�ͼ����
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);		//��ͨ��ͼ
//        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);	//���ǵ�ͼ
        //������ͨͼ
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
