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
        /***************��λ**************************/
      //�趨���ĵ����� 
        LatLng cenpt = new LatLng(43.835351,125.337083); 
      //�����ͼ״̬
        MapStatus mMapStatus = new MapStatus.Builder().target(cenpt).zoom(15).build();
        //����MapStatusUpdate�����Ա�������ͼ״̬��Ҫ�����ı仯
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //�ı��ͼ״̬
        mBaiduMap.setMapStatus(mMapStatusUpdate);        
        /*******************************************/
      //����Maker�����  
        LatLng point = new LatLng(43.834351,125.337083);
        //����Markerͼ��  
        BitmapDescriptor bitmap = BitmapDescriptorFactory  
            .fromResource(R.drawable.ic_launcher);  
        //����MarkerOption�������ڵ�ͼ�����Marker  
        OverlayOptions option = new MarkerOptions()  
            .position(point)  
            .icon(bitmap);  
        //�ڵ�ͼ�����Marker������ʾ  
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
