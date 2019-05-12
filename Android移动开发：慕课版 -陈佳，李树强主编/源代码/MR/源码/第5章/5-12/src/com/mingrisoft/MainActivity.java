package com.mingrisoft;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		String[] strInfos = {"1 | ����4000.00Ԫ  2015-05-11","2 | ����2000.00Ԫ  2015-05-20","3 | ��Ʊ5000.00Ԫ  2015-05-21","4 | ���2300.00Ԫ  2015-05-27"};// �����ַ������飬�����洢������Ϣ
		ListView lvinfo = (ListView) findViewById(R.id.lvinaccountinfo);// ��ȡ�����ļ��е�ListView���
		ArrayAdapter<String> arrayAdapter = null;// ����ArrayAdapter����
		// ʹ���ַ��������ʼ��ArrayAdapter����
		arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, strInfos);
		lvinfo.setAdapter(arrayAdapter);// ΪListView�б���������Դ
		lvinfo.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View arg1, int pos,	long id){
				String result = parent.getItemAtPosition(pos).toString(); // ��ȡѡ�����ֵ
		//��ʾ��ʾ��Ϣ��
				Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();	
			}
		});

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
