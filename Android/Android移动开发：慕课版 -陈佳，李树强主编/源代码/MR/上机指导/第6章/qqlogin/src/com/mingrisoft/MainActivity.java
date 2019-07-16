package com.mingrisoft;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;


public class MainActivity extends Activity {
	int[] ico=new int[]{R.drawable.qq,R.drawable.ico1,R.drawable.ico2};	//����ͷ����Դ������
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // ���ø�Activityʹ�õĲ���
		Intent intent=getIntent();	//��ȡIntent����
		Bundle bundle=intent.getExtras();		//��ȡ���ݵ����ݰ�
		int index=bundle.getInt("index");	//��ȡ���ݹ�����ͷ����
		ImageButton ib=(ImageButton)findViewById(R.id.qq);	//��ȡ������ʾ��¼�ʺ�ͷ���ImageButton���
		ib.setImageResource(ico[index]);					//��ʾͷ��
		//tv.setText("��ǰ��¼��"+user);	//��ʾ��ǰ��¼�û�
		
		ImageButton btn=(ImageButton)findViewById(R.id.m_exit);	//��ȡ���˳���¼����ť
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();		//�رյ�ǰActivity
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
