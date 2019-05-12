package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button login=(Button)findViewById(R.id.login);		//通过ID获取布局文件中添加的按钮
        login.setOnClickListener(new OnClickListener() {	//为按钮添加单击事件监听器
        	@Override
        	public void onClick(View v) {
        			Toast toast=Toast.makeText(MainActivity.this, "您单击了普通按钮", Toast.LENGTH_SHORT);
        			toast.show();					//显示提示信息
        	}
        });

    }
    public void myClick(View view){
    	Toast toast=Toast.makeText(MainActivity.this, "您单击了图片按钮", Toast.LENGTH_SHORT);
	toast.show();							//显示提示信息
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
