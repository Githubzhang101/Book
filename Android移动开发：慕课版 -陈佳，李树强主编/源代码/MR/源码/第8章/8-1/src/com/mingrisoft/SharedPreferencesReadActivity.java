package com.mingrisoft;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class SharedPreferencesReadActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // ���ø��෽��
        setContentView(R.layout.result);// ���ò����ļ�
        TextView usernameTV = (TextView) findViewById(R.id.username);
        TextView passwordTV = (TextView) findViewById(R.id.password);
        SharedPreferences sp = getSharedPreferences("mrsoft", MODE_PRIVATE);// ���˽�����͵�SharedPreferences
        String username = sp.getString("username", "mr");// ����û���
        String password = sp.getString("password", "001");// �������
        usernameTV.setText("�û�����" + username);// ��ʾ�û���
        passwordTV.setText("��    �룺" + password);// ��ʾ����
    }

}