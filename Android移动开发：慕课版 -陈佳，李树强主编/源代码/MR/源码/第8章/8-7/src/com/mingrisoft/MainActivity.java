package com.mingrisoft;


import java.io.UnsupportedEncodingException;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private final String TABLENAME="tb_inaccount"; 	//������Ϣ��

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		DBOpenHelper helper = new DBOpenHelper(this);// ��������ʼ��DBOpenHelper����
		final SQLiteDatabase db=helper.getWritableDatabase();// ��ʼ��SQLiteDatabase����
		//��������
		Button bt_insert=(Button)findViewById(R.id.bt_insert);
		bt_insert.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ContentValues values=new ContentValues();
//				values.put("money", 5000);
//				values.put("time", "2015-06-10");
//				try {
//					values.put("type","����");
//				values.put("handler",  new String("����".getBytes("gbk"),"utf-8"));
//				values.put("mark",  new String("5����".getBytes("utf-8")));
//				} catch (UnsupportedEncodingException e) {
//					// TODO �Զ����ɵ� catch ��
//					e.printStackTrace();
//				}
				values.put("money", 5000);
				values.put("time", "2015-06-10");
				values.put("type", "����");
				values.put("handler", "���տƼ�");
				values.put("mark", "5�·ݹ���");
				db.insert(TABLENAME,null , values);
			}
		});
		//��������
			Button bt_update=(Button)findViewById(R.id.bt_update);
			bt_update.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					ContentValues values=new ContentValues();
					values.put("handler", "����ʡ���տƼ����޹�˾");	//�޸ĸ���ֶ�
					db.update(TABLENAME, values,"handler='���տƼ�'",null);
				}
			});		
			//��ѯ����
			Button bt_query=(Button)findViewById(R.id.bt_query);
			bt_query.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Cursor cursor=db.query(TABLENAME,null, null, null, null, null, null, null);
					//������ѯ��������LogCat
					if(cursor.moveToFirst()){
						while(!cursor.isAfterLast()){
							Log.i("��ѯ���","ID:"+cursor.getInt(0)+" ��"+cursor.getFloat(1)+
									" ʱ�䣺"+cursor.getString(2)+" ���ͣ�"+cursor.getString(3)+
									" �����"+cursor.getString(4)+" ��ע��"+cursor.getString(5));
							cursor.moveToNext();
						}
					}
				}
			});
//ɾ������
Button bt_delete=(Button)findViewById(R.id.bt_delete);
bt_delete.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		db.delete(TABLENAME,"_id=?" , new String[]{"1"});
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
