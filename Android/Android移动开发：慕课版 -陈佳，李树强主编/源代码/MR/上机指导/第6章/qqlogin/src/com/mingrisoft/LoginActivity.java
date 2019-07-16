package com.mingrisoft;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Demonstration of using fragments to implement different activity layouts.
 * This sample provides a different layout (and activity flow) when run in
 * landscape.
 */
public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login); // ���ø�Activityʹ�õĲ���
		Button button=(Button)findViewById(R.id.login);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String number=((EditText)findViewById(R.id.user)).getText().toString();
				String pwd=((EditText)findViewById(R.id.pwd)).getText().toString();
				boolean flag=false;	//���ڼ�¼��¼�Ƿ�ɹ��ı�Ǳ���
				String index="";		//����ͷ���ŵı���
				//ͨ���������ݵ���ʽ�ж�������ʺź������Ƿ���ȷ
				for(int i=0;i<Data.USER.length;i++){
					if(number.equals(Data.USER[i][0])){//�ж��ʺ��Ƿ���ȷ
						if(pwd.equals(Data.USER[i][1])){	//�ж������Ƿ���ȷ
							index=Data.USER[i][2];		//��ȡͷ����
							flag=true;		//����־��������Ϊtrue
							break;		//����forѭ��
						}
					}
				}
				if(flag){
					Intent intent=new Intent(LoginActivity.this,MainActivity.class);	//����Ҫ��ʾActivity��Ӧ��Intent����
					Bundle bundle=new Bundle();		//����һ��Bundle�Ķ���bundle
					bundle.putInt("index", Integer.parseInt(index));	//����ͷ����
					intent.putExtras(bundle);	//�����ݰ���ӵ�intent������
					startActivity(intent);		//����һ���µ�Activity
				}else{
					Toast toast=Toast.makeText(LoginActivity.this, "��������ʺŻ��������", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.BOTTOM, 0, 0);			//���ö��뷽ʽ 
					toast.show();		//��ʾ�Ի���
				}
				
			}
		});
	}
}
