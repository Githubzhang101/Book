package com.mingrisoft;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.SimpleAdapter;


public class MainActivity extends Activity {
	GridView gvInfo;// ����GridView����
	// �����ַ������飬�洢ϵͳ����
	String[] titles = new String[] { "����֧��", "��������", "�ҵ�֧��", "�ҵ�����", "���ݹ���",
			"ϵͳ����", "��֧��ǩ","����", "�˳�" };
	// ����int���飬�洢���ܶ�Ӧ��ͼ��
	int[] images = new int[] { R.drawable.addoutaccount,
			R.drawable.addinaccount, R.drawable.outaccountinfo,
			R.drawable.inaccountinfo, R.drawable.showinfo, R.drawable.sysset,
			R.drawable.accountflag, R.drawable.help, R.drawable.exit };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gvInfo = (GridView) findViewById(R.id.gvInfo);// ��ȡ�����ļ��е�gvInfo���
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();	// ����һ��list����
     // ͨ��forѭ����ͼƬid���б������ַŵ�Map�У�����ӵ�list������
     for (int i = 0; i < images.length; i++) {
     	Map<String, Object> map = new HashMap<String, Object>();
     	map.put("image", images[i]);
     	map.put("title", titles[i]);
     	listItems.add(map); 									// ��map������ӵ�List������
     }
     SimpleAdapter adapter = new SimpleAdapter(this,
     						listItems,
     						R.layout.gvitem,
     						new String[] { "title", "image" },
     						new int[] {R.id.ItemTitle, R.id.ItemImage }
     );													// ����SimpleAdapter
     gvInfo.setAdapter(adapter); 								// ����������GridView����

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
