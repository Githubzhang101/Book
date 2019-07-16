package com.mingrisoft;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;


public class MainActivity extends Activity {
	int[] ico=new int[]{R.drawable.qq,R.drawable.ico1,R.drawable.ico2};	//保存头像资源的数组
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // 设置该Activity使用的布局
		Intent intent=getIntent();	//获取Intent对象
		Bundle bundle=intent.getExtras();		//获取传递的数据包
		int index=bundle.getInt("index");	//获取传递过来的头像编号
		ImageButton ib=(ImageButton)findViewById(R.id.qq);	//获取用于显示登录帐号头像的ImageButton组件
		ib.setImageResource(ico[index]);					//显示头像
		//tv.setText("当前登录："+user);	//显示当前登录用户
		
		ImageButton btn=(ImageButton)findViewById(R.id.m_exit);	//获取“退出登录”按钮
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();		//关闭当前Activity
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
