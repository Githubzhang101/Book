package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RadioGroup sex = (RadioGroup) findViewById(R.id.radioGroup1);//获取单选按钮组
      //为单选按钮组添加事件监听
      sex.setOnCheckedChangeListener(new OnCheckedChangeListener() {

      	@Override
      	public void onCheckedChanged(RadioGroup group, int checkedId) {
      		RadioButton r = (RadioButton) findViewById(checkedId);//获取被选择的单选按钮
      		Log.i("单选按钮", "您的选择是：" + r.getText());
      	}
      });
      Button button = (Button) findViewById(R.id.button1);			//获取提交按钮
    //为提交按钮添加单击事件监听
    button.setOnClickListener(new OnClickListener() {
    	@Override
    	public void onClick(View v) {
    		//通过for循环遍历单选按钮组
    		for (int i = 0; i < sex.getChildCount(); i++) {
    			RadioButton r = (RadioButton) sex.getChildAt(i);
    			if (r.isChecked()) {						//判断单选按钮是否被选中
    				Log.i("单选按钮", "性别：" + r.getText());
    				break;							//跳出for循环
    			}
    		}
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
