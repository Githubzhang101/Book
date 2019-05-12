package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RadioGroup sex = (RadioGroup) findViewById(R.id.radioGroup1);//��ȡ��ѡ��ť��
      //Ϊ��ѡ��ť������¼�����
      sex.setOnCheckedChangeListener(new OnCheckedChangeListener() {

      	@Override
      	public void onCheckedChanged(RadioGroup group, int checkedId) {
      		RadioButton r = (RadioButton) findViewById(checkedId);//��ȡ��ѡ��ĵ�ѡ��ť
      		Log.i("��ѡ��ť", "����ѡ���ǣ�" + r.getText());
      	}
      });
      Button button = (Button) findViewById(R.id.button1);			//��ȡ�ύ��ť
    //Ϊ�ύ��ť��ӵ����¼�����
    button.setOnClickListener(new OnClickListener() {
    	@Override
    	public void onClick(View v) {
    		//ͨ��forѭ��������ѡ��ť��
    		for (int i = 0; i < sex.getChildCount(); i++) {
    			RadioButton r = (RadioButton) sex.getChildAt(i);
    			if (r.isChecked()) {						//�жϵ�ѡ��ť�Ƿ�ѡ��
    				Log.i("��ѡ��ť", "�Ա�" + r.getText());
    				break;							//����forѭ��
    			}
    		}
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
