package com.mingrisoft;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView iv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		FrameLayout ll = (FrameLayout) findViewById(R.id.frameLayout1); // ��ȡ�����ļ�����ӵ�֡���ֹ�����
		ll.addView(new MyView(this)); // ���Զ�����ͼ��ӵ�֡���ֹ�������
		iv = (ImageView) findViewById(R.id.imageView1); // ��ȡ�����ļ�����ӵ�ImageView���

	}

	@Override
	protected void onDestroy() {
		BitmapDrawable b = (BitmapDrawable) iv.getDrawable(); // ��ȡImageView�����ʹ�õ�BitmapDrawabele��Դ
		if (b != null && !b.getBitmap().isRecycled()) {
			b.getBitmap().recycle(); // ������Դ
		}
		super.onDestroy();
	}

	public class MyView extends View {

		public MyView(Context context) {
			super(context);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			Paint paint = new Paint(); // ����һ������Ĭ�����õĻ���
			String path = "/sdcard/pictures/img01.png"; // ָ��ͼƬ�ļ���·��
			Bitmap bm = BitmapFactory.decodeFile(path); // ��ȡͼƬ�ļ���Ӧ��Bitmap����
			canvas.drawBitmap(bm, 10, 10, paint); // ����ȡ��Bitmap��������ڻ�����ָ��λ��
			Rect src = new Rect(72, 110, 130, 185); // ������ȡ������
			Rect dst = new Rect(360, 30, 440, 120); // ���û��Ƶ�����
			canvas.drawBitmap(bm, src, dst, paint); // ������ȡ����ͼ��
			super.onDraw(canvas);
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
