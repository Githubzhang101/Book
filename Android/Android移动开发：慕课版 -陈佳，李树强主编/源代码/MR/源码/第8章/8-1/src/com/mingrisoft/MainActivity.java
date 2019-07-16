package com.mingrisoft;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        final EditText usernameET = (EditText) findViewById(R.id.username);// 获得用户名控件
        final EditText passwordET = (EditText) findViewById(R.id.password);// 获得密码控件
        Button login = (Button) findViewById(R.id.login);// 获得按钮控件
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String username = usernameET.getText().toString();// 获得用户名
                String password = passwordET.getText().toString();// 获得密码
                SharedPreferences sp = getSharedPreferences("mrsoft", MODE_PRIVATE);// 获得私有类型的SharedPreferences
                Editor editor = sp.edit();// 获得Editor对象
                editor.putString("username", username);// 增加用户名
                editor.putString("password", password);// 增加密码
                editor.commit();// 确认提交
                Intent intent = new Intent();// 创建Intent对象
                intent.setClass(MainActivity.this, SharedPreferencesReadActivity.class);// 指定跳转到SharedPreferencesReadActivity
                startActivity(intent);// 实现跳转
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
