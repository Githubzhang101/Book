package com.mingrisoft;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {
	private String[] columns = { Contacts._ID,							// ���IDֵ
            Contacts.DISPLAY_NAME,// �������
            Phone.NUMBER,// ��õ绰
            Phone.CONTACT_ID };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView tv = (TextView) findViewById(R.id.result);				// ��ò����ļ��е��ı���
        tv.setText(getQueryData());									// Ϊ�ı���������ʾ�ı�

	}
    private String getQueryData() {
        StringBuilder sb = new StringBuilder();							// ���ڱ����ַ���
        ContentResolver resolver = getContentResolver();				// ���ContentResolver����
        Cursor cursor = resolver.query(Contacts.CONTENT_URI, null, null, null, null);// ��ѯ��¼
        while (cursor.moveToNext()) {
            int idIndex = cursor.getColumnIndex(columns[0]);				// ���IDֵ������
            int displayNameIndex = cursor.getColumnIndex(columns[1]);	// �����������
            int id = cursor.getInt(idIndex);							// ���id
            String displayName = cursor.getString(displayNameIndex);		// �������
            Cursor phone = resolver.query(Phone.CONTENT_URI, null, columns[3] + "=" + id, null, null);
            while (phone.moveToNext()) {
                int phoneNumberIndex = phone.getColumnIndex(columns[2]);	// ��õ绰����
                String phoneNumber = phone.getString(phoneNumberIndex);	// ��õ绰
                sb.append(displayName + ": " + phoneNumber + "\n");		// ��������
            }
        }
        cursor.close();// �ر��α�
        return sb.toString();
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
