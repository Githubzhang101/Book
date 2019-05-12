package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class MainActivity extends Activity {
	private WebView webview;								//声明WebView组件的对象
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		webview = (WebView) findViewById(R.id.webView1); // 获取布局管理器中添加的WebView组件
		CheckBox check = (CheckBox) findViewById(R.id.checkBox1);//获取布局管理器中添加的复选框组件
		check.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					webview.getSettings().setJavaScriptEnabled(true);// 设置JavaScript可用
					webview.setWebChromeClient(new WebChromeClient());
					//指定要加载的网页
					webview.loadUrl("http://192.168.1.66:8080/bbs/allowJS.jsp");
				}else{
					//指定要加载的网页
					webview.loadUrl("http://192.168.1.66:8080/bbs/allowJS.jsp");
				}
			}
		});
		webview.loadUrl("http://192.168.1.66:8080/bbs/allowJS.jsp");		// 指定要加载的网页

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
