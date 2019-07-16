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
        final EditText usernameET = (EditText) findViewById(R.id.username);// ����û����ؼ�
        final EditText passwordET = (EditText) findViewById(R.id.password);// �������ؼ�
        Button login = (Button) findViewById(R.id.login);// ��ð�ť�ؼ�
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String username = usernameET.getText().toString();// ����û���
                String password = passwordET.getText().toString();// �������
                FileOutputStream fos = null;
                try {
                    fos = openFileOutput("login", MODE_PRIVATE);// ����ļ������
                    fos.write((username + " " + password).getBytes());// �����û���������
                    fos.flush();// �������
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fos != null) {
                        try {
                            fos.close();// �ر��ļ������
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                Intent intent = new Intent();// ����Intent����
                intent.setClass(MainActivity.this, InternalDataReadActivity.class);// ָ����ת��InternalDataReadActivity
                startActivity(intent);// ʵ����ת
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
