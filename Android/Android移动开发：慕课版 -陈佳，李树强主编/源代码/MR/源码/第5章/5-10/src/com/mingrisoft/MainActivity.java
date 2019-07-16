package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final CheckBox like1=(CheckBox)findViewById(R.id.like1);		//��ȡ��1����ѡ��ť
        final CheckBox like2=(CheckBox)findViewById(R.id.like2);		//��ȡ��2����ѡ��ť
        final CheckBox like3=(CheckBox)findViewById(R.id.like3); 	//��ȡ��3����ѡ��ť
        like1.setOnCheckedChangeListener(checkBox_listener);		//Ϊlike1���״̬�ı������
        like2.setOnCheckedChangeListener(checkBox_listener);		//Ϊlike2���״̬�ı������
        like3.setOnCheckedChangeListener(checkBox_listener);		//Ϊlike3���״̬�ı������
        Button button=(Button)findViewById(R.id.button1);
      //Ϊ�ύ��ť��ӵ����¼�����
        button.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		String like="";				//����ѡ�е�ֵ
        		if(like1.isChecked())			//����һ����ѡ��ť��ѡ��
        			like+=like1.getText().toString()+" ";
        		if(like2.isChecked())			//����һ����ѡ��ť��ѡ��
        			like+=like2.getText().toString()+" ";
        		if(like3.isChecked())			//����һ����ѡ��ť��ѡ��
        			like+=like3.getText().toString()+" ";
        		//��ʾ��ѡ�еĸ�ѡ��ť
        		Toast.makeText(MainActivity.this, like, Toast.LENGTH_SHORT).show();	
        	}
        });


    }
    //����һ��״̬�ı��������
    private OnCheckedChangeListener checkBox_listener=new OnCheckedChangeListener() {
    	@Override
    	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
    		if(isChecked){		//�жϸ�ѡ��ť�Ƿ�ѡ��
    			Log.i("��ѡ��ť","ѡ����["+buttonView.getText().toString()+"]");
    		}
    	}
    };


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
