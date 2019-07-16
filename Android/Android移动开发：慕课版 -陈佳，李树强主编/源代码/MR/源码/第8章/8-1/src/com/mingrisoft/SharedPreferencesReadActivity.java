package com.mingrisoft;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class SharedPreferencesReadActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // 调用父类方法
        setContentView(R.layout.result);// 设置布局文件
        TextView usernameTV = (TextView) findViewById(R.id.username);
        TextView passwordTV = (TextView) findViewById(R.id.password);
        SharedPreferences sp = getSharedPreferences("mrsoft", MODE_PRIVATE);// 获得私有类型的SharedPreferences
        String username = sp.getString("username", "mr");// 获得用户名
        String password = sp.getString("password", "001");// 获得密码
        usernameTV.setText("用户名：" + username);// 显示用户名
        passwordTV.setText("密    码：" + password);// 显示密码
    }

}