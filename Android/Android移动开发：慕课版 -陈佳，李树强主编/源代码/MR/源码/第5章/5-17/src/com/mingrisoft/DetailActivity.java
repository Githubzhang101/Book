package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;

public class DetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO �Զ����ɵķ������
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		if(NavUtils.getParentActivityName(DetailActivity.this)!= null){
			getActionBar().setDisplayHomeAsUpEnabled(true);	//��ʾ����ļ�ͷͼ��
		}
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == android.R.id.home) {	//�ж��Ƿ񵥻����ļ�ͷͼ��
			if(NavUtils.getParentActivityName(DetailActivity.this)!= null){
				NavUtils.navigateUpFromSameTask(DetailActivity.this);	//��������Activity
			}
			return true;
		}else{
			return super.onOptionsItemSelected(item);
		}
	}
}
