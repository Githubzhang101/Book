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
	private float[] money=new float[]{600,1000,600,300,1500};	//������
	private int[] color=new int[]{Color.GREEN,Color.YELLOW,Color.RED,Color.MAGENTA,Color.BLUE};	//������ɫ
	private final int WIDTH = 30;	//���͵Ŀ��
	private final int OFFSET = 15;	//���
	private int x =70;	//���x
	private int y=329;	//�յ�y
	private int height=220;	//�߶�
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		FrameLayout ll=(FrameLayout)findViewById(R.id.frameLayout1);//��ȡ�����ļ�����ӵ�֡���ֹ�����
		ll.addView(new MyView(this));				//���Զ����MyView��ͼ��ӵ�֡���ֹ�������
		
	}
	public class MyView extends View{
		public MyView(Context context) {
			super(context);
		}
		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			canvas.drawColor(Color.WHITE);					//ָ�������ı���ɫΪ��ɫ
			Paint paint=new Paint();						//��������Ĭ�����õĻ���
			paint.setAntiAlias(true);						//ʹ�ÿ���ݹ���
			/***********����������**********************/
			paint.setStrokeWidth(1);						//���ñʴ��Ŀ��
			paint.setColor(Color.BLACK);		//���ñʴ�����ɫ
			canvas.drawLine(50, 330, 300, 330, paint);	//��
			canvas.drawLine(50, 100, 50, 330, paint);	//��
			/******************************************/
			/***********��������**********************/
			paint.setStyle(Style.FILL);					//���������ʽΪ���
			int left=0;	//ÿ�����͵����X����
			float max=maxMoney(money);
			for(int i=0;i<money.length;i++){
				paint.setColor(color[i]);		//���ñʴ�����ɫ
				left=x+i*(OFFSET+WIDTH);	//����ÿ���������X����
				canvas.drawRect(left, y-height/max*money[i], left+WIDTH, y, paint);	
			}
			/******************************************/
			/***********��������Ŀ̶�**********************/
			paint.setColor(Color.BLACK);		//���ñʴ�����ɫ
			int tempY=0;
			for(int i=0;i<11;i++){
				tempY=y-height+height/10*i+1;
				canvas.drawLine(47,tempY , 50, tempY, paint);	
			}
			/******************************************/

		}
	}
	//���������
	float maxMoney(float[] money){
		float max=money[0];	//����һ������Ԫ�ظ�ֵ������max
		for(int i=0;i<money.length-1;i++){
			if(max<money[i+1]){
				max=money[i+1];		//����max
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
