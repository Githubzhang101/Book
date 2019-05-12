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
		String[] strInfos = {"1 | 工资4000.00元  2015-05-11","2 | 奖金2000.00元  2015-05-20","3 | 股票5000.00元  2015-05-21","4 | 租金2300.00元  2015-05-27"};// 定义字符串数组，用来存储收入信息
		ListView lvinfo = (ListView) findViewById(R.id.lvinaccountinfo);// 获取布局文件中的ListView组件
		ArrayAdapter<String> arrayAdapter = null;// 创建ArrayAdapter对象
		// 使用字符串数组初始化ArrayAdapter对象
		arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, strInfos);
		lvinfo.setAdapter(arrayAdapter);// 为ListView列表设置数据源
		lvinfo.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View arg1, int pos,	long id){
				String result = parent.getItemAtPosition(pos).toString(); // 获取选择项的值
		//显示提示消息框
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
