package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		final Spinner spinner = (Spinner) findViewById(R.id.spInType);
		/****************ͨ��ָ���������ķ�ʽΪѡ���б��ָ���б���********************/
//		����һ
//		String[] intype=new String[]{"����","��ְ","��Ʊ","����","�ֺ�","��Ϣ","����","����","���","���","Ӧ��","���ۿ�","������","����"};
//		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,intype);
//		������
//		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
//				this, R.array.intype,android.R.layout.simple_dropdown_item_1line);	//����һ��������
//		
//		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Ϊ�����������б������ʱ��ѡ����ʽ
//		spinner.setAdapter(adapter); // ����������ѡ���б�����
		
		/***************************************************************************/		
		// Ϊѡ���б�����OnItemSelectedListener�¼�����
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View arg1,
					int pos, long id) {
				String result = parent.getItemAtPosition(pos).toString(); // ��ȡѡ�����ֵ
				Log.i("Spinnerʾ��", result);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		Button button = (Button) findViewById(R.id.btnSubmit); // ��ȡ�ύ��ť
		// Ϊ�ύ��ť��ӵ����¼�����
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// ��ȡѡ���֤�����Ͳ�ͨ����ʾ����ʾ
				Toast.makeText(MainActivity.this,
						"��ѡ��������ǣ�" + spinner.getSelectedItem().toString(),
						Toast.LENGTH_SHORT).show(); // ��ʾ��ѡ�еĸ�ѡ��ť
			}
		});
		/***************************************************************************/

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
