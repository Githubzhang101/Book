package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		final Spinner spinner = (Spinner) findViewById(R.id.spInType);
		/****************通过指定适配器的方式为选择列表框指定列表项********************/
//		方法一
//		String[] intype=new String[]{"工资","兼职","股票","基金","分红","利息","奖金","补贴","礼金","租金","应收","销售款","报销款","其他"};
//		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,intype);
//		方法二
//		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
//				this, R.array.intype,android.R.layout.simple_dropdown_item_1line);	//创建一个适配器
//		
//		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // 为适配器设置列表框下拉时的选项样式
//		spinner.setAdapter(adapter); // 将适配器与选择列表框关联
		
		/***************************************************************************/		
		// 为选择列表框添加OnItemSelectedListener事件监听
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View arg1,
					int pos, long id) {
				String result = parent.getItemAtPosition(pos).toString(); // 获取选择项的值
				Log.i("Spinner示例", result);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		Button button = (Button) findViewById(R.id.btnSubmit); // 获取提交按钮
		// 为提交按钮添加单击事件监听
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 获取选择的证件类型并通过提示框显示
				Toast.makeText(MainActivity.this,
						"您选择的类型是：" + spinner.getSelectedItem().toString(),
						Toast.LENGTH_SHORT).show(); // 显示被选中的复选按钮
			}
		});
		/***************************************************************************/

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
