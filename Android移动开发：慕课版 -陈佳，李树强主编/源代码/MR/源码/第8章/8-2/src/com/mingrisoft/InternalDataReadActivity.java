package com.mingrisoft;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class InternalDataReadActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // ���ø��෽��
        setContentView(R.layout.result);// ʹ�ò����ļ�
        FileInputStream fis = null;
        byte[] buffer = null;
        try {
            fis = openFileInput("login");// ����ļ�������
            buffer = new byte[fis.available()];// ���屣�����ݵ�����
            fis.read(buffer);// ���������ж�ȡ����
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();// �ر��ļ�������
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        TextView usernameTV = (TextView) findViewById(R.id.username);
        TextView passwordTV = (TextView) findViewById(R.id.password);
        String data = new String(buffer);// ��������б��������
        String username = data.split(" ")[0];// ���username
        String password = data.split(" ")[1];// ���password
        usernameTV.setText("�û�����" + username);// ��ʾ�û���
        passwordTV.setText("��    �룺" + password);// ��ʾ����
    }
}
