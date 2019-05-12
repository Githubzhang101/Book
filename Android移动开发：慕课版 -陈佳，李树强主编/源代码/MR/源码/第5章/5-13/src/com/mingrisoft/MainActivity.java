package com.mingrisoft;


import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/****************创建用于为ListView指定列表项的适配器********************/
		String[] ctype=new String[]{"关机","数据网络模式","飞行模式","重新启动"};
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,
android.R.layout.simple_list_item_single_choice,ctype);
		/********************************************************************/
		setListAdapter(adapter);							 //设置该窗口中显示的列表

	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		String result = l.getItemAtPosition(position).toString(); 		// 获取选择项的值
		Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
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
