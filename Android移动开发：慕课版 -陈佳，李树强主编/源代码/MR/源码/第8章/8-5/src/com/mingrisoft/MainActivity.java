package com.mingrisoft;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		XmlResourceParser xrp=getResources().getXml(R.xml.customers);	//获取XML文档
		StringBuilder sb=new StringBuilder("");	//创建一个空的字符串构建器
		try {
			//如果没有到XML文档的结尾处
			while(xrp.getEventType()!=XmlResourceParser.END_DOCUMENT){
				if(xrp.getEventType()==XmlResourceParser.START_TAG){	//判断是否为开始标记
					String tagName=xrp.getName();	//获取标记名
					if(tagName.equals("customer")){	//如果标记名是customer
						sb.append("姓名："+xrp.getAttributeValue(0)+"   ");		//获取客户姓名
						sb.append("电话："+xrp.getAttributeValue(1)+"   ");	//获取联系电话
						sb.append("E-mail："+xrp.getAttributeValue(2));	//获取E-mail
						sb.append("\n");	//添加换行符
					}
				}
				xrp.next();	//下一个标记
			}
			TextView tv=(TextView)findViewById(R.id.show);	//获取显示文本框
			tv.setText(sb.toString());	//将获取到XML文件的内容显示到文本框中
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
