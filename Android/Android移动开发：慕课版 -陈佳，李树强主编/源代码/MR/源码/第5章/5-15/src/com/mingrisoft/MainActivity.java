package com.mingrisoft;


import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


	// ����һ���̳�Activity���ڲ��࣬�������ֻ������У�ͨ��Activity��ʾ��ϸ����
	public static class DetailActivity extends Activity {

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			// �ж��Ƿ�Ϊ���������Ϊ�������������ǰActivity��׼��ʹ��Fragment��ʾ��ϸ����
			if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
				finish(); // ������ǰActivity
				return;
			}

			if (savedInstanceState == null) { //
				// �ڳ�ʼ��ʱ����һ����ʾ��ϸ���ݵ�Fragment
				DetailFragment details = new DetailFragment();// ʵ����DetailFragment�Ķ���
				details.setArguments(getIntent().getExtras()); // ����Ҫ���ݵĲ���
				getFragmentManager().beginTransaction()
						.add(android.R.id.content, details).commit(); // ���һ����ʾ��ϸ���ݵ�Fragment
			}
		}
	}

}