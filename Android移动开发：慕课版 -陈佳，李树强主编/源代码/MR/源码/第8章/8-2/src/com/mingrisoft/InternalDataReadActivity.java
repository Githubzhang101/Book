package com.mingrisoft;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class InternalDataReadActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // 调用父类方法
        setContentView(R.layout.result);// 使用布局文件
        FileInputStream fis = null;
        byte[] buffer = null;
        try {
            fis = openFileInput("login");// 获得文件输入流
            buffer = new byte[fis.available()];// 定义保存数据的数组
            fis.read(buffer);// 从输入流中读取数据
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();// 关闭文件输入流
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        TextView usernameTV = (TextView) findViewById(R.id.username);
        TextView passwordTV = (TextView) findViewById(R.id.password);
        String data = new String(buffer);// 获得数组中保存的数据
        String username = data.split(" ")[0];// 获得username
        String password = data.split(" ")[1];// 获得password
        usernameTV.setText("用户名：" + username);// 显示用户名
        passwordTV.setText("密    码：" + password);// 显示密码
    }
}
