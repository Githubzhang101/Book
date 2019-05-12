package com.wgh.sudoku;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class SudokuActivity extends Activity implements OnClickListener {
	private static final String TAG="Sudoku";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        View continueButton=this.findViewById(R.id.button1);	//为继续按钮绑定单击事件
        continueButton.setOnClickListener(this);        
        View newButton=this.findViewById(R.id.button2);
        newButton.setOnClickListener(this);
        View aboutButton=this.findViewById(R.id.button3);
        aboutButton.setOnClickListener(this);
        View exitButton=this.findViewById(R.id.button4);		//为退出按钮添加单击事件监听
        exitButton.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i;
		switch (v.getId()){
			case R.id.button1:
				StartGame(GameActivity.DIFFICULTY_CONTINUE);
				break;
			case R.id.button2:
				openNewGameDialog();
				break;
			case R.id.button3:
				i=new Intent(this,About.class);				
				startActivity(i);
				break;
			case R.id.button4:
				finish();
				break;
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Music.stop(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		Music.play(this,R.raw.jasmine);
	}

	private void openNewGameDialog() {
		new AlertDialog.Builder(this)
		.setTitle(R.string.new_game_title)
		.setItems(R.array.difficulty,new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int i) {
				// TODO Auto-generated method stub
				StartGame(i);
			}
		})
		.show();
	}
	private void StartGame(int i) {
		// TODO Auto-generated method stub
		Log.d(TAG,"clicked on "+i);
	//	startActivity(new Intent(this,GraphicsActivity.class));
		Intent intent=new Intent(this,GameActivity.class);
		intent.putExtra(GameActivity.KEY_DIFFICULTY, i);
		startActivity(intent);
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater=getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		super.onOptionsItemSelected(item);
		switch (item.getItemId()){
		case R.id.settings:
			startActivity(new Intent(this,SettingsActivity.class));
			return true;
		}
		return false;
	}
}