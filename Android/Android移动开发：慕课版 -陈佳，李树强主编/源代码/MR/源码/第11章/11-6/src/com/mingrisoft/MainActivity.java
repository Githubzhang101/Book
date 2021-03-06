package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		WebView webview=(WebView)findViewById(R.id.webView1);//获取布局管理器中添加的WebView组件
		//创建一个字符串构建器，将要显示的HTML内容放置在该构建器中
		StringBuilder sb=new StringBuilder();
		sb.append("<div>《个人理财通》使用帮助：</div>");
		sb.append("<ul>");
		sb.append("<li>修改密码：选择“系统设置”模块可以修改登录密码，项目运行时，默认没有密码。</li>");
		sb.append("<li>支出管理：选择“新增支出”模块可以添加支出信息；选择“我的支出”模块可以查看、修改或删除支出信息。</li>");
		sb.append("<li>收入管理：选择“新增收入”模块可以添加收入信息；选择“我的收入”模块可以查看、修改或删除收入信息。</li>");
		sb.append("<li>便签管理：选择“收支便签”模块可以添加便签信息；选择“数据管理”模块中的“便签信息”按钮可以查看、修改或删除便签信息。</li>");
		sb.append("<li>退出系统：选择“退出”模块可以退出《个人理财通》项目。</li>");
		sb.append("</ul>");
		webview.loadDataWithBaseURL(null, sb.toString(),"text/html","utf-8",null);//加载数据

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
