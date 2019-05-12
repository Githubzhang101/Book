package com.mingrisoft;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends Activity {
	private float[] money=new float[]{600,1000,600,300,1500};	//各项金额
	private int[] color=new int[]{Color.GREEN,Color.YELLOW,Color.RED,Color.MAGENTA,Color.BLUE};	//各项颜色
	private final int WIDTH = 30;	//柱型的宽度
	private final int OFFSET = 15;	//间距
	private int x =70;	//起点x
	private int y=329;	//终点y
	private int height=220;	//高度
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		FrameLayout ll=(FrameLayout)findViewById(R.id.frameLayout1);//获取布局文件中添加的帧布局管理器
		ll.addView(new MyView(this));				//将自定义的MyView视图添加到帧布局管理器中
		
	}
	public class MyView extends View{
		public MyView(Context context) {
			super(context);
		}
		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			canvas.drawColor(Color.WHITE);					//指定画布的背景色为白色
			Paint paint=new Paint();						//创建采用默认设置的画笔
			paint.setAntiAlias(true);						//使用抗锯齿功能
			/***********绘制坐标轴**********************/
			paint.setStrokeWidth(1);						//设置笔触的宽度
			paint.setColor(Color.BLACK);		//设置笔触的颜色
			canvas.drawLine(50, 330, 300, 330, paint);	//横
			canvas.drawLine(50, 100, 50, 330, paint);	//竖
			/******************************************/
			/***********绘制柱型**********************/
			paint.setStyle(Style.FILL);					//设置填充样式为填充
			int left=0;	//每个柱型的起点X坐标
			float max=maxMoney(money);
			for(int i=0;i<money.length;i++){
				paint.setColor(color[i]);		//设置笔触的颜色
				left=x+i*(OFFSET+WIDTH);	//计算每个柱型起点X坐标
				canvas.drawRect(left, y-height/max*money[i], left+WIDTH, y, paint);	
			}
			/******************************************/
			/***********绘制纵轴的刻度**********************/
			paint.setColor(Color.BLACK);		//设置笔触的颜色
			int tempY=0;
			for(int i=0;i<11;i++){
				tempY=y-height+height/10*i+1;
				canvas.drawLine(47,tempY , 50, tempY, paint);	
			}
			/******************************************/

		}
	}
	//计算最大金额
	float maxMoney(float[] money){
		float max=money[0];	//将第一个数组元素赋值给变量max
		for(int i=0;i<money.length-1;i++){
			if(max<money[i+1]){
				max=money[i+1];		//更新max
			}
		}
		return max;
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
