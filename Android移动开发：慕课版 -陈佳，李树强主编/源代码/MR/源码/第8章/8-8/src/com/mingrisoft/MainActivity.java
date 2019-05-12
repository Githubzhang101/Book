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
	private String[] columns = { Contacts._ID,							// 获得ID值
            Contacts.DISPLAY_NAME,// 获得姓名
            Phone.NUMBER,// 获得电话
            Phone.CONTACT_ID };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView tv = (TextView) findViewById(R.id.result);				// 获得布局文件中的文本框
        tv.setText(getQueryData());									// 为文本框设置显示文本

	}
    private String getQueryData() {
        StringBuilder sb = new StringBuilder();							// 用于保存字符串
        ContentResolver resolver = getContentResolver();				// 获得ContentResolver对象
        Cursor cursor = resolver.query(Contacts.CONTENT_URI, null, null, null, null);// 查询记录
        while (cursor.moveToNext()) {
            int idIndex = cursor.getColumnIndex(columns[0]);				// 获得ID值的索引
            int displayNameIndex = cursor.getColumnIndex(columns[1]);	// 获得姓名索引
            int id = cursor.getInt(idIndex);							// 获得id
            String displayName = cursor.getString(displayNameIndex);		// 获得名称
            Cursor phone = resolver.query(Phone.CONTENT_URI, null, columns[3] + "=" + id, null, null);
            while (phone.moveToNext()) {
                int phoneNumberIndex = phone.getColumnIndex(columns[2]);	// 获得电话索引
                String phoneNumber = phone.getString(phoneNumberIndex);	// 获得电话
                sb.append(displayName + ": " + phoneNumber + "\n");		// 保存数据
            }
        }
        cursor.close();// 关闭游标
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
