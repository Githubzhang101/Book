package com.mingrisoft;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Demonstration of using fragments to implement different activity layouts.
 * This sample provides a different layout (and activity flow) when run in
 * landscape.
 */
public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login); // 设置该Activity使用的布局
		Button button=(Button)findViewById(R.id.login);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String number=((EditText)findViewById(R.id.user)).getText().toString();
				String pwd=((EditText)findViewById(R.id.pwd)).getText().toString();
				boolean flag=false;	//用于记录登录是否成功的标记变量
				String index="";		//保存头像编号的变量
				//通过遍历数据的形式判断输入的帐号和密码是否正确
				for(int i=0;i<Data.USER.length;i++){
					if(number.equals(Data.USER[i][0])){//判断帐号是否正确
						if(pwd.equals(Data.USER[i][1])){	//判断密码是否正确
							index=Data.USER[i][2];		//获取头像编号
							flag=true;		//将标志变量设置为true
							break;		//跳出for循环
						}
					}
				}
				if(flag){
					Intent intent=new Intent(LoginActivity.this,MainActivity.class);	//创建要显示Activity对应的Intent对象
					Bundle bundle=new Bundle();		//创建一个Bundle的对象bundle
					bundle.putInt("index", Integer.parseInt(index));	//保存头像编号
					intent.putExtras(bundle);	//将数据包添加到intent对象中
					startActivity(intent);		//开启一个新的Activity
				}else{
					Toast toast=Toast.makeText(LoginActivity.this, "您输入的帐号或密码错误！", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.BOTTOM, 0, 0);			//设置对齐方式 
					toast.show();		//显示对话框
				}
				
			}
		});
	}
}
