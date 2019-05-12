package com.mingrisoft;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
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
                FileOutputStream fos = null;
                try {
                    fos = openFileOutput("login", MODE_PRIVATE);// 获得文件输出流
                    fos.write((username + " " + password).getBytes());// 保存用户名和密码
                    fos.flush();// 清除缓存
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fos != null) {
                        try {
                            fos.close();// 关闭文件输出流
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                Intent intent = new Intent();// 创建Intent对象
                intent.setClass(MainActivity.this, InternalDataReadActivity.class);// 指定跳转到InternalDataReadActivity
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
