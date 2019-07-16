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
	GridView gvInfo;// 创建GridView对象
	// 定义字符串数组，存储系统功能
	String[] titles = new String[] { "新增支出", "新增收入", "我的支出", "我的收入", "数据管理",
			"系统设置", "收支便签","帮助", "退出" };
	// 定义int数组，存储功能对应的图标
	int[] images = new int[] { R.drawable.addoutaccount,
			R.drawable.addinaccount, R.drawable.outaccountinfo,
			R.drawable.inaccountinfo, R.drawable.showinfo, R.drawable.sysset,
			R.drawable.accountflag, R.drawable.help, R.drawable.exit };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gvInfo = (GridView) findViewById(R.id.gvInfo);// 获取布局文件中的gvInfo组件
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();	// 创建一个list集合
     // 通过for循环将图片id和列表项文字放到Map中，并添加到list集合中
     for (int i = 0; i < images.length; i++) {
     	Map<String, Object> map = new HashMap<String, Object>();
     	map.put("image", images[i]);
     	map.put("title", titles[i]);
     	listItems.add(map); 									// 将map对象添加到List集合中
     }
     SimpleAdapter adapter = new SimpleAdapter(this,
     						listItems,
     						R.layout.gvitem,
     						new String[] { "title", "image" },
     						new int[] {R.id.ItemTitle, R.id.ItemImage }
     );													// 创建SimpleAdapter
     gvInfo.setAdapter(adapter); 								// 将适配器与GridView关联

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
