package com.mingrisoft;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	final int CODE= 0x717;							//定义一个请求码常量
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        Button submit=(Button)findViewById(R.id.submit);	//获取提交按钮
        submit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String user=((EditText)findViewById(R.id.user)).getText().toString();	//获取输入的用户
				String pwd=((EditText)findViewById(R.id.pwd)).getText().toString();	//获取输入的密码
				String repwd=((EditText)findViewById(R.id.repwd)).getText().toString();	//获取输入的确认密码
				String email=((EditText)findViewById(R.id.email)).getText().toString();	//获取输入的E-mail地址
				if(!"".equals(user) && !"".equals(pwd) && !"".equals(email)){
					
					if(!pwd.equals(repwd)){	//判断两次输入的密码是否一致
						Toast.makeText(MainActivity.this, "两次输入的密码不一致，请重新输入！", Toast.LENGTH_LONG).show();
						((EditText)findViewById(R.id.pwd)).setText("");	//清空密码编辑框
						((EditText)findViewById(R.id.repwd)).setText("");	//清空确认密码编辑框
						((EditText)findViewById(R.id.pwd)).requestFocus();	//让密码编辑框获得焦点
					}else{	//将输入的信息保存到Bundle中，并启动一个新的Activity显示输入的用户注册信息
						Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
						Bundle bundle=new Bundle();	//创建并实例化一个Bundle对象
						bundle.putCharSequence("user", user);	//保存用户名
						bundle.putCharSequence("pwd", pwd);	//保存密码
						bundle.putCharSequence("email", email);	//保存E-mail地址
						intent.putExtras(bundle);	//将Bundle对象添加到Intent对象中
						startActivityForResult(intent, CODE);					//启动新的Activity
						
					}
				}else{
					Toast.makeText(MainActivity.this, "请将注册信息输入完整！", Toast.LENGTH_LONG).show();
				}
				
			}
		});
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==CODE && resultCode==CODE){
			((EditText)findViewById(R.id.pwd)).setText("");			//清空密码编辑框
			((EditText)findViewById(R.id.repwd)).setText("");			//清空确认密码编辑框
		}
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
