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
	private final String TABLENAME="tb_inaccount"; 	//收入信息表

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		DBOpenHelper helper = new DBOpenHelper(this);// 创建并初始化DBOpenHelper对象
		final SQLiteDatabase db=helper.getWritableDatabase();// 初始化SQLiteDatabase对象
		//插入数据
		Button bt_insert=(Button)findViewById(R.id.bt_insert);
		bt_insert.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ContentValues values=new ContentValues();
//				values.put("money", 5000);
//				values.put("time", "2015-06-10");
//				try {
//					values.put("type","工资");
//				values.put("handler",  new String("工资".getBytes("gbk"),"utf-8"));
//				values.put("mark",  new String("5工资".getBytes("utf-8")));
//				} catch (UnsupportedEncodingException e) {
//					// TODO 自动生成的 catch 块
//					e.printStackTrace();
//				}
				values.put("money", 5000);
				values.put("time", "2015-06-10");
				values.put("type", "工资");
				values.put("handler", "明日科技");
				values.put("mark", "5月份工资");
				db.insert(TABLENAME,null , values);
			}
		});
		//更新数据
			Button bt_update=(Button)findViewById(R.id.bt_update);
			bt_update.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					ContentValues values=new ContentValues();
					values.put("handler", "吉林省明日科技有限公司");	//修改付款方字段
					db.update(TABLENAME, values,"handler='明日科技'",null);
				}
			});		
			//查询数据
			Button bt_query=(Button)findViewById(R.id.bt_query);
			bt_query.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Cursor cursor=db.query(TABLENAME,null, null, null, null, null, null, null);
					//遍历查询结果输出到LogCat
					if(cursor.moveToFirst()){
						while(!cursor.isAfterLast()){
							Log.i("查询结果","ID:"+cursor.getInt(0)+" 金额："+cursor.getFloat(1)+
									" 时间："+cursor.getString(2)+" 类型："+cursor.getString(3)+
									" 付款方："+cursor.getString(4)+" 备注："+cursor.getString(5));
							cursor.moveToNext();
						}
					}
				}
			});
//删除数据
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
